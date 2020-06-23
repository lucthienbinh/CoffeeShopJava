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
 * Servlet implementation class UpdateCustomerDeleteList
 */
@WebServlet(name = "UpdateCustomerDeleteList", urlPatterns = {"/UpdateCustomerDeleteList"})
public class UpdateCustomerDeleteList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private String validateString(String stringCheck)
	{
		if( !(stringCheck instanceof String) || stringCheck == null) 
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
        String currentEmail = this.validateString(request.getParameter("email"));
        // Update user delete list
        ArrayList<CustomerDTO> customerDeleteList = (ArrayList) session.getAttribute("customerDeleteList");
        if (customerDeleteList != null)
        {
        	session.removeAttribute("customerDeleteList");
        }
        customerDeleteList = customerBO.updateCustomerDeleteList(customerDeleteList, currentEmail);
        //set session
    	session.setAttribute("customerDeleteList", customerDeleteList);
        // Get user list
    	CustomerDTO customerSearchInfo = (CustomerDTO) session.getAttribute("customerSearchInfo");
    	ArrayList<CustomerDTO> customers = customerBO.searchCustomer(customerSearchInfo);
    	request.setAttribute("customers", customers);
        RequestDispatcher dispatcher 
        = request.getRequestDispatcher
		("./WEB-INF/jsp/CustomerPage/SearchCustomerPage.jsp");
		dispatcher.forward(request, response);
	}

}
