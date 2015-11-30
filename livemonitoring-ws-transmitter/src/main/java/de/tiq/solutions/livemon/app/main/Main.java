package de.tiq.solutions.livemon.app.main;

import java.io.File;

import javax.servlet.ServletException;
import javax.websocket.DeploymentException;

import org.eclipse.jetty.http.HttpVersion;
import org.eclipse.jetty.security.ConstraintMapping;
import org.eclipse.jetty.security.ConstraintSecurityHandler;
import org.eclipse.jetty.security.HashLoginService;
import org.eclipse.jetty.security.authentication.FormAuthenticator;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.SecureRequestCustomizer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.SslConnectionFactory;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.resource.FileResource;
import org.eclipse.jetty.util.resource.PathResource;
import org.eclipse.jetty.util.security.Constraint;
import org.eclipse.jetty.util.security.Password;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.eclipse.jetty.websocket.jsr356.server.ServerContainer;
import org.eclipse.jetty.websocket.jsr356.server.deploy.WebSocketServerContainerInitializer;

import de.tiq.solutions.livemon.servlets.EnterServlet;
import de.tiq.solutions.livemon.servlets.LogOutServlet;
import de.tiq.solutions.livemon.servlets.WorkingServlet;
import de.tiq.solutions.livemon.websocket.WsServerReceiverEndpoint;

public class Main {

	public static void main(String[] args) throws ServletException, DeploymentException {
		String host = "localhost";
		String owner = "kunde1";
		int port = 8443;
		int numberOfClients = 1;
		// String host = "192.168.1.147";

		Server server = new Server();
		ServletContextHandler context = new ServletContextHandler(server, "/", ServletContextHandler.SESSIONS
				| ServletContextHandler.SECURITY);

		setToSecure(server, host, port);
		addWebsocket(context);
		try {
			addServlets(context, owner);
			context.setSecurityHandler(addSecurityConstraint());

			ResourceHandler resource_handler = new ResourceHandler();
			// resource_handler.setDirectoriesListed(true);
			resource_handler.setResourceBase("./webapp");

			HandlerList handlers = new HandlerList();
			handlers.setHandlers(new Handler[] { resource_handler, context });
			server.setHandler(handlers);

			// server.setHandler(context);
			server.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			server.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void setToSecure(Server server, String host, int port) throws ServletException,
			DeploymentException {

		SslContextFactory sslContextFactory = new SslContextFactory();
		sslContextFactory.setKeyStoreResource(new PathResource(new
				File(Main.class.getResource("/server.jks").getFile())));
		sslContextFactory.setKeyStorePassword("password");
		sslContextFactory.setKeyManagerPassword("password");
		sslContextFactory.setTrustStoreResource(new FileResource(new
				File(Main.class.getResource("/trust.jks").getFile())));
		sslContextFactory.setTrustStorePassword("password");
		sslContextFactory.setWantClientAuth(true);

		SslConnectionFactory sslConnectionFactory = new SslConnectionFactory(sslContextFactory, HttpVersion.HTTP_1_1.asString());
		HttpConfiguration http_config = new HttpConfiguration();
		http_config.setSecureScheme("https");
		http_config.setSecurePort(port);
		HttpConfiguration https_config = new HttpConfiguration(http_config);
		https_config.addCustomizer(new SecureRequestCustomizer());
		ServerConnector sslConnector = new ServerConnector(server, sslConnectionFactory, new HttpConnectionFactory(https_config));
		sslConnector.setHost(host);
		sslConnector.setPort(port);

		server.addConnector(sslConnector);

	}

	public static void addWebsocket(ServletContextHandler context) throws ServletException, DeploymentException {

		ServerContainer wsContainer = WebSocketServerContainerInitializer
				.configureContext(context);
		wsContainer.addEndpoint(WsServerReceiverEndpoint.class);

		// ClientEndpointConfig configuration=
		// ClientEndpointConfig.Builder.create().build();

	}

	/**
	 * replace with authentification on RR-Plugin
	 * 
	 * @param context
	 * @return
	 */
	private static ConstraintSecurityHandler addSecurityConstraint() {

		Constraint constraint = new Constraint();
		constraint.setName(Constraint.__FORM_AUTH);
		constraint.setRoles(new String[] { "user", "admin", "moderator" });
		constraint.setAuthenticate(true);

		ConstraintMapping constraintMapping = new ConstraintMapping();
		constraintMapping.setConstraint(constraint);
		constraintMapping.setPathSpec("/monitoring/*");

		ConstraintSecurityHandler securityHandler = new ConstraintSecurityHandler();
		securityHandler.addConstraintMapping(constraintMapping);
		HashLoginService loginService = new HashLoginService();
		loginService.putUser("username", new Password("pass"), new String[] { "user" });
		loginService.putUser("admin", new Password("admin"), new String[] { "admin" });

		securityHandler.setLoginService(loginService);
		// BasicAuthenticator authenticator = new BasicAuthenticator();

		FormAuthenticator authenticator = new
				FormAuthenticator("/monitoring/login", "/monitoring/login", false);

		securityHandler.setAuthenticator(authenticator);
		return securityHandler;
	}

	private static void addServlets(ServletContextHandler context, String owner) {
		// context.addServlet(new ServletHolder(new EveryServlet()),
		// "/*");
		context.addServlet(new ServletHolder(new LogOutServlet()),
				"/monitoring/logout");
		context.addServlet(new ServletHolder(new WorkingServlet()),
				"/monitoring/" + owner);
		//
		// context.addServlet(new ServletHolder(new ErrorServlet()),
		// "/loginerror");
		context.addServlet(new ServletHolder(new EnterServlet()),
				"/monitoring/login");
	}

}
