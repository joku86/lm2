package de.tiq.solutions.livemon.app.main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.servlet.DefaultServlet;

public class LogInLogOutServlets {

	public static class LoginServet extends DefaultServlet {
		protected void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
System.out.println("cdfd" +request.getRealPath("."));
			response.getWriter()
					.append("<html>" + "<head>"
							+ "<link rel=\"stylesheet\" type=\"text/css\" href=\"test.css\" />"
							+ "</head><body>" + request.getRealPath(".") + "<form method='POST' action='"
							+ request.getContextPath() + "/j_security_check'>"
							+ "<input type='text' name='j_username'/>" + "<input type='password' name='j_password'/>"
							+ "<input type='submit' value='Login'/></form></body></html>");
		}
	}
	public static class LogoutServet extends HttpServlet {
		protected void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			
			response.getWriter()
			.append("<html><body><a href=\""+request.getContextPath()+"\">back</a></body></html>");
		}
	}

}