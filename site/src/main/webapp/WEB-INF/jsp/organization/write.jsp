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
<!DOCTYPE HTML>
<html>
<%@ include file="/WEB-INF/jsp/import/config.jsp"%>
<title><%=Organization.class.getSimpleName() + " " + isCreate%></title>
<body>
	<form class="well form-search"
		action="<%=request.getContextPath()%>/app/organization/<%=isCreate.toLowerCase() %>"
		method="post">
		<table class="table table-striped">
			<thead>
				<tr>
					<th>필드</th>
					<th>값</th>
				</tr>
			</thead>
			<tr>
				<th>name</th>
				<td><input type="text" class="form-vertical" name="name"
					value="<%=orgModel.getName()%>"></td>
			</tr>
			<tr>
				<th>description</th>
				<td><input type="text" class="form-vertical" name="description"
					value="<%=orgModel.getDescription()%>"></td>
			</tr>
			</tbody>
		</table>
		<div style="text-align: right">
			<button class="btn btn-primary btn-large" type="submit">
				<i class="icon-pencil icon-white"></i><%=isCreate %>
			</button>
		</div>
		<%if(isCreate.equals("Update")){ %>
		<input type="hidden" class="form-vertical" name="idx"
			value="<%=orgModel.getIdx()%>">
		</td>
		<%} %>
	</form>
</body>
</html>
