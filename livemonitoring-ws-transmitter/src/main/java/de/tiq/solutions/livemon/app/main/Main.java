package de.tiq.solutions.livemon.app.main;

import java.io.File;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.DispatcherType;
import javax.servlet.ServletException;
import javax.websocket.DeploymentException;
import javax.ws.rs.core.Application;

import org.apache.cxf.jaxrs.servlet.CXFNonSpringJaxrsServlet;
//github.com/joku86/lm2.git
import org.apache.shiro.web.env.EnvironmentLoaderListener;
import org.eclipse.jetty.http.HttpVersion;
import org.eclipse.jetty.security.Authenticator;
import org.eclipse.jetty.security.ConstraintMapping;
import org.eclipse.jetty.security.ConstraintSecurityHandler;
import org.eclipse.jetty.security.HashLoginService;
import org.eclipse.jetty.security.LoginService;
import org.eclipse.jetty.security.SecurityHandler;
import org.eclipse.jetty.security.authentication.FormAuthenticator;
import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.SecureRequestCustomizer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.SslConnectionFactory;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.server.session.SessionHandler;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ErrorPageErrorHandler;
import org.eclipse.jetty.servlet.FilterHolder;
//github.com/joku86/lm2.git
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.security.Constraint;
import org.eclipse.jetty.util.security.Password;
//github.com/joku86/lm2.git
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.eclipse.jetty.webapp.WebAppContext;
import org.eclipse.jetty.websocket.jsr356.server.ServerContainer;
import org.eclipse.jetty.websocket.jsr356.server.deploy.WebSocketServerContainerInitializer;

import de.tiq.solutions.livemon.websocket.WsConsumerServer;
import de.tiq.solutions.rest.LiveMonitoringRestApi;

public class Main extends Application {

	private static final SecurityHandler basicAuth() {
		Constraint constraint = new Constraint();
		constraint.setName(Constraint.__FORM_AUTH);
		constraint.setRoles(new String[] { "user", "admin" });
		constraint.setAuthenticate(true);

		HashLoginService loginService = new HashLoginService("MyRealm", "../config/lm_users.properties");
//		 loginService.putUser("ich", new Password("ich"), new String[]
//		 {"user"});

		FormAuthenticator authenticator = new FormAuthenticator("/login.html", "/404", false);
		ConstraintMapping cm = new ConstraintMapping();
		cm.setConstraint(constraint);
		cm.setPathSpec("/auth/*");

		ConstraintSecurityHandler csh = new ConstraintSecurityHandler();
		csh.setAuthenticator(authenticator);
		csh.addConstraintMapping(cm);
		csh.setLoginService(loginService);

		return csh;

	}

	private static WebAppContext setupWebAppContext(ContextHandlerCollection contexts, Server jetty, LmConfigs config) {

		WebAppContext webApp = new WebAppContext();
		webApp.setContextPath(
				config.getConfigValue("context.path") == null ? "/" : "/" + config.getConfigValue("context.path"));
		File warPath = new File(config.getConfigValue("server.web.resources"));
		// File warPath = new File("E:/testwar/simple.war");
		if (warPath.isDirectory()) {
			// Development mode, read from FS
			// webApp.setDescriptor(warPath+"/WEB-INF/web.xml");
			webApp.setResourceBase(warPath.getPath());
			webApp.setParentLoaderPriority(true);
		} else {
			discoverAnnotations(jetty);
			webApp.setWar(warPath.getAbsolutePath());
			webApp.setTempDirectory(new File("E:/testwar/deploy"));
		}
		// Explicit bind to root
		DefaultServlet servlet = new DefaultServlet();
		
		 webApp.addServlet(new ServletHolder(servlet),"/*");
//		 config.getConfigValue("server.login"));
//			webApp.addServlet(new ServletHolder(new LogInLogOutServlets.LoginServet()),"/index.html");
		webApp.addServlet(new ServletHolder(new LogInLogOutServlets.LogoutServet()),
				config.getConfigValue("server.404"));
		contexts.addHandler(webApp);
		// setupCustomFilter(webApp);
		ErrorPageErrorHandler errorHandler = new ErrorPageErrorHandler();
		errorHandler.addErrorPage(404, config.getConfigValue("server.404"));
		webApp.setErrorHandler(errorHandler);
		contexts.addHandler(errorHandler);

		return webApp;

	}

	public static void discoverAnnotations(Server jetty) {
		org.eclipse.jetty.webapp.Configuration.ClassList classlist = org.eclipse.jetty.webapp.Configuration.ClassList
				.setServerDefault(jetty);
		classlist.addAfter("org.eclipse.jetty.webapp.FragmentConfiguration",
				"org.eclipse.jetty.plus.webapp.EnvConfiguration", "org.eclipse.jetty.plus.webapp.PlusConfiguration");
		classlist.addBefore("org.eclipse.jetty.webapp.JettyWebXmlConfiguration",
				"org.eclipse.jetty.annotations.AnnotationConfiguration");
	}

