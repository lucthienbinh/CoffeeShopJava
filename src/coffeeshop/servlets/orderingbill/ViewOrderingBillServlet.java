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
import coffeeshop.bo.OrderingBillDetailBO;
import coffeeshop.bo.UserBO;
import coffeeshop.dto.OrderingBillDTO;
import coffeeshop.dto.OrderingBillDetailDTO;

/**
 * Servlet implementation class ViewOrderingBillServlet
 */
@WebServlet(name = "ViewOrderingBillServlet", urlPatterns = {"/ViewOrderingBillServlet"})
public class ViewOrderingBillServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private int validateInteger(String integerCheck){
		if(integerCheck == null){
			return 0;
		}
		return Integer.parseInt(integerCheck);
	}
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		// Get context for DI OrderMenuBO and session in request
		HttpSession session = request.getSession(); 
		ServletContext context =  request.getServletContext();
        UserBO userBO = new UserBO(context);
        OrderingBillBO orderingBillBO = new OrderingBillBO(context);
        OrderingBillDetailBO orderingBillDetailBO = new OrderingBillDetailBO(context);
        if (userBO.authorizationUser(session, 1) == false){
        	response.sendRedirect("./GoLoginServlet");
	    	return;
        }      
        OrderingBillDTO orderingBill = orderingBillBO.getOrderingBill(this.validateInteger(request.getParameter("id")));
        request.setAttribute("orderingBill", orderingBill);
        ArrayList<OrderingBillDetailDTO> orderingBillDetailList = orderingBillDetailBO.getOrderingBillDetails(orderingBill.getId());
        request.setAttribute("orderingBillDetailList", orderingBillDetailList);
        RequestDispatcher dispatcher 
        = request.getRequestDispatcher
		("./WEB-INF/jsp/OrderingBillPage/ViewOrderingBillPage.jsp");
        dispatcher.forward(request, response);
	}
}
