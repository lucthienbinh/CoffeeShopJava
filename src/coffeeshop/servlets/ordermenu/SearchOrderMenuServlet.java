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
import coffeeshop.dto.UserDTO;
import coffeeshop.bo.OrderMenuBO;
import coffeeshop.dto.OrderMenuDTO;

/**
 * Servlet implementation class SearchOrderMenuServlet
 */
@WebServlet(name = "SearchOrderMenuServlet", urlPatterns = {"/SearchOrderMenuServlet"})
public class SearchOrderMenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String validateString(String stringCheck){
		if(stringCheck == null) {
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

        OrderMenuDTO orderMenuSearchInfo = new OrderMenuDTO();
		orderMenuSearchInfo.setName(this.validateString(request.getParameter("name")));
    	session.setAttribute("orderMenuSearchInfo", orderMenuSearchInfo);
        ArrayList<OrderMenuDTO> orderMenus = OrderMenuBO.searchOrderMenu(orderMenuSearchInfo);
        
        request.setAttribute("orderMenus", orderMenus);
        RequestDispatcher dispatcher 
                = request.getRequestDispatcher
        ("./WEB-INF/jsp/OrderMenuPage/SearchOrderMenuPage.jsp");
        dispatcher.forward(request, response);
	}
}
