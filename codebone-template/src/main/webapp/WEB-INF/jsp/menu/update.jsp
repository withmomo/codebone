<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page
	import="org.codebone.framework.BaseModel,java.util.List,org.codebone.security.menu.Menu,org.codebone.security.manager.Manager"%>
<%@ page
	import="java.lang.reflect.Field,java.lang.reflect.Method,javax.persistence.Id,java.util.Date"%>
<%
	String isCreate = (String) request.getAttribute("isCreate");
	Manager currentLoginManager = (Manager) request
	.getAttribute("loginManager");
	Menu menuModel = null;
	String key = null;
	if (isCreate.equals("Y")) {
		menuModel = new Menu();
		isCreate = "Create";
	} else {
		BaseModel model = (BaseModel) request.getAttribute("data");
		key = (String) request.getAttribute("id");
		menuModel = (Menu) model.getData();
		isCreate = "Update";
	}
%>
<!DOCTYPE HTML>
<html>
<%@ include file="/WEB-INF/jsp/import/config.jsp"%>
<link href="../../../css/datepicker.css" rel="stylesheet">
<script type="text/javascript" src="../../../js/bootstrap-datepicker.js"></script>
<title><%=Menu.class.getSimpleName() + " " + isCreate%></title>
<body>
	<form class="well form-search"
		action="<%=request.getContextPath()%>/app/menu/<%=isCreate.toLowerCase()%>"
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
					value="<%=menuModel.getName()%>"></td>
			</tr>
			<tr>
				<th>url</th>
				<td><input type="text" class="form-vertical" name="url"
					value="<%=menuModel.getUrl()%>"></td>
			</tr>
			<tr>
				<th>priOrder</th>
				<td><input type="text" class="form-vertical" name="priOrder"
					value="<%=menuModel.getPriOrder()%>"></td>
			</tr>
			<tr>
				<th>subOrder</th>
				<td><input type="text" class="form-vertical" name="subOrder"
					value="<%=menuModel.getSubOrder()%>"></td>
			</tr>
			<tr>
				<th>isSeparate</th>
				<td><input type="text" class="form-vertical" name="isSeparate"
					value="<%=menuModel.getIsSeparate()%>"></td>
			</tr>
			<tr>
				<th>isExternal</th>
				<td><input type="text" class="form-vertical" name="isExternal"
					value="<%=menuModel.getIsExternal()%>"></td>
			</tr>
			<tr>
				<th>managerIdx</th>
				<td><input type="text" class="form-vertical" name="managerIdx"
					<%if (isCreate.equals("Create")) {%>
					value="<%=currentLoginManager.getIdx()%>"></td>
				<%
					} else {
				%>
				value="<%=menuModel.getManagerIdx()%>">
				</td>
				<%
					}
				%>
			</tr>
			</tbody>
		</table>
		<div style="text-align: right">
			<button class="btn btn-primary btn-large" type="submit">
				<i class="icon-pencil icon-white"></i><%=isCreate%>
			</button>
		</div>
		<%
			if (isCreate.equals("Update")) {
		%>
		<input type="hidden" class="form-vertical" name="idx"
			value="<%=menuModel.getIdx()%>">
		</td>
		<%
			}
		%>
	</form>
</body>
</html>
