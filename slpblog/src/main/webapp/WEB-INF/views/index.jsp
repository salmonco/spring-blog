<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
            <div class="row align-items-center mb-4">
                <span class="col-md-6 font30">Board</span>
                <div class="col-md-6 text-right">
                	<form action="${pageContext.request.contextPath}/search.do" method="post">
                		<button type="submit" class="search-icon"></button>
                		<input type="text" class="search-form" name="skey" id="skey" placeholder="Search..." onfocus="this.placeholder=''" onblur="this.placeholder='Search...'">
                	</form>
		        </div>
            </div>
            
            <c:if test="${not empty requestScope.list}">
	            <table class="table mobile-none">
	                <thead>
	                    <tr>
	                        <th>No</th>
	                        <th>제목</th>
	                        <th>작성자</th>
	                        <th>작성시간</th>
	                        <th>조회수</th>
	                    </tr>
	                </thead>
	                <c:forEach items="${requestScope.list}" var="list">
		                <tbody>
		                    <tr>
		                        <td>${list.boardNo}</td>
		                        <td class="too-long-text"><a href="${pageContext.request.contextPath}/viewContent.do?boardNo=${list.boardNo}"><c:out value="${list.boardTitle}"/></a></td>
		                        <td>${list.userId}</td>
		                        <td><fmt:formatDate value="${list.boardDate}" pattern="yyyy.MM.dd. HH:ss"/></td>
		                        <td>${list.boardHit}</td>
		                    </tr>
		                </tbody>
	                </c:forEach>
	            </table>
	            
	            <div class="pc-none">
	            <c:forEach items="${requestScope.list}" var="list">
		            <div class="row p-2 mb-3 border-bottom">
		            	<div class="col-5">
		            		<p class="row align-items-center">
				            	<a href="${pageContext.request.contextPath}/profile.do?userId=${list.userId}">
					            	<span class="indexProfile small-img-container mx-2">
					            		<c:set var="len" value="${fn:length(list.profileName)}"></c:set>
										<c:set var="filetype" value="${fn:toUpperCase(fn:substring(list.profileName, len-4, len))}"></c:set>
										
										<c:choose>
											<c:when test="${(filetype eq '.JPG') or (filetype eq 'JPEG') or (filetype eq '.GIF') or (filetype eq '.PNG')}">
												<img class="profile-img" alt="" src="<c:url value='resources/img/${list.profileName}'/>">
											</c:when>
											<c:otherwise>
												<img class="profile-img" alt="" src="<c:url value='resources/img/user.png'/>">
											</c:otherwise>
										</c:choose>
					            	</span>
				                </a>
				                <span class="font-weight-bold">${list.userId}</span>
			                </p>
			            	<p>
				            	<span><fmt:formatDate value="${list.boardDate}" pattern="yyyy.MM.dd. HH:ss"/></span>
				            	<span>&#10072; ${list.boardHit}</span>
			            	</p>
		            	</div>
		            	<div class="col-7 too-long-text">
		            		<a href="${pageContext.request.contextPath}/viewContent.do?boardNo=${list.boardNo}"><c:out value="${list.boardTitle}"/></a>
		            		<span class="float-right text-secondary">[${list.replyCount}]</span>
		            	</div>
	            	</div>
	            </c:forEach>
	            </div>
	            
	            <p class="text-right">
                	<a class="p-3" href="${pageContext.request.contextPath}/write.do"><i class="fas fa-pen"></i></a>
                </p>
	            <%-- startPage:<c:out value="${paging.startPage}"/>
	            endPage:<c:out value="${paging.endPage}"/>
	            lastPage:<c:out value="${paging.lastPage}"/>
	            nowPage:<c:out value="${paging.nowPage}"/> --%>
            	<div class="row justify-content-center">
            		<ul class="pagination">
			            <c:if test="${paging.startPage != 1}">
							<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/index.do?nowPage=${paging.startPage - 1}&cntPerPage=${paging.cntPerPage}">◀ </a></li>
						</c:if>
						
						<c:forEach begin="${paging.startPage}" end="${paging.endPage}" var="pageNum">
							<c:choose>
								<c:when test="${pageNum == paging.nowPage }">
									<li class="page-item"><a class="page-link">${pageNum}</a></li>
								</c:when>
								
								<c:when test="${pageNum != paging.nowPage }">
									<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/index.do?nowPage=${pageNum}&cntPerPage=${paging.cntPerPage}">${pageNum}</a></li>
								</c:when>
							</c:choose>
						</c:forEach>
						
						<c:if test="${paging.endPage != paging.lastPage}">
							<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/index.do?nowPage=${paging.endPage + 1}&cntPerPage=${paging.cntPerPage}"> ▶</a></li>
						</c:if>
					</ul>
				</div>
			</c:if>
	     </div>
	     <%@include file ="common/footer.jsp" %>
    </div>

<!-- <script>
console.log("al2="+$("#al2"));
if($('.nav-link').hasClass('active')) {
	$('.nav-link').removeClass('active');
}
console.log("nav-link hasClass add active");
$("#al2").addClass('active'); 
</script> -->

</body>
</html>
