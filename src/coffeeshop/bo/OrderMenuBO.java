package coffeeshop.bo;

import java.util.ArrayList;
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

	public static OrderMenuDTO getOrderMenu(int id) {
		OrderMenuDTO orderMenu = null;
		OrderMenuMapper mapper = null;
        try {
        	mapper = new OrderMenuMapper();
        	orderMenu = mapper.getOrderMenu(id);
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
    
    public static ArrayList<OrderMenuDTO> searchOrderMenu(OrderMenuDTO orderMenu) {
        ArrayList<OrderMenuDTO> orderMenus = null;
        OrderMenuMapper mapper = null;
        
        try{
            mapper = new OrderMenuMapper();
            orderMenus = mapper.searchOrderMenu(orderMenu);
        }catch(Exception ex){
            Logger.getLogger(OrderMenuBO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try{
                mapper.closeConnection();
            }catch(Exception ex){
                Logger.getLogger(OrderMenuBO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return orderMenus;
    }

    public static boolean updateOrderMenu(OrderMenuDTO orderMenu) {
		boolean updateResult = false;
        OrderMenuMapper mapper = null;

        try{
            mapper = new OrderMenuMapper();
            updateResult = mapper.updateOrderMenu(orderMenu);
        }catch(Exception ex){
            Logger.getLogger(OrderMenuBO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try{
                mapper.closeConnection();
            }catch(Exception ex) {
                Logger.getLogger(OrderMenuBO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        return updateResult;
    }
    
    public boolean deleteOrderMenuInList(ArrayList<OrderMenuDTO> orderMenuDeleteList) {
    	boolean deleteResult = false;
        OrderMenuMapper mapper = null;

        try{
            mapper = new OrderMenuMapper();
            deleteResult = mapper.deleteOrderMenuInList(orderMenuDeleteList);
        }catch(Exception ex){
            Logger.getLogger(OrderMenuBO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try{
                mapper.closeConnection();
            }catch(Exception ex){
                Logger.getLogger(OrderMenuBO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        
        return deleteResult;
    }

    public static ArrayList<OrderMenuDTO> updateOrderMenuDeleteList
    (ArrayList<OrderMenuDTO> orderMenuDeleteList, int id) {

        OrderMenuMapper orderMenuMapper = null;
        ArrayList<OrderMenuDTO> orderMenuNewArray = new ArrayList<OrderMenuDTO>();
        
        try{
            orderMenuMapper = new OrderMenuMapper();
            OrderMenuDTO orderMenu = orderMenuMapper.getOrderMenu(id);
            
            if(orderMenu != null){
                // if orderMenu delete list is empty
                if(orderMenuDeleteList == null){
                    orderMenuNewArray.add(orderMenu);
                }else{
                    //if email is exist or not 
                    boolean orderMenunameExisted = false;
                    
                    for(int i = 0; i < orderMenuDeleteList.size(); i++){
                        OrderMenuDTO getOrderMenu = orderMenuDeleteList.get(i);
                        if(getOrderMenu.getId() == id){
                            orderMenunameExisted = true;
                            // continue to not add orderMenu to new array ~ delete orderMenu in delete list
                            continue;
                        }
                        orderMenuNewArray.add(getOrderMenu);
                    }
        
                    //if id isn't exist
                    if(orderMenunameExisted == false){
                        orderMenuNewArray.add(orderMenu);
                    }
                }
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            if(orderMenuMapper != null)try {
                orderMenuMapper.closeConnection();
            }catch (Exception ex) {
                Logger.getLogger(OrderMenuBO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return orderMenuNewArray;
    }
}
