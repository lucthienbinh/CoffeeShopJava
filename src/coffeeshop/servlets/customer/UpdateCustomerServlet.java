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
import coffeeshop.bo.CustomerBO;
import coffeeshop.dto.CustomerDTO;

/**
 * Servlet implementation class UpdateCustomerServlet
 */
@WebServlet(name = "UpdateCustomerServlet", urlPatterns = {"/UpdateCustomerServlet"})
public class UpdateCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private String validateString(String stringCheck){
		if(stringCheck == null) {
			return "";
		}
		return stringCheck.trim();
    }
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		// Get context for DI CustomerBO and session in request
		HttpSession session = request.getSession(); 
		ServletContext context =  request.getServletContext();
        UserBO userBO = new UserBO(context);
        if (userBO.authorizationUser(session, 1) == false)
        {
        	response.sendRedirect("./GoLoginServlet");
	    	return;
        }        
        
        CustomerDTO customer = new CustomerDTO();
        CustomerBO customerBO = new CustomerBO(context);
		boolean updateResult = false;

		customer.setId(Integer.parseInt((request.getParameter("id"))));
        customer.setName(this.validateString(request.getParameter("name")));
        customer.setEmail(this.validateString(request.getParameter("email")));
        customer.setAddress(this.validateString(request.getParameter("address")));
        customer.setMobilephone((this.validateString(request.getParameter("mobilephone"))));
		
		updateResult = customerBO.updateCustomer(customer);
		String updateMessage = "Your information has been updated!";
		if (updateResult == false){
			updateMessage = "Server error. Please try again later";
        }
        
		customer = customerBO.getCustomer(this.validateString(request.getParameter("email")));
        request.setAttribute("customer", customer);
        request.setAttribute("updateResult", updateResult);
        request.setAttribute("updateMessage", updateMessage);
        RequestDispatcher dispatcher 
        = request.getRequestDispatcher("./WEB-INF/jsp/CustomerPage/ViewCustomerPage.jsp");
    	dispatcher.forward(request, response);
	}


}
