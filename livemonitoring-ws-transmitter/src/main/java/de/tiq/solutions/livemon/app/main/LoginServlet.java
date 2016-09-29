package de.tiq.solutions.livemon.app.main;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter()
				.append("<html><form method='POST' action='/test/j_security_check'>"
						+ "<input type='text' name='j_username'/>" + "<input type='password' name='j_password'/>"
						+ "<input type='submit' value='Login'/></form></html>");
	}

	
}