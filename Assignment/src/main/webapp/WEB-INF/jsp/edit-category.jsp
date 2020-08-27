<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>     
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin - Add/Edit Product Category</title>
</head>
<body>
<jsp:include page="/WEB-INF/view/components/admin-header.jsp" ></jsp:include>
<jsp:include page="/WEB-INF/view/components/admin-topbar.jsp" ></jsp:include>

<%
if (request.getParameter("error") != null)
	out.print(request.getParameter("error"));
%>
<form name=frmCat method=post action="admineditcataction">
	<input type=hidden name=id value=${category.ID}>
<table border=1 cellspacing=2 cellpadding=4>
 	<tr>
 		<td width=25%>Category name*</td>
 		<td><input name=name  maxlength=50 value="${category.name}"></td>
 	</tr>
 	<tr>
 		<td colspan=2>
 			<button>Save</button>
 		</td>
 	</tr>
 </table>
</form>

<jsp:include page="/WEB-INF/view/components/admin-footer.jsp"></jsp:include>
</body>
</html>