package coffeeshop.bo;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContext;

import coffeeshop.dto.OrderMenuDTO;
import coffeeshop.dto.OrderingBillDetailDTO;
import coffeeshop.mapper.OrderMenuMapper;

public class OrderingBillDetailBO {
    private ServletContext context;

    public OrderingBillDetailBO(ServletContext context) {
        this.context = context;
    }
	
    public ArrayList<OrderingBillDetailDTO> updateOrderBillDetailList
    (ArrayList<OrderingBillDetailDTO> orderingBillDetailList, int orderMenuId, boolean add) {

        OrderMenuMapper orderMenuMapper = null;
        ArrayList<OrderingBillDetailDTO> orderingBillDetailNewArray = new ArrayList<OrderingBillDetailDTO>();
        OrderingBillDetailDTO orderingBillDetail = new OrderingBillDetailDTO();
        
        try{
            orderMenuMapper = new OrderMenuMapper();
            
            OrderMenuDTO orderMenu = orderMenuMapper.getOrderMenu(orderMenuId);
            orderingBillDetail.setOrderMenuId(orderMenu.getId());
            orderingBillDetail.setQuantity(1);
            orderingBillDetail.setPrice(orderMenu.getPrice());
            
            if(orderMenu != null){
                // if orderingBillDetailNewArray is empty
                if(orderingBillDetailList == null)
                {
                    orderingBillDetailNewArray.add(orderingBillDetail);
                }
                else
                {
                    //if orderMenu is exist or not 
                    boolean orderMenuIsExisted = false;
                    
                    for(int i = 0; i < orderingBillDetailList.size(); i++){
                    	OrderingBillDetailDTO getOrderingBillDetail = orderingBillDetailList.get(i);
                        if(getOrderingBillDetail.getOrderMenuId() == orderMenuId){
                        	int quantity = getOrderingBillDetail.getQuantity();
                        	if (add == true) 
                        	{
                        		quantity++;
                        		getOrderingBillDetail.setQuantity(quantity);
                        	}
                        	else
                        	{
                        		if (quantity == 1)
                        		{
                        			// continue to not add orderMenu to new array ~ delete orderMenu in orderingBillDetailList
                        			continue;
                        		}
                        		quantity--;
                        		getOrderingBillDetail.setQuantity(quantity);
                        	}
                        	orderMenuIsExisted = true; 
                        }
                        orderingBillDetailNewArray.add(getOrderingBillDetail);
                    }
        
                    //if orderMenu isn't exist
                    if(orderMenuIsExisted == false){
                    	orderingBillDetailNewArray.add(orderingBillDetail);
                    }
                }
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            if(orderMenuMapper != null)try {
                orderMenuMapper.closeConnection();
            }catch (Exception ex) {
                Logger.getLogger(OrderingBillDetailBO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return orderingBillDetailNewArray;
    }
    
    public int caculateTotalPrice (ArrayList<OrderingBillDetailDTO> orderingBillDetailList) 
    {
    	int newTotalPrice = 0;
    	for(int i = 0; i < orderingBillDetailList.size(); i++){
    		OrderingBillDetailDTO getOrderingBillDetail = orderingBillDetailList.get(i);
    		newTotalPrice += getOrderingBillDetail.getPrice() * getOrderingBillDetail.getQuantity();
    	}
    	return newTotalPrice;
    }
}
