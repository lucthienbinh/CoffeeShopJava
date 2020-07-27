package coffeeshop.servlets.orderingbill;

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

import coffeeshop.bo.OrderingBillBO;
import coffeeshop.bo.UserBO;
import coffeeshop.dto.OrderingBillDTO;
import coffeeshop.dto.UserDTO;

/**
 * Servlet implementation class SearchOrderingBillServlet
 */
@WebServlet(name = "SearchOrderingBillServlet", urlPatterns = {"/SearchOrderingBillServlet"})
public class SearchOrderingBillServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String validateString(String stringCheck)
	{
		if(stringCheck == null) 
		{
			return "";
		}
		return stringCheck.trim();
	}
	
	private int validateInteger(String integerCheck)
	{
		if(integerCheck == null || integerCheck.equals(""))
		{
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
        OrderingBillBO orderingBillBO = new OrderingBillBO(context);
        if (userBO.authorizationUser(session, 2) == false)
        {
        	response.sendRedirect("./GoLoginServlet");
	    	return;
        }    
        // Authorization User with role
        UserDTO user = userBO.getAuthorizationUser(session);
        if (user.getGroupid() == 1)
        	request.setAttribute("isAdmin", true);
        else
        	request.setAttribute("isAdmin", false);
        
        // Set new empty search info 
 		OrderingBillDTO orderingBillSearchInfo = new OrderingBillDTO();
 		orderingBillSearchInfo.setCustomerName(this.validateString(request.getParameter("customerName")));
 		orderingBillSearchInfo.setUserName(this.validateString(request.getParameter("userName")));
 		orderingBillSearchInfo.setTotalPrice(this.validateInteger(request.getParameter("totalPrice")));
 		session.setAttribute("orderingBillSearchInfo", orderingBillSearchInfo);
    	// Get user info
        ArrayList<OrderingBillDTO> orderingBills = orderingBillBO.searchOrderingBill(orderingBillSearchInfo);
        request.setAttribute("orderingBills", orderingBills);
        RequestDispatcher dispatcher 
                = request.getRequestDispatcher
        ("./WEB-INF/jsp/OrderingBillPage/SearchOrderingBillPage.jsp");
        dispatcher.forward(request, response);
        
	}
}
