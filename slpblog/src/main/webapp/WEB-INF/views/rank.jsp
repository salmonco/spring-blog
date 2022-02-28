<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@include file ="common/header.jsp" %>
	<div class="body-wrapper">
		<div class="body-content container">
        	<c:if test="${not empty requestScope.likeRank}">
        		<p class="text-center nanum-gothic mb-5">좋아요를 많이 받은 게시글</p>
        		<div class="shadow mt-3 py-4">
        		<ul>
		        	<c:forEach items="${requestScope.likeRank}" var="likeRank">
		        		<li class="row justify-content-center">
		        			<span class="col-2 font30">${likeRank.ranking}</span>
		        			<a class="col-4 too-long-text" href="${pageContext.request.contextPath}/viewContent.do?boardNo=${likeRank.boardNo}">${likeRank.boardTitle}</a>
		        			<a class="col-3" href="${pageContext.request.contextPath}/profile.do?userId=${likeRank.userId}">
		        				<span class="small-img-container mr-1 align-middle">
				            		<c:set var="len" value="${fn:length(likeRank.profileName)}"></c:set>
									<c:set var="filetype" value="${fn:toUpperCase(fn:substring(likeRank.profileName, len-4, len))}"></c:set>
									<c:choose>
										<c:when test="${(filetype eq '.JPG') or (filetype eq 'JPEG') or (filetype eq '.GIF') or (filetype eq '.PNG')}">
											<img class="profile-img" alt="" src="<c:url value='resources/img/${likeRank.profileName}'/>">
										</c:when>
										<c:otherwise>
											<img class="profile-img" alt="" src="<c:url value='resources/img/user.png'/>">
										</c:otherwise>
									</c:choose>
				            	</span>
		        				<span class="">${likeRank.userId}</span>
		        			</a>
		        			<span class="col-3">좋아요 수: ${likeRank.boardLike}</span>
		        		</li>
		        		<hr>
		        	</c:forEach>
	        	</ul>
	        	</div>
        	</c:if>
		</div>
		<%@include file ="common/footer.jsp" %>
	</div>
</body>
</html>