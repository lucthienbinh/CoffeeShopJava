package coffeeshop.bo;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import coffeeshop.dto.UserDTO;
import coffeeshop.mapper.UserMapper;

public class UserBO {
    private ServletContext context;

    public UserBO(ServletContext context) {
        this.context = context;
    }
    
    public UserDTO getUser(String email) {
    	UserDTO user = null;
        UserMapper mapper = null;
        try {
        	mapper = new UserMapper();
        	user = mapper.getUser(email);
        } catch (Exception ex) {
            Logger.getLogger(UserBO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                mapper.closeConnection();
            } catch (Exception ex) {
                Logger.getLogger(UserBO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
        return user;
    }
    public UserDTO getUserWithPassword(String username) {
    	UserDTO user = null;
        UserMapper mapper = null;
        try {
        	mapper = new UserMapper();
        	user = mapper.getUserWithPassword(username);
        } catch (Exception ex) {
            Logger.getLogger(UserBO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                mapper.closeConnection();
            } catch (Exception ex) {
                Logger.getLogger(UserBO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
        return user;
    }
    public boolean loginUser(String email, String password) {
    	boolean loginResult = false;
    	UserMapper mapper = null;
		try {
			mapper = new UserMapper();
			loginResult = mapper.loginUser(email, password);
		} catch (Exception ex) {
		     Logger.getLogger(UserBO.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			try {
		        mapper.closeConnection();
		    } catch (Exception ex) {
		        Logger.getLogger(UserBO.class.getName()).log(Level.SEVERE, null, ex);
		    }
		}
		return loginResult;
    }
    
    public boolean createUser(UserDTO user) {
    	boolean createResult = false;
        UserMapper mapper = null;
        try {
            mapper = new UserMapper();
            createResult = mapper.createUser(user);
        } catch (Exception ex) {
            Logger.getLogger(UserBO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try {
                mapper.closeConnection();
            } catch (Exception ex) {
                Logger.getLogger(UserBO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        return createResult;
    }
    public ArrayList<UserDTO> searchUser(UserDTO user) {
        ArrayList<UserDTO> users = null;
        UserMapper mapper = null;
        try {
            mapper = new UserMapper();
            users = mapper.searchUser(user);
        } catch (Exception ex) {
            Logger.getLogger(UserBO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try {
                mapper.closeConnection();
            } catch (Exception ex) {
                Logger.getLogger(UserBO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return users;
    }
    public ArrayList<UserDTO> updateUserDeleteList
	    (ArrayList<UserDTO> userDeleteList, String currentEmail) {
    	UserMapper userMapper = null;
    	ArrayList<UserDTO> userNewArray = new ArrayList<UserDTO>();
	    try {
	    	userMapper = new UserMapper();
	    	UserDTO user = userMapper.getUser(currentEmail);
	    	if (user != null) {
	    		//store user information to usersSelected
		        if (userDeleteList == null) {
		        	userNewArray.add(user);
		        } else {
		            //if User name is exist or not 
		            boolean usernameExisted = false;
		            for (int i = 0; i < userDeleteList.size(); i++) {
		            	UserDTO getUser = userDeleteList.get(i);
		                if (getUser.getEmail().equals(currentEmail)) {
		                	usernameExisted = true;
		                	continue;
		                }
		                userNewArray.add(getUser);
		            }
		
		            //if User name isn't exist
		            if (usernameExisted == false) {
		            	userNewArray.add(user);
		            }
		        }
	    	}
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }
	    finally {
	        if (userMapper != null) try {
	        	userMapper.closeConnection();
	        } catch (Exception ex) {
	            Logger.getLogger(UserBO.class.getName()).log(Level.SEVERE, null, ex);
	        }
	    }
	
	    return userNewArray;
	}
    public boolean deleteUserInList(ArrayList<UserDTO> userDeleteList) {
    	boolean deleteResult = false;
        UserMapper mapper = null;
        try {
            mapper = new UserMapper();
            deleteResult = mapper.deleteUserInList(userDeleteList);
        } catch (Exception ex) {
            Logger.getLogger(UserBO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try {
                mapper.closeConnection();
            } catch (Exception ex) {
                Logger.getLogger(UserBO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        return deleteResult;
    }

	public boolean updateUser(UserDTO user) {
		boolean updateResult = false;
        UserMapper mapper = null;
        try {
            mapper = new UserMapper();
            updateResult = mapper.updateUser(user);
        } catch (Exception ex) {
            Logger.getLogger(UserBO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try {
                mapper.closeConnection();
            } catch (Exception ex) {
                Logger.getLogger(UserBO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        return updateResult;
	}

	public boolean updateUserPassword(UserDTO user) {
		boolean updateResult = false;
        UserMapper mapper = null;
        try {
            mapper = new UserMapper();
            updateResult = mapper.updateUserPassword(user);
        } catch (Exception ex) {
            Logger.getLogger(UserBO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try {
                mapper.closeConnection();
            } catch (Exception ex) {
                Logger.getLogger(UserBO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        return updateResult;
	}
	
	public boolean authorizationUser(HttpSession session, int roleRequire) {
		if (session.isNew() || session.getAttribute("email") == null) {
			return false;
		} else {
			String currentUserEmail = session.getAttribute("email").toString();
			UserDTO user = this.getUser(currentUserEmail);
			if (user == null) {
				return false;
			} else {
				if(user.getGroupid() == roleRequire) {
					return true;
				} else {
					return false;
				}
			}
		}
	}
}
