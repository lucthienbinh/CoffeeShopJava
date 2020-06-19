package coffeeshop.servlets.basic;

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

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String validateString(String stringCheck)
	{
		if(stringCheck == null) 
		{
			return "";
		}
		return stringCheck.trim();
	}
	
	private boolean checkUserLoggedIn(HttpSession session)
	{
		if(!session.isNew() && session.getAttribute("email") != null )
		{
			return true;
		}
		return false;
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        // Get context for DI UserBO and session in request
        HttpSession session = request.getSession();
        ServletContext context =  request.getServletContext();
        UserBO userBO = new UserBO(context);        
        // Get parameter in request
        String email = this.validateString(request.getParameter("email"));
		String password = this.validateString(request.getParameter("password"));
        boolean loginUserResult = userBO.loginUser(email, password);
        boolean checkLoggedInResult = this.checkUserLoggedIn(session);
        String loginMessage = "";
        if (checkLoggedInResult == false && loginUserResult == true)
        {
        	session.setAttribute("email", email); 
        	response.sendRedirect("./GoDashboardServlet");
        }
        else
        {
        	if(checkLoggedInResult == false && loginUserResult == false)
        	{
        		loginMessage = "Your email or password is not recognized";
        	}
        	else
        	{
        		String loggedInUser = session.getAttribute("email").toString();
	    		if (loggedInUser.equals(email)) {
	    			loginMessage = "You have already logged in";
	    		}
	    		else {
	    			loginMessage = "Another account has logged in. Please log out first";
	    		}
        	}
        	response.setContentType("text/html");
        	request.setAttribute("loginMessage", loginMessage);
	    	request.setAttribute("loginResult", false);
	    	RequestDispatcher dispatcher 
	        = request.getRequestDispatcher("./WEB-INF/jsp/BasicPage/LoginPage.jsp");
	    	dispatcher.forward(request, response);	
        }
	}

}
