//package de.tiq.solutions.livemon.websocket;
//
//import java.io.IOException;
//
//import javax.websocket.OnClose;
//import javax.websocket.OnError;
//import javax.websocket.OnMessage;
//import javax.websocket.OnOpen;
//import org.eclipse.jetty.websocket.api.Session;
//
//import org.apache.log4j.Logger;
//import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
//import org.eclipse.jetty.websocket.api.annotations.WebSocket;
//
//@WebSocket	
//public class alt {
//	private static final Logger logger = Logger.getLogger("WsServerReceiverEndpoint");
//
//	 @OnWebSocketConnect
//     public void onOpen( Session session)
//     {
//       System.out.println("asdfsdf");
//		logger.info("Verbindung zum ProxyEndpunkt aufgebaut " + session.getRemoteAddress());
//     }
//	
////	@OnOpen
////	public void onOpen(final Session session) {
////		System.out.println("defdff");
////		logger.info("Verbindung zum ProxyEndpunkt aufgebaut " + session.getId());
////	}
//
////	@OnClose
////	public void onClose(Session session) {
////		try {
////			session.close();
////			getLogger().info("Session beendet: " + session.getId());
////		} catch (IOException e) {
////			getLogger().error("Session konnte nicht geschlossen werden");
////		}
////
////	}
//
//	@OnError
//	public void onError(Throwable t) {
//		t.printStackTrace();
//		logger.error(" OnError " + t.getLocalizedMessage());
//	}
//
//	@OnMessage
//	public void onMessage(String message) {
//		DataAdapter.getInstance().update(message);
//	}
//
//	public static synchronized Logger getLogger() {
//		return logger;
//	}
//}
