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
 * Servlet implementation class DeleteCustomerServlet
 */
@WebServlet(name = "DeleteCustomerServlet", urlPatterns = {"/DeleteCustomerServlet"})
public class DeleteCustomerServlet extends HttpServlet {
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
        ArrayList<CustomerDTO> customerDeleteList = (ArrayList) session.getAttribute("customerDeleteList");
        boolean deleteResult = false;
        String deleteMessage = "";
        if (customerDeleteList == null) 
        {
        	deleteMessage = "Select customer to delete first!";
        }
        else
        {
        	session.removeAttribute("customerDeleteList");
        	deleteResult = customerBO.deleteUserInList(customerDeleteList);
            if(deleteResult == true)
            {
            	deleteMessage = customerDeleteList.size() + (customerDeleteList.size() > 1 ? " customers have" : " customer has") + " been deleted!";
            }
            else
            {
            	deleteMessage = "Failed when delete customer!";
            }
        }
        request.setAttribute("deleteResult", deleteResult);
        request.setAttribute("deleteMessage", deleteMessage);
        CustomerDTO customerSearchInfo = (CustomerDTO) session.getAttribute("customerSearchInfo");
    	ArrayList<CustomerDTO> customers = customerBO.searchCustomer(customerSearchInfo);
    	request.setAttribute("customers", customers);
	    RequestDispatcher dispatcher 
	    = request.getRequestDispatcher
		("./WEB-INF/jsp/CustomerPage/SearchCustomerPage.jsp");
		dispatcher.forward(request, response);
	}

}
