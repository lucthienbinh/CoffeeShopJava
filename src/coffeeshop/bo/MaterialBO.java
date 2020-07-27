package coffeeshop.bo;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;

import coffeeshop.dto.MaterialDTO;
// import coffeeshop.dto.OrderingBillDTO;
import coffeeshop.mapper.MaterialMapper;
// import coffeeshop.mapper.OrderingBillMapper;

public class MaterialBO {
	private ServletContext context;

    public MaterialBO(ServletContext context) {
        this.context = context;
    }
    
    public boolean createMaterial(MaterialDTO material) {
    	boolean createResult = false;
        MaterialMapper mapper = null;
        try {
            mapper = new MaterialMapper();
            createResult = mapper.createMaterial(material);
        } catch (Exception ex) {
            Logger.getLogger(MaterialBO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try {
                mapper.closeConnection();
            } catch (Exception ex) {
                Logger.getLogger(MaterialBO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        return createResult;
    }

	public static MaterialDTO getMaterial(int id) {
		MaterialDTO material = null;
		MaterialMapper mapper = null;
        try {
        	mapper = new MaterialMapper();
        	material = mapper.getMaterial(id);
        } catch (Exception ex) {
            Logger.getLogger(MaterialBO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                mapper.closeConnection();
            } catch (Exception ex) {
                Logger.getLogger(MaterialBO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
        return material;
    }
    
    public static ArrayList<MaterialDTO> searchMaterial(MaterialDTO material) {
        ArrayList<MaterialDTO> materials = null;
        MaterialMapper mapper = null;
        
        try{
            mapper = new MaterialMapper();
            materials = mapper.searchMaterial(material);
        }catch(Exception ex){
            Logger.getLogger(MaterialBO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try{
                mapper.closeConnection();
            }catch(Exception ex){
                Logger.getLogger(MaterialBO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return materials;
    }

    public static boolean updateMaterial(MaterialDTO material) {
		boolean updateResult = false;
        MaterialMapper mapper = null;

        try{
            mapper = new MaterialMapper();
            updateResult = mapper.updateMaterial(material);
        }catch(Exception ex){
            Logger.getLogger(MaterialBO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try{
                mapper.closeConnection();
            }catch(Exception ex) {
                Logger.getLogger(MaterialBO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        return updateResult;
    }
    
    public static boolean deleteMaterialInList(ArrayList<MaterialDTO> materialDeleteList){
    	boolean deleteResult = false;
        MaterialMapper mapper = null;

        try{
            mapper = new MaterialMapper();
            deleteResult = mapper.deleteMaterialInList(materialDeleteList);
        }catch(Exception ex){
            Logger.getLogger(MaterialBO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try{
                mapper.closeConnection();
            }catch(Exception ex){
                Logger.getLogger(MaterialBO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        
        return deleteResult;
    }

    public static ArrayList<MaterialDTO> updateMaterialDeleteList
    (ArrayList<MaterialDTO> materialDeleteList, int id) {

        MaterialMapper materialMapper = null;
        ArrayList<MaterialDTO> materialNewArray = new ArrayList<MaterialDTO>();
        
        try{
            materialMapper = new MaterialMapper();
            MaterialDTO material = materialMapper.getMaterial(id);
            
            if(material != null){
                // if material delete list is empty
                if(materialDeleteList == null){
                    materialNewArray.add(material);
                }else{
                    //if email is exist or not 
                    boolean materialnameExisted = false;
                    
                    for(int i = 0; i < materialDeleteList.size(); i++){
                        MaterialDTO getMaterial = materialDeleteList.get(i);
                        if(getMaterial.getId() == id){
                            materialnameExisted = true;
                            // continue to not add material to new array ~ delete material in delete list
                            continue;
                        }
                        materialNewArray.add(getMaterial);
                    }
        
                    //if id isn't exist
                    if(materialnameExisted == false){
                        materialNewArray.add(material);
                    }
                }
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            if(materialMapper != null)try {
                materialMapper.closeConnection();
            }catch (Exception ex) {
                Logger.getLogger(MaterialBO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return materialNewArray;
    }
}
