package de.tiq.solutions.websocket;

import java.io.IOException;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.apache.log4j.Logger;

@ServerEndpoint(value = "/monitoring/consumer")
public class WsServerReceiverEndpoint {
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
		System.out.println("from client  " + message);
		try {
			session.getBasicRemote().sendText("back from server");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static synchronized Logger getLogger() {
		return logger;
	}

}
