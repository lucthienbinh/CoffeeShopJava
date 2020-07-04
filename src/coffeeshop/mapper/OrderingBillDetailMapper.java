package coffeeshop.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import coffeeshop.dto.OrderingBillDetailDTO;

public class OrderingBillDetailMapper extends DBMapper {

	public OrderingBillDetailMapper() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public boolean createOrderingBillDetail(OrderingBillDetailDTO orderingBillDetail, int orderingBillId) {
		boolean createResult = false;
		Statement stmt = null;
		 
		try{
			 stmt = getConnection().createStatement();
		     String sqlStr = "INSERT INTO caphe_java_db.orderingbilldetail (`orderMenuId`, `quantity`,`orderingBillId`,`price`) VALUES ('"
		    		 + orderingBillDetail.getOrderMenuId() + "','"
    				 + orderingBillDetail.getQuantity() + "','"
					 + orderingBillId + "','"
		    		 + orderingBillDetail.getPrice() + "')";
		     createResult = stmt.executeUpdate(sqlStr) > 0; // Send the query to the server
		}catch(SQLException ex){
		     ex.printStackTrace();
	 	} 
		return createResult;
	}

	public ArrayList<OrderingBillDetailDTO> getOrderingBillDetails(int orderingBillId) {
		ArrayList<OrderingBillDetailDTO> orderingBillDetails = new ArrayList<>();    
        Statement stmt = null;
        try {     
            stmt = getConnection().createStatement();
            String sqlStr = "";
        	sqlStr = "SELECT * "
        		+ " FROM caphe_java_db.orderingbilldetail, caphe_java_db.ordermenu"
        		+ " WHERE orderingbilldetail.orderMenuId = ordermenu.id "
        		+ " AND orderingbilldetail.orderingBillId = " + orderingBillId;
            ResultSet rs = stmt.executeQuery(sqlStr); // Send the query to the server
            while (rs != null && rs.next()) {
            	OrderingBillDetailDTO orderingBillDetail = new OrderingBillDetailDTO();
            	orderingBillDetail.setOrderMenuName(rs.getString("name"));
            	orderingBillDetail.setQuantity(rs.getInt("quantity"));
            	orderingBillDetail.setPrice(rs.getInt("price"));
            	int amount = rs.getInt("price") * rs.getInt("quantity");
            	orderingBillDetail.setAmount(amount);
            	orderingBillDetails.add(orderingBillDetail);
            }          
        } catch (Exception ex) {
            ex.printStackTrace();
        } 
        
        return orderingBillDetails;
	}

	public boolean deleteOrderingBillDetailByOrderingBillId(int orderingBillId) {
		 Statement stmt = null;
		 boolean deleteResult = false;
		 try {
			 stmt = getConnection().createStatement();
			 String sqlStr = "DELETE FROM caphe_java_db.orderingbilldetail WHERE orderingBillId = " + orderingBillId;
			 deleteResult = stmt.executeUpdate(sqlStr) > 0; // Send the query to the server
	     } catch (SQLException ex) {
	          ex.printStackTrace();
	     } 
	      return deleteResult;	 
	}

}
