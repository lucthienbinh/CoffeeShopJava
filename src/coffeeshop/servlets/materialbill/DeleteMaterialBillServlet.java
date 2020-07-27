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
 * Servlet implementation class DeleteMaterialBillServlet
 */
@WebServlet(name = "DeleteMaterialBillServlet", urlPatterns = {"/DeleteMaterialBillServlet"})
public class DeleteMaterialBillServlet extends HttpServlet {
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

        ArrayList<MaterialBilllDTO> materialBillDeleteList = (ArrayList) session.getAttribute("materialBillDeleteList");
        boolean deleteResult = false;
        String deleteMessage = "";

        if(materialBillDeleteList == null){
        	deleteMessage = "Select material bill to delete first!";
        }else{
        	session.removeAttribute("materialBillDeleteList");
        	deleteResult = materialBillBO.deleteMaterialBillInList(materialBillDeleteList);
            
            if(deleteResult == true){
            	deleteMessage = materialBillDeleteList.size() + (materialBillDeleteList.size() > 1 ? " material bills have" : " material menu has") + " been deleted!";
            }else{
            	deleteMessage = "Failed when deleting material bill!";
            }
        }

        request.setAttribute("deleteResult", deleteResult);
        request.setAttribute("deleteMessage", deleteMessage);
        // Get materialBill list
    	MaterialBilllDTO materialBillSearchInfo = (MaterialBilllDTO) session.getAttribute("materialBillSearchInfo");
        ArrayList<MaterialBilllDTO> materialBills = materialBillBO.searchMaterialBill(materialBillSearchInfo);
        request.setAttribute("materialBills", materialBills);
        RequestDispatcher dispatcher = request.getRequestDispatcher
        ("./WEB-INF/jsp/MaterialBillPage/SearchMaterialBillPage.jsp");
        dispatcher.forward(request, response);
	}
}
