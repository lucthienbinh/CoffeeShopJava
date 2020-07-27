<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@page import="coffeeshop.dto.CustomerDTO"%>
<%@page import="coffeeshop.dto.OrderingBillDTO"%>
<%@page import="java.util.ArrayList" %>
<jsp:include page="/asset/PageCustomLayout/admin-header-layout.jsp">
<jsp:param name="pageTitle" value="Create Ordering Bill Page"/>
<jsp:param name="pageHeading" value="Create Ordering Bill"/>
</jsp:include>

<!-- ###################### START TO INPUT YOUR CODE FROM HERE ###################### -->
<!-- Begin Page Content -->
		<!-- Alert information -->
		<c:if test="${createResult != null}">
	 		<jsp:include page="/asset/PageCustomLayout/alert-layout.jsp">
			<jsp:param name="result" value="${createResult}"/>
			<jsp:param name="message" value="${createMessage}"/>
			</jsp:include>
		</c:if>
		
   	   	<!-- Content Row -->
       <div class="row">

         <!-- Area Chart -->
         <div class="col-xl-12 col-lg-12">
           <div class="card shadow mb-4">
             <!-- Card Header - Dropdown -->
             <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
               <h6 class="m-0 font-weight-bold text-primary">New Ordering Bill Information</h6>
             </div>
             <!-- Card Body -->
             <div class="card-body">
         	<form method="post" action="InsertOrderingBillServlet">
	    		<div class="row">
	    			<div class="col-md-6">
	    				<h4 class="small font-weight-bold">Customer name</h4>
	    				<input type="text" class="form-control bg-light border-0 small" placeholder="Select customer in list"
	    				disabled value="${orderingBillInfo.customerName}" aria-describedby="basic-addon2">
	    			</div>
	    			<div class="col-md-6">
	    				<h4 class="small font-weight-bold">Employee name</h4>
	    				<input type="text" class="form-control bg-light border-0 small" 
	    				disabled value="${orderingBillInfo.userName}" aria-describedby="basic-addon2">
	    			</div>
	    		</div>
	    		<br>
	    		<div class="row">
	    			<div class="col-md-6">
	    			<h4 class="small font-weight-bold">Total Price</h4>
	    				<input type="text" class="form-control bg-light border-0 small" value="${orderingBillInfo.totalPrice}"
	    				name="price" aria-describedby="basic-addon2">
	    			</div>
	    		</div>
	    		<br>
	    		<div class="table-responsive">
	             <table class="table" id="dataTable" width="100%" cellspacing="0">
	               <thead>
	                 <tr>
	                   <th>Drink name</th>
	                   <th>Quantity</th>
	                   <th>Drink price</th>
	                   <th>Amount</th>
	                   <th>Action</th>
	                 </tr>
	               </thead>
	               <tbody>
	               		<c:forEach var="orderingBillDetail" items="${orderingBillDetailList}">
							<tr>
								<td><c:out value="${orderingBillDetail.orderMenuName}" /></td>
								<td><c:out value="${orderingBillDetail.quantity}" /></td>
								<td><c:out value="${orderingBillDetail.price}" /></td>
								<td><c:out value="${orderingBillDetail.amount}" /></td>
								<td>
								<a href="<%=request.getContextPath()%>/UpdateOrderingBillServlet?addOrderMenuId=${orderingBillDetail.orderMenuId}" class="btn btn-success btn-circle">
								  <i class="fas fa-plus"></i>
								</a>
								&nbsp;&nbsp;
								<a href="<%=request.getContextPath()%>/UpdateOrderingBillServlet?removeOrderMenuId=${orderingBillDetail.orderMenuId}" class="btn btn-warning btn-circle">
								  <i class="fas fa-minus"></i>
								</a>
								</td>
							</tr>
						</c:forEach>
	         		</tbody>
	             </table>
	           </div>
	    		<br>
	    		<div class="row">
	    			<div class="col-md-12 d-sm-flex align-items-center justify-content-between">
		                <a href="<%=request.getContextPath()%>/GoSearchOrderingBillServlet" class="btn btn-secondary  btn-icon-split">
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
		
	   <!-- Content Row -->
       <div class="row">

         <!-- Area Chart -->
         <div class="col-xl-6 col-lg-6">
		   <!-- DataTales Example -->
	       <div class="card shadow mb-4">
	         <div class="card-header py-3">
	           <h6 class="m-0 font-weight-bold text-primary">Select Customer</h6>
	         </div>
	         <div class="card-body">
	           <div class="table-responsive">
	             <table class="table" id="dataTable1" width="100%" cellspacing="0">
	               <thead>
	                 <tr>
	                   <th>ID</th>
	                   <th>Customer name</th>
	                   <th>Mobile phone</th>
	                   <th>Select</th>
	                 </tr>
	               </thead>
	               <tbody>
	               		<c:forEach var="customer" items="${customers}">
							<tr>
								<td><c:out value="${customer.id}" /></td>
								<td><c:out value="${customer.name}" /></td>
								<td><c:out value="${customer.mobilephone}" /></td>
								<td>
								<a href="<%=request.getContextPath()%>/UpdateOrderingBillServlet?customerId=${customer.id}" class="btn btn-primary  btn-icon-split">
									<span class="text">Select</span>
								</a>
								</td>
							</tr>
						</c:forEach>
	         		</tbody>
	             </table>
	           </div>
	         </div>
	       </div>
         </div>
         
         <!-- Area Chart -->
         <div class="col-xl-6 col-lg-6">
		   <!-- DataTales Example -->
	       <div class="card shadow mb-4">
	         <div class="card-header py-3">
	           <h6 class="m-0 font-weight-bold text-primary">Select Drinks</h6>
	         </div>
	         <div class="card-body">
	           <div class="table-responsive">
	             <table class="table" id="dataTable2" width="100%" cellspacing="0">
	               <thead>
	                 <tr>
	                   <th>ID</th>
	                   <th>Drink name</th>
	                   <th>Drink price</th>
	                   <th>Select</th>
	                 </tr>
	               </thead>
	               <tbody>
	               		<c:forEach var="orderMenu" items="${orderMenus}">
							<tr>
								<td><c:out value="${orderMenu.id}" /></td>
								<td><c:out value="${orderMenu.name}" /></td>
								<td><c:out value="${orderMenu.price}" /></td>
								<td>
								<a href="<%=request.getContextPath()%>/UpdateOrderingBillServlet?addOrderMenuId=${orderMenu.id}" class="btn btn-primary  btn-icon-split">
									<span class="text">Select</span>
								</a>
								</td>
							</tr>
						</c:forEach>
	         		</tbody>
	             </table>
	           </div>
	         </div>
	       </div>
         </div>
       </div>
       
<!-- ###################### END OF YOUR CODE ###################### -->

<jsp:include page="/asset/PageCustomLayout/footer-layout.jsp"></jsp:include>