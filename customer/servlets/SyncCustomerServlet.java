package customer.servlets;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import customer.api.APICaller;
import customer.dao.CustomerDAO;
import customer.dao.impl.CustomerDAOImpl;
import customer.model.Customer;

@WebServlet("/SyncCustomerServlet")
public class SyncCustomerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Assume you have obtained the token after the login
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNzA2OTY5NjQyfQ.S0PBvhiy4mpF93C0mBKawhahQhONshSH4gtkA_fa1hs";

        // Specify the API endpoint for getting customer list
        String endpoint = "/customers";

        // Make API call to get customer list
        String customerListResponse = null;
		try {
			customerListResponse = APICaller.makeGetRequest(endpoint, token);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        // Process the response and update or insert customers into the local database
        if (customerListResponse != null) {
            // Parse the JSON response into a list of Customer objects
            ObjectMapper objectMapper = new ObjectMapper();
            List<Customer> remoteCustomerList = Arrays.asList(objectMapper.readValue(customerListResponse, Customer[].class));

            // Update or insert customers into the local database
            if (remoteCustomerList != null) {
                CustomerDAO customerDAO = new CustomerDAOImpl();

                for (Customer remoteCustomer : remoteCustomerList) {
                    // Check if the customer already exists in the local database
                    Customer existingCustomer = customerDAO.getCustomerById(remoteCustomer.getId());

                    if (existingCustomer != null) {
                        // Customer exists, update it
                        customerDAO.updateCustomer(remoteCustomer);
                    } else {
                        // Customer doesn't exist, insert it
                        customerDAO.addCustomer(remoteCustomer);
                    }
                }
            }
        }

        // Redirect back to the home page
        response.sendRedirect("HomeServlet");
    }
}