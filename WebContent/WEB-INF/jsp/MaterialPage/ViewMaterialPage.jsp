<%@page import="coffeeshop.dto.MaterialDTO"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<jsp:include page="/asset/PageCustomLayout/admin-header-layout.jsp">
<jsp:param name="pageTitle" value="Material Information Page"/>
<jsp:param name="pageHeading" value="Material Information"/>
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
               <h6 class="m-0 font-weight-bold text-primary">View/Edit Material Information</h6>
             </div>
             <!-- Card Body -->
             <div class="card-body">
         	<form method="post" action="UpdateMaterialServlet">
         		<input type="hidden" name="id" value="${material.id}">
         		<input type="hidden" name="name" value="${material.name}">
	    		<div class="row">
	    			<div class="col-md-12">
	    				<h4 class="small font-weight-bold">Material name</h4>
	    				<input type="text" disabled class="form-control bg-light border-0 small"
	    				required="required" aria-describedby="basic-addon2" value="${material.name}">
	    			</div>
	    		</div>
	    		<br>
	    		<div class="row">
	    			<div class="col-md-12">
	    			<h4 class="small font-weight-bold">Price</h4>
	    				<input type="number" class="form-control bg-light border-0 small"
	    				name="price" aria-describedby="basic-addon2" value="${material.price}">
	    			</div>
	    		</div>
	    		<br>
	    		<div class="row">
	    			<div class="col-md-12">
	    			<h4 class="small font-weight-bold">Remaining</h4>
	    				<input type="number" class="form-control bg-light border-0 small"
	    				name="remaining" aria-describedby="basic-addon2" value="${material.remaining}">
	    			</div>
	    		</div>
	    		<br>
	    		<div class="row">
	    			<div class="col-md-12">
	    				<h4 class="small font-weight-bold">Unit</h4>
	    				<input type="text" class="form-control bg-light border-0 small" placeholder="Input Material Unit" 
	    				name="unit" aria-describedby="basic-addon2" value="${material.unit}">
	    			</div>
	    		</div>
	    		<br>
	    		<div class="row">
	    			<div class="col-md-12 d-sm-flex align-items-center justify-content-between">
		                <a href="<%=request.getContextPath()%>/GoSearchMaterialServlet" class="btn btn-secondary  btn-icon-split">
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