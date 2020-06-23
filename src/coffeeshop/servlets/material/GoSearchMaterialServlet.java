package coffeeshop.servlets.material;

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

import coffeeshop.bo.CustomerBO;
import coffeeshop.bo.UserBO;
import coffeeshop.dto.CustomerDTO;

/**
 * Servlet implementation class GoSearchMaterialServlet
 */
@WebServlet(name = "GoSearchMaterialServlet", urlPatterns = {"/GoSearchMaterialServlet"})
public class GoSearchMaterialServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
        RequestDispatcher dispatcher 
                = request.getRequestDispatcher
		("./WEB-INF/jsp/MaterialPage/SearchMaterialPage.jsp");
        dispatcher.forward(request, response);
	}

}
