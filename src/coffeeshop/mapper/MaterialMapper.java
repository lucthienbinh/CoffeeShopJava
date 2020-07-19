package coffeeshop.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import coffeeshop.dto.MaterialDTO;
import coffeeshop.dto.UserDTO;

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
	 public ArrayList<MaterialDTO> searchMaterial(MaterialDTO materialInfo) {
	        ArrayList<MaterialDTO> materials = new ArrayList<>();    
	        Statement stmt = null;
	        try {     
	            stmt = getConnection().createStatement();
	            String sqlStr = "";
	            if (materialInfo == null)
	            {
	            	 sqlStr = "SELECT material.id, material.name, material.price, material.unit,"
	            		+ " FROM caphe_java_db.material"
	            		+ " ORDER BY material.id ASC ";
	            }
	            else
	            {
	            	sqlStr = "SELECT material.id, material.name, material.price, material.unit,"
	            		+ " FROM caphe_java_db.material"
	                 	+ " WHERE material.name LIKE '%" + materialInfo.getName() + "%'"
						+ " AND material.price LIKE '%" + materialInfo.getPrice() + "%'"
						+ " AND material.unit LIKE '%" + materialInfo.getUnit()+ "%'";
	            	sqlStr += " ORDER BY material.id ASC ";
	            }
	            ResultSet rs = stmt.executeQuery(sqlStr); // Send the query to the server
	            while (rs != null && rs.next()) {
	            	MaterialDTO material = new MaterialDTO();
	            	material.setId(rs.getInt("id"));
	            	material.setName(rs.getString("name"));
	            	material.setPrice(rs.getInt("price"));
	            	material.setUnit(rs.getString("unit"));
	            	materials.add(material);
	            }          
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        } 
	        
	        return materials;
 }
}
