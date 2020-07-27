package coffeeshop.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import coffeeshop.dto.MaterialBilllDTO;

public class MaterialBillMapper extends DBMapper {

	public MaterialBillMapper() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ArrayList<MaterialBilllDTO> searchMaterialBill(MaterialBilllDTO materialBillInfo){
        ArrayList<MaterialBilllDTO> materialBills = new ArrayList<>();   
        Statement stmt = null;
		
		try{     
            stmt = getConnection().createStatement();
            String sqlStr = "";
            sqlStr = "SELECT materialbill.id, users.firstname, users.lastname, materialbill.totalPrice"
            		+ " FROM caphe_java_db.materialbill "
            		+ " LEFT JOIN caphe_java_db.users ON materialbill.userId = users.id ";
					
					if (materialBillInfo != null){
            			if (materialBillInfo.getId() != 0){
            				sqlStr += " WHERE materialbill.id = " + materialBillInfo.getId();
            			}else{
            				sqlStr += " WHERE concat(users.firstname, ' ', users.lastname) LIKE '%" + materialBillInfo.getUserName()+ "%'";
	            			if (materialBillInfo.getTotalPrice() != 0){
								sqlStr += " AND materialbill.totalPrice = " + materialBillInfo.getTotalPrice();
							}
            			}
            		}
            		sqlStr += " ORDER BY materialbill.id ASC ";
            			
			ResultSet rs = stmt.executeQuery(sqlStr); // Send the query to the server
            while (rs != null && rs.next()){
            	MaterialBilllDTO materialBill = new MaterialBilllDTO();
            	materialBill.setId(rs.getInt("id"));
            	materialBill.setUserName(rs.getString("firstname")+" "+rs.getString("lastname"));
            	materialBill.setTotalPrice(rs.getInt("totalPrice"));
            	materialBills.add(materialBill);
            }   
        }catch(Exception ex){
            ex.printStackTrace();
        } 
        
        return materialBills;
	}

	public int createMaterialBill(MaterialBilllDTO materialBill){
		int lastId = 0;
		Statement stmt = null;
		 
		try{
			stmt = getConnection().createStatement();
		    String sqlStr = "INSERT INTO caphe_java_db.materialbill (`userId`,`totalPrice`) VALUES ('"
    				 + materialBill.getUserId()+ "','"
					 + materialBill.getTotalPrice() + "')";
					 
		    if (stmt.executeUpdate(sqlStr, Statement.RETURN_GENERATED_KEYS) > 0){
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

	public boolean deleteMaterialBill(int id){
		Statement stmt = null;
		boolean deleteResult = false;
		
		try{
           stmt = getConnection().createStatement();
           String sqlStr = "DELETE FROM caphe_java_db.materialbill WHERE id = " + id;
           deleteResult = stmt.executeUpdate(sqlStr) > 0; // Send the query to the server
		}catch(SQLException ex){
			ex.printStackTrace();
		} 

        return deleteResult;	 
	}

	public boolean deleteMaterialBillInList(ArrayList<MaterialBilllDTO> materialBillDeleteList){
		for(int i = 0; i < materialBillDeleteList.size(); i++){
			MaterialBilllDTO getMaterialBill = materialBillDeleteList.get(i);
			
			if (this.deleteMaterialBill(getMaterialBill.getId()) == false){
        		return false;
        	}
		}
		
		return true;
	}
}
