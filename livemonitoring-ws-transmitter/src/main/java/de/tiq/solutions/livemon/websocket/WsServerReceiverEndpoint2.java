package de.tiq.solutions.livemon.websocket;

import java.io.IOException;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.apache.log4j.Logger;
import org.eclipse.jetty.websocket.api.WebSocketListener;

//@ServerEndpoint(value = "/monitoring/consumer")
public class WsServerReceiverEndpoint2 implements WebSocketListener {
	private static final Logger logger = Logger.getLogger("WsServerReceiverEndpoint");

	@OnOpen
	public void onOpen(final Session session) {
		logger.info("Verbindung zum ProxyEndpunkt aufgebaut " + session.getId());
	}

	@OnClose
	public void onClose(Session session) {
		try {
			session.close();
			getLogger().info("Session beendet: " + session.getId());
		} catch (IOException e) {
			getLogger().error("Session konnte nicht geschlossen werden");
		}

	}

	@OnError
	public void onError(Throwable t) {
		t.printStackTrace();
		logger.error(" OnError " + t.getLocalizedMessage());
	}

	@OnMessage
	public void onMessage(String message, Session session) {
		DataAdapter.getInstance().update(message);
	}

	public static synchronized Logger getLogger() {
		return logger;
	}

	@Override
	public void onWebSocketBinary(byte[] payload, int offset, int len) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onWebSocketClose(int statusCode, String reason) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onWebSocketConnect(org.eclipse.jetty.websocket.api.Session session) {
		 
		try {
			session.getRemote().sendString("zurück von dem server ws 2");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onWebSocketError(Throwable cause) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onWebSocketText(String message) {
		
		
	}
}