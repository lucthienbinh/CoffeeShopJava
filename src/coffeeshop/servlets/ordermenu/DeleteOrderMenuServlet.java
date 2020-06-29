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
 * Servlet implementation class DeleteOrderMenuServlet
 */
@WebServlet(name = "DeleteOrderMenuServlet", urlPatterns = {"/DeleteOrderMenuServlet"})
public class DeleteOrderMenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		// Get context for DI UserBO and session in request
		HttpSession session = request.getSession(); 
		ServletContext context =  request.getServletContext();
        UserBO userBO = new UserBO(context);
        if (userBO.authorizationUser(session, 1) == false){
        	response.sendRedirect("./GoLoginServlet");
	    	return;
        }  

        ArrayList<OrderMenuDTO> orderMenuDeleteList = (ArrayList) session.getAttribute("orderMenuDeleteList");
        boolean deleteResult = false;
        String deleteMessage = "";

        if(orderMenuDeleteList == null){
        	deleteMessage = "Select order menu to delete first!";
        }else{
        	session.removeAttribute("orderMenuDeleteList");
        	deleteResult = OrderMenuBO.deleteOrderMenuInList(orderMenuDeleteList);
            
            if(deleteResult == true){
            	deleteMessage = orderMenuDeleteList.size() + (orderMenuDeleteList.size() > 1 ? " order menu have" : " order menu has") + " been deleted!";
            }else{
            	deleteMessage = "Failed when deleting order menu!";
            }
        }

        request.setAttribute("deleteResult", deleteResult);
        request.setAttribute("deleteMessage", deleteMessage);
		OrderMenuDTO orderMenuSearchInfo = (OrderMenuDTO) session.getAttribute("orderMenuSearchInfo");
		ArrayList<OrderMenuDTO> orderMenus = OrderMenuBO.searchOrderMenu(orderMenuSearchInfo);
		request.setAttribute("orderMenus", orderMenus);
	    RequestDispatcher dispatcher 
	    = request.getRequestDispatcher
		("./WEB-INF/jsp/OrderMenuPage/SearchOrderMenuPage.jsp");
		dispatcher.forward(request, response);
	}
}
