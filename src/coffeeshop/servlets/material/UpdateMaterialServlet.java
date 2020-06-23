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

import coffeeshop.bo.MaterialBO;
import coffeeshop.bo.UserBO;
import coffeeshop.dto.MaterialDTO;
import coffeeshop.dto.UserDTO;

/**
 * Servlet implementation class UpdateMaterialServlet
 */
@WebServlet(name = "UpdateMaterialServlet", urlPatterns = {"/UpdateMaterialServlet"})
public class UpdateMaterialServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private String validateString(String stringCheck)
	{
		if(stringCheck == null) 
		{
			return "";
		}
		return stringCheck.trim();
	}
	
	private int validateInteger(String integerCheck)
	{
		if(integerCheck == null) 
		{
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
        MaterialBO materialBO = new MaterialBO(context);
        if (userBO.authorizationUser(session, 1) == false)
        {
        	response.sendRedirect("./GoLoginServlet");
	    	return;
        }        
        MaterialDTO material = new MaterialDTO();
        boolean updateResult = false;
        material.setId(this.validateInteger(request.getParameter("id")));
        material.setName(this.validateString(request.getParameter("name")));
        material.setPrice(this.validateInteger(request.getParameter("price")));
        material.setRemaining(this.validateInteger(request.getParameter("remaining")));
        material.setUnit(this.validateString(request.getParameter("unit")));
		
		updateResult = materialBO.updateMaterial(material);
		String updateMessage = "Your information has been updated!";
		if (updateResult == false)
		{
			updateMessage = "Server error. Please try again later";
		}
		material = materialBO.getMaterial(request.getParameter("name"));
        request.setAttribute("material", material);
        request.setAttribute("updateResult", updateResult);
        request.setAttribute("updateMessage", updateMessage);
        RequestDispatcher dispatcher = request.getRequestDispatcher
		("./WEB-INF/jsp/MaterialPage/ViewMaterialPage.jsp");
    	dispatcher.forward(request, response);
	}

}
