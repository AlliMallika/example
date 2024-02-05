package customer.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import customer.dao.LoginDAO;
import customer.util.DbConnection;

public class LoginDAOImpl implements LoginDAO {
//    // JDBC URL, username, and password of MySQL server
//	    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/customermanagementsystem";
//	    private static final String USERNAME = "root";
//	    private static final String PASSWORD = "root";
//

    private static final LoginDAOImpl instance = new LoginDAOImpl();

    private LoginDAOImpl() {
        // Private constructor to enforce singleton pattern
    }

    public static LoginDAOImpl getInstance() {
        return instance;
    }

    @Override
    public int validateLogin(String username, String password) {
        try (Connection connection = DbConnection.init()) {
            String sql = "SELECT * FROM Login WHERE username = ? AND password = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    // If the result set has at least one row, the login is valid
                    return resultSet.next() ? 1 : 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return 0; // Assume validation failed on SQL error
        }
    }
}

