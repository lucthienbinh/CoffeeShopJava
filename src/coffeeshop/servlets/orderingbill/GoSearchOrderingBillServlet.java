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
 * Servlet implementation class GoSearchOrderingBillServlet
 */
@WebServlet(name = "GoSearchOrderingBillServlet", urlPatterns = {"/GoSearchOrderingBillServlet"})
public class GoSearchOrderingBillServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		// Get context for DI UserBO and session in request
		HttpSession session = request.getSession(); 
		ServletContext context =  request.getServletContext();
		UserBO userBO = new UserBO(context);
        OrderingBillBO orderingBillBO = new OrderingBillBO(context);
        if (userBO.authorizationUser(session, 1) == false)
        {
        	response.sendRedirect("./GoLoginServlet");
	    	return;
        }    
		// Remove old search info stored in session if exists
        OrderingBillDTO orderingBillSearchInfo = (OrderingBillDTO) session.getAttribute("orderingBillSearchInfo");
		if (orderingBillSearchInfo != null)
        {
			session.removeAttribute("orderingBillSearchInfo");
        }
		// Set new empty search info 
		OrderingBillDTO newEmptyOrderingBillInfo = new OrderingBillDTO();
		newEmptyOrderingBillInfo.generateEmptyObjectForJSP();
		session.setAttribute("orderingBillSearchInfo", newEmptyOrderingBillInfo);
		// Get user info
        ArrayList<OrderingBillDTO> orderingBills = orderingBillBO.searchOrderingBill(null);
        request.setAttribute("orderingBills", orderingBills);
        RequestDispatcher dispatcher 
                = request.getRequestDispatcher
        ("./WEB-INF/jsp/OrderingBillPage/SearchOrderingBillPage.jsp");
        dispatcher.forward(request, response);
	}
}
