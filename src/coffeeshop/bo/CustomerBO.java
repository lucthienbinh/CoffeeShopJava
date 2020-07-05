package coffeeshop.bo;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContext;

import coffeeshop.dto.CustomerDTO;
import coffeeshop.dto.UserDTO;
import coffeeshop.mapper.CustomerMapper;
import coffeeshop.mapper.UserMapper;

public class CustomerBO {
    private ServletContext context;

    public CustomerBO(ServletContext context) {
        this.context = context;
    }
	
	public ArrayList<CustomerDTO> searchCustomer(CustomerDTO customer) {
        ArrayList<CustomerDTO> customers = null;
        CustomerMapper mapper = null;
        try {
            mapper = new CustomerMapper();
            customers = mapper.searchCustomer(customer);
        } catch (Exception ex) {
            Logger.getLogger(CustomerBO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try {
                mapper.closeConnection();
            } catch (Exception ex) {
                Logger.getLogger(CustomerBO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return customers;
    }

    public CustomerDTO getCustomer(String email) {
    	CustomerDTO customer = null;
    	CustomerMapper mapper = null;
        try {
        	mapper = new CustomerMapper();
        	customer = mapper.getCustomer(email);
        } catch (Exception ex) {
            Logger.getLogger(CustomerBO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                mapper.closeConnection();
            } catch (Exception ex) {
                Logger.getLogger(CustomerBO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
        return customer;
    }
    
    public CustomerDTO getCustomerById(int id) {
    	CustomerDTO customer = null;
    	CustomerMapper mapper = null;
        try {
        	mapper = new CustomerMapper();
        	customer = mapper.getCustomerById(id);
        } catch (Exception ex) {
            Logger.getLogger(CustomerBO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                mapper.closeConnection();
            } catch (Exception ex) {
                Logger.getLogger(CustomerBO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
        return customer;
    }
	
	public ArrayList<CustomerDTO> updateCustomerDeleteList(ArrayList<CustomerDTO> customerDeleteList,
			String currentEmail) {
		CustomerMapper mapper = null;
    	ArrayList<CustomerDTO> customerNewArray = new ArrayList<CustomerDTO>();
	    try {
	    	mapper = new CustomerMapper();
	    	CustomerDTO customer = mapper.getCustomer(currentEmail);
	    	if (customer != null) {
	    		// if user delete list is empty
		        if (customerDeleteList == null) {
		        	customerNewArray.add(customer);
		        } else {
		        	//if email is exist or not 
		            boolean emailExisted = false;
		            for (int i = 0; i < customerDeleteList.size(); i++) {
		            	CustomerDTO getUser = customerDeleteList.get(i);
		                if (getUser.getEmail().equals(currentEmail)) {
		                	emailExisted = true;
		                	// continue to not add user to new array ~ delete user in delete list
		                	continue;
		                }
		                customerNewArray.add(getUser);
		            }
		
		            //if email isn't exist
		            if (emailExisted == false) {
		            	customerNewArray.add(customer);
		            }
		        }
	    	}
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }
	    finally {
	        if (mapper != null) try {
	        	mapper.closeConnection();
	        } catch (Exception ex) {
	            Logger.getLogger(CustomerBO.class.getName()).log(Level.SEVERE, null, ex);
	        }
	    }
	
	    return customerNewArray;
	}

	public boolean deleteUserInList(ArrayList<CustomerDTO> customerDeleteList) {
		boolean deleteResult = false;
		CustomerMapper mapper = null;
        try {
            mapper = new CustomerMapper();
            deleteResult = mapper.deleteUserInList(customerDeleteList);
        } catch (Exception ex) {
            Logger.getLogger(CustomerBO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try {
                mapper.closeConnection();
            } catch (Exception ex) {
                Logger.getLogger(CustomerBO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        return deleteResult;
    }
    
    public boolean createCustomer(CustomerDTO customer){
    	boolean createResult = false;
        CustomerMapper mapper = null;
        
        try{
            mapper = new CustomerMapper();
            createResult = mapper.createCustomer(customer);
        }catch(Exception ex){
            Logger.getLogger(CustomerBO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try{
                mapper.closeConnection();
            }catch(Exception ex){
                Logger.getLogger(CustomerBO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        
        return createResult;
    }
}
