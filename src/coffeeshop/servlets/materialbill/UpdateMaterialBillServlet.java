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
import coffeeshop.bo.MaterialBillDetailBO;
import coffeeshop.bo.UserBO;
import coffeeshop.dto.MaterialDTO;
import coffeeshop.dto.MaterialBilllDTO;
import coffeeshop.dto.MaterialBillDetailDTO;

/**
 * Servlet implementation class UpdateMaterialBillServlet
 */
@WebServlet(name = "UpdateMaterialBillServlet", urlPatterns = {"/UpdateMaterialBillServlet"})
public class UpdateMaterialBillServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String validateString(String stringCheck){
		if(stringCheck == null){
			return "";
		}
		return stringCheck.trim();
	}
	
	private int validateInteger(String integerCheck){
		if(integerCheck == null){
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
        MaterialBillDetailBO materialBillDetailBO = new MaterialBillDetailBO(context);
        
        if (userBO.authorizationUser(session, 1) == false){
        	response.sendRedirect("./GoLoginServlet");
	    	return;
        }

        MaterialBilllDTO materialBillInfo = (MaterialBilllDTO) session.getAttribute("materialBillInfo");
        session.removeAttribute("materialBillInfo");
        
        // Update materialBillDetail list
        if (!this.validateString(request.getParameter("addMaterialId")).isEmpty() 
            || !this.validateString(request.getParameter("removeMaterialId")).isEmpty()){
                
        	ArrayList<MaterialBillDetailDTO> materialBillDetailList = (ArrayList) session.getAttribute("materialBillDetailList");
            
            if (materialBillDetailList != null){
	        	session.removeAttribute("materialBillDetailList");
	        }
	        if(!this.validateString(request.getParameter("addMaterialId")).isEmpty()){
	        	int materialId = this.validateInteger(request.getParameter("addMaterialId"));
	        	materialBillDetailList = materialBillDetailBO.updateMaterialBillDetailList(materialBillDetailList, materialId, true);
	        }else{
	        	int materialId = this.validateInteger(request.getParameter("removeMaterialId"));
	        	materialBillDetailList = materialBillDetailBO.updateMaterialBillDetailList(materialBillDetailList, materialId, false);
	        }
	        int newTotalPrice = materialBillDetailBO.caculateTotalPrice(materialBillDetailList);
	        materialBillInfo.setTotalPrice(newTotalPrice);
	        //set session
	    	session.setAttribute("materialBillDetailList", materialBillDetailList);
        }

        session.setAttribute("materialBillInfo", materialBillInfo);
        
        // Get material info
        ArrayList<MaterialDTO> materials = MaterialBO.searchMaterial(null);
        request.setAttribute("materials", materials);
        
        RequestDispatcher dispatcher 
        = request.getRequestDispatcher
		("./WEB-INF/jsp/MaterialBillPage/CreateMaterialBillPage.jsp");
		dispatcher.forward(request, response);
	}
}
