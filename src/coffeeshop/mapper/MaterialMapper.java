package coffeeshop.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import coffeeshop.dto.MaterialDTO;

public class MaterialMapper extends DBMapper{

	public MaterialMapper() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}

	public MaterialDTO getMaterial(String name) {
		MaterialDTO material = new MaterialDTO();
        Statement stmt = null;
        try {
            stmt = getConnection().createStatement();
            String sqlStr = "SELECT * FROM caphe_java_db.material WHERE name = '" + name + "'";
            ResultSet rs = stmt.executeQuery(sqlStr); // Send the query to the server
            if (rs != null && rs.next()) {
            	material.setId(rs.getInt("id"));
            	material.setName(rs.getString("name"));
            	material.setPrice(rs.getInt("price"));
            	material.setRemaining(rs.getInt("remaining"));
            	material.setUnit(rs.getString("unit"));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } 
        return material;
	 }

	public boolean updateUser(MaterialDTO material) {
		boolean updateResult = false;
	 	 Statement stmt = null;
		 try {
			 stmt = getConnection().createStatement();
		     String sqlStr = "UPDATE caphe_java_db.material SET " 
		    		 + " price = '" + material.getPrice() + "',"
					 + " remaining = '" + material.getRemaining() + "',"
					 + " unit = '" + material.getUnit() + "'"
					 + " WHERE id = " + material.getId(); 
		     updateResult = stmt.executeUpdate(sqlStr) > 0; // Send the query to the server
		 } catch (SQLException ex) {
		     ex.printStackTrace();
	 	 } 
		 return updateResult;
	}
}
