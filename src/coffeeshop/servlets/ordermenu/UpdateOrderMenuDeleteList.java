package coffeeshop.servlets.ordermenu;

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


import coffeeshop.bo.UserBO;
import coffeeshop.bo.OrderMenuBO;
import coffeeshop.dto.OrderMenuDTO;

/**
 * Servlet implementation class AddToOrderMenuDeleteList
 */
@WebServlet(name = "UpdateOrderMenuDeleteList", urlPatterns = {"/UpdateOrderMenuDeleteList"})
public class UpdateOrderMenuDeleteList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String validateString(String stringCheck){
		if( !(stringCheck instanceof String) || stringCheck == null){
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
        if (userBO.authorizationUser(session, 1) == false){
        	response.sendRedirect("./GoLoginServlet");
	    	return;
		}
		             
		int currentId = Integer.parseInt(this.validateString(request.getParameter("id")));
        // Update OrderMenu delete list
        ArrayList<OrderMenuDTO> orderMenuDeleteList = (ArrayList) session.getAttribute("orderMenuDeleteList");
        if (orderMenuDeleteList != null){
        	session.removeAttribute("orderMenuDeleteList");
		}
		
        orderMenuDeleteList = OrderMenuBO.updateOrderMenuDeleteList(orderMenuDeleteList, currentId);
		
		//set session
    	session.setAttribute("orderMenuDeleteList", orderMenuDeleteList);
        // Get orderMenu list
    	OrderMenuDTO orderMenuSearchInfo = (OrderMenuDTO) session.getAttribute("orderMenuSearchInfo");
    	ArrayList<OrderMenuDTO> orderMenus = OrderMenuBO.searchOrderMenu(orderMenuSearchInfo);
    	request.setAttribute("orderMenus", orderMenus);
        RequestDispatcher dispatcher 
        = request.getRequestDispatcher
		("./WEB-INF/jsp/OrderMenuPage/SearchOrderMenuPage.jsp");
		dispatcher.forward(request, response);
	}
}
