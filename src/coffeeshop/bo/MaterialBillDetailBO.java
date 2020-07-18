package coffeeshop.bo;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContext;

import coffeeshop.dto.OrderMenuDTO;
import coffeeshop.dto.MaterialBilllDTO;
import coffeeshop.dto.MaterialBillDetailDTO;
import coffeeshop.mapper.OrderMenuMapper;
import coffeeshop.mapper.MaterialBillDetailMapper;
import coffeeshop.mapper.MaterialBillMapper;

public class MaterialBillDetailBO {
    private ServletContext context;

    public MaterialBillDetailBO(ServletContext context) {
        this.context = context;
    }
	
    public ArrayList<MaterialBillDetailDTO> updateOrderBillDetailList
    (ArrayList<MaterialBillDetailDTO> materialBillDetailList, int orderMenuId, boolean add){

        OrderMenuMapper orderMenuMapper = null;
        ArrayList<MaterialBillDetailDTO> materialBillDetailNewArray = new ArrayList<MaterialBillDetailDTO>();
        MaterialBillDetailDTO materialBillDetail = new MaterialBillDetailDTO();
        
        try{
            orderMenuMapper = new OrderMenuMapper();
            
            OrderMenuDTO orderMenu = orderMenuMapper.getOrderMenu(orderMenuId);
            materialBillDetail.setOrderMenuId(orderMenu.getId());
            materialBillDetail.setOrderMenuName(orderMenu.getName());
            materialBillDetail.setQuantity(1);
            materialBillDetail.setPrice(orderMenu.getPrice());
            materialBillDetail.setAmount(orderMenu.getPrice());
            
            if(orderMenu != null){
                // if materialBillDetailNewArray is empty
                if(materialBillDetailList == null){
                    materialBillDetailNewArray.add(materialBillDetail);
                }else{
                    //if orderMenu is exist or not 
                    boolean orderMenuIsExisted = false;
                    
                    for(int i = 0; i < materialBillDetailList.size(); i++){

                    	MaterialBillDetailDTO getMaterialBillDetail = materialBillDetailList.get(i);
                        
                        if(getMaterialBillDetail.getOrderMenuId() == orderMenuId){
                        	orderMenuIsExisted = true; 
                        	int quantity = getMaterialBillDetail.getQuantity();
                        	if (add == true) {
                        		quantity++;
                        		getMaterialBillDetail.setQuantity(quantity);
                        	}else{
                        		if (quantity == 1){
                        			// continue to not add orderMenu to new array ~ delete orderMenu in materialBillDetailList
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
        
                    //if orderMenu isn't exist
                    if(orderMenuIsExisted == false){
                    	materialBillDetailNewArray.add(materialBillDetail);
                    }
                }
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            if(orderMenuMapper != null) try{
                orderMenuMapper.closeConnection();
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
