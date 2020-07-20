package coffeeshop.servlets.materialbill;

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
import coffeeshop.bo.MaterialBO;
import coffeeshop.bo.UserBO;
import coffeeshop.dto.CustomerDTO;
import coffeeshop.dto.MaterialDTO;
import coffeeshop.dto.MaterialBilllDTO;
import coffeeshop.dto.UserDTO;

/**
 * Servlet implementation class GoCreateMaterialBillServlet
 */
@WebServlet(name = "GoCreateMaterialBillServlet", urlPatterns = {"/GoCreateMaterialBillServlet"})
public class GoCreateMaterialBillServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		// Get context for DI UserBO and session in request
		HttpSession session = request.getSession(); 
		ServletContext context =  request.getServletContext();
        UserBO userBO = new UserBO(context);
        CustomerBO customerBO = new CustomerBO(context);
        MaterialBO materialBO = new MaterialBO(context);
        if (userBO.authorizationUser(session, 1) == false)
        {
        	response.sendRedirect("./GoLoginServlet");
	    	return;
        }    
        // Get customer info
        ArrayList<CustomerDTO> customers = customerBO.searchCustomer(null);
        request.setAttribute("customers", customers);
        // Get material info
        ArrayList<MaterialDTO> materials = MaterialBO.searchMaterial(null);
        request.setAttribute("materials", materials);
        
        // Create materialBillInfo with userID is current User
        MaterialBilllDTO materialBillInfo = new MaterialBilllDTO();
        UserDTO user = userBO.getAuthorizationUser(session);
        materialBillInfo.setUserId(user.getId());
        materialBillInfo.setUserName(user.getFirstname()+" "+user.getLastname());
        materialBillInfo.setTotalPrice(0);
        session.setAttribute("materialBillInfo", materialBillInfo);
        
		RequestDispatcher dispatcher 
		= request.getRequestDispatcher
	 	("./WEB-INF/jsp/MaterialBillPage/CreateMaterialBillPage.jsp");
		dispatcher.forward(request, response);
	}

}
