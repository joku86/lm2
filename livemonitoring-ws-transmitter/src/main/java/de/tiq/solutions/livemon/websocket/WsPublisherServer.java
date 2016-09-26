package de.tiq.solutions.livemon.websocket;

import javax.websocket.EndpointConfig;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import de.tiq.solutions.livemon.authentification.AuthInstansObserver;

@ServerEndpoint(value = "/echo",configurator = AuthInstansObserver.class)
public class WsPublisherServer {
	@OnOpen
	public void onMessage(Session session, EndpointConfig config) {
		System.out.println("verbingun aufgebaut");
	}

	@OnMessage
	public void onMessage(Session session, String message) {
		session.getAsyncRemote().sendText("von dem server");
	}
	// public static void main( String[] args ) throws Exception
	// {
	// Server server = new Server(8080);
	//
	// ServletContextHandler context = new ServletContextHandler(
	// ServletContextHandler.SESSIONS);
	// context.setContextPath("/");
	// server.setHandler(context);
	//
	// // Add the echo socket servlet to the /echo path map
	//
	//
	// server.start();
	// context.dumpStdErr();
	// server.join();
	// }
}