package customer.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import customer.dao.impl.CustomerDAOImpl;
import customer.model.Customer;
import customer.model.PaginationParams;

@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Check if the user is authenticated based on the presence of the "username" attribute
        String username = (String) request.getSession().getAttribute("username");

        if (username != null) {
            // User is authenticated, proceed with retrieving customer list
            CustomerDAOImpl customerDAO = new CustomerDAOImpl();

            // Pagination parameters
            int page = 1; // Default to the first page
            int recordsPerPage = 3; // Adjust as needed

            if (request.getParameter("page") != null) {
                page = Integer.parseInt(request.getParameter("page"));
            }

            int offset = (page - 1) * recordsPerPage;
            int limit = recordsPerPage;

            // Create PaginationParams object
            PaginationParams paginationParams = new PaginationParams(limit, offset);

            // Call the pagination method with PaginationParams object
            List<Customer> customerList = customerDAO.getCustomersWithPagination(paginationParams);

            // Set the customerList attribute in the request
            request.setAttribute("customerList", customerList);

            // Set pagination parameters in the request
            request.setAttribute("currentPage", page);

            // Other existing logic...

            // Redirect to home.jsp with pagination parameters
            request.getRequestDispatcher("home.jsp").forward(request, response);
        } else {
            // User is not authenticated, redirect to the login page (replace with your login page)
            response.sendRedirect("index.jsp");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Your logic for handling POST requests (if needed)
    }
}