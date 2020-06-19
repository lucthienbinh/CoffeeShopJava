package coffeeshop.servlets.basic;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import coffeeshop.bo.UserBO;

/**
 * Servlet implementation class GoAdminFunctionServlet
 */
@WebServlet(name = "GoDashboardServlet", urlPatterns = {"/GoDashboardServlet"})
public class GoDashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html"); 
		// Get context for DI UserBO and session in request
		HttpSession session = request.getSession(); 
		ServletContext context =  request.getServletContext();
	    UserBO userBO = new UserBO(context);
	    if (userBO.authorizationUser(session, 1) == true)
	    {
	    	RequestDispatcher dispatcher 
			 = request.getRequestDispatcher
		 	 ("./WEB-INF/jsp/BasicPage/AdminDashboardPage.jsp");
			 dispatcher.forward(request, response);
	    	return;
	    } else if (userBO.authorizationUser(session, 2) == true) {
	    	RequestDispatcher dispatcher 
			 = request.getRequestDispatcher
		 	 ("./WEB-INF/jsp/BasicPage/UserDashboardPage.jsp");
			 dispatcher.forward(request, response);
	    	return;
	    } else {
	    	response.sendRedirect("./GoLoginServlet");
	    	return;
	    }
	}


}
