<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page
	import="org.codebone.framework.BaseModel,java.util.List,org.codebone.security.authorities.Authorities"%>
<%@ page
	import="java.lang.reflect.Field,java.lang.reflect.Method,javax.persistence.Id,java.util.Date"%>
<%
	String isCreate = (String) request.getAttribute("isCreate");
	Authorities authorities = null;
	String key = null;
	if (isCreate.equals("Y")) {
		authorities = new Authorities();
		authorities.setOrganizationIdx((Long)request.getAttribute("organizationIdx"));
		isCreate = "Create";
	} else {
		BaseModel model = (BaseModel) request.getAttribute("data");
		key = (String) request.getAttribute("id");
		authorities = (Authorities) model.getData();
		isCreate = "Update";
	}
%>
<!DOCTYPE HTML>
<html>

<body>
	<form class="well form-search"
		action="<%=request.getContextPath()%>/app/organization/authCreate"
		method="post">
		<table class="table table-striped">
			<thead>
				<tr>
					<th>필드</th>
					<th>값</th>
				</tr>
			</thead>
			<tr>
				<th>organizationIdx</th>
				<td><input type="text" class="form-vertical" name="organizationIdx"
					value="<%=authorities.getOrganizationIdx()%>"></td>
			</tr>
			<tr>
				<th>authority</th>
				<td><input type="text" class="form-vertical" name="authority"
					value="<%=authorities.getAuthority()%>"></td>
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
			value="<%=authorities.getIdx()%>">
		</td>
		<%} %>
	</form>
</body>
</html>
