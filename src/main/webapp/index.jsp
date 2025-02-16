<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>SOAP Request</title>
</head>
<body>
<h2>Enter Organization Name</h2>
<form action="SoapServlet" method="post">
    <label>Organization Name:</label>
    <input type="text" name="organizationName" required />
    <br><br>
    <input type="submit" value="Get Contacts">
</form>
</body>
</html>
