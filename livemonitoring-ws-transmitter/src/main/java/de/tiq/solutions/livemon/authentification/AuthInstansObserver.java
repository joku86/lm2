package de.tiq.solutions.livemon.authentification;

import java.security.Principal;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;
import javax.websocket.server.ServerEndpointConfig.Configurator;

import de.tiq.solutions.livemon.websocket.WsConsumerServer;

public class AuthInstansObserver extends Configurator {

	public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
		System.out.println("modify handshake");
		HttpSession httpSession = (HttpSession) request.getHttpSession();
		String id = httpSession.getId();
		System.out.println("session id in handshake " + id);
		Principal userPrincipal = request.getUserPrincipal();
		System.out.println(userPrincipal.getName());
		System.out.println(request);
	}

	// WorkingServlet.getLoggedUser
	public <T> T getEndpointInstance(Class<T> endpointClass) throws InstantiationException {
		try {
			WsConsumerServer new_name = (WsConsumerServer) endpointClass.newInstance();
			return (T) new_name;
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("gib mir instanz");

		return null;

	}
}
