package coffeeshop.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import coffeeshop.dto.OrderMenuDTO;

public class OrderMenuMapper extends DBMapper{

	public OrderMenuMapper() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public boolean createOrderMenu(OrderMenuDTO orderMenu) {
		boolean createResult = false;
		Statement stmt = null;
		 
		try{
			 stmt = getConnection().createStatement();
		     String sqlStr = "INSERT INTO caphe_java_db.ordermenus (`name`, `price`) VALUES ('"
		    		 + orderMenu.getName() + "','"
		    		 + orderMenu.getPrice() + "')";
		     createResult = stmt.executeUpdate(sqlStr) > 0; // Send the query to the server
		}catch(SQLException ex){
		     ex.printStackTrace();
	 	} 
		return createResult;
	}
	
	public OrderMenuDTO getOrderMenu(int id){
		OrderMenuDTO orderMenu = new OrderMenuDTO();
		Statement stmt = null;
		
        try{
            stmt = getConnection().createStatement();
            String sqlStr = "SELECT * FROM ordermenus WHERE id = '" + id + "'";
            ResultSet rs = stmt.executeQuery(sqlStr); // Send the query to the server
			
			if(rs != null && rs.next()){
				orderMenu.setId(rs.getInt("id"));
				orderMenu.setPrice(rs.getInt("price"));
            	orderMenu.setName(rs.getString("name"));
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        } 
        return orderMenu;
	}

	public ArrayList<OrderMenuDTO> searchOrderMenu(OrderMenuDTO orderMenuInfo){
		ArrayList<OrderMenuDTO> orderMenus = new ArrayList<>();    
		Statement stmt = null;
		
		try{     
			stmt = getConnection().createStatement();
			String sqlStr = "";
			
			if(orderMenuInfo == null){
				 sqlStr = "SELECT id, name, price FROM ordermenus WHERE 1"
					+ " ORDER BY id ASC ";
			}else{
				// sqlStr = "SELECT ordermenus.id, ordermenus.firstname, ordermenus.lastname, ordermenus.sex,"
				// 	+ " ordermenus.address, ordermenus.email, ordermenus.mobilephone, ordermenus.groupid, orderMenugroup.groupname"
				// 	+ " FROM caphe_java_db.ordermenus, caphe_java_db.orderMenugroup"
				// 	+ " WHERE ordermenus.groupid = orderMenugroup.groupid"
				// 	+ " AND ordermenus.firstname LIKE '%" + orderMenuInfo.getFirstname() + "%'"
				// 	+ " AND ordermenus.lastname LIKE '%" + orderMenuInfo.getLastname() + "%'"
				// 	+ " AND ordermenus.sex LIKE '%" + orderMenuInfo.getSex() + "%'"
				// 	+ " AND ordermenus.address LIKE '%" + orderMenuInfo.getAddress() + "%'"
				// 	+ " AND ordermenus.email LIKE '%" + orderMenuInfo.getEmail()+ "%'"
				// 	+ " AND ordermenus.mobilephone LIKE '%" + orderMenuInfo.getMobilephone()+ "%'";
				// if (orderMenuInfo.getGroupid() != 0)
				// 	sqlStr += " AND ordermenus.groupid LIKE '%" + orderMenuInfo.getGroupid()+ "%'";
				// sqlStr += " ORDER BY ordermenus.id ASC ";
			}

			ResultSet rs = stmt.executeQuery(sqlStr); // Send the query to the server
			while(rs != null && rs.next()){
				OrderMenuDTO orderMenu = new OrderMenuDTO();
				orderMenu.setId(rs.getInt("id"));
				orderMenu.setName(rs.getString("name"));
				orderMenu.setPrice(rs.getInt("price"));
				orderMenus.add(orderMenu);
			}          
		}catch(Exception ex){
			ex.printStackTrace();
		} 

		return orderMenus;
	}

	public boolean updateOrderMenu(OrderMenuDTO orderMenu) {
		boolean updateResult = false;
		Statement stmt = null;

		String a = orderMenu.getName();
		int b = orderMenu.getPrice();
		int c = orderMenu.getId();

		try {
			stmt = getConnection().createStatement();
			String sqlStr = "UPDATE ordermenus SET " 
					+ " name = '" + a + "',"
					+ " price = '" + b + "'"
					+ " WHERE id = " + c; 
			updateResult = stmt.executeUpdate(sqlStr) > 0; // Send the query to the server
		} catch (SQLException ex) {
			ex.printStackTrace();
		 } 
		return updateResult;
   }
}
