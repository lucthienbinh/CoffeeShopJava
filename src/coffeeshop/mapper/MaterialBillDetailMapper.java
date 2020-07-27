package coffeeshop.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import coffeeshop.dto.MaterialBillDetailDTO;

public class MaterialBillDetailMapper extends DBMapper {

	public MaterialBillDetailMapper() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public boolean createMaterialBillDetail(MaterialBillDetailDTO materialBillDetail, int materialBillId) {
		boolean createResult = false;
		Statement stmt = null;
		 
		try{
			 stmt = getConnection().createStatement();
		     String sqlStr = "INSERT INTO caphe_java_db.materialbilldetail (`materialId`, `quantity`,`materialBillId`) VALUES ('"
		    		 + materialBillDetail.getMaterialId() + "','"
    				 + materialBillDetail.getQuantity() + "','"
		    		 + materialBillId + "')";
		     createResult = stmt.executeUpdate(sqlStr) > 0; // Send the query to the server
		}catch(SQLException ex){
		     ex.printStackTrace();
	 	} 
		return createResult;
	}

	public ArrayList<MaterialBillDetailDTO> getMaterialBillDetails(int materialBillId){
		ArrayList<MaterialBillDetailDTO> materialBillDetails = new ArrayList<>();    
        Statement stmt = null;
		
		try{     
            stmt = getConnection().createStatement();
            String sqlStr = "";
        	sqlStr = "SELECT * "
        		+ " FROM caphe_java_db.materialbilldetail, caphe_java_db.material"
        		+ " WHERE materialbilldetail.materialId = material.id "
        		+ " AND materialbilldetail.materialBillId = " + materialBillId;
			ResultSet rs = stmt.executeQuery(sqlStr); // Send the query to the server
				
            while (rs != null && rs.next()) {
            	MaterialBillDetailDTO materialBillDetail = new MaterialBillDetailDTO();
            	materialBillDetail.setMaterialName((rs.getString("name")));
            	materialBillDetail.setQuantity(rs.getInt("quantity"));
				materialBillDetail.setPrice(rs.getInt("price"));
				
            	int amount = rs.getInt("price") * rs.getInt("quantity");
            	materialBillDetail.setAmount(amount);
            	materialBillDetails.add(materialBillDetail);
            }          
        }catch(Exception ex){
            ex.printStackTrace();
        } 
        
        return materialBillDetails;
	}

	public boolean deleteMaterialBillDetailByMaterialBillId(int materialBillId){
		Statement stmt = null;
		boolean deleteResult = false;
		
		try{
			 stmt = getConnection().createStatement();
			 String sqlStr = "DELETE FROM caphe_java_db.materialbilldetail WHERE materialBillId = " + materialBillId;
			 deleteResult = stmt.executeUpdate(sqlStr) > 0; // Send the query to the server
	    }catch(SQLException ex){
	          ex.printStackTrace();
	    } 
		
		return deleteResult;	 
	}

}
