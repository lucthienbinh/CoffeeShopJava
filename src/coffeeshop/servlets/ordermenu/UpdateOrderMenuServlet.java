package coffeeshop.servlets.ordermenu;

import java.io.IOException;

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
 * Servlet implementation class UpdateOrderMenuServlet
 */
@WebServlet(name = "UpdateOrderMenuServlet", urlPatterns = {"/UpdateOrderMenuServlet"})
public class UpdateOrderMenuServlet extends HttpServlet {
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
        if (userBO.authorizationUser(session, 1) == false)
        {
        	response.sendRedirect("./GoLoginServlet");
	    	return;
        }        
        OrderMenuDTO orderMenu = new OrderMenuDTO();
		boolean updateResult = false;

        orderMenu.setId(Integer.parseInt(this.validateString(request.getParameter("id"))));
		orderMenu.setName(this.validateString(request.getParameter("name")));
		orderMenu.setPrice(Integer.parseInt(this.validateString(request.getParameter("price"))));
		
		updateResult = OrderMenuBO.updateOrderMenu(orderMenu);
		String updateMessage = "Your information has been updated!";
		if (updateResult == false){
			updateMessage = "Server error. Please try again later";
        }
        
		orderMenu = OrderMenuBO.getOrderMenu(Integer.parseInt(request.getParameter("id")));
        request.setAttribute("orderMenu", orderMenu);
        request.setAttribute("updateResult", updateResult);
        request.setAttribute("updateMessage", updateMessage);
        RequestDispatcher dispatcher 
        = request.getRequestDispatcher("./WEB-INF/jsp/OrderMenuPage/ViewOrderMenuPage.jsp");
    	dispatcher.forward(request, response);
	}


}
