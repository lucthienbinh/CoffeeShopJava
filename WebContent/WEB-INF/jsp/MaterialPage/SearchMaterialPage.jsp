<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@page import="coffeeshop.dto.MaterialDTO"%>
<%@page import="java.util.ArrayList" %>
<jsp:include page="/asset/PageCustomLayout/admin-header-layout.jsp">
<jsp:param name="pageTitle" value="Order Menu Page"/>
<jsp:param name="pageHeading" value="Order Menu List"/>
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
                    <h6 class="m-0 font-weight-bold text-primary">Search Order Menu Information</h6>
                    </div>
                    <!-- Card Body -->
                    <div class="card-body">
                        <form method="get" action="SearchMaterialServlet" id="searchMaterialForm">
                            <% 
                                MaterialDTO materialSearchInfo = (MaterialDTO) session.getAttribute("materialSearchInfo");
                            %>
                            
                            <div class="row">
                                <div class="col-md-12">
                                    <input type="text" class="form-control bg-light border-0 small" placeholder="Name" 
                                    name="name" value="<%=materialSearchInfo.getName()%>" aria-describedby="basic-addon2">
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
                                <button type="submit" class="btn btn-primary btn-icon-split" form="searchMaterialForm" value="Submit">
                                <span class="icon text-white-50"><i class="fas fa-search"></i></span>
                                <span class="text btn-text-action-list">Search Order Menu</span>
                                </button>
                            </div>
                            <br>

                            <div>
                                <a href="<%=request.getContextPath()%>/GoCreateMaterialServlet" class="btn btn-success btn-icon-split">
                                    <span class="icon text-white-50"><i class="fas fa-check"></i></span>
                                    <span class="text btn-text-action-list">Create Order Menu</span>
                                </a>
                            </div>
                            <br>

                            <div>
                                <a href="<%=request.getContextPath()%>/DeleteMaterialServlet" class="btn btn-danger btn-icon-split">
                                    <span class="icon text-white-50"><i class="fas fa-trash"></i></span>
                                    <span class="text btn-text-action-list">Delete Order Menu</span>
                                </a>
                            </div>
                            <br>

                            <div>
                                <a href="<%=request.getContextPath()%>/GoDashboardServlet" class="btn btn-info btn-icon-split">
                                    <span class="icon text-white-50"><i class="fas fa-arrow-right"></i></span>
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
            <h6 class="m-0 font-weight-bold text-primary">Order Menu List Result</h6>
            </div>

            <div class="card-body" style="cursor:default">
                <div class="table-responsive">
                    <table class="table" id="dataTable" width="100%" cellspacing="0">
                        <thead>
                            <tr>
                            <th>Name</th>
                            <th>Price</th>
                            <th>Remaining</th>
                            <th>Unit</th>
                            <th>Select</th>
                            </tr>
                        </thead>
                        <tbody>
                        <% 
                        ArrayList<MaterialDTO> materialDeleteList = (ArrayList) session.getAttribute("materialDeleteList");
                        ArrayList<MaterialDTO> materials = (ArrayList<MaterialDTO>) request.getAttribute("materials"); 
                        for (int i = 0; i < materials.size(); i++) {
                            MaterialDTO getMaterial = materials.get(i); %>
                            <tr>
                            <td><a href="<%=request.getContextPath()%>/ViewMaterialServlet?id=<%=getMaterial.getId()%>"><%=getMaterial.getName()%></a></td>
                            <td><%=getMaterial.getPrice()%></td>
                            <td><%=getMaterial.getRemaining()%></td>
                            <td><%=getMaterial.getUnit()%></td>
                            <%
                                boolean checkMaterialname = false;
                                    if (materialDeleteList != null){
                                        for (int j = 0; j < materialDeleteList.size(); j++) {
                                            MaterialDTO newMaterial = materialDeleteList.get(j);
                                            
                                            if(newMaterial.getId() == getMaterial.getId()){
                                                checkMaterialname = true;
                                                break;
                                            }
                                        }%>
                                            <td><input type="checkbox" name="selectAndUpdate" style="cursor:pointer"
                                            <%  if(checkMaterialname == true){
                                                out.print("checked");
                                            }
                                            %>
                                            onclick='window.location.assign("UpdateMaterialDeleteList?id=<%=getMaterial.getId()%>")'/>
                                            </td> 
                                    <%}
                                    else {%>
                                        <td>
                                        <input type="checkbox" name="selectAndUpdate" style="cursor:pointer"
                                        onclick='window.location.assign("UpdateMaterialDeleteList?id=<%=getMaterial.getId()%>")'/>
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