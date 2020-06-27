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

import coffeeshop.bo.OrderMenuBO;
import coffeeshop.bo.UserBO;
import coffeeshop.dto.OrderMenuDTO;

/**
 * Servlet implementation class InserOrderMenuServlet
 */
@WebServlet(name = "InsertOrderMenuServlet", urlPatterns = {"/InsertOrderMenuServlet"})
public class InsertOrderMenuServlet extends HttpServlet {
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
        OrderMenuBO orderMenuBO = new OrderMenuBO(context);
		OrderMenuDTO orderMenu = new OrderMenuDTO();

		if (userBO.authorizationUser(session, 1) == false){
        	response.sendRedirect("./GoLoginServlet");
	    	return;
		}
		
		// Get parameter in request
		boolean checkRequireString = true; 
		boolean createResult = false;
		String createMessage = "Invalid request!";
		
        if (this.validateString(request.getParameter("name")).isEmpty())
        	checkRequireString = false;
        if (this.validateString(request.getParameter("price")).isEmpty())
			checkRequireString = false;

        if(checkRequireString == true){
        	orderMenu.setName(this.validateString(request.getParameter("name")));
        	orderMenu.setPrice(this.validateInteger(request.getParameter("price")));
			createResult = orderMenuBO.createOrderMenu(orderMenu);
			createMessage = "New order menu has been created";
			
			if(createResult == false){
				createMessage = "Server error. Please try again later";
				orderMenu = OrderMenuBO.getOrderMenu(Integer.parseInt(request.getParameter("id")));
				
				if(orderMenu != null){
					createMessage = "Drinks name existed in system";
				}
			}
		}
		
        request.setAttribute("createResult", createResult);
        request.setAttribute("createMessage", createMessage);
        RequestDispatcher dispatcher = request.getRequestDispatcher
 	    ("./WEB-INF/jsp/OrderMenuPage/CreateOrderMenuPage.jsp");
    	dispatcher.forward(request, response);
	}

}
