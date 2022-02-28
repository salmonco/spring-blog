<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
<!-- <meta http-equiv="X-UA-Compatible" content="IE=edge"> -->
<meta charset="UTF-8">
</head>
<body>
<%@include file ="common/header.jsp" %>
	<div class="body-wrapper">
        <div class="body-content container">
	        <form method="post" action="${pageContext.request.contextPath}/writeProcess.do" class="write" name="writeForm" enctype="multipart/form-data">
	    		<div class="row align-items-center mb-4">
		        	<span class="col-md-6 font30">Write</span>
		        	<div class="col-md-6 text-right">
	                	<button type="submit" class="smallBtn p-2 hover-bg-tomato">등록</button>
			        </div>
			    </div>
			    <input type="hidden" name="editorStatus" value="">
	            <p><input type="text" name="boardTitle" class="writeTitle mb-4" placeholder="제목을 입력해 주세요." onfocus="this.placeholder=''" onblur="this.placeholder='제목을 입력해 주세요.'" maxlength="100" required></p>
	            <p><textarea name="boardContent" id="contents" class="writeContent text-wrap" placeholder="내용을 입력하세요." onfocus="this.placeholder=''" onblur="this.placeholder='내용을 입력하세요.'"></textarea></p>
	            <sup>(<span id="nowByte">0</span>/500bytes)</sup>
	            <div class="my-4">
					<p><input type="file" name="file1" maxlength="200" class="mb-2"></p>
					<p><input type="file" name="file2" maxlength="200"></p>
	            </div>
	        </form>
    	</div>
    	<%@include file ="common/footer.jsp" %>
    </div>
</body>
</html>