<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>     
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html> 
<head>
<meta charset="UTF-8">
<title>Sporty Shoes - Checkout</title>
</head>
<body>
<jsp:include page="/WEB-INF/view/components/header.jsp" ></jsp:include>
<jsp:include page="/WEB-INF/view/components/topbar.jsp" ></jsp:include>

<br><br>
Your Total Order is worth ${cartValue}<br><br>

<a href="gateway">Pay via secure Payment Gateway</a>
<jsp:include page="/WEB-INF/view/components/footer.jsp"></jsp:include>
</body>
</html>