<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>DIY videos</title>
</head>
<body>
	
    <div align="center">
       <h3 align="center"> User Table </h3>

	<table border="1" width="70%" align="center">

	<tr>

	<th>name</th>

	<th>First name</th>
	
	<th>Last name</th>

	</tr>

	<c:forEach items="${listUser_}" var="user">

<tr>

<td>${user.EmailID}</td>

<td>${user.firstName }</td>

<td>${user.lastName }</td>

</tr>

</c:forEach>

</table>
    </div>   
</body>
</html>