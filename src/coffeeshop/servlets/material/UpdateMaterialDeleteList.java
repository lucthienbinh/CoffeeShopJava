package coffeeshop.servlets.material;

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
import coffeeshop.bo.MaterialBO;
import coffeeshop.dto.MaterialDTO;

/**
 * Servlet implementation class AddToMaterialDeleteList
 */
@WebServlet(name = "UpdateMaterialDeleteList", urlPatterns = {"/UpdateMaterialDeleteList"})
public class UpdateMaterialDeleteList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String validateString(String stringCheck){
		if( !(stringCheck instanceof String) || stringCheck == null){
			return "";
		}
		
		return stringCheck.trim();
	}
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {   
		response.setContentType("text/html");
		// Get context for DI MaterialBO and session in request
		HttpSession session = request.getSession(); 
		ServletContext context =  request.getServletContext();
        UserBO userBO = new UserBO(context);
        if (userBO.authorizationUser(session, 1) == false){
        	response.sendRedirect("./GoLoginServlet");
	    	return;
		}
		             
		int currentId = Integer.parseInt(this.validateString(request.getParameter("id")));
        // Update Material delete list
        ArrayList<MaterialDTO> materialDeleteList = (ArrayList) session.getAttribute("materialDeleteList");
        if (materialDeleteList != null){
        	session.removeAttribute("materialDeleteList");
		}
		
        materialDeleteList = MaterialBO.updateMaterialDeleteList(materialDeleteList, currentId);
		
		//set session
    	session.setAttribute("materialDeleteList", materialDeleteList);
        // Get material list
    	MaterialDTO materialSearchInfo = (MaterialDTO) session.getAttribute("materialSearchInfo");
    	ArrayList<MaterialDTO> materials = MaterialBO.searchMaterial(materialSearchInfo);
    	request.setAttribute("materials", materials);
        RequestDispatcher dispatcher 
        = request.getRequestDispatcher
		("./WEB-INF/jsp/MaterialPage/SearchMaterialPage.jsp");
		dispatcher.forward(request, response);
	}
}
