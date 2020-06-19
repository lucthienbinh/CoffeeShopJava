<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html lang="en">

<head>

  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
  <title>Login Page</title>
  <link rel="shortcut icon" href="Martin-Berube-Food-Coffee.ico">
  <!-- Custom fonts for this template-->
  <link href="<c:url value='/asset/vendor/fontawesome-free/css/all.min.css' />" type="text/css" rel="stylesheet" >
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

  <!-- Custom styles for this template-->
  <link href="<c:url value='/asset/css/sb-admin-2.min.css' />" rel="stylesheet"></link> 
  <link href="<c:url value='/asset/css/my-style.css' />" rel="stylesheet"></link> 
  <style type="text/css">
  	h1 {
  		font-family: 'Rubik', sans-serif;
	    font-weight: 500;
	    font-size: 64px;
	    color: #fff;
	}
	p {
	    font-size: 18px;
	    color: #fff;
	}
	.btn{
		text-transform: uppercase;
		padding: 19px 36px;
	}
	.btn{
		display: inline-block;
		font-weight: 600;
		text-align: center;
		white-space: nowrap;
		vertical-align: middle;
		-webkit-user-select: none;
		-moz-user-select: none;
		-ms-user-select: none;
		user-select: none;
		border: 2px solid transparent;
		padding: 12px 30px;
		font-size: 16px;
		line-height: 1.5;
		border-radius: .1875rem;
		transition: color .15s ease-in-out, background-color .15s ease-in-out, border-color .15s ease-in-out, box-shadow .15s ease-in-out;
	}
	.btn-outline-new-white {
	    color: #fff;
	    background-color: #d65106;
	    background-image: none;
	    border-color: #d65106;
	}
	
	a.btn.btn-lg.btn-outline-new-white {}
	.btn-outline-new-white:hover {
	    color: #ffffff;
	    background-color: #333333;
	    border-color: #333333;
	}
	.row {
		padding-top:300px;
	}
  
  </style>
</head>

<body  style="background: url('${pageContext.request.contextPath}/public/img/lead.jpg') no-repeat 0 0; background-size:cover;">

  <div class="container">
	
    <!-- Outer Row -->
    <div class="row justify-content-center">


		<div class="col-md-10">
			<h1 class="m-b-20"><strong>Welcome To <br> New Generation Coffee Shop</strong></h1>
			<p class="m-b-40">This web-site is designed for management and running coffee shop<br> 
			 with some cool features that everyone wants to use.</p>
			<p><a class="btn btn-lg btn-outline-new-white" href="<%=request.getContextPath()%>/GoLoginServlet">Login</a></p>
		</div>

    </div>

  </div>
<!-- Bootstrap core JavaScript-->
<script src="${pageContext.request.contextPath}/asset/vendor/jquery/jquery.min.js" type="text/javascript"></script> 
<script src="${pageContext.request.contextPath}/asset/vendor/bootstrap/js/bootstrap.bundle.min.js" type="text/javascript"></script> 

<!-- Core plug-in JavaScript-->
<script src="${pageContext.request.contextPath}/asset/vendor/jquery-easing/jquery.easing.min.js" type="text/javascript"></script> 

<!-- Custom scripts for all pages-->
<script src="${pageContext.request.contextPath}/asset/js/sb-admin-2.min.js" type="text/javascript"></script> 

</body>

</html>
