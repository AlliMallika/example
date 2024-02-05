package customer.dao;
import java.util.List;

import customer.model.Customer;
import customer.model.PaginationParams;

public interface CustomerDAO {

    // Create operation
    boolean addCustomer(Customer customer);

    // Read operation (retrieve all customers)
    List<Customer> getAllCustomers();

    // Read operation (retrieve customer by ID)
    Customer getCustomerById(int id);

    // Update operation
    boolean updateCustomer(Customer customer);

    // Delete operation
    boolean deleteCustomer(int id);
    
    // Search operation
    List<Customer> searchCustomers(Customer customer);
    
    List<Customer> searchCustomers(Customer customer, String orderByColumn);
    
 // Pagination method
    List<Customer> getCustomersWithPagination(PaginationParams paginationParams);
    
 // New method to get the total count of customers
    int getTotalCustomersCount();
}