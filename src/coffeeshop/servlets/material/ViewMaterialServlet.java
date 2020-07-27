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
import coffeeshop.dto.UserDTO;
import coffeeshop.bo.MaterialBO;
import coffeeshop.dto.MaterialDTO;

/**
 * Servlet implementation class ViewMaterialServlet
 */
@WebServlet(name = "ViewMaterialServlet", urlPatterns = {"/ViewMaterialServlet"})
public class ViewMaterialServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
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
        
        MaterialDTO material = MaterialBO.getMaterial(Integer.parseInt(request.getParameter("id")));
        request.setAttribute("material", material);

        RequestDispatcher dispatcher 
        = request.getRequestDispatcher
        ("./WEB-INF/jsp/MaterialPage/ViewMaterialPage.jsp");
        dispatcher.forward(request, response);
	}
}
