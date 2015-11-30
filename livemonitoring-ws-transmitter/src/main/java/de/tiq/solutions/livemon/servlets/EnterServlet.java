package de.tiq.solutions.livemon.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EnterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.getWriter().append("<html><form method='POST' action='/j_security_check'>"
				+ "<input type='text' name='j_username'/>"
				+ "<input type='password' name='j_password'/>"
				+ "<input type='submit' value='Login'/></form></html>");

	}

}