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
import coffeeshop.bo.OrderingBillBO;
import coffeeshop.bo.OrderingBillDetailBO;
import coffeeshop.bo.UserBO;
import coffeeshop.dto.CustomerDTO;
import coffeeshop.dto.OrderMenuDTO;
import coffeeshop.dto.OrderingBillDTO;
import coffeeshop.dto.OrderingBillDetailDTO;

/**
 * Servlet implementation class InsertOrderingBillServlet
 */
@WebServlet(name = "InsertOrderingBillServlet", urlPatterns = {"/InsertOrderingBillServlet"})
public class InsertOrderingBillServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		// Get context for DI UserBO and session in request
		HttpSession session = request.getSession(); 
		ServletContext context =  request.getServletContext();
        UserBO userBO = new UserBO(context);
        CustomerBO customerBO = new CustomerBO(context);
        OrderingBillBO orderingBillBO = new OrderingBillBO(context);
        OrderingBillDetailBO orderingBillDetailBO = new OrderingBillDetailBO(context);
		if (userBO.authorizationUser(session, 1) == false){
        	response.sendRedirect("./GoLoginServlet");
	    	return;
		}
		boolean createResult = false;
		String createMessage = "Server error. Please try again later";
		// Get info from session then remove it
		OrderingBillDTO orderingBillInfo = (OrderingBillDTO) session.getAttribute("orderingBillInfo");
		ArrayList<OrderingBillDetailDTO> orderingBillDetailList = (ArrayList) session.getAttribute("orderingBillDetailList");
		if (orderingBillDetailList != null)
		{
			session.removeAttribute("orderingBillInfo");
			session.removeAttribute("orderingBillDetailList");
			orderingBillInfo.setTotalPrice(0);
			orderingBillInfo.setCustomerId(0);
			orderingBillInfo.setCustomerName(null);
	        session.setAttribute("orderingBillInfo", orderingBillInfo);
			int lastId = orderingBillBO.createOrderingBill(orderingBillInfo);
			if (lastId != 0)
			{
				createResult = orderingBillDetailBO.createOrderingBillDetail(orderingBillDetailList, lastId);
				if(createResult == true)
				{
					createMessage = "New ordering bill has been created";
				}	
			}
		}
		else
		{
			createMessage = "Please select drinks";
		}
		
		// Get customer info
        ArrayList<CustomerDTO> customers = customerBO.searchCustomer(null);
        request.setAttribute("customers", customers);
        // Get order menu info
        ArrayList<OrderMenuDTO> orderMenus = OrderMenuBO.searchOrderMenu(null);
        request.setAttribute("orderMenus", orderMenus);
        
        request.setAttribute("createResult", createResult);
        request.setAttribute("createMessage", createMessage);
        RequestDispatcher dispatcher = request.getRequestDispatcher
		("./WEB-INF/jsp/OrderingBillPage/CreateOrderingBillPage.jsp");
    	dispatcher.forward(request, response);
	}
}
