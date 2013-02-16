<%@ page language="java"
	contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"

%><%@ page

	import="java.util.*,org.codebone.domain.customer.*,org.codebone.framework.BaseModel,java.util.Date,org.codebone.framework.util.PagingNavigation"

%><%

	String isCreate = (String) request.getAttribute("isCreate");
	Customer model = null;
	String key = null;
	if (isCreate.equals("Y")) {
		model = new Customer();
		isCreate = "Create";
	} else {
		BaseModel baseModel = (BaseModel) request.getAttribute("data");
		key = (String) request.getAttribute("id");
		model = (Customer) baseModel.getData();
		isCreate = "Update";
	}
%>
<section id="form">
	<form class="form-horizontal"
		action="<%=request.getContextPath()%>/app/customer/<%=isCreate.toLowerCase()%>"
		method="post">
		<div class="row-fluid">
			<div class="span12">
				<% if(isCreate.equals("Create")){ %>
				<div class="control-group">
					<label class="control-label" for="SID">SID</label>
					<div class="controls">
						<input type="text" class="input-large" id="SID" name="Sid"
							value="<%=model.getSid()%>">
					</div>
				</div>
				<% } %>
				<div class="control-group">
					<label class="control-label" for="Last_Name">Last_Name</label>
					<div class="controls">
						<input type="text" class="input-large" id="Last_Name" name="LastName"
							value="<%=model.getLastName()%>">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="First_Name">First_Name</label>
					<div class="controls">
						<input type="text" class="input-large" id="First_Name" name="FirstName"
							value="<%=model.getFirstName()%>">
					</div>
				</div>
			</div>
			
		</div>

		<div class="form-actions">
			<button type="submit" class="btn btn-primary"><%=isCreate%></button>
			<button type="reset" class="btn">Cancel</button>
		</div>

		<%
			if (isCreate.equals("Update")) {
		%>
		<input type="hidden" class="form-vertical" name="Sid" value="<%=model.getSid()%>">
		<%
			}
		%>

	</form>
</section>
