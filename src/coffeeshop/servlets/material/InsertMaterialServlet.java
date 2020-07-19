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

/**
 * Servlet implementation class InserMaterialServlet
 */
@WebServlet(name = "InsertMaterialServlet", urlPatterns = {"/InsertMaterialServlet"})
public class InsertMaterialServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String validateString(String stringCheck){
		if(stringCheck == null){
			return "";
		}
		return stringCheck.trim();
	}
	
	private int validateInteger(String integerCheck){
		if(integerCheck == null){
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
		MaterialDTO material = new MaterialDTO();

		if (userBO.authorizationUser(session, 1) == false){
        	response.sendRedirect("./GoLoginServlet");
	    	return;
		}
		
		// Get parameter in request
		boolean checkRequireString = true; 
		boolean createResult = false;
		String createMessage = "Invalid request!";
		
        if (this.validateString(request.getParameter("name")).isEmpty())
        	checkRequireString = false;
        if (this.validateString(request.getParameter("price")).isEmpty())
			checkRequireString = false;
        if (this.validateString(request.getParameter("remaining")).isEmpty())
			checkRequireString = false;
        if (this.validateString(request.getParameter("unit")).isEmpty())
			checkRequireString = false;
        
            if(checkRequireString == true){
        	material.setName(this.validateString(request.getParameter("name")));
            material.setPrice(this.validateInteger(request.getParameter("price")));
            material.setRemaining(this.validateInteger(request.getParameter("remaining")));
            material.setUnit(this.validateString(request.getParameter("unit")));
			createResult = materialBO.createMaterial(material);
			createMessage = "New material has been created";
			
			if(createResult == false){
				createMessage = "Server error. Please try again later";
				material = MaterialBO.getMaterial(Integer.parseInt(request.getParameter("id")));
				
				if(material != null){
					createMessage = "Material name existed in system";
				}
			}
		}
		
        request.setAttribute("createResult", createResult);
        request.setAttribute("createMessage", createMessage);
        RequestDispatcher dispatcher = request.getRequestDispatcher
 	    ("./WEB-INF/jsp/MaterialPage/CreateMaterialPage.jsp");
    	dispatcher.forward(request, response);
	}

}
