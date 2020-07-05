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
import coffeeshop.dto.UserDTO;
import coffeeshop.bo.CustomerBO;
import coffeeshop.dto.CustomerDTO;

/**
 * Servlet implementation class ViewCustomerServlet
 */
@WebServlet(name = "ViewCustomerServlet", urlPatterns = {"/ViewCustomerServlet"})
public class ViewCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		// Get context for DI CustomerBO and session in request
		HttpSession session = request.getSession(); 
		ServletContext context =  request.getServletContext();
        UserBO userBO = new UserBO(context);
        CustomerBO customerBO = new CustomerBO(context);

        if (userBO.authorizationUser(session, 1) == false){
        	response.sendRedirect("./GoLoginServlet");
	    	return;
        }      
        
        CustomerDTO customer = customerBO.getCustomer((request.getParameter("email")));
        request.setAttribute("customer", customer);

        RequestDispatcher dispatcher 
        = request.getRequestDispatcher
        ("./WEB-INF/jsp/CustomerPage/ViewCustomerPage.jsp");
        dispatcher.forward(request, response);
	}
}
