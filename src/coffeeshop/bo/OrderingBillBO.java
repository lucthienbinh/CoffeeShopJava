package coffeeshop.bo;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContext;

import coffeeshop.dto.OrderingBillDTO;
import coffeeshop.mapper.CustomerMapper;
import coffeeshop.mapper.OrderMenuMapper;
import coffeeshop.mapper.OrderingBillMapper;

public class OrderingBillBO {
    private ServletContext context;

    public OrderingBillBO(ServletContext context) {
        this.context = context;
    }
    
	public ArrayList<OrderingBillDTO> searchCustomer(OrderingBillDTO orderingBill) {
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
}
