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
<h2>Get Contact by ID</h2>
<form action="ContactServlet" method="post">
    <label>Hashed phoneNumber:</label>
    <textarea type="text" name="phoneNumber" required></textarea>
    <input type="submit" value="Fetch Contact by ID">
</form>

<h2>Get Contact by Phone and Name</h2>
<form action="ContactServlet" method="post">
    <label>Phone Number:</label>
    <input type="text" name="phoneNumber" required />
    <br><br>
    <label>Name:</label>
    <input type="text" name="name" required />
    <br><br>
    <input type="submit" value="Fetch Contact by Phone and Name">
</form>
</body>
</html>
