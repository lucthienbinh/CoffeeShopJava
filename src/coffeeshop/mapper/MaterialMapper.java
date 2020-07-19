package coffeeshop.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import coffeeshop.dto.MaterialDTO;

public class MaterialMapper extends DBMapper{

	public MaterialMapper() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public boolean createMaterial(MaterialDTO material) {
		boolean createResult = false;
		Statement stmt = null;
		 
		try{
			 stmt = getConnection().createStatement();
		     String sqlStr = "INSERT INTO caphe_java_db.material (`name`, `price`, `remaining`, `unit`) VALUES ('"
					 + material.getName() + "','"
					 + material.getPrice() + "','"
					 + material.getRemaining() + "','"
		    		 + material.getUnit() + "')";
		     createResult = stmt.executeUpdate(sqlStr) > 0; // Send the query to the server
		}catch(SQLException ex){
		     ex.printStackTrace();
		} 
		 
		return createResult;
	}
	
	public MaterialDTO getMaterial(int id){
		MaterialDTO material = new MaterialDTO();
		Statement stmt = null;
		
        try{
            stmt = getConnection().createStatement();
            String sqlStr = "SELECT * FROM material WHERE id = '" + id + "'";
            ResultSet rs = stmt.executeQuery(sqlStr); // Send the query to the server
			
			if(rs != null && rs.next()){
				material.setId(rs.getInt("id"));
				material.setName(rs.getString("name"));
				material.setPrice(rs.getInt("price"));
				material.setRemaining(rs.getInt("remaining"));
				material.setUnit(rs.getString("unit"));
            }
        }catch(SQLException ex){
            ex.printStackTrace();
		} 
		
        return material;
	}

	public ArrayList<MaterialDTO> searchMaterial(MaterialDTO materialInfo){
		ArrayList<MaterialDTO> materials = new ArrayList<>();    
		Statement stmt = null;
		
		try{     
			stmt = getConnection().createStatement();
			String sqlStr = "";
			
			if(materialInfo == null){
				sqlStr = "SELECT id, name, price, remaining, unit FROM material WHERE 1"
					+ " ORDER BY id ASC ";
			}else{
				sqlStr = "SELECT id, name, price, remaining, unit FROM material"
					+ " WHERE name LIKE '%" + materialInfo.getName() + "%'"
					+ " ORDER BY name ASC ";
			}

			ResultSet rs = stmt.executeQuery(sqlStr); // Send the query to the server
			while(rs != null && rs.next()){
				MaterialDTO material = new MaterialDTO();
				material.setId(rs.getInt("id"));
				material.setName(rs.getString("name"));
				material.setPrice(rs.getInt("price"));
				material.setRemaining(rs.getInt("remaining"));
				material.setUnit(rs.getString("unit"));
				materials.add(material);
			}          
		}catch(Exception ex){
			ex.printStackTrace();
		} 

		return materials;
	}

	public boolean updateMaterial(MaterialDTO material) {
		boolean updateResult = false;
		Statement stmt = null;
		
		try {
			stmt = getConnection().createStatement();
			String sqlStr = "UPDATE material SET " 
					+ " name = '" + material.getName() + "',"
					// + " price = '" + material.getPrice() + "'"
					+ " price = '" + material.getPrice() + ","
					+ " remaining = '" + material.getRemaining() + ","
					+ " unit = '" + material.getUnit() + "'"
					+ " WHERE id = " + material.getId(); 
			updateResult = stmt.executeUpdate(sqlStr) > 0; // Send the query to the server
		} catch (SQLException ex) {
			ex.printStackTrace();
		} 
		return updateResult;
    }

   public boolean deleteMaterial(int id){
		Statement stmt = null;
		boolean deleteResult = false;

		try{
		stmt = getConnection().createStatement();
		String sqlStr = "DELETE FROM caphe_java_db.material WHERE id = " + id;
		deleteResult = stmt.executeUpdate(sqlStr) > 0; // Send the query to the server
		}catch(SQLException ex){
			ex.printStackTrace();
		}

		return deleteResult;	 
   }

    public boolean deleteMaterialInList(ArrayList<MaterialDTO> materialDeleteList){
		for(int i = 0; i < materialDeleteList.size(); i++){
			MaterialDTO getMaterial = materialDeleteList.get(i);

			if (this.deleteMaterial(getMaterial.getId()) == false){
				return false;
			}
		}
		return true;
    }

	// public ArrayList<MaterialDTO> searchMaterial(MaterialDTO materialInfo) {
	// 	ArrayList<MaterialDTO> materials = new ArrayList<>();    
    //     Statement stmt = null;
    //     try {     
    //         stmt = getConnection().createStatement();
    //         String sqlStr = "";
    //         if (materialInfo == null)
    //         {
    //         	 sqlStr = "SELECT * "
    //         		+ " FROM caphe_java_db.material"
    //         		+ " ORDER BY id ASC ";
    //         }
    //         else
    //         {
    //         	sqlStr = "SELECT * "
    //         		+ " FROM caphe_java_db.material"
    //         		+ " WHERE name LIKE '%" + materialInfo.getName() + "%'";
    //         }
    //         ResultSet rs = stmt.executeQuery(sqlStr); // Send the query to the server
    //         while (rs != null && rs.next()) {
    //         	MaterialDTO material = new MaterialDTO();
    //         	material.setId(rs.getInt("id"));
    //         	material.setName(rs.getString("name"));
    //         	material.setPrice(rs.getInt("price"));
    //         	materials.add(material);
    //         }          
    //     } catch (Exception ex) {
    //         ex.printStackTrace();
    //     } 
        
    //     return materials;
	// }

}
