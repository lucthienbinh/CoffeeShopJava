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
 * Servlet implementation class SearchCustomerServlet
 */
@WebServlet(name = "SearchCustomerServlet", urlPatterns = {"/SearchCustomerServlet"})
public class SearchCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private String validateString(String stringCheck)
	{
		if(stringCheck == null) 
		{
			return "";
		}
		return stringCheck.trim();
	}
       
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
        CustomerDTO customerSearchInfo = new CustomerDTO();
        customerSearchInfo.setName(this.validateString(request.getParameter("name")));
        customerSearchInfo.setAddress(this.validateString(request.getParameter("address")));
        customerSearchInfo.setEmail(this.validateString(request.getParameter("email")));
        customerSearchInfo.setMobilephone(this.validateString(request.getParameter("mobilephone")));
    	session.setAttribute("customerSearchInfo", customerSearchInfo);
    	ArrayList<CustomerDTO> customers = customerBO.searchCustomer(customerSearchInfo);
        request.setAttribute("customers", customers);
        RequestDispatcher dispatcher 
                = request.getRequestDispatcher
		("./WEB-INF/jsp/CustomerPage/SearchCustomerPage.jsp");
        dispatcher.forward(request, response);
        
	}

}
