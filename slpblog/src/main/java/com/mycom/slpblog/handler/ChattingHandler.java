package com.mycom.slpblog.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class ChattingHandler extends TextWebSocketHandler
{
  private List<WebSocketSession> sessions = new ArrayList<WebSocketSession>();
  private Map<String, WebSocketSession> userSessions = new HashMap<String, WebSocketSession>();

  public void afterConnectionEstablished(WebSocketSession session)
    throws Exception
  {
    String senderId = getUserId(session);

    this.sessions.add(session);
    this.userSessions.put(senderId, session);

    for (WebSocketSession s : this.sessions)
      s.sendMessage(new TextMessage(senderId + "님이 입장하셨습니다."));
  }

  protected void handleTextMessage(WebSocketSession session, TextMessage message)
    throws Exception
  {
    String senderId = getUserId(session);

    for (WebSocketSession s : this.sessions)
      s.sendMessage(new TextMessage(senderId + " : " + (String)message.getPayload()));
  }

  public void afterConnectionClosed(WebSocketSession session, CloseStatus status)
    throws Exception
  {
    String senderId = getUserId(session);

    this.sessions.remove(session);

    for (WebSocketSession s : this.sessions)
      s.sendMessage(new TextMessage(senderId + "님이 퇴장하셨습니다."));
  }

  private String getUserId(WebSocketSession session)
  {
    Map<String, Object> httpSession = session.getAttributes();
    String userId = (String)httpSession.get("userId");
    return userId == null ? null : userId;
  }
}