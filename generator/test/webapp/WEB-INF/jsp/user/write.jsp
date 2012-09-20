<%@ page language="java"
	contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"

%><%@ page

	import="java.util.*,org.codebone.domain.user.*,org.codebone.framework.BaseModel,java.util.Date,org.codebone.framework.util.PagingNavigation"

%><%

	String isCreate = (String) request.getAttribute("isCreate");
	User model = null;
	String key = null;
	if (isCreate.equals("Y")) {
		model = new User();
		isCreate = "Create";
	} else {
		BaseModel baseModel = (BaseModel) request.getAttribute("data");
		key = (String) request.getAttribute("id");
		model = (User) baseModel.getData();
		isCreate = "Update";
	}
%>
<!DOCTYPE HTML>
<html>
<%@ include file="/WEB-INF/jsp/import/config.jsp"%>
<title>user <%=isCreate%></title>
<body>
	<form class="well form-search" action="<%=request.getContextPath()%>/app/user/<%=isCreate.toLowerCase()%>" method="post">
	<%if(isCreate.equals("Update")){ %>
	<input type="hidden" class="form-vertical" name="idx" value="<%=model.getIdx().toString()%>">
	<%} %>

	<table class="table table-striped">
		<tr>
		<th>Host</th>
		<td><input type="text" class="form-vertical" name="Host" value="<%=model.getHost()%>"></td>
	</tr>
		<tr>
		<th>User</th>
		<td><input type="text" class="form-vertical" name="User" value="<%=model.getUser()%>"></td>
	</tr>
		<tr>
		<th>Password</th>
		<td><input type="text" class="form-vertical" name="Password" value="<%=model.getPassword()%>"></td>
	</tr>
		<tr>
		<th>Select_priv</th>
		<td><input type="text" class="form-vertical" name="Select_priv" value="<%=model.getSelect_priv()%>"></td>
	</tr>
		<tr>
		<th>Insert_priv</th>
		<td><input type="text" class="form-vertical" name="Insert_priv" value="<%=model.getInsert_priv()%>"></td>
	</tr>
		<tr>
		<th>Update_priv</th>
		<td><input type="text" class="form-vertical" name="Update_priv" value="<%=model.getUpdate_priv()%>"></td>
	</tr>
		<tr>
		<th>Delete_priv</th>
		<td><input type="text" class="form-vertical" name="Delete_priv" value="<%=model.getDelete_priv()%>"></td>
	</tr>
		<tr>
		<th>Create_priv</th>
		<td><input type="text" class="form-vertical" name="Create_priv" value="<%=model.getCreate_priv()%>"></td>
	</tr>
		<tr>
		<th>Drop_priv</th>
		<td><input type="text" class="form-vertical" name="Drop_priv" value="<%=model.getDrop_priv()%>"></td>
	</tr>
		<tr>
		<th>Reload_priv</th>
		<td><input type="text" class="form-vertical" name="Reload_priv" value="<%=model.getReload_priv()%>"></td>
	</tr>
		<tr>
		<th>Shutdown_priv</th>
		<td><input type="text" class="form-vertical" name="Shutdown_priv" value="<%=model.getShutdown_priv()%>"></td>
	</tr>
		<tr>
		<th>Process_priv</th>
		<td><input type="text" class="form-vertical" name="Process_priv" value="<%=model.getProcess_priv()%>"></td>
	</tr>
		<tr>
		<th>File_priv</th>
		<td><input type="text" class="form-vertical" name="File_priv" value="<%=model.getFile_priv()%>"></td>
	</tr>
		<tr>
		<th>Grant_priv</th>
		<td><input type="text" class="form-vertical" name="Grant_priv" value="<%=model.getGrant_priv()%>"></td>
	</tr>
		<tr>
		<th>References_priv</th>
		<td><input type="text" class="form-vertical" name="References_priv" value="<%=model.getReferences_priv()%>"></td>
	</tr>
		<tr>
		<th>Index_priv</th>
		<td><input type="text" class="form-vertical" name="Index_priv" value="<%=model.getIndex_priv()%>"></td>
	</tr>
		<tr>
		<th>Alter_priv</th>
		<td><input type="text" class="form-vertical" name="Alter_priv" value="<%=model.getAlter_priv()%>"></td>
	</tr>
		<tr>
		<th>Show_db_priv</th>
		<td><input type="text" class="form-vertical" name="Show_db_priv" value="<%=model.getShow_db_priv()%>"></td>
	</tr>
		<tr>
		<th>Super_priv</th>
		<td><input type="text" class="form-vertical" name="Super_priv" value="<%=model.getSuper_priv()%>"></td>
	</tr>
		<tr>
		<th>Create_tmp_table_priv</th>
		<td><input type="text" class="form-vertical" name="Create_tmp_table_priv" value="<%=model.getCreate_tmp_table_priv()%>"></td>
	</tr>
		<tr>
		<th>Lock_tables_priv</th>
		<td><input type="text" class="form-vertical" name="Lock_tables_priv" value="<%=model.getLock_tables_priv()%>"></td>
	</tr>
		<tr>
		<th>Execute_priv</th>
		<td><input type="text" class="form-vertical" name="Execute_priv" value="<%=model.getExecute_priv()%>"></td>
	</tr>
		<tr>
		<th>Repl_slave_priv</th>
		<td><input type="text" class="form-vertical" name="Repl_slave_priv" value="<%=model.getRepl_slave_priv()%>"></td>
	</tr>
		<tr>
		<th>Repl_client_priv</th>
		<td><input type="text" class="form-vertical" name="Repl_client_priv" value="<%=model.getRepl_client_priv()%>"></td>
	</tr>
		<tr>
		<th>Create_view_priv</th>
		<td><input type="text" class="form-vertical" name="Create_view_priv" value="<%=model.getCreate_view_priv()%>"></td>
	</tr>
		<tr>
		<th>Show_view_priv</th>
		<td><input type="text" class="form-vertical" name="Show_view_priv" value="<%=model.getShow_view_priv()%>"></td>
	</tr>
		<tr>
		<th>Create_routine_priv</th>
		<td><input type="text" class="form-vertical" name="Create_routine_priv" value="<%=model.getCreate_routine_priv()%>"></td>
	</tr>
		<tr>
		<th>Alter_routine_priv</th>
		<td><input type="text" class="form-vertical" name="Alter_routine_priv" value="<%=model.getAlter_routine_priv()%>"></td>
	</tr>
		<tr>
		<th>Create_user_priv</th>
		<td><input type="text" class="form-vertical" name="Create_user_priv" value="<%=model.getCreate_user_priv()%>"></td>
	</tr>
		<tr>
		<th>Event_priv</th>
		<td><input type="text" class="form-vertical" name="Event_priv" value="<%=model.getEvent_priv()%>"></td>
	</tr>
		<tr>
		<th>Trigger_priv</th>
		<td><input type="text" class="form-vertical" name="Trigger_priv" value="<%=model.getTrigger_priv()%>"></td>
	</tr>
		<tr>
		<th>Create_tablespace_priv</th>
		<td><input type="text" class="form-vertical" name="Create_tablespace_priv" value="<%=model.getCreate_tablespace_priv()%>"></td>
	</tr>
		<tr>
		<th>ssl_type</th>
		<td><input type="text" class="form-vertical" name="ssl_type" value="<%=model.getSsl_type()%>"></td>
	</tr>
		<tr>
		<th>ssl_cipher</th>
		<td><input type="text" class="form-vertical" name="ssl_cipher" value="<%=model.getSsl_cipher()%>"></td>
	</tr>
		<tr>
		<th>x509_issuer</th>
		<td><input type="text" class="form-vertical" name="x509_issuer" value="<%=model.getX509_issuer()%>"></td>
	</tr>
		<tr>
		<th>x509_subject</th>
		<td><input type="text" class="form-vertical" name="x509_subject" value="<%=model.getX509_subject()%>"></td>
	</tr>
		<tr>
		<th>max_questions</th>
		<td><input type="text" class="form-vertical" name="max_questions" value="<%=model.getMax_questions()%>"></td>
	</tr>
		<tr>
		<th>max_updates</th>
		<td><input type="text" class="form-vertical" name="max_updates" value="<%=model.getMax_updates()%>"></td>
	</tr>
		<tr>
		<th>max_connections</th>
		<td><input type="text" class="form-vertical" name="max_connections" value="<%=model.getMax_connections()%>"></td>
	</tr>
		<tr>
		<th>max_user_connections</th>
		<td><input type="text" class="form-vertical" name="max_user_connections" value="<%=model.getMax_user_connections()%>"></td>
	</tr>
		<tr>
		<th>plugin</th>
		<td><input type="text" class="form-vertical" name="plugin" value="<%=model.getPlugin()%>"></td>
	</tr>
		<tr>
		<th>authentication_string</th>
		<td><input type="text" class="form-vertical" name="authentication_string" value="<%=model.getAuthentication_string()%>"></td>
	</tr>
	
	</table>

	<div style="text-align: right">
		<button class="btn btn-primary btn-large" type="submit">
			<i class="icon-pencil icon-white"></i><%=isCreate %>
		</button>
	</div>
	</form>
</body>
</html>
