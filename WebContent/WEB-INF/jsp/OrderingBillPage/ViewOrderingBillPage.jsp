<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@page import="coffeeshop.dto.OrderingBillDTO"%>
<jsp:include page="/asset/PageCustomLayout/admin-header-layout.jsp">
<jsp:param name="pageTitle" value="View Ordering Bill Page"/>
<jsp:param name="pageHeading" value="View Ordering Bill"/>
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
	    				disabled value="${orderingBill.customerName}" aria-describedby="basic-addon2">
	    			</div>
	    			<div class="col-md-6">
	    				<h4 class="small font-weight-bold">Employee name</h4>
	    				<input type="text" class="form-control bg-light border-0 small" 
	    				disabled value="${orderingBill.userName}" aria-describedby="basic-addon2">
	    			</div>
	    		</div>
	    		<br>
	    		<div class="row">
	    			<div class="col-md-6">
	    			<h4 class="small font-weight-bold">Total Price</h4>
	    				<input type="text" class="form-control bg-light border-0 small" value="${orderingBill.totalPrice}"
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
	                 </tr>
	               </thead>
	               <tbody>
	               		<c:forEach var="orderingBillDetail" items="${orderingBillDetailList}">
							<tr>
								<td><c:out value="${orderingBillDetail.orderMenuName}" /></td>
								<td><c:out value="${orderingBillDetail.quantity}" /></td>
								<td><c:out value="${orderingBillDetail.price}" /></td>
								<td><c:out value="${orderingBillDetail.amount}" /></td>
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
<!-- ###################### END OF YOUR CODE ###################### -->

<jsp:include page="/asset/PageCustomLayout/footer-layout.jsp"></jsp:include>