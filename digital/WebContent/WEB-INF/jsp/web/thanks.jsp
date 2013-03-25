<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page import="java.util.List" %>



<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Spring Hibernate Sample</title>
</head>
<body>
<center>

<h1> Here is the details of the Users</h1>
<table border="1px">
<tr bordercolor="black"><td><b>First Name</b></td><td><b>LastName</b></td><td><b>Address</b></td><td><b>Telephone</b></td><td><b>Mobile</b></td><td><b>Country</b></td><td><b>State</b></td>
<td><b>City</b></td><td><b>Postal</b></td></tr>
<c:forEach items="${us}" var="usr">

<tr bordercolor="black"><td>${usr.firstName}</td><td>${usr.lastName}</td><td>${usr.address}</td><td>${usr.telephone}</td><td>${usr.mobile}</td>
<td>${usr.country}</td><td>${usr.state}</td><td>${usr.city}</td><td>${usr.postal}</td></tr>
		      
	</c:forEach>
	</table>	
	
</center>
</body>
</html>