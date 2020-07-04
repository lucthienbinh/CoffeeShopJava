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

/**
 * Servlet implementation class UpdateOrderingBillDeleteList
 */
@WebServlet(name = "UpdateOrderingBillDeleteList", urlPatterns = {"/UpdateOrderingBillDeleteList"})
public class UpdateOrderingBillDeleteList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private String validateString(String stringCheck){
		if(stringCheck == null){
			return "";
		}
		return stringCheck.trim();
	}
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {   
		response.setContentType("text/html");
		// Get context for DI OrderMenuBO and session in request
		HttpSession session = request.getSession(); 
		ServletContext context =  request.getServletContext();
        UserBO userBO = new UserBO(context);
        OrderingBillBO orderingBillBO = new OrderingBillBO(context);
        if (userBO.authorizationUser(session, 1) == false){
        	response.sendRedirect("./GoLoginServlet");
	    	return;
		}
		             
		int currentId = Integer.parseInt(this.validateString(request.getParameter("id")));
        // Update OrderMenu delete list
        ArrayList<OrderingBillDTO> orderingBillDeleteList = (ArrayList) session.getAttribute("orderingBillDeleteList");
        if (orderingBillDeleteList != null){
        	session.removeAttribute("orderingBillDeleteList");
		}
		
        orderingBillDeleteList = orderingBillBO.updateOrderingBillDeleteList(orderingBillDeleteList, currentId);
		
		//set session
    	session.setAttribute("orderingBillDeleteList", orderingBillDeleteList);
        // Get orderingBill list
    	OrderingBillDTO orderingBillSearchInfo = (OrderingBillDTO) session.getAttribute("orderingBillSearchInfo");
        ArrayList<OrderingBillDTO> orderingBills = orderingBillBO.searchOrderingBill(orderingBillSearchInfo);
        request.setAttribute("orderingBills", orderingBills);
        RequestDispatcher dispatcher 
                = request.getRequestDispatcher
        ("./WEB-INF/jsp/OrderingBillPage/SearchOrderingBillPage.jsp");
        dispatcher.forward(request, response);
	}

}
