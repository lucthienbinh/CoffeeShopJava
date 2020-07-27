package coffeeshop.servlets.materialbill;

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

import coffeeshop.bo.MaterialBillBO;
import coffeeshop.bo.UserBO;
import coffeeshop.dto.MaterialBilllDTO;

/**
 * Servlet implementation class SearchMaterialBillServlet
 */
@WebServlet(name = "SearchMaterialBillServlet", urlPatterns = {"/SearchMaterialBillServlet"})
public class SearchMaterialBillServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String validateString(String stringCheck){
		if(stringCheck == null){
			return "";
		}
		return stringCheck.trim();
	}
	
	private int validateInteger(String integerCheck){
		if(integerCheck == null || integerCheck.equals("")){
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
        MaterialBillBO materialBillBO = new MaterialBillBO(context);
        if (userBO.authorizationUser(session, 1) == false){
        	response.sendRedirect("./GoLoginServlet");
	    	return;
        } 

        // Set new empty search info 
 		MaterialBilllDTO materialBillSearchInfo = new MaterialBilllDTO();
 		materialBillSearchInfo.setUserName(this.validateString(request.getParameter("userName")));
 		materialBillSearchInfo.setTotalPrice(this.validateInteger(request.getParameter("totalPrice")));
 		session.setAttribute("materialBillSearchInfo", materialBillSearchInfo);
        
         // Get user info
        ArrayList<MaterialBilllDTO> materialBills = materialBillBO.searchMaterialBill(materialBillSearchInfo);
        request.setAttribute("materialBills", materialBills);
        
        RequestDispatcher dispatcher 
                = request.getRequestDispatcher
        ("./WEB-INF/jsp/MaterialBillPage/SearchMaterialBillPage.jsp");
        dispatcher.forward(request, response);
        
	}
}
