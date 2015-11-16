package de.tiq.solutions.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EveryServlet extends HttpServlet {
	static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.isSecure())
		{

			if (request.getUserPrincipal() != null) {
				System.out.println("eingeloggt");
			}

		} else {
			// redirect to secure but should always be secure

		}

		System.out.println("im every servlet");
		response.getWriter().append("hello das ist die dfault seite");

	}

	public void destroy()
	{
		System.out.println("destroy");
	}
}