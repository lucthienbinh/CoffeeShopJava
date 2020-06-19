package coffeeshop.servlets.basic;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GoLoginServlet
 */
@WebServlet(name = "GoLoginServlet", urlPatterns= {"/GoLoginServlet"})
public class GoLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		RequestDispatcher dispatcher 
        = request.getRequestDispatcher
	    ("./WEB-INF/jsp/BasicPage/LoginPage.jsp");
	    dispatcher.forward(request, response);
	}

}
