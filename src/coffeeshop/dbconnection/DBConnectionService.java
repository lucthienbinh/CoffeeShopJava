
package coffeeshop.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnectionService {

    protected static void loadJDBCDriver() throws Exception {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (java.lang.ClassNotFoundException e) {
            throw new Exception("SQL JDBC Driver not found ...");
        }
    }

    public static Connection getConnection() throws Exception {
	    Connection connect = null;
	    if (connect == null) {
	    	loadJDBCDriver();
	    try {
	    
	    	connect = DriverManager.getConnection("jdbc:mysql://localhost:3308/caphe_java_db?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
	    			"root", "");
	    } catch (java.sql.SQLException e) {
	    	throw new Exception("Can not access to Database Server ..." + e.getMessage());
	    	}
	    }
	    return connect;
    }
    
    
//    public static Connection getConnection() throws Exception {
//        Connection connect = null;
//        if (connect == null) {
//            try {                
//                DataSource ds = (DataSource) new InitialContext().
//                        lookup("jdbc/j2ee_MySQLConnection");                
//                return ds.getConnection();
//            } catch (Exception e) {
//                throw new Exception("Can not access to Database Server ..." + e.getMessage());
//            }
//        }
//        return connect;
//    }
    
}
