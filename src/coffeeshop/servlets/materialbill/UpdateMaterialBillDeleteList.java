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
 * Servlet implementation class UpdateMaterialBillDeleteList
 */
@WebServlet(name = "UpdateMaterialBillDeleteList", urlPatterns = {"/UpdateMaterialBillDeleteList"})
public class UpdateMaterialBillDeleteList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private String validateString(String stringCheck){
		if(stringCheck == null){
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
        MaterialBillBO materialBillBO = new MaterialBillBO(context);
        if (userBO.authorizationUser(session, 1) == false){
        	response.sendRedirect("./GoLoginServlet");
	    	return;
		}
		             
		int currentId = Integer.parseInt(this.validateString(request.getParameter("id")));
        // Update OrderMenu delete list
        ArrayList<MaterialBilllDTO> materialBillDeleteList = (ArrayList) session.getAttribute("materialBillDeleteList");
        if (materialBillDeleteList != null){
        	session.removeAttribute("materialBillDeleteList");
		}
		
        materialBillDeleteList = materialBillBO.updateMaterialBillDeleteList(materialBillDeleteList, currentId);
		
		//set session
    	session.setAttribute("materialBillDeleteList", materialBillDeleteList);
        // Get materialBill list
    	MaterialBilllDTO materialBillSearchInfo = (MaterialBilllDTO) session.getAttribute("materialBillSearchInfo");
        ArrayList<MaterialBilllDTO> materialBills = materialBillBO.searchMaterialBill(materialBillSearchInfo);
        request.setAttribute("materialBills", materialBills);
        RequestDispatcher dispatcher 
                = request.getRequestDispatcher
        ("./WEB-INF/jsp/MaterialBillPage/SearchMaterialBillPage.jsp");
        dispatcher.forward(request, response);
	}

}
