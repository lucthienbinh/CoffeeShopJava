package coffeeshop.servlets.user;

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

/**
 * Servlet implementation class UpdateUserPasswordServlet
 */
@WebServlet(name = "UpdateUserPasswordServlet", urlPatterns = {"/UpdateUserPasswordServlet"})
public class UpdateUserPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private String validateString(String stringCheck)
	{
		if(stringCheck == null) 
		{
			return "";
		}
		return stringCheck.trim();
	}
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		// Get context for DI UserBO and session in request
		HttpSession session = request.getSession(); 
		ServletContext context =  request.getServletContext();
        UserBO userBO = new UserBO(context);
        if (userBO.authorizationUser(session, 1) == false)
        {
        	response.sendRedirect("./GoLoginServlet");
	    	return;
        }        
        
        UserDTO user = new UserDTO();
        user.setId(Integer.parseInt(this.validateString(request.getParameter("id"))));
        String currentPassword = this.validateString(request.getParameter("password"));
        
        boolean updateResult = false;
        String updateMessage = "";
        if(currentPassword.isEmpty()) {
        	updateMessage = "Input your new password then save";
        } else {
        	
	        user.setPassword(currentPassword);
			updateResult = userBO.updateUserPassword(user);
			updateMessage = "Your information has been updated!";
			if (updateResult == false)
			{
				updateMessage = "Server error. Please try again later";
			}
        }
		user = userBO.getUser(request.getParameter("email"));
        request.setAttribute("user", user);
        request.setAttribute("updateResult", updateResult);
        request.setAttribute("updateMessage", updateMessage);
        RequestDispatcher dispatcher 
        = request.getRequestDispatcher("./WEB-INF/jsp/UserPage/ChangePasswordPage.jsp");
    	dispatcher.forward(request, response);
	}


}
