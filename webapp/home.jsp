<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="customer.model.Customer" %>
<%@ page import="customer.dao.CustomerDAO" %>
<%@ page import="customer.dao.impl.CustomerDAOImpl" %>
<%@ page import="customer.model.PaginationParams" %>

<%
    // Assuming you have a method getAllCustomers() in your DAO
    CustomerDAO customerDAO = new CustomerDAOImpl();
    int recordsPerPage = 3; // Adjust as needed
    int currentPage = (request.getParameter("page") != null) ? Integer.parseInt(request.getParameter("page")) : 1;
    int offset = (currentPage - 1) * recordsPerPage;

    // Create PaginationParams object
    PaginationParams paginationParams = new PaginationParams(recordsPerPage, offset);

    // Call the pagination method with PaginationParams object
    List<Customer> customerList = customerDAO.getCustomersWithPagination(paginationParams);

    // Get total number of customers for pagination
    int totalCustomers = customerDAO.getTotalCustomersCount();
    int totalPages = (int) Math.ceil(totalCustomers / (double) recordsPerPage);
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Home Page</title>
    <link rel="stylesheet" type="text/css" href="home.css">
</head>
<body>

    <h1>Customer Details</h1>

    <!-- Add Customer Button -->
    <a href="addCustomer.jsp" class="add-btn">Add Customer</a>

    
    
    <!-- Add Sync Button -->
<form action="SyncCustomerServlet">
    <button type="submit">Sync</button>
</form>
    
    

    <!-- Search Box and Dropdowns -->
    <div class="search-container">
        <form action="searchCustomer.jsp" method="get">
            <!-- Dropdown for other search criteria -->
            <select name="searchField">
                <option value="firstname">Search by FirstName</option>
                <option value="lastname">Search by LastName</option>
                <option value="street">Search by Street</option>
                <option value="address">Search by Address</option>
                <option value="city">Search by City</option>
                <option value="state">Search by State</option>
                <option value="email">Search by Email</option>
                <option value="phone">Search by Phone</option>
                <!-- Add more options based on your fields -->
            </select>
            <button type="submit">Search</button>
        </form>
    </div>

    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Street</th>
                <th>Address</th>
                <th>City</th>
                <th>State</th>
                <th>Email</th>
                <th>Phone</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <!-- Iterate over the list of customers using scriptlet loop -->
            <% for (Customer customer : customerList) { %>
                <tr>
                    <td><%= customer.getId() %></td>
                    <td><%= customer.getFirstname() %></td>
                    <td><%= customer.getLastname() %></td>
                    <td><%= customer.getStreet() %></td>
                    <td><%= customer.getAddress() %></td>
                    <td><%= customer.getCity() %></td>
                    <td><%= customer.getState() %></td>
                    <td><%= customer.getEmail() %></td>
                    <td><%= customer.getPhone() %></td>
                    <td>
                        <a href="editCustomer.jsp?id=<%= customer.getId() %>" class="edit-btn">Edit</a>
                        |
                        <a href="deleteCustomer.jsp?id=<%= customer.getId() %>" class="delete-btn">Delete</a>
                    </td>
                </tr>
            <% } %>
        </tbody>
    </table>
    
    <!-- Adding Pagination to Home page -->
    <div class="pagination">
        <% if (customerList != null && !customerList.isEmpty()) { %>
            <% for (int pageNumber = 1; pageNumber <= totalPages; pageNumber++) { %>
                <%
                    String paginationUrl = "HomeServlet?page=" + pageNumber;
                %>
                <a href="<%= paginationUrl %>"><%= pageNumber %></a>
            <% } %>
        <% } %>
    </div>
    
    
    <a href="logout.jsp">LogOut</a>
</body>
</html>