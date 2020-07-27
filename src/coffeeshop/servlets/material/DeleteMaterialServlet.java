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
 * Servlet implementation class DeleteMaterialServlet
 */
@WebServlet(name = "DeleteMaterialServlet", urlPatterns = {"/DeleteMaterialServlet"})
public class DeleteMaterialServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		// Get context for DI UserBO and session in request
		HttpSession session = request.getSession(); 
		ServletContext context =  request.getServletContext();
        UserBO userBO = new UserBO(context);
        if (userBO.authorizationUser(session, 1) == false){
        	response.sendRedirect("./GoLoginServlet");
	    	return;
        }  

        ArrayList<MaterialDTO> materialDeleteList = (ArrayList) session.getAttribute("materialDeleteList");
        boolean deleteResult = false;
        String deleteMessage = "";

        if(materialDeleteList == null){
        	deleteMessage = "Select material to delete first!";
        }else{
        	session.removeAttribute("materialDeleteList");
        	deleteResult = MaterialBO.deleteMaterialInList(materialDeleteList);
            
            if(deleteResult == true){
            	deleteMessage = materialDeleteList.size() + (materialDeleteList.size() > 1 ? " material have" : " material has") + " been deleted!";
            }else{
            	deleteMessage = "Failed when deleting material!";
            }
        }

        request.setAttribute("deleteResult", deleteResult);
        request.setAttribute("deleteMessage", deleteMessage);
		MaterialDTO materialSearchInfo = (MaterialDTO) session.getAttribute("materialSearchInfo");
		ArrayList<MaterialDTO> materials = MaterialBO.searchMaterial(materialSearchInfo);
		request.setAttribute("materials", materials);
	    RequestDispatcher dispatcher 
	    = request.getRequestDispatcher
		("./WEB-INF/jsp/MaterialPage/SearchMaterialPage.jsp");
		dispatcher.forward(request, response);
	}
}
