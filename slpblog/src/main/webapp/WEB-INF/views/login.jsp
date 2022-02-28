<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	
	<!-- javascript files -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="resources/js/bootstrap.min.js"></script>
	<script src="resources/js/main.js"></script>
</head>
<body>
	<div class="container">
		<div class="row justify-content-center h-100 align-items-center">
		    <div>
		    	<div class="row mb-4">
			    	<span class="col-6 font30">Login</span>
			    	<div class="header-logo col-6 text-right">
			        	<a href="${pageContext.request.contextPath}/home.do">
			            	<i class="fas fa-meteor"></i>
			            </a>
			        </div>
		        </div>
		    	
		        <form method="post" action='${pageContext.request.contextPath}/loginProcess.do'>
		            <p><input class="text-form mb20" name="userId" type="text" placeholder="ID" onfocus="this.placeholder=''" onblur="this.placeholder='ID'" required></p>
		            <p><input class="text-form mb20" name="password" type="password" placeholder="Password" onfocus="this.placeholder=''" onblur="this.placeholder='Password'" required></p>
		            <button id="loginBtn" type="submit">Login</button>
		        </form>
		        <span>Don't have an account?</span>
		        <span class="circle"></span>
		        <span class="circle"></span>
		        <span class="circle"></span>
		        <button class="hover-tomato float-right" onclick="location.href='${pageContext.request.contextPath}/join.do'">Join</button>
		    </div>
	    </div>
	</div>
    
</body>
</html>