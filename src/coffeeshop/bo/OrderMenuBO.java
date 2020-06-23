package coffeeshop.bo;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContext;

import coffeeshop.dto.OrderMenuDTO;
import coffeeshop.mapper.OrderMenuMapper;

public class OrderMenuBO {
	private ServletContext context;

    public OrderMenuBO(ServletContext context) {
        this.context = context;
    }
    
    public boolean createOrderMenu(OrderMenuDTO orderMenu) {
    	boolean createResult = false;
        OrderMenuMapper mapper = null;
        try {
            mapper = new OrderMenuMapper();
            createResult = mapper.createOrderMenu(orderMenu);
        } catch (Exception ex) {
            Logger.getLogger(OrderMenuBO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try {
                mapper.closeConnection();
            } catch (Exception ex) {
                Logger.getLogger(OrderMenuBO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        return createResult;
    }

	public OrderMenuDTO getOrderMenu(String name) {
		OrderMenuDTO orderMenu = null;
		OrderMenuMapper mapper = null;
        try {
        	mapper = new OrderMenuMapper();
        	orderMenu = mapper.getOrderMenu(name);
        } catch (Exception ex) {
            Logger.getLogger(OrderMenuBO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                mapper.closeConnection();
            } catch (Exception ex) {
                Logger.getLogger(OrderMenuBO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
        return orderMenu;
	}
}
