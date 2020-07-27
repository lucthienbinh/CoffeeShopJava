package coffeeshop.bo;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContext;

import coffeeshop.dto.MaterialDTO;
import coffeeshop.dto.MaterialBilllDTO;
import coffeeshop.dto.MaterialBillDetailDTO;
import coffeeshop.mapper.MaterialMapper;
import coffeeshop.mapper.MaterialBillDetailMapper;
import coffeeshop.mapper.MaterialBillMapper;

public class MaterialBillDetailBO {
    private ServletContext context;

    public MaterialBillDetailBO(ServletContext context) {
        this.context = context;
    }
	
    public ArrayList<MaterialBillDetailDTO> updateMaterialBillDetailList
    (ArrayList<MaterialBillDetailDTO> materialBillDetailList, int materialId, boolean add){

        MaterialMapper materialMapper = null;
        ArrayList<MaterialBillDetailDTO> materialBillDetailNewArray = new ArrayList<MaterialBillDetailDTO>();
        MaterialBillDetailDTO materialBillDetail = new MaterialBillDetailDTO();
        
        try{
            materialMapper = new MaterialMapper();
            
            MaterialDTO material = materialMapper.getMaterial(materialId);
            materialBillDetail.setMaterialId(material.getId());
            materialBillDetail.setMaterialName(material.getName());
            materialBillDetail.setQuantity(1);
            materialBillDetail.setPrice(material.getPrice());
            materialBillDetail.setAmount(material.getPrice());
            
            if(material != null){
                // if materialBillDetailNewArray is empty
                if(materialBillDetailList == null){
                    materialBillDetailNewArray.add(materialBillDetail);
                }else{
                    //if material is exist or not 
                    boolean materialIsExisted = false;
                    
                    for(int i = 0; i < materialBillDetailList.size(); i++){
                    	MaterialBillDetailDTO getMaterialBillDetail = materialBillDetailList.get(i);
                        
                        if(getMaterialBillDetail.getMaterialId() == materialId){
                        	materialIsExisted = true; 
                        	int quantity = getMaterialBillDetail.getQuantity();
                        	if (add == true) {
                        		quantity++;
                        		getMaterialBillDetail.setQuantity(quantity);
                        	}else{
                        		if (quantity == 1){
                        			// continue to not add material to new array ~ delete material in materialBillDetailList
                        			continue;
                                }
                                
                        		quantity--;
                        		getMaterialBillDetail.setQuantity(quantity);
                            }
                            
                        	int amount = getMaterialBillDetail.getPrice() * getMaterialBillDetail.getQuantity();
                        	getMaterialBillDetail.setAmount(amount);
                        }

                        materialBillDetailNewArray.add(getMaterialBillDetail);
                    }
        
                    //if material isn't exist
                    if(materialIsExisted == false){
                    	materialBillDetailNewArray.add(materialBillDetail);
                    }
                }
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            if(materialMapper != null) try{
                materialMapper.closeConnection();
            }catch (Exception ex){
                Logger.getLogger(MaterialBillDetailBO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return materialBillDetailNewArray;
    }
    
    public int caculateTotalPrice (ArrayList<MaterialBillDetailDTO> materialBillDetailList){
        int newTotalPrice = 0;
        
    	for(int i = 0; i < materialBillDetailList.size(); i++){
    		MaterialBillDetailDTO getMaterialBillDetail = materialBillDetailList.get(i);
    		newTotalPrice += getMaterialBillDetail.getAmount();
    	}
    	return newTotalPrice;
    }

	public boolean createMaterialBillDetail(ArrayList<MaterialBillDetailDTO> materialBillDetailList, int materialBillId){
		boolean createResult = false;
        MaterialBillDetailMapper mapper = null;
        
        try{
            mapper = new MaterialBillDetailMapper();

            for(int i = 0; i < materialBillDetailList.size(); i++){
            	MaterialBillDetailDTO getMaterialBillDetail = materialBillDetailList.get(i);
            	createResult = mapper.createMaterialBillDetail(getMaterialBillDetail, materialBillId);
                
                if (createResult == false){
            		break;
            	}
            }
        }catch(Exception ex){
            Logger.getLogger(MaterialBillDetailBO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try{
                mapper.closeConnection();
            }catch(Exception ex){
                Logger.getLogger(MaterialBillDetailBO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 

        return createResult;
	}

	public ArrayList<MaterialBillDetailDTO> getMaterialBillDetails(int materialBillId){
        ArrayList<MaterialBillDetailDTO> materialBillDetails = null;
        MaterialBillDetailMapper mapper = null;
        
        try{
            mapper = new MaterialBillDetailMapper();
            materialBillDetails = mapper.getMaterialBillDetails(materialBillId);
        }catch(Exception ex){
            Logger.getLogger(MaterialBillDetailBO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try{
                mapper.closeConnection();
            }catch(Exception ex){
                Logger.getLogger(MaterialBillDetailBO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return materialBillDetails;
	}
	
	public boolean deleteMaterialBillDetailByMaterialBillId(int id){
		boolean deleteResult = false;
		MaterialBillDetailMapper mapper = null;
        
        try{
            mapper = new MaterialBillDetailMapper();
            deleteResult = mapper.deleteMaterialBillDetailByMaterialBillId(id);
        }catch(Exception ex){
            Logger.getLogger(MaterialBillDetailBO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try{
                mapper.closeConnection();
            }catch(Exception ex){
                Logger.getLogger(MaterialBillDetailBO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        
        return deleteResult;
	}
}
