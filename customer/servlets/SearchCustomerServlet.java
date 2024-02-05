package customer.servlets;

import java.io.IOException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import customer.model.Customer;
import customer.dao.CustomerDAO;
import customer.dao.impl.CustomerDAOImpl;


@WebServlet("/SearchCustomerServlet")
public class SearchCustomerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String searchField = request.getParameter("searchField");
        String searchTerm = request.getParameter("searchTerm");
        String orderByColumn = request.getParameter("orderByColumn"); // Updated parameter name

        CustomerDAO customerDAO = new CustomerDAOImpl();
        List<Customer> customerList = customerDAO.searchCustomers(new Customer(searchField, searchTerm), orderByColumn);

        // Set the search results in the request attribute
        request.setAttribute("customerList", customerList);

        // Forward to the searchCustomer.jsp page to display the results
        request.getRequestDispatcher("searchCustomer.jsp").forward(request, response);
    }
}