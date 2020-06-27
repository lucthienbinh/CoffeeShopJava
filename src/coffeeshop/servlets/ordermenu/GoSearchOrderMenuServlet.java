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

import coffeeshop.bo.OrderMenuBO;
import coffeeshop.bo.UserBO;
import coffeeshop.dto.OrderMenuDTO;


/**
 * Servlet implementation class GoSearchOrderMenuServlet
 */
@WebServlet(name = "GoSearchOrderMenuServlet", urlPatterns = {"/GoSearchOrderMenuServlet"})
public class GoSearchOrderMenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		// Get context for DI UserBO and session in request
		HttpSession session = request.getSession(); 
		ServletContext context =  request.getServletContext();
		UserBO userBO = new UserBO(context);
		OrderMenuBO orderMenuBO = new OrderMenuBO(context);
		// OrderMenuDTO orderMenu = new OrderMenuDTO();
		
		if (userBO.authorizationUser(session, 1) == false){
        	response.sendRedirect("./GoLoginServlet");
	    	return;
		}  
		  
		// Remove old search info stored in session if exists
		OrderMenuDTO orderMenuSearchInfo = (OrderMenuDTO) session.getAttribute("orderMenuSearchInfo");
		if (orderMenuSearchInfo != null){
			session.removeAttribute("orderMenuSearchInfo");
		}
		
		// Set new empty search info 
		OrderMenuDTO newEmptyOrderMenuInfo = new OrderMenuDTO();
		newEmptyOrderMenuInfo.generateEmptyObjectForJSP();
		session.setAttribute("orderMenuSearchInfo", newEmptyOrderMenuInfo);

		// Get orderMenu info
        ArrayList<OrderMenuDTO> orderMenus = orderMenuBO.searchOrderMenu(null);
        request.setAttribute("orderMenus", orderMenus);
        RequestDispatcher dispatcher 
                = request.getRequestDispatcher
        ("./WEB-INF/jsp/OrderMenuPage/SearchOrderMenuPage.jsp");
        dispatcher.forward(request, response);
	}
}