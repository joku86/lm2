package de.tiq.solutions.authentification;

import java.security.Principal;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;
import javax.websocket.server.ServerEndpointConfig.Configurator;

import org.eclipse.jetty.servlet.ServletContextHandler;

import de.tiq.solutions.servlets.WorkingServlet;

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

		System.out.println("gib mir instanz");

		return null;

	}
}
