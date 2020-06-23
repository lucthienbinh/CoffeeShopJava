package coffeeshop.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import coffeeshop.dto.CustomerDTO;

public class CustomerMapper extends DBMapper {
	
	public CustomerMapper() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}

	public ArrayList<CustomerDTO> searchCustomer(CustomerDTO customerInfo) {
        ArrayList<CustomerDTO> customers = new ArrayList<>();    
        Statement stmt = null;
        try {     
            stmt = getConnection().createStatement();
            String sqlStr = "";
            if (customerInfo == null)
            {
            	 sqlStr = "SELECT * "
            		+ " FROM caphe_java_db.customers"
            		+ " ORDER BY id ASC ";
            }
            else
            {
            	sqlStr = "SELECT *"
            		+ " FROM caphe_java_db.customers"
            		+ " WHERE name LIKE '%" + customerInfo.getName() + "%'"
					+ " AND address LIKE '%" + customerInfo.getAddress() + "%'"
					+ " AND email LIKE '%" + customerInfo.getEmail()+ "%'"
					+ " AND mobilephone LIKE '%" + customerInfo.getMobilephone()+ "%'";
            }
            ResultSet rs = stmt.executeQuery(sqlStr); // Send the query to the server
            while (rs != null && rs.next()) {
            	CustomerDTO customer = new CustomerDTO();
            	customer.setId(rs.getInt("id"));
            	customer.setName(rs.getString("name"));
                customer.setAddress(rs.getString("address"));
                customer.setEmail(rs.getString("email"));
                customer.setMobilephone(rs.getString("mobilephone"));
                customers.add(customer);
            }          
        } catch (Exception ex) {
            ex.printStackTrace();
        } 
        
        return customers;
}

	public CustomerDTO getCustomer(String email) {
		CustomerDTO user = new CustomerDTO();
        Statement stmt = null;
        try {
            stmt = getConnection().createStatement();
            String sqlStr = "SELECT * FROM caphe_java_db.customers WHERE email = '" + email + "'";
            ResultSet rs = stmt.executeQuery(sqlStr); // Send the query to the server
            if (rs != null && rs.next()) {
            	user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setAddress(rs.getString("address"));
                user.setEmail(rs.getString("email"));
                user.setMobilephone(rs.getString("mobilephone"));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } 
        return user;
	}

	 public boolean deleteCustomer(int id) {
		 Statement stmt = null;
		 boolean deleteResult = false;
		 try {
            stmt = getConnection().createStatement();
            String sqlStr = "DELETE FROM caphe_java_db.customers WHERE id = " + id;
            deleteResult = stmt.executeUpdate(sqlStr) > 0; // Send the query to the server
        } catch (SQLException ex) {
            ex.printStackTrace();
        } 
        return deleteResult;	 
	 }
	
	public boolean deleteUserInList(ArrayList<CustomerDTO> customerDeleteList) {
		for (int i = 0; i < customerDeleteList.size(); i++) {
			CustomerDTO getCustomer = customerDeleteList.get(i);
         	if (this.deleteCustomer(getCustomer.getId()) == false)
         	{
         		return false;
         	}
         }
		 return true;
	}
}
