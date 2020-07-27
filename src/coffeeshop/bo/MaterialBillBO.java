package coffeeshop.bo;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContext;

import coffeeshop.dto.MaterialBilllDTO;
import coffeeshop.mapper.MaterialBillMapper;

public class MaterialBillBO {
    private ServletContext context;

    public MaterialBillBO(ServletContext context){
        this.context = context;
    }
    
	public ArrayList<MaterialBilllDTO> searchMaterialBill(MaterialBilllDTO materialBill){
        ArrayList<MaterialBilllDTO> materialBills = null;
        MaterialBillMapper mapper = null;
        
        try{
            mapper = new MaterialBillMapper();
            materialBills = mapper.searchMaterialBill(materialBill);
        }catch(Exception ex){
            Logger.getLogger(MaterialBillBO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try{
                mapper.closeConnection();
            }catch(Exception ex){
                Logger.getLogger(MaterialBillBO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return materialBills;
    }

	public int createMaterialBill(MaterialBilllDTO materialBill){
		int LAST_INSERT_ID = 0;
		MaterialBillMapper mapper = null;
        
        try{
            mapper = new MaterialBillMapper();
            LAST_INSERT_ID = mapper.createMaterialBill(materialBill);
        }catch(Exception ex){
            Logger.getLogger(MaterialBillBO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try{
                mapper.closeConnection();
            }catch(Exception ex){
                Logger.getLogger(MaterialBillBO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        return LAST_INSERT_ID;
	}
	
	public MaterialBilllDTO getMaterialBill(int id){
		MaterialBilllDTO materialBill = new MaterialBilllDTO() ;
		materialBill.setId(id);
		MaterialBillMapper mapper = null;
        
        try{
        	mapper = new MaterialBillMapper();
        	materialBill = mapper.searchMaterialBill(materialBill).get(0);
        }catch(Exception ex){
            Logger.getLogger(MaterialBillBO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try{
                mapper.closeConnection();
            }catch(Exception ex){
                Logger.getLogger(MaterialBillBO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }     

        return materialBill;
    }

	public ArrayList<MaterialBilllDTO> updateMaterialBillDeleteList(
		ArrayList<MaterialBilllDTO> materialBillDeleteList, int id){
            
        ArrayList<MaterialBilllDTO> materialBillNewArray = new ArrayList<MaterialBilllDTO>();
    	MaterialBilllDTO materialBill = this.getMaterialBill(id);
        
        if(materialBill != null){
            // if materialBill delete list is empty
            if(materialBillDeleteList == null){
            	materialBillNewArray.add(materialBill);
            }else{
                //if id is exist or not 
                boolean materialBillIdExisted = false;
                
                for(int i = 0; i < materialBillDeleteList.size(); i++){
                	MaterialBilllDTO getMaterialBill = materialBillDeleteList.get(i);
                    if(getMaterialBill.getId() == id){
                    	materialBillIdExisted = true;
                        // continue to not add orderMenu to new array ~ delete orderMenu in delete list
                        continue;
                    }
                    materialBillNewArray.add(getMaterialBill);
                }
    
                //if id isn't exist
                if(materialBillIdExisted == false){
                	materialBillNewArray.add(materialBill);
                }
            }
        }

        return materialBillNewArray;
	}

	public boolean deleteMaterialBillInList(ArrayList<MaterialBilllDTO> materialBillDeleteList){
        
        boolean deleteResult = false;
		MaterialBillDetailBO materialBillDetailBO = new MaterialBillDetailBO(context);	
		MaterialBillMapper mapper = null;
        
        try{
            mapper = new MaterialBillMapper();
            
            for (int i = 0; i < materialBillDeleteList.size(); i++) {
    			MaterialBilllDTO getMaterialBill = materialBillDeleteList.get(i);
    			int currentId = getMaterialBill.getId();
    			deleteResult = materialBillDetailBO.deleteMaterialBillDetailByMaterialBillId(currentId);
            	if (deleteResult == true){
            		deleteResult = mapper.deleteMaterialBill(currentId);
                    if (deleteResult == false) return deleteResult;
            	}else{
            		return deleteResult;
            	}
            }
        }catch(Exception ex){
            Logger.getLogger(MaterialBillBO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try{
                mapper.closeConnection();
            }catch(Exception ex){
                Logger.getLogger(MaterialBillBO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        
        return deleteResult;
	}
}
