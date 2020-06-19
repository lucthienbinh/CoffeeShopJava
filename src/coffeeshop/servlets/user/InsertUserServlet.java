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
 * Servlet implementation class InsertUserServlet
 */
@WebServlet(name = "InsertUserServlet", urlPatterns = {"/InsertUserServlet"})
public class InsertUserServlet extends HttpServlet {
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
        // Get parameter in request
        boolean checkRequireString = true; 
        if (this.validateString(request.getParameter("email")).isEmpty())
        	checkRequireString = false;
        if (this.validateString(request.getParameter("password")).isEmpty())
        	checkRequireString = false;
        boolean createResult = false;
		String createMessage = "Invalid request!";
        if (checkRequireString == true)
        {
        	user.setEmail(this.validateString(request.getParameter("email")));
			user.setPassword(this.validateString(request.getParameter("password")));
			user.setFirstname(this.validateString(request.getParameter("firstname")));
			user.setLastname(this.validateString(request.getParameter("lastname")));
			user.setAddress(this.validateString(request.getParameter("address")));
			user.setSex(this.validateString(request.getParameter("sex")));
			user.setMobilephone(this.validateString(request.getParameter("mobilephone")));
			user.setGroupid(2);
			createResult = userBO.createUser(user);
			createMessage = "New user has been created";
			if (createResult == false)
			{
				createMessage = "Server error. Please try again later";
				user = userBO.getUser(request.getParameter("email"));
				if(user != null)
				{
					createMessage = "Email exited in system";
				}
			}
        }
        request.setAttribute("createResult", createResult);
        request.setAttribute("createMessage", createMessage);
        RequestDispatcher dispatcher 
        = request.getRequestDispatcher("./WEB-INF/jsp/UserPage/CreateUserPage.jsp");
    	dispatcher.forward(request, response);
	}
}
