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

import coffeeshop.bo.CustomerBO;
import coffeeshop.bo.UserBO;
import coffeeshop.dto.CustomerDTO;

/**
 * Servlet implementation class InserCustomerServlet
 */
@WebServlet(name = "InsertCustomerServlet", urlPatterns = {"/InsertCustomerServlet"})
public class InsertCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String validateString(String stringCheck){
		if(stringCheck == null){
			return "";
		}
		return stringCheck.trim();
	}
	
	private int validateInteger(String integerCheck){
		if(integerCheck == null){
			return 0;
		}
		return Integer.parseInt(integerCheck);
	}
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		// Get context for DI UserBO and session in request
		HttpSession session = request.getSession(); 
		ServletContext context =  request.getServletContext();
        UserBO userBO = new UserBO(context);
        CustomerBO customerBO = new CustomerBO(context);
		CustomerDTO customer = new CustomerDTO();

		if (userBO.authorizationUser(session, 1) == false){
        	response.sendRedirect("./GoLoginServlet");
	    	return;
		}
		
		// Get parameter in request
		boolean checkRequireString = true; 
		boolean createResult = false;
		String createMessage = "Invalid request!";
		
        if (this.validateString(request.getParameter("name")).isEmpty())
            checkRequireString = false;
        if (this.validateString(request.getParameter("email")).isEmpty())
        	checkRequireString = false;
        if (this.validateString(request.getParameter("address")).isEmpty())
            checkRequireString = false;
        if (this.validateString(request.getParameter("mobilephone")).isEmpty())
            checkRequireString = false;
            
        if(checkRequireString == true){
            customer.setName(this.validateString(request.getParameter("name")));
            customer.setEmail(this.validateString(request.getParameter("email")));
            customer.setAddress(this.validateString(request.getParameter("address")));
            customer.setMobilephone((this.validateString(request.getParameter("mobilephone"))));
			createResult = customerBO.createCustomer(customer);
			createMessage = "New customer has been created";
			
			if(createResult == false){
				createMessage = "Server error. Please try again later";
				customer = customerBO.getCustomer(this.validateString(request.getParameter("email")));
				
				if(customer != null){
					createMessage = "Customer name existed in system";
				}
			}
		}
		
        request.setAttribute("createResult", createResult);
        request.setAttribute("createMessage", createMessage);
        RequestDispatcher dispatcher = request.getRequestDispatcher
 	    ("./WEB-INF/jsp/CustomerPage/CreateCustomerPage.jsp");
    	dispatcher.forward(request, response);
	}

}
