<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page
	import="org.codebone.framework.BaseModel,java.util.List,org.codebone.security.manager.Manager,org.codebone.security.organization.Organization"%>
<%@ page
	import="java.lang.reflect.Field,java.lang.reflect.Method,javax.persistence.Id,java.util.Date"%>
<%
	String isCreate = (String) request.getAttribute("isCreate");
	Manager managerModel = null;
	String key = null;
	if (isCreate.equals("Y")) {
		managerModel = new Manager();
		isCreate = "Create";
	} else {
		BaseModel model = (BaseModel) request.getAttribute("data");
		key = (String) request.getAttribute("id");
		managerModel = (Manager) model.getData();
		isCreate = "Update";
	}
	List<Organization> organizationList = (List) request
			.getAttribute("organizationList");
%>
<section id="form">
	<form class="form-horizontal"
		action="<%=request.getContextPath()%>/app/manager/<%=isCreate.toLowerCase()%>"
		method="post">
		<div class="row-fluid">
			<div class="span12">

				<div class="control-group">
					<label class="control-label" for="id">ID</label>
					<div class="controls">
						<input type="text" class="input-large" id="id"
							value="<%=managerModel.getId()%>">
					</div>
				</div>

				<div class="control-group">
					<label class="control-label" for="id">password</label>
					<div class="controls">
						<input type="text" class="input-large" id="password">
					</div>
				</div>

				<div class="control-group">
					<label class="control-label" for="id">email</label>
					<div class="controls">
						<input type="text" class="input-large" id="email"
							value="<%=managerModel.getEmail()%>">
					</div>
				</div>

				<div class="control-group">
					<label class="control-label" for="id">name</label>
					<div class="controls">
						<input type="text" class="input-large" id="name"
							value="<%=managerModel.getName()%>">
					</div>
				</div>

				<div class="control-group">
					<label class="control-label" for="phonenumber">phonenumber</label>
					<div class="controls">
						<input type="text" class="input-large" id="phonenumber"
							value="<%=managerModel.getPhoneNumber()%>">
					</div>
				</div>

				<div class="control-group">
					<label class="control-label" for="organization">Organization</label>
					<div class="controls">
						<select name="organizationIdx">
							<%
								for (Organization org : organizationList) {
									String selected = "";
									if (isCreate.equals("Update")
											&& org.getIdx().equals(
													managerModel.getOrganizationIdx())) {
										selected = "selected=\"selected\"";
									}
							%>
							<option value="<%=org.getIdx()%>" <%=selected%>><%=org.getName()%></option>
							<%
								}
							%>
						</select>
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
		<input type="hidden" class="form-vertical" name="idx"
			value="<%=managerModel.getIdx()%>">
		<%
			}
		%>

	</form>
</section>