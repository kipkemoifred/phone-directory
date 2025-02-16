<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>Contact Details</title>
</head>
<body>
<h2>Contact Details</h2>

<div><%= request.getAttribute("contactData") %></div>
<br>
<a href="index.jsp">Back to Search</a>
</body>
</html>
