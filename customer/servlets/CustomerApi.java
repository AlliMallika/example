package customer.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import customer.dao.impl.CustomerDAOImpl;
import customer.model.Customer;

/**
 * Servlet implementation class CustomerApi
 */
@WebServlet("/api/customers/*")
public class CustomerApi extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerApi() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		CustomerDAOImpl service = new CustomerDAOImpl();
		response.setHeader("Content-Type", "application/json");
		ObjectMapper objectMapper = new ObjectMapper();
		try {
		      if(request.getPathInfo() != null && request.getPathInfo().length() >1)
		      {
		    	  List<String> paths = Arrays.asList(request.getPathInfo().substring(1).split("/"));
		    	  if(paths.size() > 1)
		    	  {
		    		  response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		    		  out.println("Invalid path");
		    	  }
		    	  else
		    	  {
		    		  Customer customer = service.getCustomerById(Integer.parseInt(paths.get(0)));
		    		  out.print(objectMapper.writeValueAsString(customer));
		    	  }
		      }
		      else
		      {
		    	  List<Customer> list = service.getAllCustomers();
		    	  out.print(objectMapper.writeValueAsString(list));
		      }
		}
		catch(Exception e)
		{
			out.write("{\"error\": \""+e.getMessage()+"\"}");
		}
			
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		StringBuffer jb = new StringBuffer();
		String line = null;
		try {
			  BufferedReader reader = request.getReader();
			  while((line = reader.readLine())!= null)
			  {
				  jb.append(line);
			  }
			  ObjectMapper objectMapper = new ObjectMapper();
			  Customer customer = objectMapper.readValue(jb.toString(), Customer.class);
			  CustomerDAOImpl service = new CustomerDAOImpl();
			  service.addCustomer(customer);
			  out.write(objectMapper.writeValueAsString(customer));
		}
		catch(Exception e)
		{
			out.write("{\"error\":\""+e.getMessage()+"\"}");
		}
		
		
	}
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		PrintWriter out = response.getWriter();
		StringBuffer jb = new StringBuffer();
		String line = null;
		try {
			  BufferedReader reader = request.getReader();
			  while((line = reader.readLine())!= null)
			  {
				  jb.append(line);
			  }
			  ObjectMapper objectMapper = new ObjectMapper();
			  Customer customer = objectMapper.readValue(jb.toString(), Customer.class);
			  CustomerDAOImpl service = new CustomerDAOImpl();
			  service.updateCustomer(customer);
			  out.write(objectMapper.writeValueAsString(customer));
		}
		catch(Exception e)
		{
			out.write("{\"error\":\""+e.getMessage()+"\"}");
		}
			  
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		PrintWriter out = response.getWriter();
		CustomerDAOImpl service = new CustomerDAOImpl();
		response.setHeader("Content Type", "application/json");
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			   if(request.getPathInfo() != null && request.getPathInfo().length() > 1) {
				   List<String> paths = Arrays.asList(request.getPathInfo().substring(1).split("/"));
				   if(paths.size() > 1)
				   {
					   response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					   out.write("{\"error\":\"Invalid path\"}");
				   }
				   else
				   {
					   service.deleteCustomer(Integer.parseInt(paths.get(0)));
					   out.write("{\"message\": \"Record deleted\"}");
				   }
			   }
		}
		catch(Exception e)
		{
			e.printStackTrace();
			out.write("{\"error\": \""+e.getMessage()+"\"}");
		}
	}
}
package customer.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import customer.daoimpl.CustomerDAOImpl;

@WebServlet("/DeleteCustomerServlet")
public class DeleteCustomerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Check if the user is authenticated based on the presence of the "username" attribute
        String username = (String) request.getSession().getAttribute("username");

        if (username != null) {
            // User is authenticated, proceed with deleting the customer

            // Get the customer ID from the request parameter
            int customerId = Integer.parseInt(request.getParameter("id"));

            // Call the DAO to delete the customer
            CustomerDAOImpl customerDAO = new CustomerDAOImpl();
            boolean result = customerDAO.deleteCustomer(customerId);

            if (result) {
                // Deletion successful, redirect to HomeServlet to display updated customer list
                response.sendRedirect("HomeServlet");
            } else {
                // Error in deletion, redirect to an error page or handle accordingly
                response.sendRedirect("error.jsp");
            }
        } else {
            // User is not authenticated, redirect to the login page
            response.sendRedirect("index.jsp");
        }
    }
}package customer.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import customer.daoimpl.CustomerDAOImpl;
import customer.model.Customer;

@WebServlet("/EditCustomerServlet")
public class EditCustomerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Check if the user is authenticated based on the presence of the "username" attribute
        String username = (String) request.getSession().getAttribute("username");

        if (username != null) {
            // User is authenticated, proceed with updating the customer

            // Get parameters from the form
            int customerId = Integer.parseInt(request.getParameter("id"));
            String firstname = request.getParameter("firstname");
            String lastname = request.getParameter("lastname");
            String street = request.getParameter("street");
            String address = request.getParameter("address");
            String city = request.getParameter("city");
            String state = request.getParameter("state");
            String email = request.getParameter("email");
            long phone = Long.parseLong(request.getParameter("phone"));

            // Create a Customer object
            Customer customer = new Customer(customerId, firstname, lastname, street, address, city, state, email, phone);

            // Retrieve the existing customer from the database
            CustomerDAOImpl customerDAO = new CustomerDAOImpl();
            Customer existingCustomer = customerDAO.getCustomerById(customerId);

            if (existingCustomer != null) {
                // Update the customer properties with the new values
                existingCustomer.setFirstname(firstname);
                existingCustomer.setLastname(lastname);
                existingCustomer.setStreet(street);
                existingCustomer.setAddress(address);
                existingCustomer.setCity(city);
                existingCustomer.setState(state);
                existingCustomer.setEmail(email);
                existingCustomer.setPhone(phone);

                // Update the customer in the database
                int result = customerDAO.updateCustomer(existingCustomer);

                if (result > 0) {
                    // Set the updated customer as an attribute and forward to the JSP
                    request.setAttribute("customer", existingCustomer);
                    request.getRequestDispatcher("editCustomer.jsp").forward(request, response);
                } else {
                    // Error in updating, redirect to an error page or handle accordingly
                    response.sendRedirect("error.jsp");
                }
            } else {
                // Customer not found, handle accordingly
                response.sendRedirect("error.jsp");
            }
        } else {
            // User is not authenticated, redirect to the login page
            response.sendRedirect("index.jsp");
        }
    }
}