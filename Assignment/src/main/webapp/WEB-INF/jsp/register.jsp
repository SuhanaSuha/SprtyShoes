<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>     
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sporty Shoes - Register</title>
</head>
<body>
<jsp:include page="/WEB-INF/view/components/header.jsp" ></jsp:include>
<jsp:include page="/WEB-INF/view/components/topbar.jsp" ></jsp:include>

${error}

<form name=frmReg action="signupaction" method="post">
 <table border=1 cellspacing=2 cellpadding=4>
 	<tr>
 		<td width=25%>Email id*</td>
 		<td><input name=email_id type="email" maxlength=50></td>
 	</tr>
 	<tr>
 		<td width=25%>Password*</td>
 		<td><input name=pwd maxlength=10 type="password"></td>
 	</tr>
 	<tr>
 		<td width=25%>Confirm Password*</td>
 		<td><input name=pwd2 maxlength=10 type="password"></td>
 	</tr>
 	<tr>
 		<td width=25%>First name*</td>
 		<td><input name=fname maxlength=25 ></td>
 	</tr>
 	<tr>
 		<td width=25%>Last name*</td>
 		<td><input name=lname maxlength=25 ></td>
 	</tr>
 	<tr>
 		<td width=25%>Age*</td>
 		<td><input name=age maxlength=3 type="number" ></td>
 	</tr>
 	<tr>
 		<td width=25%>Address</td>
 		<td><input name=address maxlength=100 ></td>
 	</tr>
 	
 	<tr>
 		<td colspan=2>
 			<button>Signup</button><br>
 			<a href="login">Already a member? Login</a>
 		</td>
 	</tr>
 </table>
</form>
<jsp:include page="/WEB-INF/view/components/footer.jsp"></jsp:include>
</body>
</html>