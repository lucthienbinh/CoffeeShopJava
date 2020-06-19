<% 
boolean result = Boolean.parseBoolean(request.getParameter("result")); 
String message = request.getParameter("message"); 
%>
<%
	if(result == true)
	{%>
		<div class="row">
			<div class="col-lg-6 mb-4">
			  <div class="card bg-success text-white shadow">
			    <div class="card-body">
			      <%=message%>
			    </div>
			  </div>
			</div>
		</div>
	<%}
	if(result == false)
	{%>
		<div class="row">
			<div class="col-lg-6 mb-4">
			  <div class="card bg-danger text-white shadow">
			    <div class="card-body">
			      <%=message%>
			    </div>
			  </div>
			</div>
		</div>
	<%}
%>

