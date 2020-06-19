<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Function page</title>
</head>
<body>
	<h1>User Function</h1>
	<% String username = (String) session.getAttribute("username");%>
	<a href="<%=request.getContextPath()%>/ViewUserServlet?username=<%=username%>">Personal Information</a><br>
	<a href="<%=request.getContextPath()%>/ListUserServlet">List users</a><br>
	<a href="<%=request.getContextPath()%>/LogoutServlet">Log out</a><br>
</body>
</html>