package coffeeshop.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import coffeeshop.dto.OrderingBillDTO;

public class OrderingBillMapper extends DBMapper {

	public OrderingBillMapper() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ArrayList<OrderingBillDTO> searchOrderingBill(OrderingBillDTO orderingBillInfo) {
        ArrayList<OrderingBillDTO> orderingBills = new ArrayList<>();   
        Statement stmt = null;
        try {     
            stmt = getConnection().createStatement();
            String sqlStr = "";
            sqlStr = "SELECT orderingbill.customerId, orderingbill.id, customers.name, users.firstname, users.lastname, orderingbill.totalPrice"
            		+ " FROM caphe_java_db.orderingbill "
            		+ " LEFT JOIN caphe_java_db.customers ON orderingbill.customerId = customers.id "
            		+ " LEFT JOIN caphe_java_db.users ON orderingbill.userId = users.id ";
            		if (orderingBillInfo != null)
            		{
            			if (orderingBillInfo.getId() != 0)
            			{
            				sqlStr += " WHERE orderingbill.id = " + orderingBillInfo.getId();
            			}
            			else
            			{
            				sqlStr += " WHERE concat(users.firstname, ' ', users.lastname) LIKE '%" + orderingBillInfo.getUserName()+ "%'";
	            			if (!orderingBillInfo.getCustomerName().isEmpty())
	            			{
	            				sqlStr += " AND orderingbill.customerId != " + 0;
	            				sqlStr += " AND customers.name LIKE '%" + orderingBillInfo.getCustomerName() + "%'";
	            			}
	            			if (orderingBillInfo.getTotalPrice() != 0)
	                    		sqlStr += " AND orderingbill.totalPrice = " + orderingBillInfo.getTotalPrice();
            			}
            		}
            		sqlStr += " ORDER BY orderingbill.id ASC ";
            			
            ResultSet rs = stmt.executeQuery(sqlStr); // Send the query to the server
            while (rs != null && rs.next()) {
            	OrderingBillDTO orderingBill = new OrderingBillDTO();
            	orderingBill.setId(rs.getInt("id"));
            	if (rs.getInt("customerId") == 0)
            	{
            		orderingBill.setCustomerName("None customer");
            	}
            	else
            	{
            		orderingBill.setCustomerName(rs.getString("name"));
            	}
            	orderingBill.setUserName(rs.getString("firstname")+" "+rs.getString("lastname"));
            	orderingBill.setTotalPrice(rs.getInt("totalPrice"));
            	orderingBills.add(orderingBill);
            }   
        } catch (Exception ex) {
            ex.printStackTrace();
        } 
        
        return orderingBills;
	}

	public int createOrderingBill(OrderingBillDTO orderingBill) {
		int lastId = 0;
		Statement stmt = null;
		 
		try{
			 stmt = getConnection().createStatement();
		     String sqlStr = "INSERT INTO caphe_java_db.orderingbill (`customerId`,`userId`,`totalPrice`) VALUES ('"
		    		 + orderingBill.getCustomerId() + "','"
    				 + orderingBill.getUserId()+ "','"
		    		 + orderingBill.getTotalPrice() + "')";
		     if (stmt.executeUpdate(sqlStr, Statement.RETURN_GENERATED_KEYS) > 0)
		     {
		    	 ResultSet rs = stmt.getGeneratedKeys();
			     if (rs.next()){
			    	 lastId=rs.getInt(1);
			     }
		     }
		     
		}catch(SQLException ex){
		     ex.printStackTrace();
	 	} 
		return lastId;
	}
	public boolean deleteOrderingBill(int id) {
		 Statement stmt = null;
		 boolean deleteResult = false;
		 try {
           stmt = getConnection().createStatement();
           String sqlStr = "DELETE FROM caphe_java_db.orderingbill WHERE id = " + id;
           deleteResult = stmt.executeUpdate(sqlStr) > 0; // Send the query to the server
       } catch (SQLException ex) {
           ex.printStackTrace();
       } 
       return deleteResult;	 
	 }

	public boolean deleteOrderingBillInList(ArrayList<OrderingBillDTO> orderingBillDeleteList) {
		for (int i = 0; i < orderingBillDeleteList.size(); i++) {
			OrderingBillDTO getOrderingBill = orderingBillDeleteList.get(i);
        	if (this.deleteOrderingBill(getOrderingBill.getId()) == false)
        	{
        		return false;
        	}
        }
		return true;
	}

}
