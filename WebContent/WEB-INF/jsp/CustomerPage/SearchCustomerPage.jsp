<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@page import="coffeeshop.dto.CustomerDTO"%>
<%@page import="java.util.ArrayList" %>
<jsp:include page="/asset/PageCustomLayout/admin-header-layout.jsp">
<jsp:param name="pageTitle" value="Customer User Page"/>
<jsp:param name="pageHeading" value="Customer User List"/>
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
               <h6 class="m-0 font-weight-bold text-primary">Search Customer Information</h6>
             </div>
             <!-- Card Body -->
             <div class="card-body">
                <form method="get" action="SearchCustomerServlet" id="searchUserForm">
    		<% 
    			CustomerDTO customerSearchInfo = (CustomerDTO) session.getAttribute("customerSearchInfo");
    		%>
    		
    		<div class="row">
    			<div class="col-md-12">
    				<input type="text" class="form-control bg-light border-0 small" placeholder="Customer name" 
    				name="name" value="<%=customerSearchInfo.getName() %>" aria-describedby="basic-addon2">
    			</div>
    		</div>
    		<br>
    		<div class="row">
    			<div class="col-md-12">
    				<input type="text" class="form-control bg-light border-0 small" placeholder="Email" 
    				name="email" value="<%=customerSearchInfo.getEmail() %>" aria-describedby="basic-addon2">
    			</div>
    		</div>
    		<br>
    		<div class="row">
    			<div class="col-md-12">
    				<input type="text" class="form-control bg-light border-0 small" placeholder="Address" 
    				name="address" value="<%=customerSearchInfo.getAddress() %>" aria-describedby="basic-addon2">
    			</div>
    		</div>
    		<br>
    		<div class="row">
    			<div class="col-md-12">
    				<input type="text" class="form-control bg-light border-0 small" placeholder="Mobile phone" 
    				name="mobilephone" value="<%=customerSearchInfo.getMobilephone() %>" aria-describedby="basic-addon2">
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
                	<button type="submit" class="btn btn-primary btn-icon-split" form="searchUserForm" value="Submit">
                   <span class="icon text-white-50">
                    	<i class="fas fa-search"></i>
                   </span>
                   <span class="text btn-text-action-list">Search Customer</span>
                	</button>
                </div>
                <br>
                <div>
                	<a href="<%=request.getContextPath()%>/GoCreateCustomerServlet" class="btn btn-success btn-icon-split">
                   <span class="icon text-white-50">
                     <i class="fas fa-check"></i>
                   </span>
                   <span class="text btn-text-action-list">Create Customer</span>
                	</a>
                </div>
                <br>
               	  <div>
               	  	<a href="<%=request.getContextPath()%>/DeleteCustomerServlet" class="btn btn-danger btn-icon-split">
                   <span class="icon text-white-50">
                     <i class="fas fa-trash"></i>
                   </span>
                   <span class="text btn-text-action-list">Delete Customer</span>
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
           <h6 class="m-0 font-weight-bold text-primary">User List Result</h6>
         </div>
         <div class="card-body">
           <div class="table-responsive">
             <table class="table" id="dataTable" width="100%" cellspacing="0">
               <thead>
                 <tr>
                 	<th>Customer name</th>
                   <th>Email</th>
                   <th>Address</th>
                   <th>Mobile-phone</th>
                   <th>Select</th>
                 </tr>
               </thead>
               <tbody>
           <% 
            ArrayList<CustomerDTO> customerDeleteList = (ArrayList) session.getAttribute("customerDeleteList");
          	ArrayList<CustomerDTO> customers = (ArrayList<CustomerDTO>) request.getAttribute("customers"); 
           	for (int i = 0; i < customers.size(); i++) {
           		CustomerDTO getCustomer = customers.get(i); %>
            	<tr>
            	<td><a href="<%=request.getContextPath()%>/ViewCustomerServlet?email=<%=getCustomer.getEmail()%>"><%=getCustomer.getName()%></a></td>
            	<td><%=getCustomer.getEmail()%></td>
            	<td><%=getCustomer.getAddress()%></td>
            	<td><%=getCustomer.getMobilephone()%></td>
            	<%
            		boolean emailExisted = false;
                      	if (customerDeleteList != null)
                      	{
                      		for (int j = 0; j < customerDeleteList.size(); j++) {
                      			CustomerDTO newUser = customerDeleteList.get(j);
          		                if (newUser.getId() == getCustomer.getId()) {
          		                	emailExisted = true;
          		                    break;
          		                }
          		            }%>
          		            	<td><input type="checkbox" name="selectAndUpdate" 
          		            	<%
           		            	if (emailExisted == true)
                        		{
           		            		out.print("checked");
                        		}
          		            	%>
                           		onclick='window.location.assign("UpdateCustomerDeleteList?email=<%=getCustomer.getEmail()%>")'/></td> 
                           		
                      	<%}
                      	else {%>
                      		<td>
                       		<input type="checkbox" name="selectAndUpdate" 
                           		onclick='window.location.assign("UpdateCustomerDeleteList?email=<%=getCustomer.getEmail()%>")'/>
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