package coffeeshop.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import coffeeshop.dto.OrderMenuDTO;

public class OrderMenuMapper extends DBMapper{

	public OrderMenuMapper() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public boolean createOrderMenu(OrderMenuDTO orderMenu) {
		 boolean createResult = false;
	 	 Statement stmt = null;
		 try {
			 stmt = getConnection().createStatement();
		     String sqlStr = "INSERT INTO caphe_java_db.ordermenu (`name`, `price`) VALUES ('"
		    		 + orderMenu.getName() + "','"
		    		 + orderMenu.getPrice() + "')";
		     createResult = stmt.executeUpdate(sqlStr) > 0; // Send the query to the server
		 } catch (SQLException ex) {
		     ex.printStackTrace();
	 	 } 
		 return createResult;
	 }
	
	public OrderMenuDTO getOrderMenu(String name) {
		OrderMenuDTO orderMenu = new OrderMenuDTO();
        Statement stmt = null;
        try {
            stmt = getConnection().createStatement();
            String sqlStr = "SELECT * FROM caphe_java_db.ordermenu WHERE name = '" + name + "'";
            ResultSet rs = stmt.executeQuery(sqlStr); // Send the query to the server
            if (rs != null && rs.next()) {
            	orderMenu.setId(rs.getInt("price"));
            	orderMenu.setName(rs.getString("name"));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } 
        return orderMenu;
	 }

}
