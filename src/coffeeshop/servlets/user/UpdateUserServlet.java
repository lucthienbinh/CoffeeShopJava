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
 * Servlet implementation class UpdateUserServlet
 */
@WebServlet(name = "UpdateUserServlet", urlPatterns = {"/UpdateUserServlet"})
public class UpdateUserServlet extends HttpServlet {
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
        boolean updateResult = false;
        user.setId(Integer.parseInt(this.validateString(request.getParameter("id"))));
		user.setFirstname(this.validateString(request.getParameter("firstname")));
		user.setLastname(this.validateString(request.getParameter("lastname")));
		user.setAddress(this.validateString(request.getParameter("address")));
		user.setSex(this.validateString(request.getParameter("sex")));
		user.setMobilephone(this.validateString(request.getParameter("mobilephone")));
		
		updateResult = userBO.updateUser(user);
		String updateMessage = "Your information has been updated!";
		if (updateResult == false)
		{
			updateMessage = "Server error. Please try again later";
		}
		user = userBO.getUser(request.getParameter("username"));
        request.setAttribute("user", user);
        request.setAttribute("updateResult", updateResult);
        request.setAttribute("updateMessage", updateMessage);
        RequestDispatcher dispatcher 
        = request.getRequestDispatcher("./WEB-INF/jsp/UserPage/ViewUserPage.jsp");
    	dispatcher.forward(request, response);
	}


}
