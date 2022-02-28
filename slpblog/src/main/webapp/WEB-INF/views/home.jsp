<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="UTF-8">
</head>
<body>
<%@include file ="common/header.jsp" %>
	<div class="body-wrapper bg-white-gray">
		<div class="body-content container">
			<div class="row justify-content-center">
				<form action="${pageContext.request.contextPath}/search.do" method="post">
		            <input type="text" class="text-form" name="skey" placeholder="Search..." onfocus="this.placeholder=''" onblur="this.placeholder='Search...'">
		            <button type="submit" class="large-searchBtn p-2">
		                <i class="fas fa-search"></i>
		            </button>
		        </form>
	        </div>
		</div>
	<%@include file ="common/footer.jsp" %>
	</div>
</body>
</html>