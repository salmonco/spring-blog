<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
	<div class="body-wrapper bg-white-gray">
		<div class="body-content container">
			<div class="row justify-content-center h-100 align-items-center">
				<div class="text-center">
		           	<c:set var="len" value="${fn:length(profileName)}"></c:set>
		           	<c:set var="filetype" value="${fn:toUpperCase(fn:substring(profileName, len-4, len))}"></c:set>
		           	<div class="large-img-container d-inline-block">
		           	<c:choose>
		           		<c:when test="${(filetype eq '.JPG') or (filetype eq 'JPEG') or (filetype eq '.GIF') or (filetype eq '.PNG')}">
		           			<img class="profile-img" alt="" src="<c:url value='resources/img/${profileName}'/>">
		           		</c:when>
		           		<c:otherwise>
		           			<img class="profile-img" alt="" src="resources/img/user.png">
		           		</c:otherwise>
		           	</c:choose>
		           	</div>
		           	
		           	<c:if test="${sessionScope.userId == userId}">
		           	<form method="post" action="${pageContext.request.contextPath}/profileUpload.do" enctype="multipart/form-data">
		           		<input type="hidden" name="userId" value="${userId}">
		           		<input type="file" name="profile" maxlength="200">
		           		<button type="submit" class="smallBtn p-2 hover-bg-tomato">등록</button>
		           	</form>
		           	</c:if>
		           	<p class="font-weight-bold font30 font-dark-gray text-center mt-2">${userId}</p>
		           	<c:if test="${sessionScope.userId != userId}">
		           	<a href="${pageContext.request.contextPath}/chat.do" id="chatBtn" class="mt-2">채팅</a>
		           	<button id="reportBtn" class="mt-2">신고</button>
		           	</c:if>
	           	</div>
	           	
           	</div>
		</div>
	<%@include file ="common/footer.jsp" %>
	</div>

<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:mm:ss" var="today" />

<script>
	$('#reportBtn').click(function(e){
		e.preventDefault();
		$('#reportModal').modal("show");
	});
</script>

	<!-- 사용자 신고 Modal -->
	<div class="modal fade" id="reportModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">신고하기</h5>
					<button class="close" type="button" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">X</span>
					</button>
				</div>
				<form method="post" action="${pageContext.request.contextPath}/report.do">
					<div class="modal-body">
						<input type="hidden" name="reportDate" value="<c:out value="${today}"/>">
						<input type="hidden" name="reportTarget" value="${userId}">
						<p class="mb-2"><input type="text" name="reportTitle" placeholder="신고 제목" onfocus="this.placeholder=''" onblur="this.placeholder='신고 제목'" maxlength="20" required></p>
						<p><textarea name="reportContent" style="width: 466px; height: 150px; word-wrap: break-word;" placeholder="신고 사유" onfocus="this.placeholder=''" onblur="this.placeholder='신고 사유'" maxlength="200" required></textarea></p>
					</div>
					<div class="modal-footer">
						<button type="submit" class="btn hover-tomato" id="modalY">제출</button>
						<button class="btn hover-tomato" type="button" data-dismiss="modal">취소</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>