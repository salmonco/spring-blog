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
        	<form method="post" action="${pageContext.request.contextPath}/updateProcess.do?boardNo=${content.boardNo}" name="writeForm" class="write" enctype="multipart/form-data">
            	<div class="row align-items-center mb-4">
		        	<span class="col-md-6 font30">Edit</span>
		        	<div class="col-md-6 text-right">
	                	<button type="submit" class="smallBtn p-2 hover-bg-tomato">등록</button>
			        </div>
			    </div>
            	<input type="hidden" name="editorStatus" value="">
            	<input type="hidden" name="boardNo" value="${content.boardNo}">
                <p><input type="text" class="writeTitle mb-4" id="name" name="boardTitle" value="${content.boardTitle}" maxlength="100" required></p>
                <p><textarea name="boardContent" id="contents" class="writeContent text-wrap">${content.boardContent}</textarea></p>
                <sup>(<span id="nowByte">0</span>/500bytes)</sup>
                <div class="my-4">
					<p>첨부파일 |</p>
					<input type="file" name="file1" class="mb-2">
					<input type="file" name="file2">
				</div>
            </form>
    	</div>
    <%@include file ="common/footer.jsp" %>
    </div>
</body>
</html>