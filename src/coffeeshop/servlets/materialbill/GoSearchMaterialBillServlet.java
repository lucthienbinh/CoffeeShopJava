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

import coffeeshop.bo.MaterialBillBO;
import coffeeshop.bo.UserBO;
import coffeeshop.dto.MaterialBilllDTO;

/**
 * Servlet implementation class GoSearchMaterialBillServlet
 */
@WebServlet(name = "GoSearchMaterialBillServlet", urlPatterns = {"/GoSearchMaterialBillServlet"})
public class GoSearchMaterialBillServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		// Get context for DI UserBO and session in request
		HttpSession session = request.getSession(); 
		ServletContext context =  request.getServletContext();
		UserBO userBO = new UserBO(context);
        MaterialBillBO materialBillBO = new MaterialBillBO(context);
        
        if (userBO.authorizationUser(session, 1) == false){
        	response.sendRedirect("./GoLoginServlet");
	    	return;
        }   

		// Remove old search info stored in session if exists
        MaterialBilllDTO materialBillSearchInfo = (MaterialBilllDTO) session.getAttribute("materialBillSearchInfo");
		if (materialBillSearchInfo != null){
			session.removeAttribute("materialBillSearchInfo");
        }

		// Set new empty search info 
		MaterialBilllDTO newEmptyMaterialBillInfo = new MaterialBilllDTO();
		newEmptyMaterialBillInfo.generateEmptyObjectForJSP();
		session.setAttribute("materialBillSearchInfo", newEmptyMaterialBillInfo);
        
        // Get user info
        ArrayList<MaterialBilllDTO> materialBills = materialBillBO.searchMaterialBill(null);
        request.setAttribute("materialBills", materialBills);
        RequestDispatcher dispatcher 
                = request.getRequestDispatcher
        ("./WEB-INF/jsp/MaterialBillPage/SearchMaterialBillPage.jsp");
        dispatcher.forward(request, response);
	}
}
