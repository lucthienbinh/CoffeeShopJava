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
 * Servlet implementation class SearchMaterialServlet
 */
@WebServlet(name = "SearchMaterialServlet", urlPatterns = {"/SearchMaterialServlet"})
public class SearchMaterialServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String validateString(String stringCheck){
		if(stringCheck == null) {
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

        MaterialDTO materialSearchInfo = new MaterialDTO();
		materialSearchInfo.setName(this.validateString(request.getParameter("name")));
    	session.setAttribute("materialSearchInfo", materialSearchInfo);
        ArrayList<MaterialDTO> materials = MaterialBO.searchMaterial(materialSearchInfo);
        
        request.setAttribute("materials", materials);
        RequestDispatcher dispatcher 
                = request.getRequestDispatcher
        ("./WEB-INF/jsp/MaterialPage/SearchMaterialPage.jsp");
        dispatcher.forward(request, response);
	}
}
