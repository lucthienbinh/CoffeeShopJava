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

import coffeeshop.bo.CustomerBO;
import coffeeshop.bo.OrderMenuBO;
import coffeeshop.bo.UserBO;
import coffeeshop.dto.CustomerDTO;
import coffeeshop.dto.OrderMenuDTO;
import coffeeshop.dto.OrderingBillDTO;
import coffeeshop.dto.UserDTO;

/**
 * Servlet implementation class GoCreateOrderingBillServlet
 */
@WebServlet(name = "GoCreateOrderingBillServlet", urlPatterns = {"/GoCreateOrderingBillServlet"})
public class GoCreateOrderingBillServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		// Get context for DI UserBO and session in request
		HttpSession session = request.getSession(); 
		ServletContext context =  request.getServletContext();
        UserBO userBO = new UserBO(context);
        CustomerBO customerBO = new CustomerBO(context);
        OrderMenuBO orderMenuBO = new OrderMenuBO(context);
        if (userBO.authorizationUser(session, 1) == false)
        {
        	response.sendRedirect("./GoLoginServlet");
	    	return;
        }    
        // Get customer info
        ArrayList<CustomerDTO> customers = customerBO.searchCustomer(null);
        request.setAttribute("customers", customers);
        // Get order menu info
        ArrayList<OrderMenuDTO> orderMenus = OrderMenuBO.searchOrderMenu(null);
        request.setAttribute("orderMenus", orderMenus);
        
        // Create orderingBillInfo with userID is current User
        OrderingBillDTO orderingBillInfo = new OrderingBillDTO();
        UserDTO user = userBO.getAuthorizationUser(session);
        orderingBillInfo.setUserId(user.getId());
        orderingBillInfo.setUserName(user.getFirstname()+" "+user.getLastname());
        orderingBillInfo.setTotalPrice(0);
        session.setAttribute("orderingBillInfo", orderingBillInfo);
        
		RequestDispatcher dispatcher 
		 = request.getRequestDispatcher
	 	 ("./WEB-INF/jsp/OrderingBillPage/CreateOrderingBillPage.jsp");
		 dispatcher.forward(request, response);
	}

}
