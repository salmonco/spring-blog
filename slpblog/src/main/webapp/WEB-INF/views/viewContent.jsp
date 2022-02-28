<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
<%@include file ="common/header.jsp" %>
	<div class="body-wrapper">
        <div class="body-content container">
	        <div class="border p-5">
	            <div class="row align-items-center mb-4">
	                <div class="col-12">
	                    <h3 class="text-wrap"><c:out value="${content.boardTitle}"/></h3>
	                    <p class="py-2">
	                    	<a href="${pageContext.request.contextPath}/profile.do?userId=${content.userId}">
				            	<span id="contentProfile" class="small-img-container align-middle mr-2"></span>
	                    	</a>
	                    	<span class="font-weight-bold">${content.userId}</span>
	                    </p>
	                    <p class="text-secondary">
		                    <span><fmt:formatDate value="${content.boardDate}" pattern="yyyy.MM.dd. HH:ss"/></span>
		                    <span class="viewNum">&nbsp;조회수: ${content.boardHit}</span>
	                    </p>
	                </div>
	                <c:if test="${sessionScope.userId == content.userId}">
		                <div class="col-12 text-right mt-4">
		                    <a href="${pageContext.request.contextPath}/updateContent.do?boardNo=${content.boardNo}" class="smallBtn p-2 mr-2">수정</a>
		                    <a href="${pageContext.request.contextPath}/deleteProcess.do?boardNo=${content.boardNo}" class="smallBtn p-2">삭제</a>
		                </div>
	                </c:if>
	            </div>
	            <hr>
	            <div class="nanum-gothic" style="white-space:pre-wrap;">${content.boardContent}</div>
	            <p class="text-right">
		            <c:choose>
						<c:when test="${like eq 1}">
							<img class="like-img" style="width: 20px; height: 20px; cursor: pointer;" alt="" src="resources/img/heart.png">
						</c:when>
						<c:otherwise>
							<img class="like-img" style="width: 20px; height: 20px; cursor: pointer;" alt="" src="resources/img/emptyHeart.png">
						</c:otherwise>
					</c:choose>
		            <span class="likeCnt">${likeCnt}</span>
	            </p>
	            <hr>
	            <h6 class="text-secondary font13">첨부파일 &#10072;<br><br>
	            	<a href="${pageContext.request.contextPath}/fileDownload.do?fileName=${content.boardFile1}">${content.boardFile1}</a><br>
	            	<a href="${pageContext.request.contextPath}/fileDownload.do?fileName=${content.boardFile2}">${content.boardFile2}</a>
				</h6>
				<hr>
				<div class="replyContainer">
		        	<c:if test="${not empty requestScope.reply}">
						<div id="commentBox">
							<h2 class="mb-3">Comment</h2>
							<c:forEach items="${reply}" var="reply">
								<div class="replyList">
									<a href="${pageContext.request.contextPath}/profile.do?userId=${reply.replyWriter}">
						            	<span class="small-img-container align-middle mr-2">
						            		<c:set var="len" value="${fn:length(reply.profileName)}"></c:set>
											<c:set var="filetype" value="${fn:toUpperCase(fn:substring(reply.profileName, len-4, len))}"></c:set>
											<c:choose>
												<c:when test="${(filetype eq '.JPG') or (filetype eq 'JPEG') or (filetype eq '.GIF') or (filetype eq '.PNG')}">
													<img class="profile-img" alt="" src="<c:url value='resources/img/${reply.profileName}'/>">
												</c:when>
												<c:otherwise>
													<img class="profile-img" alt="" src="<c:url value='resources/img/user.png'/>">
												</c:otherwise>
											</c:choose>
						            	</span>
					                </a>
									<span class="font-weight-bold align-middle">${reply.replyWriter}</span>
									<p class="pl-5 py-1">${reply.replyContent}</p>
									<span class="text-secondary mr-2"><fmt:formatDate pattern="YYYY.MM.dd. HH:mm" value="${reply.replyDate}"/></span>
									<c:if test="${sessionScope.userId == reply.replyWriter}">
										<input type="button" value="수정" class="hover-tomato mr-1" onclick="location.href='${pageContext.request.contextPath}/updateReply.do?boardNo=${content.boardNo}&&replyNo=${reply.replyNo}'">
										<input type="button" value="삭제" class="hover-tomato" onclick="location.href='${pageContext.request.contextPath}/deleteReply.do?boardNo=${content.boardNo}&&replyNo=${reply.replyNo}'">
									</c:if>
								</div>
								<hr>
							</c:forEach>
						</div>
					</c:if>
					<form action="${pageContext.request.contextPath}/writeReply.do?boardNo=${content.boardNo}" method="post">
						<div class="mb-3">
							<input class="reply-form" type="text" name="replyContent" placeholder="댓글을 남겨보세요." onfocus="this.placeholder=''" onblur="this.placeholder='댓글을 남겨보세요.'" maxlength="100">
							<button type="submit" class="float-right smallBtn p-2 hover-bg-tomato mt-2">등록</button>
						</div>
					</form>
		        </div>
	        </div>
	        <p class="my-4"><a href="${pageContext.request.contextPath}/index.do">목록으로 돌아가기</a></p>
        </div>
        <%@include file ="common/footer.jsp" %>
    </div>
    
<script>
	$(document).ready(function() {

		$.ajax({
			url : "${pageContext.request.contextPath}/getProfileName.do",
			type : "post",
			data : {userId : "${content.userId}"},
			success : function(result){
				console.log(result);
				$("#contentProfile").html(result);
			},
			error : function(error) {
		        alert("Error!");
		    },
		    complete : function() {
		    }

		});
		
		$(".like-img").click(function() {
			$.ajax({
				url : "${pageContext.request.contextPath}/boardLike.do",
				type : "post",
				data : {boardNo : "${content.boardNo}", userId : "${sessionScope.userId}"},
				dataType: "json",
				success : function(data) {
					if(data.result == 1) {
						$(".like-img").attr("src", "resources/img/heart.png");
					}
					if(data.result == 0) {
						$(".like-img").attr("src", "resources/img/emptyHeart.png");
					}
					$(".likeCnt").html(data.likeCnt);
				},
				error : function(error) {
					alert("Error!");
			    },
			    complete : function() {
			    }

			});
		});

	});
</script>
</body>
</html>