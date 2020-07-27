package coffeeshop.servlets.material;

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
import coffeeshop.bo.MaterialBO;
import coffeeshop.dto.MaterialDTO;

/**
 * Servlet implementation class UpdateMaterialServlet
 */
@WebServlet(name = "UpdateMaterialServlet", urlPatterns = {"/UpdateMaterialServlet"})
public class UpdateMaterialServlet extends HttpServlet {
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
        if (userBO.authorizationUser(session, 1) == false)
        {
        	response.sendRedirect("./GoLoginServlet");
	    	return;
        }        
        MaterialDTO material = new MaterialDTO();
		boolean updateResult = false;

        material.setId(Integer.parseInt(this.validateString(request.getParameter("id"))));
		material.setName(this.validateString(request.getParameter("name")));
		material.setPrice(Integer.parseInt(this.validateString(request.getParameter("price"))));
		material.setRemaining(Integer.parseInt(this.validateString(request.getParameter("remaining"))));
		material.setUnit(this.validateString(request.getParameter("unit")));

		updateResult = MaterialBO.updateMaterial(material);
		String updateMessage = "Your information has been updated!";
		if (updateResult == false){
			updateMessage = "Server error. Please try again later";
        }
        
		material = MaterialBO.getMaterial(Integer.parseInt(request.getParameter("id")));
        request.setAttribute("material", material);
        request.setAttribute("updateResult", updateResult);
        request.setAttribute("updateMessage", updateMessage);
        RequestDispatcher dispatcher 
        = request.getRequestDispatcher("./WEB-INF/jsp/MaterialPage/ViewMaterialPage.jsp");
    	dispatcher.forward(request, response);
	}


}
