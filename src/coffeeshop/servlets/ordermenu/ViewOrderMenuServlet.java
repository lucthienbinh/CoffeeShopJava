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
import coffeeshop.dto.UserDTO;
import coffeeshop.bo.OrderMenuBO;
import coffeeshop.dto.OrderMenuDTO;

/**
 * Servlet implementation class ViewOrderMenuServlet
 */
@WebServlet(name = "ViewOrderMenuServlet", urlPatterns = {"/ViewOrderMenuServlet"})
public class ViewOrderMenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
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
        
        OrderMenuDTO orderMenu = OrderMenuBO.getOrderMenu(Integer.parseInt(request.getParameter("id")));
        request.setAttribute("orderMenu", orderMenu);

        RequestDispatcher dispatcher 
        = request.getRequestDispatcher
        ("./WEB-INF/jsp/OrderMenuPage/ViewOrderMenuPage.jsp");
        dispatcher.forward(request, response);
	}
}
