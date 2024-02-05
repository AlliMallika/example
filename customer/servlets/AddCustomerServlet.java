package customer.servlets;



import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import customer.dao.impl.CustomerDAOImpl;
import customer.model.Customer;

@WebServlet("/AddCustomerServlet")
public class AddCustomerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	
    	// Check if the user is authenticated based on the presence of the "username" attribute
        String username = (String) request.getSession().getAttribute("username");

        if (username != null) {
            // User is authenticated, proceed with add customer to database
        	
        	 // Get parameters from the form
            String firstname = request.getParameter("firstname");
            String lastname = request.getParameter("lastname");
            String street = request.getParameter("street");
            String address = request.getParameter("address");
            String city = request.getParameter("city");
            String state = request.getParameter("state");
            String email = request.getParameter("email");
            long phone = Long.parseLong(request.getParameter("phone"));

            // Create a Customer object
            Customer customer = new Customer(0, firstname, lastname, street, address, city, state, email, phone);
            
            
            CustomerDAOImpl customerDAO = new CustomerDAOImpl();
            // Add the customer to the database
            customerDAO.addCustomer(customer);
            

//            // Add the customer to the database
//            CustomerDAOImpl.getInstance().addCustomer(customer);

//            // Redirect to HomeServlet to display updated customer list
//            response.sendRedirect("HomeServlet");

//            // Set the customerList attribute in the session
//            request.getSession().setAttribute("customerList", customerList);

            // Redirect to home.jsp to display the customer list
            response.sendRedirect("home.jsp");
        } else {
            // User is not authenticated, redirect to the login page (replace with your login page)
            response.sendRedirect("index.jsp");
        }
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
//        // Get parameters from the form
//        String firstname = request.getParameter("firstname");
//        String lastname = request.getParameter("lastname");
//        String address = request.getParameter("address");
//        String city = request.getParameter("city");
//        String state = request.getParameter("state");
//        String email = request.getParameter("email");
//        long phone = Long.parseLong(request.getParameter("phone"));
//
//        // Create a Customer object
//        Customer customer = new Customer(0, firstname, lastname, address, city, state, email, phone);
//
//        // Add the customer to the database
//        CustomerDAOImpl.getInstance().addCustomer(customer);
//
//        // Redirect to HomeServlet to display updated customer list
//        response.sendRedirect("HomeServlet");
    }
}