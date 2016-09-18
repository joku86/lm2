package de.tiq.solutions.livemon.app.main;

import java.io.File;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.DispatcherType;
import javax.ws.rs.core.Application;

import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.web.env.EnvironmentLoaderListener;
import org.eclipse.jetty.http.HttpVersion;
import org.eclipse.jetty.security.ConstraintMapping;
import org.eclipse.jetty.security.ConstraintSecurityHandler;
import org.eclipse.jetty.security.HashLoginService;
import org.eclipse.jetty.security.SecurityHandler;
import org.eclipse.jetty.security.authentication.BasicAuthenticator;
import org.eclipse.jetty.security.authentication.FormAuthenticator;
import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.SecureRequestCustomizer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.SslConnectionFactory;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ErrorPageErrorHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.security.Constraint;
import org.eclipse.jetty.util.security.Password;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.eclipse.jetty.webapp.WebAppContext;

import de.tiq.solutions.livemon.websocket.WebSocketServer;
import de.tiq.solutions.livemon.websocket.WebSocketServer.WsReceiver;
import de.tiq.solutions.livemon.websocket.WebSocketTransmitter.WsSender;
import de.tiq.solutions.rest.LiveMonitoringRestApi;

public class Main extends Application {

	
	private static final SecurityHandler basicAuth(String username, String password, String realm) {

        Constraint constraint = new Constraint();
        constraint.setName(Constraint.__FORM_AUTH);
        constraint.setRoles(new String[]{"user"});
        constraint.setAuthenticate(true);
        HashLoginService loginService = new HashLoginService();
        loginService.putUser("ich", new Password("ich"), new String[] {"user"});

        FormAuthenticator authenticator = new FormAuthenticator("/login.html", "/404.html", false);
        ConstraintMapping cm = new ConstraintMapping();
        cm.setConstraint(constraint);
        cm.setPathSpec("/*");
        
        ConstraintSecurityHandler csh = new ConstraintSecurityHandler();
        csh.setAuthenticator(authenticator);
        csh.setRealmName("myrealm");
        csh.addConstraintMapping(cm);
        csh.setLoginService(loginService);
        
        return csh;
    	
}
	private static WebAppContext setupWebAppContext(ContextHandlerCollection contexts) {

		WebAppContext webApp = new WebAppContext();
		webApp.setContextPath("/livemon");
		webApp.setSecurityHandler(basicAuth("scott", "tiger", "Private!"));
		
		
		File warPath = new File("../livemonitoring-web");
		if (warPath.isDirectory()) {
			// Development mode, read from FS
			 webApp.setDescriptor(warPath+"src/main/webapp/WEB-INF/web.xml");
			webApp.setResourceBase(warPath.getPath());
			webApp.setParentLoaderPriority(true);
		} else {
			System.out.println("else");
		}
		// Explicit bind to root
		webApp.addServlet(new ServletHolder(new DefaultServlet()), "/*");
		contexts.addHandler(webApp);
		ErrorPageErrorHandler errorHandler = new ErrorPageErrorHandler();
	    errorHandler.addErrorPage(404, "/404.html");
	    webApp.setErrorHandler(errorHandler);

//		webApp.addServlet(new ServletHolder(new DefaultServlet() {
//			  @Override
//			  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//			    response.getWriter().append("<html><form method='POST' action='/j_security_check'>"
//			      + "<input type='text' name='j_username'/>"
//			      + "<input type='password' name='j_password'/>"
//			      + "<input type='submit' value='Login'/></form></html>");
//			    }
//			}), "/login.jsp");

	 
		
		return webApp;

	}

	private static void setuShiro(WebAppContext webapp) {
//
//		final ServletHolder cxfServletHolder = new ServletHolder(new CXFNonSpringJaxrsServlet());
//		cxfServletHolder.setInitParameter("javax.ws.rs.Application", Main.class.getName());
//		cxfServletHolder.setName("rest");
//		cxfServletHolder.setForcedPath("rest");
//
//		webapp.setSessionHandler(new SessionHandler());
//		webapp.addServlet(cxfServletHolder, "/api/*");

		
	   
//		webapp.addFilter(new FilterHolder(new CorsFilter()), "/*", EnumSet.allOf(DispatcherType.class));
 		
		
		webapp.setInitParameter("shiroConfigLocations", Main.class.getClassLoader().getResource("shiro.ini").toString());
 		  IniSecurityManagerFactory factory = new IniSecurityManagerFactory(Main.class.getClassLoader().getResource("shiro.ini").toString());
 		    SecurityManager securityManager = factory.getInstance();
 		    org.apache.shiro.SecurityUtils.setSecurityManager(securityManager);

		//Create and init the Shiro filter that will lookup and use environment we just created     
		    webapp.addFilter(
		        "org.apache.shiro.web.servlet.ShiroFilter", "/*",EnumSet.allOf(DispatcherType.class));
		    webapp.addEventListener(new EnvironmentLoaderListener());
	}

//	private static void setToSecure(Server server, String host, int port) throws ServletException {
//
//		SslContextFactory sslContextFactory = new SslContextFactory();
//		sslContextFactory
//				.setKeyStoreResource(new PathResource(new File(Main.class.getResource("/server.jks").getFile())));
//		sslContextFactory.setKeyStorePassword("password");
//		sslContextFactory.setKeyManagerPassword("password");
//		sslContextFactory
//				.setTrustStoreResource(new PathResource(new File(Main.class.getResource("/trust.jks").getFile())));
//		sslContextFactory.setTrustStorePassword("password");
//		// sslContextFactory.setWantClientAuth(true);
//
//		SslConnectionFactory sslConnectionFactory = new SslConnectionFactory(sslContextFactory,
//				HttpVersion.HTTP_1_1.asString());
//		HttpConfiguration http_config = new HttpConfiguration();
//		http_config.setSecureScheme("https");
//		http_config.setSecurePort(port);
//		HttpConfiguration https_config = new HttpConfiguration(http_config);
//		https_config.addCustomizer(new SecureRequestCustomizer());
//		ServerConnector sslConnector = new ServerConnector(server, sslConnectionFactory,
//				new HttpConnectionFactory(https_config));
//		sslConnector.setHost(host);
//		sslConnector.setPort(5671);
//
//		server.addConnector(sslConnector);
//	}

