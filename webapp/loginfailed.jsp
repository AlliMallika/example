<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="loginfailed.css">
</head>
<body>
    <h1>OOPS! Login failed. Please try again with valid Credentials.</h1>
    <a href="index.jsp" class="login-link">Login</a>
</body>
</html>//below code for logout.jsp  <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession" %>

<%
    
    if (session != null) {
        session.invalidate();
    }
    response.sendRedirect("index.jsp");
%>