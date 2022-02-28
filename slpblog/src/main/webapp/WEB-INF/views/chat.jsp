<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.4.0/sockjs.js"></script>
	<script type="text/javascript">
		var webSocket = {
			init: function(param) {
				this._url = param.url;
				this._initSocket();
			},
			sendChat: function() {
				this._sendMessage($('#message').val());
				$('#message').val('');
			},
			receiveMessage: function(str) {
				$('#divChatData')
					.append('<div>' + str + '</div>')
					.stop()
			        .animate({ scrollTop: $('#divChatData')[0].scrollHeight }, 1000);
			},
			closeMessage: function(str) {
				$('#divChatData')
					.append('<div>' + '연결 끊김 : ' + str + '</div>')
					.stop()
			        .animate({ scrollTop: $('#divChatData')[0].scrollHeight }, 1000);
			},
			disconnect: function() {
				this._socket.close();
			},
			_initSocket: function() {
				this._socket = new SockJS(this._url);
				this._socket.onmessage = function(evt) {
					webSocket.receiveMessage(evt.data);
				};
				this._socket.onclose = function(evt) {
					webSocket.closeMessage(evt.data);
				}
			},
			_sendMessage: function(str) {
				this._socket.send(str);
			}
		};
	</script>	
	<script type="text/javascript">
		$(document).ready(function() {
			webSocket.init({ url: '<c:url value="/chatting" />' });			
		});
	</script>
</head>
<body>
<%@include file ="common/header.jsp" %>
	<div class="body-wrapper">
        <div class="body-content container">
        	<p class="font30 mb-1">Chatting</p>
		    <div class="border" style="height: 350px; padding: 10px; overflow-y: auto;">
				<div id="divChatData"></div>
			</div>
			<div class="my-3">
				<input type="text" class="chat-form" id="message" onkeypress="if(event.keyCode==13){webSocket.sendChat();}" placeholder="채팅을 남겨보세요." onfocus="this.placeholder=''" onblur="this.placeholder='채팅을 남겨보세요.'" maxlength="100" />
				<button type="button" id="btnSend" class="float-right smallBtn p-2 hover-bg-tomato mt-2" onclick="webSocket.sendChat()">전송</button>
			</div>
     	</div>
        <%@include file ="common/footer.jsp" %>
    </div>
</body>
</html>