	private static Server setupJettyServer(boolean ssl,int port) {

		final Server server = new Server();
		ServerConnector connector = null;

		if (ssl) {
			HttpConfiguration httpConfig = new HttpConfiguration();
			httpConfig.setSecureScheme("https");
			httpConfig.setSecurePort(port);
			httpConfig.setOutputBufferSize(32768);
			HttpConfiguration httpsConfig = new HttpConfiguration(httpConfig);
			SecureRequestCustomizer src = new SecureRequestCustomizer();
			// Only with Jetty 9.3.x
			// src.setStsMaxAge(2000);
			// src.setStsIncludeSubDomains(true);
			httpsConfig.addCustomizer(src);
			connector = new ServerConnector(server,
					new SslConnectionFactory(getSslContextFactory(), HttpVersion.HTTP_1_1.asString()),
					new HttpConnectionFactory(httpsConfig));
		} else {
			connector = new ServerConnector(server);
		}
		// Set some timeout options to make debugging easier.
		int timeout = 1000 * 30;
		connector.setIdleTimeout(timeout);
		connector.setSoLingerTime(-1);
		connector.setHost("127.0.0.1");
		connector.setPort(port);

		server.addConnector(connector);

		return server;
	}

	private static void setupNotebookServer(WebAppContext webapp) {
		WebSocketServer lm = new WebSocketServer();
		String maxTextMessageSize = "9000";
		final ServletHolder servletHolder = new ServletHolder();
		servletHolder.setInitParameter("maxTextMessageSize", maxTextMessageSize);

		final ServletContextHandler cxfContext = new ServletContextHandler(ServletContextHandler.SESSIONS);
		webapp.addServlet(new ServletHolder(WsReceiver.class), "/ws/receiver");
		webapp.addServlet(new ServletHolder(WsSender.class), "/ws/transmitter");
	}

	public static void main(String[] args) throws Exception {
		Server jettyWebServer = setupJettyServer(true,8443);
		ContextHandlerCollection contexts = new ContextHandlerCollection();
		jettyWebServer.setHandler(contexts);

		// // Enable javax.websocket configuration for the context
		// ServerContainer wsContainer =
		// WebSocketServerContainerInitializer.configureContext(context);
		//
		// // Add your websockets to the container
		// wsContainer.addEndpoint(EchoJsrSocket.class);

		// Web UI
		final WebAppContext webApp = setupWebAppContext(contexts);
//		setuShiro(webApp);

		setupNotebookServer(webApp);
		try {
			jettyWebServer.start();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}

	// private static void setupNotebookServer(WebAppContext webapp,
	// ZeppelinConfiguration conf) {
	// notebookWsServer = new NotebookServer();
	// String maxTextMessageSize = conf.getWebsocketMaxTextMessageSize();
	// final ServletHolder servletHolder = new ServletHolder(notebookWsServer);
	// servletHolder.setInitParameter("maxTextMessageSize", maxTextMessageSize);
	//
	// final ServletContextHandler cxfContext = new
	// ServletContextHandler(ServletContextHandler.SESSIONS);
	//
	// webapp.addServlet(servletHolder, "/ws/*");
	// }

	private static SslContextFactory getSslContextFactory() {
		SslContextFactory sslContextFactory = new SslContextFactory();

		// Set keystore
		sslContextFactory.setKeyStorePath(Main.class.getClassLoader().getResource("server.jks").getFile());
		sslContextFactory.setKeyStoreType("JKS");
		sslContextFactory.setKeyStorePassword("password");
		// sslContextFactory.setKeyManagerPassword(conf.getKeyMa
		// nagerPassword());
		// Set truststore
		sslContextFactory.setTrustStorePath(Main.class.getClassLoader().getResource("trust.jks").getFile());
		sslContextFactory.setTrustStoreType("JKS");
		sslContextFactory.setTrustStorePassword("password");
		// sslContextFactory.setNeedClientAuth(conf.useClientAuth());
		return sslContextFactory;
	}

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> classes = new HashSet<Class<?>>();
		return classes;
	}

	@Override
	public Set<Object> getSingletons() {
		Set<Object> singletons = new HashSet<>();

		/** Rest-api root endpoint */
		LiveMonitoringRestApi root = new LiveMonitoringRestApi();
		singletons.add(root);

		return singletons;
	}

}
