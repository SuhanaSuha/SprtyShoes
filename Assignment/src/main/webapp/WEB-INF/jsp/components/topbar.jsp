<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>     
<%@ page isELIgnored="false" %>

<a href="home">Home</a> | 
<% if (session.getAttribute("user_id") == null ) { %>
	<a href="login">Login/Signup</a> | 
<% }  else { %>
	<a href="dashboard">Dashboard</a> | 
	<a href="cart">Cart</a> | 
	<a href="logout">Logout</a>
	<br>
	<a href="editprofile">Edit Profile</a> | 
	<a href="memberpurchases">Your Orders</a> 

<% }  %>


 
<br><br>