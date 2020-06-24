<%@page import="coffeeshop.dto.MaterialDTO"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<jsp:include page="/asset/PageCustomLayout/admin-header-layout.jsp">
<jsp:param name="pageTitle" value="User Information Page"/>
<jsp:param name="pageHeading" value="User Information"/>
</jsp:include>

<!-- ###################### START TO INPUT YOUR CODE FROM HERE ###################### -->
<a href="<%=request.getContextPath()%>/ViewMaterialServlet?name=Trai Cam">View/Edit example material</a>
<!-- ###################### END OF YOUR CODE ###################### -->

<jsp:include page="/asset/PageCustomLayout/footer-layout.jsp"></jsp:include>