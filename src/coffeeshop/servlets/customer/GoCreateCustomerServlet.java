package coffeeshop.servlets.customer;

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
 * Servlet implementation class GoCreateCustomerServlet
 */
@WebServlet(name = "GoCreateCustomerServlet", urlPatterns = {"/GoCreateCustomerServlet"})
public class GoCreateCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		// Get context for DI UserBO and session in request
		HttpSession session = request.getSession(); 
		ServletContext context =  request.getServletContext();
        UserBO userBO = new UserBO(context);
        if (userBO.authorizationUser(session, 1) == false)
        {
        	response.sendRedirect("./GoLoginServlet");
	    	return;
        }    
		RequestDispatcher dispatcher 
		 = request.getRequestDispatcher
	 	 ("./WEB-INF/jsp/CustomerPage/CreateCustomerPage.jsp");
		 dispatcher.forward(request, response);
	}
}
