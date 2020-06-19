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
 * Servlet implementation class SearchUserServlet
 */
@WebServlet(name = "SearchUserServlet", urlPatterns = {"/SearchUserServlet"})
public class SearchUserServlet extends HttpServlet {
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
        if (userBO.authorizationUser(session, 1) == false)
        {
        	response.sendRedirect("./GoLoginServlet");
	    	return;
        }      
        UserDTO userSearchInfo = new UserDTO();
    	userSearchInfo.setFirstname(this.validateString(request.getParameter("firstname")));
    	userSearchInfo.setLastname(this.validateString(request.getParameter("lastname")));
    	userSearchInfo.setAddress(this.validateString(request.getParameter("address")));
    	userSearchInfo.setSex(this.validateString(request.getParameter("sex")));
    	userSearchInfo.setEmail(this.validateString(request.getParameter("email")));
    	userSearchInfo.setMobilephone(this.validateString(request.getParameter("mobilephone")));
    	userSearchInfo.setGroupid(this.validateInteger(request.getParameter("groupid")));
    	session.setAttribute("userSearchInfo", userSearchInfo);
        ArrayList<UserDTO> users = userBO.searchUser(userSearchInfo);
        request.setAttribute("users", users);
        RequestDispatcher dispatcher 
                = request.getRequestDispatcher
        ("./WEB-INF/jsp/UserPage/SearchUserPage.jsp");
        dispatcher.forward(request, response);
        
	}

}
