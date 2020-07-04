package coffeeshop.bo;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContext;

import coffeeshop.dto.OrderingBillDTO;
import coffeeshop.mapper.OrderingBillMapper;

public class OrderingBillBO {
    private ServletContext context;

    public OrderingBillBO(ServletContext context) {
        this.context = context;
    }
    
	public ArrayList<OrderingBillDTO> searchOrderingBill(OrderingBillDTO orderingBill) {
        ArrayList<OrderingBillDTO> orderingBills = null;
        OrderingBillMapper mapper = null;
        try {
            mapper = new OrderingBillMapper();
            orderingBills = mapper.searchOrderingBill(orderingBill);
        } catch (Exception ex) {
            Logger.getLogger(OrderingBillBO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try {
                mapper.closeConnection();
            } catch (Exception ex) {
                Logger.getLogger(OrderingBillBO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return orderingBills;
    }

	public int createOrderingBill(OrderingBillDTO orderingBill) {
		int LAST_INSERT_ID = 0;
		OrderingBillMapper mapper = null;
        try {
            mapper = new OrderingBillMapper();
            LAST_INSERT_ID = mapper.createOrderingBill(orderingBill);
        } catch (Exception ex) {
            Logger.getLogger(OrderingBillBO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try {
                mapper.closeConnection();
            } catch (Exception ex) {
                Logger.getLogger(OrderingBillBO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        return LAST_INSERT_ID;
	}
	
	public OrderingBillDTO getOrderingBill(int id) {
		OrderingBillDTO orderingBill = new OrderingBillDTO() ;
		orderingBill.setId(id);
		OrderingBillMapper mapper = null;
        try {
        	mapper = new OrderingBillMapper();
        	orderingBill = mapper.searchOrderingBill(orderingBill).get(0);
        } catch (Exception ex) {
            Logger.getLogger(OrderingBillBO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                mapper.closeConnection();
            } catch (Exception ex) {
                Logger.getLogger(OrderingBillBO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
        return orderingBill;
    }

	public ArrayList<OrderingBillDTO> updateOrderingBillDeleteList(
			ArrayList<OrderingBillDTO> orderingBillDeleteList, int id) {
        ArrayList<OrderingBillDTO> orderingBillNewArray = new ArrayList<OrderingBillDTO>();

    	OrderingBillDTO orderingBill = this.getOrderingBill(id);
        
        if(orderingBill != null){
            // if orderingBill delete list is empty
            if(orderingBillDeleteList == null){
            	orderingBillNewArray.add(orderingBill);
            }else{
                //if id is exist or not 
                boolean orderingBillIdExisted = false;
                
                for(int i = 0; i < orderingBillDeleteList.size(); i++){
                	OrderingBillDTO getOrderingBill = orderingBillDeleteList.get(i);
                    if(getOrderingBill.getId() == id){
                    	orderingBillIdExisted = true;
                        // continue to not add orderMenu to new array ~ delete orderMenu in delete list
                        continue;
                    }
                    orderingBillNewArray.add(getOrderingBill);
                }
    
                //if id isn't exist
                if(orderingBillIdExisted == false){
                	orderingBillNewArray.add(orderingBill);
                }
            }
        }

        return orderingBillNewArray;
	}

	public boolean deleteOrderingBillInList(ArrayList<OrderingBillDTO> orderingBillDeleteList) {
		boolean deleteResult = false;
		OrderingBillDetailBO orderingBillDetailBO = new OrderingBillDetailBO(context);
		
		OrderingBillMapper mapper = null;
        try {
            mapper = new OrderingBillMapper();
            for (int i = 0; i < orderingBillDeleteList.size(); i++) {
    			OrderingBillDTO getOrderingBill = orderingBillDeleteList.get(i);
    			int currentId = getOrderingBill.getId();
    			deleteResult = orderingBillDetailBO.deleteOrderingBillDetailByOrderingBillId(currentId);
            	if (deleteResult == true)
            	{
            		deleteResult = mapper.deleteOrderingBill(currentId);
            		if (deleteResult == false)
            			return deleteResult;
            	} else {
            		return deleteResult;
            	}
            }
        } catch (Exception ex) {
            Logger.getLogger(OrderingBillBO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try {
                mapper.closeConnection();
            } catch (Exception ex) {
                Logger.getLogger(OrderingBillBO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        return deleteResult;
	}
}
