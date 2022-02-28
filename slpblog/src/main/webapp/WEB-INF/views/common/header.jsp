<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%

    %>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>board site</title>
	<!-- css files -->
	<link rel="stylesheet" href="resources/css/bootstrap.min.css" type="text/css">
	<link rel="stylesheet" href="resources/css/style.css" type="text/css">
	<!-- <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"> -->
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.3/css/all.css" integrity="sha384-SZXxX4whJ79/gErwcOYf+zWLeJdY/qpuqC4cAa9rOGUstPomtqpuNWT9wdPEn2fk" crossorigin="anonymous">
	<link href="https://fonts.googleapis.com/css2?family=Source+Sans+Pro:wght@200;400&display=swap" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap" rel="stylesheet">
	
	<!-- javascript files -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="resources/js/bootstrap.min.js"></script>
	<script src="resources/ckeditor/ckeditor.js"></script>
	<script src="resources/js/main.js"></script>
	
	
	<script>
	$(function(){ // 실행할 기능을 정의해주세요. 
	});

	</script>

</head>
<body>
<header>
    <nav class="navbar navbar-expand-md font-dark-gray bg-white-gray">    		
   		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
		    <span class="navbar-toggler-icon bg-black-blue"></span>
		</button>
		
		<div class="header-logo">
        	<a href="${pageContext.request.contextPath}/home.do">
            	<i class="fas fa-meteor"></i>
            </a>
        </div>
        
		<div class="collapse navbar-collapse" id="navbarNav">
	        <ul class="navbar-nav mr-auto list-border">
	            <li class="nav-item"><a id="al1" class="nav-link" href="${pageContext.request.contextPath}/home.do">Home<span class="sr-only">(current)</span></a></li>
	            <li class="nav-item"><a id="al2" class="nav-link" href="${pageContext.request.contextPath}/index.do">Board</a></li>
	            <li class="nav-item"><a id="al3" class="nav-link" href="${pageContext.request.contextPath}/rank.do">Ranking</a></li>
	            <li class="nav-item"><a id="al4" class="nav-link" href="#">FAQ</a></li>
	            <li class="nav-item"><a id="al5" class="nav-link" href="#">Contact</a></li>
	        </ul>

			<ul class="navbar-nav list-border">
	            <c:if test="${sessionScope.userId != null}">
	            	<%-- <input type="hidden" id="userId" value="${sessionScope.userId}"> --%>
	            	<li class="nav-item mobile-none">
		            	<div class="dropdown">
		            		<span class="headerProfile small-img-container align-middle mr-2"></span>
		            		<button class="btn btn-primary dropdown-toggle align-middle" data-toggle="dropdown">my</button>
		            		<div class="dropdown-menu">
			            		<a class="dropdown-item" href="${pageContext.request.contextPath}/profile.do?userId=${sessionScope.userId}">${sessionScope.userId}</a>
			            		<div class="dropdown-divider"></div>
		            			<a class="dropdown-item" href="${pageContext.request.contextPath}/profile.do?userId=${sessionScope.userId}">profile</a>
		            			<a class="dropdown-item" href="${pageContext.request.contextPath}/logout.do">Logout</a>
			            	</div>
	            		</div>
	                </li>
	                
	            	<li class="nav-item pc-none">
	            		<a class="nav-link" href="${pageContext.request.contextPath}/profile.do?userId=${sessionScope.userId}">
			            	<span class="headerProfile small-img-container align-middle mr-2"></span>
			                <span class="align-middle">${sessionScope.userId}</span>
		                </a>
	                </li>
	                <li class="nav-item pc-none">
	                	<a class="nav-link" href="${pageContext.request.contextPath}/logout.do">Logout</a>
	                </li>
	            </c:if>
	            <c:if test="${sessionScope.userId == null}">
	                <li class="nav-item">
	                	<a class="nav-link" href="${pageContext.request.contextPath}/login.do">Login</a>
	                </li>
	                <li class="nav-item">
	                	<a class="nav-link" href="${pageContext.request.contextPath}/join.do">Join</a>
	                </li>
	            </c:if>
	        </ul>
		</div>
    </nav>
</header>

<script>
	$(document).ready(function() {

		$.ajax({
			url : "${pageContext.request.contextPath}/getProfileName.do",
			type : "post",
			/* data : {userId : $("#userId").val()}, */
			data : {userId : "${sessionScope.userId}"},
			success : function(result){
				console.log(result);
				$(".headerProfile").html(result);
			},
			error : function(error) {
		        alert("Error!");
		    },
		    complete : function() {
		    }

		});
	});
</script>

</body>