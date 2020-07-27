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
import coffeeshop.bo.MaterialBillDetailBO;
import coffeeshop.bo.UserBO;
import coffeeshop.dto.MaterialBilllDTO;
import coffeeshop.dto.MaterialBillDetailDTO;

/**
 * Servlet implementation class ViewMaterialBillServlet
 */
@WebServlet(name = "ViewMaterialBillServlet", urlPatterns = {"/ViewMaterialBillServlet"})
public class ViewMaterialBillServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private int validateInteger(String integerCheck){
		if(integerCheck == null){
			return 0;
		}
		return Integer.parseInt(integerCheck);
	}
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		// Get context for DI OrderMenuBO and session in request
		HttpSession session = request.getSession(); 
		ServletContext context =  request.getServletContext();
        UserBO userBO = new UserBO(context);
        MaterialBillBO materialBillBO = new MaterialBillBO(context);
        MaterialBillDetailBO materialBillDetailBO = new MaterialBillDetailBO(context);
        
        if (userBO.authorizationUser(session, 1) == false){
        	response.sendRedirect("./GoLoginServlet");
	    	return;
        }      
        
        MaterialBilllDTO materialBill = materialBillBO.getMaterialBill(this.validateInteger(request.getParameter("id")));
        request.setAttribute("materialBill", materialBill);
        ArrayList<MaterialBillDetailDTO> materialBillDetailList = materialBillDetailBO.getMaterialBillDetails(materialBill.getId());
        request.setAttribute("materialBillDetailList", materialBillDetailList);
        
        RequestDispatcher dispatcher 
        = request.getRequestDispatcher
		("./WEB-INF/jsp/MaterialBillPage/ViewMaterialBillPage.jsp");
        dispatcher.forward(request, response);
	}
}
