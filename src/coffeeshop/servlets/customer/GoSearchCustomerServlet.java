package coffeeshop.servlets.customer;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import coffeeshop.bo.CustomerBO;
import coffeeshop.bo.UserBO;
import coffeeshop.dto.CustomerDTO;

/**
 * Servlet implementation class GoSearchCustomerServlet
 */
@WebServlet(name = "GoSearchCustomerServlet", urlPatterns = {"/GoSearchCustomerServlet"})
public class GoSearchCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L; 
    
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		// Get context for DI UserBO and session in request
		HttpSession session = request.getSession(); 
		ServletContext context =  request.getServletContext();
        UserBO userBO = new UserBO(context);
        CustomerBO customerBO = new CustomerBO(context);
        if (userBO.authorizationUser(session, 1) == false)
        {
        	response.sendRedirect("./GoLoginServlet");
	    	return;
        }    
		// Remove old search info stored in session if exists
		CustomerDTO customerSearchInfo = (CustomerDTO) session.getAttribute("customerSearchInfo");
		if (customerSearchInfo != null)
        {
			session.removeAttribute("customerSearchInfo");
        }
		// Set new empty search info 
		CustomerDTO newEmptyCustomerInfo = new CustomerDTO();
		newEmptyCustomerInfo.generateEmptyObjectForJSP();
		session.setAttribute("customerSearchInfo", newEmptyCustomerInfo);
		// Get user info
        ArrayList<CustomerDTO> customers = customerBO.searchCustomer(null);
        request.setAttribute("customers", customers);
        RequestDispatcher dispatcher 
                = request.getRequestDispatcher
        ("./WEB-INF/jsp/CustomerPage/SearchCustomerPage.jsp");
        dispatcher.forward(request, response);
	}

}
