package coffeeshop.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import coffeeshop.dto.UserDTO;

public class UserMapper extends DBMapper{

	public UserMapper() throws Exception {
		super();
	}
	
	 public UserDTO getUser(String email) {
        UserDTO user = new UserDTO();
        Statement stmt = null;
        try {
            stmt = getConnection().createStatement();
            String sqlStr = "SELECT * FROM caphe_java_db.users WHERE email = '" + email + "'";
            ResultSet rs = stmt.executeQuery(sqlStr); // Send the query to the server
            if (rs != null && rs.next()) {
            	user.setId(rs.getInt("id"));
                user.setFirstname(rs.getString("firstname"));
                user.setLastname(rs.getString("lastname"));
                user.setSex(rs.getString("sex"));
                user.setAddress(rs.getString("address"));
                user.setEmail(rs.getString("email"));
                user.setMobilephone(rs.getString("mobilephone"));
                user.setGroupid(rs.getInt("groupid"));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } 
        return user;
	 }
	 public UserDTO getUserWithPassword(String email) {
        UserDTO user = new UserDTO();
        Statement stmt = null;
        try {
            stmt = getConnection().createStatement();
            String sqlStr = "SELECT * FROM caphe_java_db.users WHERE email = '" + email + "'";
            ResultSet rs = stmt.executeQuery(sqlStr); // Send the query to the server
            if (rs != null && rs.next()) {
            	user.setId(rs.getInt("id"));
                user.setPassword(rs.getString("password"));
                user.setFirstname(rs.getString("firstname"));
                user.setLastname(rs.getString("lastname"));
                user.setSex(rs.getString("sex"));
                user.setAddress(rs.getString("address"));
                user.setEmail(rs.getString("email"));
                user.setMobilephone(rs.getString("mobilephone"));
                user.setGroupid(rs.getInt("groupid"));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } 
        return user;
	 }
	 public boolean loginUser(String email, String passsword) {
		 UserDTO user = this.getUserWithPassword(email);
		 if (user == null || !user.getPassword().equals(passsword))
		 {
			 return false;
		 }
		 return true;
	 }
	 public boolean createUser(UserDTO user) {
		 boolean createResult = false;
	 	 Statement stmt = null;
		 try {
			 stmt = getConnection().createStatement();
		     String sqlStr = "INSERT INTO caphe_java_db.users (`password`, `firstname`, `lastname`, `sex`, `address`, `email`, `mobilephone`, `groupid`) VALUES ('"
		    		 + user.getPassword() + "','"
		    		 + user.getFirstname() + "','"
		    		 + user.getLastname() + "','"
		    		 + user.getSex() + "','"
		    		 + user.getAddress() + "','"
		    		 + user.getEmail() + "','"
		    		 + user.getMobilephone() + "','"
		    		 + user.getGroupid() + "')";
		     createResult = stmt.executeUpdate(sqlStr) > 0; // Send the query to the server
		 } catch (SQLException ex) {
		     ex.printStackTrace();
	 	 } 
		 return createResult;
	 }
	 
	 public ArrayList<UserDTO> searchUser(UserDTO userInfo) {
	        ArrayList<UserDTO> users = new ArrayList<>();    
	        Statement stmt = null;
	        try {     
	            stmt = getConnection().createStatement();
	            String sqlStr = "";
	            if (userInfo == null)
	            {
	            	 sqlStr = "SELECT users.id, users.firstname, users.lastname, users.sex,"
	            		+ " users.address, users.email, users.mobilephone, usergroup.groupname"
	            		+ " FROM caphe_java_db.users, caphe_java_db.usergroup"
	            		+ " WHERE users.groupid = usergroup.groupid" 
	            		+ " ORDER BY users.id ASC ";
	            }
	            else
	            {
	            	sqlStr = "SELECT users.id, users.firstname, users.lastname, users.sex,"
	            		+ " users.address, users.email, users.mobilephone, users.groupid, usergroup.groupname"
	            		+ " FROM caphe_java_db.users, caphe_java_db.usergroup"
	            		+ " WHERE users.groupid = usergroup.groupid"
        				+ " AND users.firstname LIKE '%" + userInfo.getFirstname() + "%'"
						+ " AND users.lastname LIKE '%" + userInfo.getLastname() + "%'"
						+ " AND users.sex LIKE '%" + userInfo.getSex() + "%'"
						+ " AND users.address LIKE '%" + userInfo.getAddress() + "%'"
						+ " AND users.email LIKE '%" + userInfo.getEmail()+ "%'"
						+ " AND users.mobilephone LIKE '%" + userInfo.getMobilephone()+ "%'";
	            	if (userInfo.getGroupid() != 0)
	            		sqlStr += " AND users.groupid LIKE '%" + userInfo.getGroupid()+ "%'";
	            	sqlStr += " ORDER BY users.id ASC ";
	            }
	            ResultSet rs = stmt.executeQuery(sqlStr); // Send the query to the server
	            while (rs != null && rs.next()) {
	            	UserDTO user = new UserDTO();
	            	user.setId(rs.getInt("id"));
	                user.setFirstname(rs.getString("firstname"));
	                user.setLastname(rs.getString("lastname"));
	                user.setSex(rs.getString("sex"));
	                user.setAddress(rs.getString("address"));
	                user.setEmail(rs.getString("email"));
	                user.setMobilephone(rs.getString("mobilephone"));
	                user.setGroupname(rs.getString("groupname"));
	                users.add(user);
	            }          
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        } 
	        
	        return users;
    }
	 public boolean deleteUser(int id) {
		 Statement stmt = null;
		 boolean deleteResult = false;
		 try {
            stmt = getConnection().createStatement();
            String sqlStr = "DELETE FROM caphe_java_db.users WHERE id = " + id;
            deleteResult = stmt.executeUpdate(sqlStr) > 0; // Send the query to the server
        } catch (SQLException ex) {
            ex.printStackTrace();
        } 
        return deleteResult;	 
	 }
	 public boolean deleteUserInList(ArrayList<UserDTO> userDeleteList)  {
		 for (int i = 0; i < userDeleteList.size(); i++) {
         	UserDTO getUser = userDeleteList.get(i);
         	if (this.deleteUser(getUser.getId()) == false)
         	{
         		return false;
         	}
         }
		 return true;
	 }

	public boolean updateUser(UserDTO user) {
		 boolean updateResult = false;
	 	 Statement stmt = null;
		 try {
			 stmt = getConnection().createStatement();
		     String sqlStr = "UPDATE caphe_java_db.users SET " 
		    		 + " firstname = '" + user.getFirstname() + "',"
    				 + " lastname = '" + user.getLastname() + "',"
					 + " sex = '" + user.getSex() + "',"
					 + " address = '" + user.getAddress() + "',"
					 + " mobilephone = '" + user.getMobilephone() + "'"
					 + " WHERE id = " + user.getId(); 
		     updateResult = stmt.executeUpdate(sqlStr) > 0; // Send the query to the server
		 } catch (SQLException ex) {
		     ex.printStackTrace();
	 	 } 
		 return updateResult;
	}

	public boolean updateUserPassword(UserDTO user) {
		boolean updateResult = false;
	 	 Statement stmt = null;
		 try {
			 stmt = getConnection().createStatement();
		     String sqlStr = "UPDATE caphe_java_db.users SET " 
		    		 + " password = '" + user.getPassword() + "' "
					 + " WHERE id = " + user.getId(); 
		     updateResult = stmt.executeUpdate(sqlStr) > 0; // Send the query to the server
		 } catch (SQLException ex) {
		     ex.printStackTrace();
	 	 } 
		 return updateResult;
	}
}
