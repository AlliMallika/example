package customer.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import customer.dao.CustomerDAO;
import customer.model.Customer;
import customer.model.PaginationParams;
import customer.util.DbConnection;

public class CustomerDAOImpl implements CustomerDAO {

    // Database connection
    private Connection connection;

    public CustomerDAOImpl() {
        // Initialize the database connection
        connection = DbConnection.init();
    }

    @Override
    public boolean addCustomer(Customer Customer) {
        // Insert new Customer into the database
        
        // Return true if insertion is successful, false otherwise
        // Example:
        // "INSERT INTO customerdetails (firstname, lastname, street, address, city, state, email, phone) VALUES (?, ?, ?, ?, ?, ?, ?, ?)"
        try {
            Connection connection = DbConnection.init();
            String query = "INSERT INTO customerdetails (firstname, lastname, street, address, city, state, email, phone) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, Customer.getFirstname());
                preparedStatement.setString(2, Customer.getLastname());
                preparedStatement.setString(3, Customer.getStreet());
                preparedStatement.setString(4, Customer.getAddress());
                preparedStatement.setString(5, Customer.getCity());
                preparedStatement.setString(6, Customer.getState());
                preparedStatement.setString(7, Customer.getEmail());
                preparedStatement.setLong(8, Customer.getPhone());

                int rowsAffected = preparedStatement.executeUpdate();

                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();

        try {
            // Prepare the SQL statement for retrieving all customers
            String sql = "SELECT * FROM customerdetails";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                // Execute the SQL statement and retrieve the result set
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    // Iterate over the result set and create Customer objects
                    while (resultSet.next()) {
                        Customer customer = new Customer();
                        customer.setId(resultSet.getInt("id"));
                        customer.setFirstname(resultSet.getString("firstname"));
                        customer.setLastname(resultSet.getString("lastname"));
                        customer.setStreet(resultSet.getString("street"));
                        customer.setAddress(resultSet.getString("address"));
                        customer.setCity(resultSet.getString("city"));
                        customer.setState(resultSet.getString("state"));
                        customer.setEmail(resultSet.getString("email"));
                        customer.setPhone(resultSet.getLong("phone"));

                        customers.add(customer);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customers;
    }

    @Override
    public Customer getCustomerById(int id) {
        Customer customer = null;

        try {
            // Prepare the SQL statement for retrieving a customer by ID
            String sql = "SELECT * FROM customerdetails WHERE id=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                // Set the parameter for the SQL statement
                preparedStatement.setInt(1, id);

                // Execute the SQL statement and retrieve the result set
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    // If a customer is found, create a Customer object
                    if (resultSet.next()) {
                        customer = new Customer();
                        customer.setId(resultSet.getInt("id"));
                        customer.setFirstname(resultSet.getString("firstname"));
                        customer.setLastname(resultSet.getString("lastname"));
                        customer.setStreet(resultSet.getString("street"));
                        customer.setAddress(resultSet.getString("address"));
                        customer.setCity(resultSet.getString("city"));
                        customer.setState(resultSet.getString("state"));
                        customer.setEmail(resultSet.getString("email"));
                        customer.setPhone(resultSet.getLong("phone"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customer;
    }

    
    @Override
    public boolean updateCustomer(Customer Customer) {
        // Update updatedCustomer in the database
        
        // Return true if update is successful, false otherwise
        // Example:
        // "UPDATE customerdetails SET firstname=?, lastname=?, street=?, address=?, city=?, state=?, email=?, phone=? WHERE id=?"
        try {
            Connection connection = DbConnection.init();
            String query = "UPDATE customerdetails SET firstname=?, lastname=?, street=?, address=?, city=?, state=?, email=?, phone=? WHERE id=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, Customer.getFirstname());
                preparedStatement.setString(2, Customer.getLastname());
                preparedStatement.setString(3, Customer.getStreet());
                preparedStatement.setString(4, Customer.getAddress());
                preparedStatement.setString(5, Customer.getCity());
                preparedStatement.setString(6, Customer.getState());
                preparedStatement.setString(7, Customer.getEmail());
                preparedStatement.setLong(8, Customer.getPhone());
                preparedStatement.setInt(9, Customer.getId());

                int rowsAffected = preparedStatement.executeUpdate();

                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean deleteCustomer(int id) {
        // Delete customer with customerId from the database
        
        // Return true if delete is successful, false otherwise
        // Example:
        // "DELETE FROM customerdetails WHERE id=?"
        try {
            Connection connection = DbConnection.init();
            String query = "DELETE FROM customerdetails WHERE id=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, id);

                int rowsAffected = preparedStatement.executeUpdate();

                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    
    
    
    @Override
    public List<Customer> searchCustomers(Customer customer) {
        return searchCustomers(customer, null); // default to no order by
    }

    @Override
    public List<Customer> searchCustomers(Customer customer, String orderBy) {
        List<Customer> customers = new ArrayList<>();

        try {
            // Construct the SQL query for searching customers
            StringBuilder query = new StringBuilder("SELECT * FROM customerdetails WHERE 1=1");

            // Prepare a list to store the parameters for the prepared statement
            List<Object> parameters = new ArrayList<>();

            // Check and append conditions for each search parameter
            if (customer.getFirstname() != null && !customer.getFirstname().isEmpty()) {
                query.append(" AND firstname LIKE ?");
                parameters.add("%" + customer.getFirstname() + "%");
            }

            if (customer.getLastname() != null && !customer.getLastname().isEmpty()) {
                query.append(" AND lastname LIKE ?");
                parameters.add("%" + customer.getLastname() + "%");
            }
            if (customer.getStreet() != null && !customer.getStreet().isEmpty()) {
                query.append(" AND street LIKE ?");
                parameters.add("%" + customer.getStreet() + "%");
            }

            if (customer.getAddress() != null && !customer.getAddress().isEmpty()) {
                query.append(" AND address LIKE ?");
                parameters.add("%" + customer.getAddress() + "%");
            }

            if (customer.getCity() != null && !customer.getCity().isEmpty()) {
                query.append(" AND city LIKE ?");
                parameters.add("%" + customer.getCity() + "%");
            }

            if (customer.getState() != null && !customer.getState().isEmpty()) {
                query.append(" AND state LIKE ?");
                parameters.add("%" + customer.getState() + "%");
            }

            if (customer.getEmail() != null && !customer.getEmail().isEmpty()) {
                query.append(" AND email LIKE ?");
                parameters.add("%" + customer.getEmail() + "%");
            }

            if (customer.getPhone() != 0) { // Assuming 0 is the default value for phone if not provided
                query.append(" AND phone = ?");
                parameters.add(customer.getPhone());
            }

            
            

            // Add more conditions based on other fields in the Customer object

            // Add ORDER BY clause based on the selected field
            if (orderBy != null && !orderBy.isEmpty()) {
                query.append(" ORDER BY ").append(orderBy);
            }

            // Create a prepared statement
            try (PreparedStatement preparedStatement = connection.prepareStatement(query.toString())) {
                // Set parameters in the prepared statement
                for (int i = 0; i < parameters.size(); i++) {
                    preparedStatement.setObject(i + 1, parameters.get(i));
                }

                // Execute the query
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    // Process the result set and populate the list of customers
                    while (resultSet.next()) {
                        Customer resultCustomer = new Customer();
                        resultCustomer.setId(resultSet.getInt("id"));
                        resultCustomer.setFirstname(resultSet.getString("firstname"));
                        resultCustomer.setLastname(resultSet.getString("lastname"));
                        resultCustomer.setStreet(resultSet.getString("street"));
                        resultCustomer.setAddress(resultSet.getString("address"));
                        resultCustomer.setCity(resultSet.getString("city"));
                        resultCustomer.setState(resultSet.getString("state"));
                        resultCustomer.setEmail(resultSet.getString("email"));
                        resultCustomer.setPhone(resultSet.getLong("phone"));
                        customers.add(resultCustomer);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception as needed
        }

        return customers;
    }
    
    
    
    
 // Pagination method implementation
    @Override
    public List<Customer> getCustomersWithPagination(PaginationParams paginationParams) {
        List<Customer> customerList = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM customerdetails LIMIT ? OFFSET ?")) {

            preparedStatement.setInt(1, paginationParams.getLimit());
            preparedStatement.setInt(2, paginationParams.getOffset());

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                // Process the result set and populate the customer list
                while (resultSet.next()) {
                    Customer customer = new Customer();
                    customer.setId(resultSet.getInt("id"));
                    customer.setFirstname(resultSet.getString("firstname"));
                    customer.setLastname(resultSet.getString("lastname"));
                    customer.setStreet(resultSet.getString("street"));
                    customer.setAddress(resultSet.getString("address"));
                    customer.setCity(resultSet.getString("city"));
                    customer.setState(resultSet.getString("state"));
                    customer.setEmail(resultSet.getString("email"));
                    customer.setPhone(resultSet.getLong("phone"));

                    customerList.add(customer);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions as needed
        }

        return customerList;
    }
    
    
    
    @Override
    public int getTotalCustomersCount() {
        int totalCount = 0;

        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) as total FROM customerdetails")) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    totalCount = resultSet.getInt("total");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions as needed
        }

        return totalCount;
    }

}
