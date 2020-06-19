<%@page import="coffeeshop.dto.UserDTO"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<jsp:include page="/asset/PageCustomLayout/admin-header-layout.jsp">
<jsp:param name="pageTitle" value="User Information Page"/>
<jsp:param name="pageHeading" value="User Information"/>
</jsp:include>

<!-- ###################### START TO INPUT YOUR CODE FROM HERE ###################### -->
<!-- Begin Page Content -->
		<!-- Alert information -->
		<c:if test="${updateResult != null}">
	 		<jsp:include page="/asset/PageCustomLayout/alert-layout.jsp">
			<jsp:param name="result" value="${updateResult}"/>
			<jsp:param name="message" value="${updateMessage}"/>
			</jsp:include>
		</c:if>
       <!-- Content Row -->
       <div class="row">
       
         <!-- Area Chart -->
         <div class="col-xl-6 col-lg-6">
           <div class="card shadow mb-4">
             <!-- Card Header - Dropdown -->
             <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
               <h6 class="m-0 font-weight-bold text-primary">View/Edit User Information</h6>
                <a href="<%=request.getContextPath()%>/ViewUserServlet?email=${user.email}&pagename=ChangePassword">Change password</a>
             </div>
             <!-- Card Body -->
             <div class="card-body">
         	<form method="post" action="UpdateUserServlet">
         		<input type="hidden" name="id" value="${user.id}">
	    		<div class="row">
	    			<div class="col-md-12">
	    				<h4 class="small font-weight-bold">Email</h4>
	    				<input type="email" disabled class="form-control bg-light border-0 small" placeholder="Input Email" 
	    				name="email" required="required" aria-describedby="basic-addon2" value="${user.email}">
	    			</div>
	    		</div>
	    		<br>
	    		<div class="row">
	    			<div class="col-md-6">
	    			<h4 class="small font-weight-bold">First name</h4>
	    				<input type="text" class="form-control bg-light border-0 small" placeholder="Input First name" 
	    				name="firstname" aria-describedby="basic-addon2" value="${user.firstname}">
	    			</div>
	    			<div class="col-md-6">
	    				<h4 class="small font-weight-bold">Last name</h4>
	    				<input type="text" class="form-control bg-light border-0 small" placeholder="Input Last name" 
	    				name="lastname" aria-describedby="basic-addon2" value="${user.lastname}">
	    			</div>
	    		</div>
	    		<br>
	    		<div class="row">
	    			<div class="col-md-6">
	    			<h4 class="small font-weight-bold">Sex</h4>
	    				<input type="text" class="form-control bg-light border-0 small" placeholder="Input Sex" 
	    				name="sex" aria-describedby="basic-addon2" value="${user.sex}">
	    			</div>
	    			<div class="col-md-6">
	    			<h4 class="small font-weight-bold">Mobile phone</h4>
	    				<input type="text" class="form-control bg-light border-0 small" placeholder="Input Mobile phone" 
	    				name="mobilephone" aria-describedby="basic-addon2" value="${user.mobilephone}">
	    			</div>
	    		</div>
	    		<br>
	    		<div class="row">
	    			<div class="col-md-12">
	    				<h4 class="small font-weight-bold">Address</h4>
	    				<input type="text" class="form-control bg-light border-0 small" placeholder="Input Address" 
	    				name="address" aria-describedby="basic-addon2" value="${user.address}">
	    			</div>
	    		</div>
	    		<br>
	    		<div class="row">
	    			<div class="col-md-12 d-sm-flex align-items-center justify-content-between">
		                <a href="<%=request.getContextPath()%>/GoSearchUserServlet" class="btn btn-secondary  btn-icon-split">
				           <span class="icon text-gray-600">
				             <i class="fas fa-angle-left"></i>
				           </span>
				           <span class="text">Back</span>
				         </a>
				         <button class="btn btn-success btn-icon-split" type="submit">
		                    <span class="icon text-white-50">
		                      <i class="fas fa-check"></i>
		                    </span>
		                    <span class="text">Save</span>
		                  </button>
	    			</div>
	    		</div>
     		   </form>
             </div>
           </div>
         </div>
       </div> 
<!-- ###################### END OF YOUR CODE ###################### -->

<jsp:include page="/asset/PageCustomLayout/footer-layout.jsp"></jsp:include>