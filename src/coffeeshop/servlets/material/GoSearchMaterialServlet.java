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

import coffeeshop.bo.MaterialBO;
import coffeeshop.bo.UserBO;
import coffeeshop.dto.MaterialDTO;


/**
 * Servlet implementation class GoSearchMaterialServlet
 */
@WebServlet(name = "GoSearchMaterialServlet", urlPatterns = {"/GoSearchMaterialServlet"})
public class GoSearchMaterialServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		// Get context for DI UserBO and session in request
		HttpSession session = request.getSession(); 
		ServletContext context =  request.getServletContext();
		UserBO userBO = new UserBO(context);
		MaterialBO materialBO = new MaterialBO(context);
		// MaterialDTO material = new MaterialDTO();
		
		if (userBO.authorizationUser(session, 1) == false){
        	response.sendRedirect("./GoLoginServlet");
	    	return;
		}  
		  
		// Remove old search info stored in session if exists
		MaterialDTO materialSearchInfo = (MaterialDTO) session.getAttribute("materialSearchInfo");
		if (materialSearchInfo != null){
			session.removeAttribute("materialSearchInfo");
		}
		
		// Set new empty search info 
		MaterialDTO newEmptyMaterialInfo = new MaterialDTO();
		newEmptyMaterialInfo.generateEmptyObjectForJSP();
		session.setAttribute("materialSearchInfo", newEmptyMaterialInfo);

		// Get material info
        ArrayList<MaterialDTO> materials = MaterialBO.searchMaterial(null);
        request.setAttribute("materials", materials);
        RequestDispatcher dispatcher 
                = request.getRequestDispatcher
        ("./WEB-INF/jsp/MaterialPage/SearchMaterialPage.jsp");
        dispatcher.forward(request, response);
	}
}