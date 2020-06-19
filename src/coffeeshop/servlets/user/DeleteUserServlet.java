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
 * Servlet implementation class DeleteUserServlet
 */
@WebServlet(name = "DeleteUserServlet", urlPatterns = {"/DeleteUserServlet"})
public class DeleteUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
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
        ArrayList<UserDTO> userDeleteList = (ArrayList) session.getAttribute("userDeleteList");
        boolean deleteResult = false;
        String deleteMessage = "";
        if (userDeleteList == null) 
        {
        	deleteMessage = "Select user to delete first!";
        }
        else
        {
        	session.removeAttribute("userDeleteList");
        	deleteResult = userBO.deleteUserInList(userDeleteList);
            if(deleteResult == true)
            {
            	deleteMessage = userDeleteList.size() + (userDeleteList.size() > 1 ? " users have" : " user has") + " been deleted!";
            }
            else
            {
            	deleteMessage = "Failed when delete user!";
            }
        }
        request.setAttribute("deleteResult", deleteResult);
        request.setAttribute("deleteMessage", deleteMessage);
		UserDTO userSearchInfo = (UserDTO) session.getAttribute("userSearchInfo");
		ArrayList<UserDTO> users = userBO.searchUser(userSearchInfo);
		request.setAttribute("users", users);
	    RequestDispatcher dispatcher 
	    = request.getRequestDispatcher
		("./WEB-INF/jsp/UserPage/SearchUserPage.jsp");
		dispatcher.forward(request, response);
	}
}
