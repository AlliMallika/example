<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="customer.model.Customer" %>
<%@ page import="customer.dao.CustomerDAO" %>
<%@ page import="customer.dao.impl.CustomerDAOImpl" %>

<%
    // Retrieve the customer ID from the request parameter
    int customerId = Integer.parseInt(request.getParameter("id"));

    // Assuming you have a method to get a customer by ID in your DAO
    CustomerDAO customerDAO = new CustomerDAOImpl();
    Customer customer = customerDAO.getCustomerById(customerId);
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Customer</title>
    <link rel="stylesheet" type="text/css" href="editCustomer.css">
</head>
<body>

    <h1>Edit Customer Details</h1>

    <%-- Check if the customer is not null before displaying the form --%>
    <% if (customer != null) { %>

    <form action="EditCustomerServlet" method="post">
        <input type="hidden" name="id" value="<%= customer.getId() %>">

        <label for="firstname">First Name:</label>
        <input type="text" id="firstname" name="firstname" value="<%= customer.getFirstname() %>"><br>
          <label for="lastname">Last Name:</label>
        <input type="text" id="lastname" name="lastname" value="<%= customer.getLastname() %>"><br>
        <label for="street">Street:</label>
        <input type="text" id="street" name="street" value="<%= customer.getStreet() %>"><br>
         <label for="address">Address:</label>
        <input type="text" id="address" name="address" value="<%= customer.getAddress() %>"><br>
        <label for="city">City:</label>
        <input type="text" id="city" name="city" value="<%= customer.getCity() %>"><br>
        <label for="state">State:</label>
        <input type="text" id="state" name="state" value="<%= customer.getState() %>"><br>
        <label for="email">Email:</label>
        <input type="text" id="email" name="email" value="<%= customer.getEmail() %>"><br>
        <label for="phone">Phone:</label>
        <input type="tel" id="phone" name="phone" value="<%= customer.getPhone() %>"><br>

        <!-- Other form fields go here -->

        <input type="submit" value="Save Changes" class="save-changes"><br><br>
        <a href="HomeServlet">Back to Homepage</a>
    </form>

    <%-- Close the if block for null check --%>
    <% } else { %>
        <p>Error: Customer not found.</p>
    <% } %>

</body>
</html>