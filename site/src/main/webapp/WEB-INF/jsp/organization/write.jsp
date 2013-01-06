<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page
	import="org.codebone.framework.BaseModel,java.util.List,org.codebone.security.organization.Organization"%>
<%@ page
	import="java.lang.reflect.Field,java.lang.reflect.Method,javax.persistence.Id,java.util.Date"%>
<%
	String isCreate = (String) request.getAttribute("isCreate");
	Organization orgModel = null;
	String key = null;
	if (isCreate.equals("Y")) {
		orgModel = new Organization();
		isCreate = "Create";
	} else {
		BaseModel model = (BaseModel) request.getAttribute("data");
		key = (String) request.getAttribute("id");
		orgModel = (Organization) model.getData();
		isCreate = "Update";
	}
%>
<section id="form">
	<form class="form-horizontal"
		action="<%=request.getContextPath()%>/app/organization/<%=isCreate.toLowerCase()%>"
		method="post">
		
		<div class="row-fluid">
			<div class="span12">
				<div class="control-group">
					<label class="control-label" for="name">name</label>
					<div class="controls">
						<input type="text" class="input-large" id="name" name="name"
							value="<%=orgModel.getName()%>">
					</div>
				</div>

				<div class="control-group">
					<label class="control-label" for="url">description</label>
					<div class="controls">
						<input type="text" class="input-large" id="description" name="description"
							value="<%=orgModel.getDescription()%>">
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
			value="<%=orgModel.getIdx()%>">
		<%
			}
		%>

	</form>
</section>