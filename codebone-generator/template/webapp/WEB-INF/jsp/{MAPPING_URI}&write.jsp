<%@ page language="java"
	contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"

%><%@ page

	import="java.util.*,<PACKAGE>.*,org.codebone.framework.BaseModel,java.util.Date,org.codebone.framework.util.PagingNavigation"

%><%

	String isCreate = (String) request.getAttribute("isCreate");
	<TABLE_NAME_CAMELCASE> model = null;
	String key = null;
	if (isCreate.equals("Y")) {
		model = new <TABLE_NAME_CAMELCASE>();
		isCreate = "Create";
	} else {
		BaseModel baseModel = (BaseModel) request.getAttribute("data");
		key = (String) request.getAttribute("id");
		model = (<TABLE_NAME_CAMELCASE>) baseModel.getData();
		isCreate = "Update";
	}
%>
<!DOCTYPE HTML>
<html>
<%@ include file="/WEB-INF/jsp/import/config.jsp"%>
<title><SITE_TITLE> <%=isCreate%></title>
<body>
	<form class="well form-search" action="<%=request.getContextPath()%>/app/<MAPPING_URI>/<%=isCreate.toLowerCase()%>" method="post">
	<%if(isCreate.equals("Update")){ %>
	<input type="hidden" class="form-vertical" name="idx" value="<%=model.getIdx().toString()%>">
	<%} %>

	<table class="table table-striped">
	<COLUMN_LOOP>	<tr>
		<th><COLUMN_DESCRIPTION></th>
		<td><input type="text" class="form-vertical" name="<COLUMN_NAME>" value="<%=model.get<COLUMN_NAME_CAMELCASE>()%>"></td>
	</tr>
	</COLUMN_LOOP>
	</table>

	<div style="text-align: right">
		<button class="btn btn-primary btn-large" type="submit">
			<i class="icon-pencil icon-white"></i><%=isCreate %>
		</button>
	</div>
	</form>
</body>
</html>