	private static void setupCustomFilter(WebAppContext webapp) {
		webapp.addFilter(new FilterHolder(new CorsFilter()), "/auth/api", EnumSet.allOf(DispatcherType.class));
	}

	private static void setuShiro(WebAppContext webapp) {
		webapp.setInitParameter("shiroConfigLocations",
				Main.class.getClassLoader().getResource("shiro.ini").toString());
		// IniSecurityManagerFactory factory = new
		// IniSecurityManagerFactory(Main.class.getClassLoader().getResource("shiro.ini").toString());
		// SecurityManager securityManager = factory.getInstance();
		// org.apache.shiro.SecurityUtils.setSecurityManager(securityManager);
		// Create and init the Shiro filter that will lookup and use environment
		// we just created
		webapp.addFilter("org.apache.shiro.web.servlet.ShiroFilter", "/*", EnumSet.allOf(DispatcherType.class));
		webapp.addEventListener(new EnvironmentLoaderListener());
	}

	private static Server setupJettyServer(LmConfigs config) {

		final Server server = new Server();
		ServerConnector connector = null;
		if (config.getConfigValue("server.secure").equals("true")) {
			HttpConfiguration httpConfig = new HttpConfiguration();
			httpConfig.setSecureScheme("https");
			httpConfig.setSecurePort(config.getConfigValue("server.port") == null ? 8443
					: Integer.parseInt(config.getConfigValue("server.port")));
			httpConfig.setOutputBufferSize(32768);
			HttpConfiguration httpsConfig = new HttpConfiguration(httpConfig);
			SecureRequestCustomizer src = new SecureRequestCustomizer();
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
		connector
				.setHost(config.getConfigValue("server.ip") == null ? "127.0.0.1" : config.getConfigValue("server.ip"));
		connector.setPort(config.getConfigValue("server.port") == null ? 8080
				: Integer.parseInt(config.getConfigValue("server.port")));

		server.addConnector(connector);

		return server;
	}

	private static void setupRestApi(WebAppContext webapp) {

		final ServletHolder cxfServletHolder = new ServletHolder(new CXFNonSpringJaxrsServlet());
		cxfServletHolder.setInitParameter("javax.ws.rs.Application", Main.class.getName());
		cxfServletHolder.setName("rest");
		cxfServletHolder.setForcedPath("rest");
		String maxTextMessageSize = "9000";
		cxfServletHolder.setInitParameter("maxTextMessageSize", maxTextMessageSize);
		webapp.setSessionHandler(new SessionHandler());
		webapp.addServlet(cxfServletHolder, "/auth/api/*");

	}

	public static void main(String[] args) throws Exception {
//
		LmConfigs serverConfigs = LmConfigs.getInsance();

		final Server jettyWebServer = setupJettyServer(serverConfigs);

		ContextHandlerCollection contextes = new ContextHandlerCollection();
		jettyWebServer.setHandler(contextes);

		final WebAppContext webApp = setupWebAppContext(contextes, jettyWebServer, serverConfigs);
		SecurityHandler basicAuth = basicAuth();
		
		contextes.addHandler(basicAuth);
		webApp.setSecurityHandler(basicAuth);
		setupWebsocketJSR(webApp);

		setupRestApi(webApp);
		// setupWs(webApp);
		// setuShiro(webApp);

		try {
			jettyWebServer.start();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				// LOG.info("Shutting down Zeppelin Server ... ");
				try {
					jettyWebServer.stop();
				} catch (Exception e) {
					// LOG.error("Error while stopping servlet container", e);
				}
			}
		});
		jettyWebServer.join();
		
	}
	private static ConstraintMapping[] getConstraintMappings() {
	    // CONSTRAINT
	    Constraint constraint = new Constraint();
	    constraint.setName(Constraint.__FORM_AUTH);
	    constraint.setRoles(new String[]{"user", "admin"});
	    constraint.setAuthenticate( true );
	    // MAPPINGS
	    ConstraintMapping mapping = new ConstraintMapping();
	    mapping.setPathSpec( "/" );
	    mapping.setConstraint( constraint );

	    return new ConstraintMapping[] {mapping};
	}



	private static void setupWebsocketJSR(final WebAppContext webApp) throws ServletException, DeploymentException {
		ServerContainer wsContainer = WebSocketServerContainerInitializer.configureContext(webApp.getServletContext(),
				webApp);

		wsContainer.addEndpoint(WsConsumerServer.class);
	}

	private static SslContextFactory getSslContextFactory() {
		SslContextFactory sslContextFactory = new SslContextFactory();

		// Set keystore TODO programmatic generation
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
