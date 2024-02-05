<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 <link rel="stylesheet" type="text/css" href="addCustomer.css">
</head>
<body>
     <h1>Customer Details</h1>
     <form action="AddCustomerServlet" method="get" class="customer-form">
          <input type="text"  name="firstname" placeholder="First name">&nbsp;&nbsp;&nbsp;
          <input type="text"  name="lastname" placeholder="Last name"><br><br>
          <input type="text" name="street" placeholder="Street">&nbsp;&nbsp;&nbsp;
          <input type="text" name="address" placeholder="Address"><br><br>
          <input type="text" name="city" placeholder="City">&nbsp;&nbsp;&nbsp;
          <input type="text"  name="state" placeholder="State"><br><br>
          <input type="email" name="email" placeholder="Email">&nbsp;&nbsp;&nbsp;
          <input type="tel"  name="phone" placeholder="Phone"><br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <input type="submit"><br><br>
          <a href="HomeServlet">Back to Homepage</a>
     </form>
</body>
</html>