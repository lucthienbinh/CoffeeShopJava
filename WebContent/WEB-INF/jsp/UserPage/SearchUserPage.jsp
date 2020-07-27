<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@page import="coffeeshop.dto.UserDTO"%>
<%@page import="java.util.ArrayList" %>
<jsp:include page="/asset/PageCustomLayout/admin-header-layout.jsp">
<jsp:param name="pageTitle" value="Employee User Page"/>
<jsp:param name="pageHeading" value="Employee User List"/>
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
               <h6 class="m-0 font-weight-bold text-primary">Search User Information</h6>
             </div>
             <!-- Card Body -->
             <div class="card-body">
                <form method="get" action="SearchUserServlet" id="searchUserForm">
    		<% 
    			UserDTO userSearchInfo = (UserDTO) session.getAttribute("userSearchInfo");
    		%>
    		<div class="row">
    			<div class="col-md-12">
    				<input type="text" class="form-control bg-light border-0 small" placeholder="Email" 
    				name="email" value="<%=userSearchInfo.getEmail() %>" aria-describedby="basic-addon2">
    			</div>
    		</div>
    		<br>
    		<div class="row">
    			<div class="col-md-6">
    				<input type="text" class="form-control bg-light border-0 small" placeholder="First name" 
    				name="firstname" value="<%=userSearchInfo.getFirstname() %>" aria-describedby="basic-addon2">
    			</div>
    			<div class="col-md-6">
    				<input type="text" class="form-control bg-light border-0 small" placeholder="Last name" 
    				name="lastname" value="<%=userSearchInfo.getLastname() %>" aria-describedby="basic-addon2">
    			</div>
    		</div>
    		<br>
    		<div class="row">
    			<div class="col-md-6">
    				<input type="text" class="form-control bg-light border-0 small" placeholder="Sex" 
    				name="sex" value="<%=userSearchInfo.getSex() %>" aria-describedby="basic-addon2">
    			</div>
    			<div class="col-md-6">
    				<input type="text" class="form-control bg-light border-0 small" placeholder="Address" 
    				name="address" value="<%=userSearchInfo.getAddress() %>" aria-describedby="basic-addon2">
    			</div>
    		</div>
    		<br>
    		<div class="row">
    			<div class="col-md-6">
    				<input type="text" class="form-control bg-light border-0 small" placeholder="Mobile phone" 
    				name="mobilephone" value="<%=userSearchInfo.getMobilephone() %>" aria-describedby="basic-addon2">
    			</div>
    			<div class="col-md-6">
    				<select name="groupid" class="form-control bg-light border-0 small">
                    <option value="0" selected>All</option>
                    <option value="1">Admin</option>
                    <option value="2">User</option>
                    <option value="3">Customer</option>
                </select>
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
                   <span class="text btn-text-action-list">Search User</span>
                	</button>
                </div>
                <br>
                <div>
                	<a href="<%=request.getContextPath()%>/GoCreateUserServlet" class="btn btn-success btn-icon-split">
                   <span class="icon text-white-50">
                     <i class="fas fa-check"></i>
                   </span>
                   <span class="text btn-text-action-list">Create User</span>
                	</a>
                </div>
                <br>
               	  <div>
               	  	<a href="<%=request.getContextPath()%>/DeleteUserServlet" class="btn btn-danger btn-icon-split">
                   <span class="icon text-white-50">
                     <i class="fas fa-trash"></i>
                   </span>
                   <span class="text btn-text-action-list">Delete User</span>
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
                   <th>Email</th>
                   <th>First name</th>
                   <th>Last name</th>
                   <th>Sex</th>
                   <th>Address</th>
                   <th>Mobile phone</th>
                   <th>Role name</th>
                   <th>Select</th>
                 </tr>
               </thead>
               <tbody>
           <% 
          	ArrayList<UserDTO> userDeleteList = (ArrayList) session.getAttribute("userDeleteList");
          	ArrayList<UserDTO> users = (ArrayList<UserDTO>) request.getAttribute("users"); 
           	for (int i = 0; i < users.size(); i++) {
            	UserDTO getUser = users.get(i); %>
            	<tr>
            	<td><a href="<%=request.getContextPath()%>/ViewUserServlet?email=<%=getUser.getEmail()%>"><%=getUser.getEmail()%></a></td>
            	<td><%=getUser.getFirstname()%></td>
            	<td><%=getUser.getLastname()%></td>
            	<td><%=getUser.getSex()%></td>
            	<td><%=getUser.getAddress()%></td>
            	<td><%=getUser.getMobilephone()%></td>
            	<td><%=getUser.getGroupname()%></td>
            	<%
            		boolean checkUsername = false;
                      	if (userDeleteList != null)
                      	{
                      		for (int j = 0; j < userDeleteList.size(); j++) {
          		            	UserDTO newUser = userDeleteList.get(j);
          		                if (newUser.getId() == getUser.getId()) {
          		                	checkUsername = true;
          		                    break;
          		                }
          		            }%>
          		            	<td><input type="checkbox" name="selectAndUpdate" 
          		            	<%
           		            	if (checkUsername == true)
                        		{
           		            		out.print("checked");
                        		}
          		            	%>
                           		onclick='window.location.assign("UpdateUserDeleteList?email=<%=getUser.getEmail()%>")'/></td> 
                           		
                      	<%}
                      	else {%>
                      		<td>
                       		<input type="checkbox" name="selectAndUpdate" 
                           		onclick='window.location.assign("UpdateUserDeleteList?email=<%=getUser.getEmail()%>")'/>
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