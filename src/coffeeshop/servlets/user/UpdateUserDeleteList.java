package coffeeshop.servlets.user;

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
import coffeeshop.dto.UserDTO;

/**
 * Servlet implementation class AddToUserDeleteList
 */
@WebServlet(name = "UpdateUserDeleteList", urlPatterns = {"/UpdateUserDeleteList"})
public class UpdateUserDeleteList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String validateString(String stringCheck)
	{
		if( !(stringCheck instanceof String) || stringCheck == null) 
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
        String currentEmail = this.validateString(request.getParameter("email"));
        // Update user delete list
        ArrayList<UserDTO> userDeleteList = (ArrayList) session.getAttribute("userDeleteList");
        if (userDeleteList != null)
        {
        	session.removeAttribute("userDeleteList");
        }
        userDeleteList = userBO.updateUserDeleteList(userDeleteList, currentEmail);
        //set session
    	session.setAttribute("userDeleteList", userDeleteList);
        // Get user list
    	UserDTO userSearchInfo = (UserDTO) session.getAttribute("userSearchInfo");
    	ArrayList<UserDTO> users = userBO.searchUser(userSearchInfo);
    	request.setAttribute("users", users);
        RequestDispatcher dispatcher 
        = request.getRequestDispatcher
		("./WEB-INF/jsp/UserPage/SearchUserPage.jsp");
		dispatcher.forward(request, response);
	}
}
