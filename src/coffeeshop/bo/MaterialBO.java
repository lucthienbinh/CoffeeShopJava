package coffeeshop.bo;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContext;

import coffeeshop.dto.MaterialDTO;
import coffeeshop.mapper.MaterialMapper;
import coffeeshop.mapper.UserMapper;

public class MaterialBO {
    private ServletContext context;

    public MaterialBO(ServletContext context) {
        this.context = context;
    }
    
    public MaterialDTO getMaterial(String name) {
    	MaterialDTO user = null;
    	MaterialMapper mapper = null;
        try {
        	mapper = new MaterialMapper();
        	user = mapper.getMaterial(name);
        } catch (Exception ex) {
            Logger.getLogger(MaterialBO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                mapper.closeConnection();
            } catch (Exception ex) {
                Logger.getLogger(MaterialBO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
        return user;
    }

	public boolean updateMaterial(MaterialDTO material) {
		boolean updateResult = false;
		MaterialMapper mapper = null;
        try {
            mapper = new MaterialMapper();
            updateResult = mapper.updateUser(material);
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
        return updateResult;
	}
}
