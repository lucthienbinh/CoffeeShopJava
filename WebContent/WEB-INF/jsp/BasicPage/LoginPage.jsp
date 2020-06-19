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
  <style>
	.login-from-area {
		padding: 125px 55px 10px 33px;
	}
	.custom-row {
    display: flex;
    justify-content: space-between;  
	}
	.hyperlink-text {
		font-weight: 600;
	    text-decoration: none;
	    color: #2962ff;
	    font-size: 0.95rem;
	}
	.hyperlink-text:hover {
		text-decoration: none;
	    color: #299bff;
	}
	.btn-login {
	    font-size: 20px!important;
	    font-weight: 600;
	    padding: 0!important;
	    line-height: 48px;
	}
	.error-alert {
		background-color: red;
    	color: white;
	}

</style>
</head>

<body  style="background: url('${pageContext.request.contextPath}/public/img/373999.jpg') no-repeat 0 0; background-size:cover;">

  <div class="container">
	
    <!-- Outer Row -->
    <div class="row justify-content-center">

      <div class="col-xl-10 col-lg-12 col-md-9">

        <div class="card o-hidden border-0 shadow-lg my-5">
          <div class="card-body p-0">
            <!-- Nested Row within Card Body -->
            <div class="row">
              <div class="col-lg-6 d-none d-lg-block">
              	<img class="img-fluid" style="width: 100%;" src="${pageContext.request.contextPath}/public/img/login-image2.jpg" alt="">
              </div>
              <div class="col-lg-6">
                <div class="login-from-area">
                  <div class="text-center">
                    <h1 class="h4 text-gray-900 mb-4">Welcome Back!</h1>
                  </div>
                  <form class="user" method="post" action="LoginServlet">
	                <c:if test="${loginResult == false}">
		                <div class="form-group">
	                      <input type="text" class="form-control error-alert" value="${loginMessage}">
	                    </div>
					</c:if>
                    <div class="form-group">
                      <input name="email" type="email" class="form-control form-control-user" id="exampleInputEmail" aria-describedby="emailHelp" placeholder="Enter Email Address...">
                    </div>
                    <div class="form-group">
                      <input name="password" type="password" class="form-control form-control-user" id="exampleInputPassword" placeholder="Password">
                    </div>
                    <div class="custom-row">
	                    <div class="form-group">
	                      <div class="custom-control custom-checkbox small">
	                        <input type="checkbox" class="custom-control-input" id="customCheck">
	                        <label class="custom-control-label" for="customCheck">Remember Me</label>
	                      </div>
	                    </div>
	                    <a class="small hyperlink-text" href="forgot-password.html">Forgot Password?</a>
                    </div>
                    <button type="submit" class="btn btn-primary btn-block btn-user btn-login">
                      Login
                    </button>
                  </form>
                  <hr>
                  <div class="text-center">Already login? 
                    <a class="small hyperlink-text" href="<%=request.getContextPath()%>/GoDashboardServlet">Go to dashboard page!</a>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>

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
