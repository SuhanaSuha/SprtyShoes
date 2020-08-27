<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>     
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sporty Shoes - Your Cart</title>
</head>
<body>
<jsp:include page="/WEB-INF/view/components/header.jsp" ></jsp:include>
<jsp:include page="/WEB-INF/view/components/topbar.jsp" ></jsp:include>

<%
if (request.getParameter("error") != null)
	out.print(request.getParameter("error") + "<br>");
%>

<br><br>Total Cart Value: ${cartValue }<br>

<table border=1 cellspacing=2 cellpadding=4>
 	<tr>
 		<td><b>Product</b></td>
 		<td><b>Qty</b></td>
 		<td><b>Rate</b></td>
 		<td><b>Price</b></td>
 		<td></td>
 	</tr>
 	<c:forEach items="${cartItems}" var="item">
 	  	<tr>
	 		<td>${item.name }</td>
 			<td>${item.rate }</td>
 			<td>${item.qty}</td>
 			<td>${item.price}</td>
 	  		<td>
 	  			<a href="cartdeleteitem?id=${item.productId}">Remove</a>
 	  		</td>
 	  	</tr>
 	  </c:forEach>
</table> 		

<br>
 <c:if test = "${cartItems.size() > 0}">
	<a href="checkout">Checkout Now</a>
</c:if>

<jsp:include page="/WEB-INF/view/components/footer.jsp"></jsp:include>
</body>
</html>