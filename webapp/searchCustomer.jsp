<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="customer.model.Customer" %>
<%@ page import="customer.dao.CustomerDAO" %>
<%@ page import="customer.dao.impl.CustomerDAOImpl" %>

<%
    String searchField = request.getParameter("searchField");
    String searchTerm = request.getParameter("searchTerm");
    String orderBy = request.getParameter("orderBy");

    CustomerDAO customerDAO = new CustomerDAOImpl();
    List<Customer> customerList = customerDAO.searchCustomers(new Customer(searchField, searchTerm), orderBy);
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Search Results</title>
    <link rel="stylesheet" type="text/css" href="searchCustomer.css">
</head>
<body>

    <h1>Search Results</h1>

    <form action="SearchCustomerServlet" method="get">
        <label for="searchField">Search Field:</label>
        <select name="searchField">
        <!-- Add other search fields as needed -->
            <option value="firstname">First Name</option>
            <option value="lastname">Last Name</option>
            <option value="street">Street</option>
            <option value="address">Address</option>
            <option value="city">City</option>
            <option value="state">State</option>
            <option value="email">Email</option>
            <option value="phone">Phone</option>
            
        </select>

        <label for="searchTerm">Search Term:</label>
        <input type="text" name="searchTerm">

        <label for="orderBy">Sort By:</label>
        <select name="orderBy">
        <!-- Add other sorting options as needed -->
            <option value="firstname">First Name</option>
            <option value="lastname">Last Name</option>
            <option value="street">Street</option>
            <option value="address">Address</option>
            <option value="city">City</option>
            <option value="state">State</option>
            <option value="email">Email</option>
            <option value="phone">Phone</option>
        </select>

        <input type="submit" value="Search" class="search-btn">
    </form>

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
        <a href="HomeServlet">Back to Homepage</a>
    </table>

</body>
</html>