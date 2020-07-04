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

import coffeeshop.bo.OrderMenuBO;
import coffeeshop.bo.OrderingBillBO;
import coffeeshop.bo.UserBO;
import coffeeshop.dto.OrderMenuDTO;
import coffeeshop.dto.OrderingBillDTO;

/**
 * Servlet implementation class DeleteOrderingBillServlet
 */
@WebServlet(name = "DeleteOrderingBillServlet", urlPatterns = {"/DeleteOrderingBillServlet"})
public class DeleteOrderingBillServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		// Get context for DI UserBO and session in request
		HttpSession session = request.getSession(); 
		ServletContext context =  request.getServletContext();
        UserBO userBO = new UserBO(context);
        OrderingBillBO orderingBillBO = new OrderingBillBO(context);
        if (userBO.authorizationUser(session, 1) == false){
        	response.sendRedirect("./GoLoginServlet");
	    	return;
        }  

        ArrayList<OrderingBillDTO> orderingBillDeleteList = (ArrayList) session.getAttribute("orderingBillDeleteList");
        boolean deleteResult = false;
        String deleteMessage = "";

        if(orderingBillDeleteList == null){
        	deleteMessage = "Select ordering bill to delete first!";
        }else{
        	session.removeAttribute("orderingBillDeleteList");
        	deleteResult = orderingBillBO.deleteOrderingBillInList(orderingBillDeleteList);
            
            if(deleteResult == true){
            	deleteMessage = orderingBillDeleteList.size() + (orderingBillDeleteList.size() > 1 ? " ordering bills have" : " order menu has") + " been deleted!";
            }else{
            	deleteMessage = "Failed when deleting ordering bill!";
            }
        }

        request.setAttribute("deleteResult", deleteResult);
        request.setAttribute("deleteMessage", deleteMessage);
        // Get orderingBill list
    	OrderingBillDTO orderingBillSearchInfo = (OrderingBillDTO) session.getAttribute("orderingBillSearchInfo");
        ArrayList<OrderingBillDTO> orderingBills = orderingBillBO.searchOrderingBill(orderingBillSearchInfo);
        request.setAttribute("orderingBills", orderingBills);
        RequestDispatcher dispatcher = request.getRequestDispatcher
        ("./WEB-INF/jsp/OrderingBillPage/SearchOrderingBillPage.jsp");
        dispatcher.forward(request, response);
	}
}
