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
 * Servlet implementation class GoSearchUserServlet
 */
@WebServlet(name = "GoSearchUserServlet", urlPatterns = {"/GoSearchUserServlet"})
public class GoSearchUserServlet extends HttpServlet {
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
		// Remove old search info stored in session if exists
		UserDTO userSearchInfo = (UserDTO) session.getAttribute("userSearchInfo");
		if (userSearchInfo != null)
        {
			session.removeAttribute("userSearchInfo");
        }
		// Set new empty search info 
		UserDTO newEmptyUserInfo = new UserDTO();
		newEmptyUserInfo.generateEmptyObjectForJSP();
		session.setAttribute("userSearchInfo", newEmptyUserInfo);
		// Get user info
        ArrayList<UserDTO> users = userBO.searchUser(null);
        request.setAttribute("users", users);
        RequestDispatcher dispatcher 
                = request.getRequestDispatcher
        ("./WEB-INF/jsp/UserPage/SearchUserPage.jsp");
        dispatcher.forward(request, response);
	}



}
