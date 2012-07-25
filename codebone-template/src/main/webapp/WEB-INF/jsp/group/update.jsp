<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page
	import="org.codebone.framework.BaseModel,java.util.List,org.codebone.security.manager.ManagerModel"%>
<%@ page
	import="java.lang.reflect.Field,java.lang.reflect.Method,javax.persistence.Id,java.util.Date"%>
<%
	String isCreate = (String) request.getAttribute("isCreate");
	ManagerModel managerModel = null;
	String key = null;
	if (isCreate.equals("Y")) {
		managerModel = new ManagerModel();
		isCreate = "Create";
	} else {
		BaseModel model = (BaseModel) request.getAttribute("data");
		key = (String) request.getAttribute("id");
		managerModel = (ManagerModel) model.getData();
		isCreate = "Update";
	}
%>
<!DOCTYPE HTML>
<html>
<%@ include file="/WEB-INF/jsp/import/config.jsp"%>
<link href="../../../css/datepicker.css" rel="stylesheet">
<script type="text/javascript" src="../../../js/bootstrap-datepicker.js"></script>
<title><%=ManagerModel.class.getSimpleName() + " " + isCreate%></title>
<body>
	<form class="well form-search"
		action="<%=request.getContextPath()%>/admin/manager/<%=isCreate.toLowerCase() %>"
		method="post">
		<table class="table table-striped">
			<thead>
				<tr>
					<th>필드</th>
					<th>값</th>
				</tr>
			</thead>


			<tr>
				<th>id</th>
				<td><input type="text" class="form-vertical" name="id"
					value="<%=managerModel.getId()%>"></td>
			</tr>
			<tr>
				<th>password</th>
				<td><input type="password" class="form-vertical"
					name="password" value=""></td>
			</tr>
			<tr>
				<th>email</th>
				<td><input type="text" class="form-vertical" name="email"
					value="<%=managerModel.getEmail()%>"></td>
			</tr>
			<tr>
				<th>name</th>
				<td><input type="text" class="form-vertical" name="name"
					value="<%=managerModel.getName()%>"></td>
			</tr>
			<tr>
				<th>phoneNumber</th>
				<td><input type="text" class="form-vertical" name="phoneNumber"
					value="<%=managerModel.getPhoneNumber()%>"></td>
			</tr>
			<tr>
				<th>groupIdx</th>
				<td><input type="text" class="form-vertical" name="level"
					value="<%=managerModel.getGroupIdx()%>"></td>
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
			value="<%=managerModel.getIdx()%>">
		</td>
		<%} %>
	</form>
</body>
</html>
