<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Delete Customer</title>
    <link rel="stylesheet" type="text/css" href="deleteCustomer.css">
    
</head>
<body>

    <h1>Delete Customer</h1>

    <form action="DeleteCustomerServlet" method="get">
        <p>Are you sure you want to delete this customer?</p>

        <!-- Hidden field to store the customer ID -->
        <input type="hidden" name="id" value="<%= request.getParameter("id") %>">

        <button type="submit">Yes, Delete</button>
        <a href="HomeServlet">No, Cancel</a>
    </form>

</body>
</html>