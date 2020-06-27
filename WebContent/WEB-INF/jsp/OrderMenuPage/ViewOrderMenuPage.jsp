<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@page import="coffeeshop.dto.OrderMenuDTO"%>
<jsp:include page="/asset/PageCustomLayout/admin-header-layout.jsp">
<jsp:param name="pageTitle" value="Order Menu Information Page"/>
<jsp:param name="pageHeading" value="Order Menu Information"/>
</jsp:include>

<style>
	/* Chrome, Safari, Edge, Opera */
	input::-webkit-outer-spin-button,
	input::-webkit-inner-spin-button {
	-webkit-appearance: none;
	margin: 0;
	}
</style>

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
					<h6 class="m-0 font-weight-bold text-primary">View/Edit Order Menu Information</h6>
					</div>
					
					<!-- Card Body -->
					<div class="card-body">
						<form method="post" action="UpdateOrderMenuServlet">
							<input type="hidden" name="id" value="${orderMenu.getId()}">

							<div class="row">
								<div class="col-md-12">
									<h4 class="small font-weight-bold">Drink name</h4>
									<input type="text" class="form-control bg-light border-0 small" placeholder="Input Drink Name" 
									name="name" aria-describedby="basic-addon2" value="${orderMenu.getName()}">
								</div>
							</div>
							<br>

							<div class="row">
								<div class="col-md-12">
								<h4 class="small font-weight-bold">Drink Price</h4>
									<input type="number" class="form-control bg-light border-0 small" placeholder="Input Price" 
									name="price" aria-describedby="basic-addon2" value="${orderMenu.getPrice()}">
								</div>
							</div>
							<br>
							
							<div class="row">
								<div class="col-md-12 d-sm-flex align-items-center justify-content-between">
									<a href="<%=request.getContextPath()%>/GoSearchOrderMenuServlet" class="btn btn-secondary  btn-icon-split">
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