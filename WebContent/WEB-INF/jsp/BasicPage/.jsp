<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Administrator Function page</title>
</head>
<body>
	<h1>Administrator Function</h1>
	<a href="<%=request.getContextPath()%>/GoCreateUserServlet">Create a new user</a><br>
	<a href="<%=request.getContextPath()%>/GoSearchUserServlet">Search user</a><br>
	<a href="<%=request.getContextPath()%>/ListUserServlet">List users</a><br>
	<a href="<%=request.getContextPath()%>/LogoutServlet">Log out</a><br>
</body>
</html>