package de.tiq.solutions.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.eclipse.jetty.security.DefaultUserIdentity;
import org.eclipse.jetty.security.authentication.SessionAuthentication;
import org.eclipse.jetty.server.UserIdentity;

import de.tiq.solutions.websocket.WsServerReceiverEndpoint;

public class WorkingServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("im working");
		HttpSession session = request.getSession();
		Enumeration<String> attributeNames = session.getAttributeNames();
		System.out.println("session id " + session.getId());
		while (attributeNames.hasMoreElements()) {
			String string = (String) attributeNames.nextElement();
			System.out.println(string);

			if (string.contains("UserIdentity")) {
				SessionAuthentication uident = (SessionAuthentication) session.getAttribute(string);
				UserIdentity userIdentity = uident.getUserIdentity();
				System.out.println(userIdentity.getSubject().getPrincipals());
			}

		}

		if (request.getUserPrincipal() != null) {
			response.setContentType("text/html");

			PrintWriter out = response.getWriter();
			String title = "Using GET Method to Read Form Data";
			String docType =
					"<!doctype html public \"-//w3c//dtd html 4.0 " +
							"transitional//de\">\n";
			out.println(docType +
					"<html>\n" +
					"<head><title>" + title + "</title>" +
					"<script src=\"../function.js\"></script>" + "</head>\n" +
					"<body bgcolor=\"#f0f0f0\">\n" +
					"<a href='logout'>Logout</a>" +

					" <button type=\"button\" onclick=\"init()\">Click Me!</button> "
					+ "<h2>WebSocket Test</h2>  <div id=\"output\"></div>" +

					"</body></html>");

		}

	}

	public void destroy()
	{
		// do nothing.
	}
}