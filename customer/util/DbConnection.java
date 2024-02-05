package customer.util;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
   
	
	  private static final String JDBC_URL = "jdbc:mysql://localhost:3306/customermanagementsystem";
      private static final String JDBC_USER = "root";
      private static final String JDBC_PASSWORD = "root";
	
	
	
	public static Connection init() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
			
		}
		 catch (SQLException | ClassNotFoundException e) {
	            e.printStackTrace();
	            
	        }
		 
		return con;
	}
	
	public static void closeConnection(Connection connection) {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            // Handle or log the exception as needed
            e.printStackTrace();
        }
    }
}
