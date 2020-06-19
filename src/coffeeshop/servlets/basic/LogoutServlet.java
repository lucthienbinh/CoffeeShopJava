package coffeeshop.servlets.basic;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet(name = "LogoutServlet", urlPatterns = {"/LogoutServlet"})
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private boolean checkUserLoggedIn(HttpSession session)
	{
		if(!session.isNew() && session.getAttribute("email") != null )
		{
			return true;
		}
		return false;
	}
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 HttpSession session = request.getSession();
		 boolean isLoggedInResult = this.checkUserLoggedIn(session);
		 if (isLoggedInResult == true)
		 {
			 session.removeAttribute("email");
		 }
		 response.sendRedirect("./GoLoginServlet");
	}

}
