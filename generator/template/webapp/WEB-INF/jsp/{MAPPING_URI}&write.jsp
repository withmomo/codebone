<%@ page language="java"
	contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"

%><%@ page

	import="java.util.*,{{package}}.*,org.codebone.framework.BaseModel,java.util.Date,org.codebone.framework.util.PagingNavigation"

%><%

	String isCreate = (String) request.getAttribute("isCreate");
	{{tableNameCamelcase}} model = null;
	String key = null;
	if (isCreate.equals("Y")) {
		model = new {{tableNameCamelcase}}();
		isCreate = "Create";
	} else {
		BaseModel baseModel = (BaseModel) request.getAttribute("data");
		key = (String) request.getAttribute("id");
		model = ({{tableNameCamelcase}}) baseModel.getData();
		isCreate = "Update";
	}
%>
<!DOCTYPE HTML>
<html>
<%@ include file="/WEB-INF/jsp/import/config.jsp"%>
<title>{{siteTitle}} <%=isCreate%></title>
<body>
	<form class="well form-search" action="<%=request.getContextPath()%>/app/{{mappingUri}}/<%=isCreate.toLowerCase()%>" method="post">
	<%if(isCreate.equals("Update")){ %>
	<input type="hidden" class="form-vertical" name="idx" value="<%=model.getIdx().toString()%>">
	<%} %>

	<table class="table table-striped">
	{{#columns}}	<tr>
		<th>{{description}}</th>
		<td><input type="text" class="form-vertical" name="{{name}}" value="<%=model.get{{nameCamelcase}}()%>"></td>
	</tr>
	{{/columns}}
	</table>

	<div style="text-align: right">
		<button class="btn btn-primary btn-large" type="submit">
			<i class="icon-pencil icon-white"></i><%=isCreate %>
		</button>
	</div>
	</form>
</body>
</html>