<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@page import="coffeeshop.dto.MaterialBilllDTO"%>
<%@page import="java.util.ArrayList" %>
<jsp:include page="/asset/PageCustomLayout/admin-header-layout.jsp">
<jsp:param name="pageTitle" value="Material Bill Page"/>
<jsp:param name="pageHeading" value="Material Bill List"/>
</jsp:include>
        
<!-- ###################### START TO INPUT YOUR CODE FROM HERE ###################### -->
<!-- Begin Page Content -->
		<!-- Alert information -->
		<c:if test="${deleteResult != null}">
	 		<jsp:include page="/asset/PageCustomLayout/alert-layout.jsp">
			<jsp:param name="result" value="${deleteResult}"/>
			<jsp:param name="message" value="${deleteMessage}"/>
			</jsp:include>
		</c:if>
      <!-- Content Row -->
       <div class="row">

			<!-- Area Chart -->
			<div class="col-xl-8 col-lg-8">
				<div class="card shadow mb-4">
					<!-- Card Header - Dropdown -->
					<div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
					<h6 class="m-0 font-weight-bold text-primary">Search Material Bill Information</h6>
					</div>
					<!-- Card Body -->
					<div class="card-body">
						<form method="get" action="SearchMaterialBillServlet" id="searchMaterialBillForm">    		
							<div class="row">
								<div class="col-md-12">
									<input type="text" class="form-control bg-light border-0 small" placeholder="User name" 
									name="userName" value="${materialBillSearchInfo.userName}" aria-describedby="basic-addon2">
								</div>
							</div>
							<br>

							<div class="row">
								<div class="col-md-12">
									<input type="number" class="form-control bg-light border-0 small" placeholder="Total price" 
									name="totalPrice" value="${materialBillSearchInfo.totalPrice}" aria-describedby="basic-addon2">
								</div>
							</div>
							<br>
						</form>
					</div>
				</div>
			</div>

			<!-- Pie Chart -->
			<div class="col-xl-4 col-lg-4">
			<div class="card shadow mb-4">
				<!-- Card Header - Dropdown -->
				<div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
				<h6 class="m-0 font-weight-bold text-primary">Button Action List</h6>
				</div>
				<!-- Card Body -->
				<div class="card-body">
				<div class="pt-2 pb-2">
					<div>
						<button type="submit" class="btn btn-primary btn-icon-split" form="searchMaterialBillForm" value="Submit">
					<span class="icon text-white-50">
							<i class="fas fa-search"></i>
					</span>
					<span class="text btn-text-action-list">Search Material Bill</span>
						</button>
					</div>
					<br>
					<div>
						<a href="<%=request.getContextPath()%>/GoCreateMaterialBillServlet" class="btn btn-success btn-icon-split">
					<span class="icon text-white-50">
						<i class="fas fa-check"></i>
					</span>
					<span class="text btn-text-action-list">Create Material Bill</span>
						</a>
					</div>
					<br>
					<div>
						<a href="<%=request.getContextPath()%>/DeleteMaterialBillServlet" class="btn btn-danger btn-icon-split">
					<span class="icon text-white-50">
						<i class="fas fa-trash"></i>
					</span>
					<span class="text btn-text-action-list">Delete Material Bill</span>
						</a>
					</div>
					<br>
					<div>
						<a href="<%=request.getContextPath()%>/GoDashboardServlet" class="btn btn-info btn-icon-split">
						<span class="icon text-white-50">
							<i class="fas fa-arrow-right"></i>
						</span>
						<span class="text btn-text-action-list">Dashboard</span>
						</a>
					</div>
				</div>
				</div>
			</div>
			</div>
       </div> 
       
       <!-- DataTales Example -->
       <div class="card shadow mb-4">
         <div class="card-header py-3">
           <h6 class="m-0 font-weight-bold text-primary">Material Bill List Result</h6>
         </div>
         <div class="card-body">
           <div class="table-responsive">
             <table class="table" id="dataTable" width="100%" cellspacing="0">
               <thead>
                 <tr>
                   <th>ID</th>
                   <th>User name</th>
                   <th>Total price</th>
                   <th>Select</th>
                 </tr>
               </thead>
               <tbody>
           <% 
            ArrayList<MaterialBilllDTO> materialBillDeleteList = (ArrayList) session.getAttribute("materialBillDeleteList");
          	ArrayList<MaterialBilllDTO> materialBills = (ArrayList<MaterialBilllDTO>) request.getAttribute("materialBills"); 
           	for (int i = 0; i < materialBills.size(); i++) {
           		MaterialBilllDTO getMaterialBill = materialBills.get(i); %>
            	<tr>
            	<td><a href="<%=request.getContextPath()%>/ViewMaterialBillServlet?id=<%=getMaterialBill.getId()%>"><%=getMaterialBill.getId()%></a></td>
            	<td><%=getMaterialBill.getUserName()%></td>
            	<td><%=getMaterialBill.getTotalPrice()%></td>
            	<%
            		boolean idExisted = false;
                      	if (materialBillDeleteList != null)
                      	{
                      		for (int j = 0; j < materialBillDeleteList.size(); j++) {
                      			MaterialBilllDTO newMaterialBill = materialBillDeleteList.get(j);
          		                if (newMaterialBill.getId() == getMaterialBill.getId()) {
          		                	idExisted = true;
          		                    break;
          		                }
          		            }%>
          		            	<td><input type="checkbox" name="selectAndUpdate" 
          		            	<%
           		            	if (idExisted == true)
                        		{
           		            		out.print("checked");
                        		}
          		            	%>
                           		onclick='window.location.assign("UpdateMaterialBillDeleteList?id=<%=getMaterialBill.getId()%>")'/></td> 
                           		
                      	<%}
                      	else {%>
                      		<td>
                       		<input type="checkbox" name="selectAndUpdate" 
                           		onclick='window.location.assign("UpdateMaterialBillDeleteList?id=<%=getMaterialBill.getId()%>")'/>
						</td> 
						</tr>
                      	<%}
           		}%>
         		</tbody>
             </table>
           </div>
         </div>
       </div>
<!-- ###################### END OF YOUR CODE ###################### -->

<jsp:include page="/asset/PageCustomLayout/footer-layout.jsp"></jsp:include>