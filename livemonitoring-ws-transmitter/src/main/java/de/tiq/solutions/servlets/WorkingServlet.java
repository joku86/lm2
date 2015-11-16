package de.tiq.solutions.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WorkingServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("im working");
		// if (request.getUserPrincipal() != null) {
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

		// }
	}

	public void destroy()
	{
		// do nothing.
	}
}