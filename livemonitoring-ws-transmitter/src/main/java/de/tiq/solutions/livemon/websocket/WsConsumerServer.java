package de.tiq.solutions.livemon.websocket;

import javax.websocket.EndpointConfig;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import de.tiq.solutions.livemon.authentification.AuthInstansObserver;

@ServerEndpoint(value = "/ws/server",configurator = AuthInstansObserver.class)
public class WsConsumerServer {
	@OnOpen
	public void onMessage(Session session, EndpointConfig config) {
		System.out.println("verbingun aufgebaut");
	}

	@OnMessage
	public void onMessage(Session session, String message) {
		session.getAsyncRemote().sendText("von dem server");
	}
}