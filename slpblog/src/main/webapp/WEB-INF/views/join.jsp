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
	<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300&display=swap" rel="stylesheet">
	
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
			    	<span class="col-6 font30">Join</span>
			    	<div class="header-logo col-6 text-right">
			        	<a href="${pageContext.request.contextPath}/home.do">
			            	<i class="fas fa-meteor"></i>
			            </a>
			        </div>
		        </div>

		        <form method="post" action='${pageContext.request.contextPath}/joinProcess.do'>
		            <p><input class="text-form" type="text" id="userId" name="userId" placeholder="ID" onfocus="this.placeholder=''" onblur="this.placeholder='ID'" maxlength="100" required></p>
		            <div class="row justify-content-center align-items-center"> 
			            <input type="button" id="idCheckBtn" onclick="idCheck();" value="중복 확인">
						<p class="result">
							<span class="message">※ 아이디 중복 확인을 해주세요.</span>
						</p>
					</div>
		            <p><input class="text-form mb20" type="password" name="password" placeholder="Password" onfocus="this.placeholder=''" onblur="this.placeholder='Password'" maxlength="30" required></p>
		            <p><input class="text-form mb20" type="text" name="name" placeholder="Name" onfocus="this.placeholder=''" onblur="this.placeholder='Name'" maxlength="30" required></p>
		            
		            <button id="joinBtn" type="submit">Join</button>
		        </form>

	        </div>
	    </div>
    </div>
<script>
	function idCheck() {
		$.ajax({
			url : "${pageContext.request.contextPath}/idCheck.do",
			dataType: 'json',
			type : "post",
			data : {"userId" : $("#userId").val()},
			async : true,
			success : function(data){
				if(data == 1){
					$(".result .message").text("이미 사용중인 아이디입니다.");
					$(".result .message").attr("style", "color: tomato");
				}else{
					$(".result .message").text("사용 가능한 아이디입니다.");
					$(".result .message").attr("style", "color: #4795DA");
				}
			},
			error : function(error) {
		        alert("Error!="+JSON.stringify(error));
		    },
		    complete : function() {
		    }

		});
	};
</script>

</body>
</html>