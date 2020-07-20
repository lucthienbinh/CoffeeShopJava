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

import coffeeshop.bo.MaterialBO;
import coffeeshop.bo.MaterialBillBO;
import coffeeshop.bo.MaterialBillDetailBO;
import coffeeshop.bo.UserBO;
import coffeeshop.dto.MaterialDTO;
import coffeeshop.dto.MaterialBilllDTO;
import coffeeshop.dto.MaterialBillDetailDTO;

/**
 * Servlet implementation class InsertMaterialBillServlet
 */
@WebServlet(name = "InsertMaterialBillServlet", urlPatterns = {"/InsertMaterialBillServlet"})
public class InsertMaterialBillServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		// Get context for DI UserBO and session in request
		HttpSession session = request.getSession(); 
		ServletContext context =  request.getServletContext();
        UserBO userBO = new UserBO(context);
        MaterialBillBO materialBillBO = new MaterialBillBO(context);
        MaterialBillDetailBO materialBillDetailBO = new MaterialBillDetailBO(context);
        
        if (userBO.authorizationUser(session, 1) == false){
        	response.sendRedirect("./GoLoginServlet");
	    	return;
		}
        
        boolean createResult = false;
		String createMessage = "Server error. Please try again later";
		// Get info from session then remove it
		MaterialBilllDTO materialBillInfo = (MaterialBilllDTO) session.getAttribute("materialBillInfo");
		ArrayList<MaterialBillDetailDTO> materialBillDetailList = (ArrayList) session.getAttribute("materialBillDetailList");
        
        if (materialBillDetailList != null){
			session.removeAttribute("materialBillInfo");
			session.removeAttribute("materialBillDetailList");
			int lastId = materialBillBO.createMaterialBill(materialBillInfo);
			if (lastId != 0){
				createResult = materialBillDetailBO.createMaterialBillDetail(materialBillDetailList, lastId);
				if(createResult == true){
					createMessage = "New material bill has been created";
				}	
			}
			materialBillInfo.setTotalPrice(0);
	        session.setAttribute("materialBillInfo", materialBillInfo);
		}else{
			createMessage = "Please select drinks";
		}
		
        // Get order menu info
        ArrayList<MaterialDTO> materials = MaterialBO.searchMaterial(null);
        request.setAttribute("materials", materials);
        
        request.setAttribute("createResult", createResult);
        request.setAttribute("createMessage", createMessage);
        RequestDispatcher dispatcher = request.getRequestDispatcher
		("./WEB-INF/jsp/MaterialBillPage/CreateMaterialBillPage.jsp");
    	dispatcher.forward(request, response);
	}
}
