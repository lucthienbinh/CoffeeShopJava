package coffeeshop.mapper;

import java.sql.SQLException;
import java.sql.Statement;

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

}
