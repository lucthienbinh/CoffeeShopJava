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
import coffeeshop.bo.OrderingBillDetailBO;
import coffeeshop.bo.UserBO;
import coffeeshop.dto.CustomerDTO;
import coffeeshop.dto.OrderMenuDTO;
import coffeeshop.dto.OrderingBillDTO;
import coffeeshop.dto.OrderingBillDetailDTO;

/**
 * Servlet implementation class UpdateOrderingBillServlet
 */
@WebServlet(name = "UpdateOrderingBillServlet", urlPatterns = {"/UpdateOrderingBillServlet"})
public class UpdateOrderingBillServlet extends HttpServlet {
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
        OrderingBillDetailBO orderingBillDetailBO = new OrderingBillDetailBO(context);
        CustomerBO customerBO = new CustomerBO(context);
        if (userBO.authorizationUser(session, 1) == false)
        {
        	response.sendRedirect("./GoLoginServlet");
	    	return;
        }
        OrderingBillDTO orderingBillInfo = (OrderingBillDTO) session.getAttribute("orderingBillInfo");
        session.removeAttribute("orderingBillInfo");
        // Update orderingBillDetail list
        if (!this.validateString(request.getParameter("addOrderMenuId")).isEmpty() || !this.validateString(request.getParameter("removeOrderMenuId")).isEmpty()) 
        {
        	ArrayList<OrderingBillDetailDTO> orderingBillDetailList = (ArrayList) session.getAttribute("orderingBillDetailList");
	        if (orderingBillDetailList != null)
	        {
	        	session.removeAttribute("orderingBillDetailList");
	        }
	        if(!this.validateString(request.getParameter("addOrderMenuId")).isEmpty())
	        {
	        	int orderMenuId = this.validateInteger(request.getParameter("addOrderMenuId"));
	        	orderingBillDetailList = orderingBillDetailBO.updateOrderBillDetailList(orderingBillDetailList, orderMenuId, true);
	        }
	        else
	        {
	        	int orderMenuId = this.validateInteger(request.getParameter("removeOrderMenuId"));
	        	orderingBillDetailList = orderingBillDetailBO.updateOrderBillDetailList(orderingBillDetailList, orderMenuId, false);
	        }
	        int newTotalPrice = orderingBillDetailBO.caculateTotalPrice(orderingBillDetailList);
	        orderingBillInfo.setTotalPrice(newTotalPrice);
	        //set session
	    	session.setAttribute("orderingBillDetailList", orderingBillDetailList);
        }
        // Update orderingBill information
        if (!this.validateString(request.getParameter("customerId")).isEmpty())
        {
        	int customerId = this.validateInteger(request.getParameter("customerId"));
        	String customerName = customerBO.getCustomerById(customerId).getName();
        	orderingBillInfo.setCustomerId(customerId);
        	orderingBillInfo.setCustomerName(customerName);
        }
        session.setAttribute("orderingBillInfo", orderingBillInfo);
        
        // Get customer info
        ArrayList<CustomerDTO> customers = customerBO.searchCustomer(null);
        request.setAttribute("customers", customers);
        // Get order menu info
        ArrayList<OrderMenuDTO> orderMenus = OrderMenuBO.searchOrderMenu(null);
        request.setAttribute("orderMenus", orderMenus);
        
        RequestDispatcher dispatcher 
        = request.getRequestDispatcher
		("./WEB-INF/jsp/OrderingBillPage/CreateOrderingBillPage.jsp");
		dispatcher.forward(request, response);
	}
}
