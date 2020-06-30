package coffeeshop.mapper;

import java.sql.ResultSet;
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
            if (orderingBillInfo == null)
            {
            	sqlStr = "SELECT orderingbill.id, customers.name, users.lastname, users.firstname, orderingbill.totalPrice"
            		+ " FROM caphe_java_db.orderingbill, caphe_java_db.customers, caphe_java_db.users "
            		+ " WHERE orderingbill.customerId = customers.id "
            		+ " AND orderingbill.userId = users.id "
            		+ " ORDER BY orderingbill.id ASC ";
            }
            else
            {
            	sqlStr = "SELECT orderingbill.id, customers.name, users.lastname, users.firstname, orderingbill.totalPrice"
            		+ " FROM caphe_java_db.orderingbill, caphe_java_db.customers, caphe_java_db.users"
            		+ " WHERE orderingbill.customerId = customers.id "
            		+ " AND orderingbill.userId = users.id "
					+ " AND customers.name LIKE '%" + orderingBillInfo.getCustomerName() + "%'"
					+ " AND users.lastname LIKE '%" + orderingBillInfo.getUserName()+ "%'"
					+ " AND users.firstname LIKE '%" + orderingBillInfo.getUserName()+ "%'";
            	if (orderingBillInfo.getTotalPrice() != 0)
            		sqlStr += " AND orderingbill.totalPrice = " + orderingBillInfo.getTotalPrice();
            	sqlStr += " ORDER BY orderingbill.id ASC ";
            }
            ResultSet rs = stmt.executeQuery(sqlStr); // Send the query to the server
            while (rs != null && rs.next()) {
            	OrderingBillDTO orderingBill = new OrderingBillDTO();
            	orderingBill.setId(rs.getInt("id"));
            	orderingBill.setCustomerName(rs.getString("name"));
            	orderingBill.setUserName(rs.getString("firstname")+" "+rs.getString("lastname"));
            	orderingBill.setTotalPrice(rs.getInt("totalPrice"));
            	orderingBills.add(orderingBill);
            }          
        } catch (Exception ex) {
            ex.printStackTrace();
        } 
        
        return orderingBills;
	}

}
