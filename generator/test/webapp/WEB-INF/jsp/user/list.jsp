<%@ page language="java"
	contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"

%><%@ page

	import="java.util.*,org.codebone.domain.user.*,org.codebone.framework.BaseModel,java.util.Date,org.codebone.framework.util.PagingNavigation"

%><%BaseModel baseModel = (BaseModel) request.getAttribute("data");
	List<User> list = (List<User>) baseModel.getData();
	boolean hasNext = baseModel.isHasNext();
	int allCount = baseModel.getCount();
	int currentPage = (Integer) request.getAttribute("page");%>
<!DOCTYPE HTML>
<html>
<head>
<title>user</title>
</head>
<body>
	
	
	<form class="well form-search"
		action="<%=request.getContextPath()%>/app/user/search" method="post">
		<div class="controls" style="text-align: right">
			<select name="property">
				
				<option value="Host">Host</option>
				
				<option value="User">User</option>
				
				<option value="Password">Password</option>
				
				<option value="Select_priv">Select_priv</option>
				
				<option value="Insert_priv">Insert_priv</option>
				
				<option value="Update_priv">Update_priv</option>
				
				<option value="Delete_priv">Delete_priv</option>
				
				<option value="Create_priv">Create_priv</option>
				
				<option value="Drop_priv">Drop_priv</option>
				
				<option value="Reload_priv">Reload_priv</option>
				
				<option value="Shutdown_priv">Shutdown_priv</option>
				
				<option value="Process_priv">Process_priv</option>
				
				<option value="File_priv">File_priv</option>
				
				<option value="Grant_priv">Grant_priv</option>
				
				<option value="References_priv">References_priv</option>
				
				<option value="Index_priv">Index_priv</option>
				
				<option value="Alter_priv">Alter_priv</option>
				
				<option value="Show_db_priv">Show_db_priv</option>
				
				<option value="Super_priv">Super_priv</option>
				
				<option value="Create_tmp_table_priv">Create_tmp_table_priv</option>
				
				<option value="Lock_tables_priv">Lock_tables_priv</option>
				
				<option value="Execute_priv">Execute_priv</option>
				
				<option value="Repl_slave_priv">Repl_slave_priv</option>
				
				<option value="Repl_client_priv">Repl_client_priv</option>
				
				<option value="Create_view_priv">Create_view_priv</option>
				
				<option value="Show_view_priv">Show_view_priv</option>
				
				<option value="Create_routine_priv">Create_routine_priv</option>
				
				<option value="Alter_routine_priv">Alter_routine_priv</option>
				
				<option value="Create_user_priv">Create_user_priv</option>
				
				<option value="Event_priv">Event_priv</option>
				
				<option value="Trigger_priv">Trigger_priv</option>
				
				<option value="Create_tablespace_priv">Create_tablespace_priv</option>
				
				<option value="ssl_type">ssl_type</option>
				
				<option value="ssl_cipher">ssl_cipher</option>
				
				<option value="x509_issuer">x509_issuer</option>
				
				<option value="x509_subject">x509_subject</option>
				
				<option value="max_questions">max_questions</option>
				
				<option value="max_updates">max_updates</option>
				
				<option value="max_connections">max_connections</option>
				
				<option value="max_user_connections">max_user_connections</option>
				
				<option value="plugin">plugin</option>
				
				<option value="authentication_string">authentication_string</option>
				
			</select>
			<input type="text" class="input-xlarge search-query" name="keyword">
			<button type="submit" class="btn">Search</button>
		</div>
	</form>
	
	
	<table class="table">
		<thead>
			<tr>
			
				<th>Host</th>
			
				<th>User</th>
			
				<th>Password</th>
			
				<th>Select_priv</th>
			
				<th>Insert_priv</th>
			
				<th>Update_priv</th>
			
				<th>Delete_priv</th>
			
				<th>Create_priv</th>
			
				<th>Drop_priv</th>
			
				<th>Reload_priv</th>
			
				<th>Shutdown_priv</th>
			
				<th>Process_priv</th>
			
				<th>File_priv</th>
			
				<th>Grant_priv</th>
			
				<th>References_priv</th>
			
				<th>Index_priv</th>
			
				<th>Alter_priv</th>
			
				<th>Show_db_priv</th>
			
				<th>Super_priv</th>
			
				<th>Create_tmp_table_priv</th>
			
				<th>Lock_tables_priv</th>
			
				<th>Execute_priv</th>
			
				<th>Repl_slave_priv</th>
			
				<th>Repl_client_priv</th>
			
				<th>Create_view_priv</th>
			
				<th>Show_view_priv</th>
			
				<th>Create_routine_priv</th>
			
				<th>Alter_routine_priv</th>
			
				<th>Create_user_priv</th>
			
				<th>Event_priv</th>
			
				<th>Trigger_priv</th>
			
				<th>Create_tablespace_priv</th>
			
				<th>ssl_type</th>
			
				<th>ssl_cipher</th>
			
				<th>x509_issuer</th>
			
				<th>x509_subject</th>
			
				<th>max_questions</th>
			
				<th>max_updates</th>
			
				<th>max_connections</th>
			
				<th>max_user_connections</th>
			
				<th>plugin</th>
			
				<th>authentication_string</th>
			
			</tr>
		</thead>
		<tbody>
			<%
				for (User model : list) {
			%>
			<tr>
				
					<td><%=model.getHost()%></td>
				
					<td><%=model.getUser()%></td>
				
					<td><%=model.getPassword()%></td>
				
					<td><%=model.getSelect_priv()%></td>
				
					<td><%=model.getInsert_priv()%></td>
				
					<td><%=model.getUpdate_priv()%></td>
				
					<td><%=model.getDelete_priv()%></td>
				
					<td><%=model.getCreate_priv()%></td>
				
					<td><%=model.getDrop_priv()%></td>
				
					<td><%=model.getReload_priv()%></td>
				
					<td><%=model.getShutdown_priv()%></td>
				
					<td><%=model.getProcess_priv()%></td>
				
					<td><%=model.getFile_priv()%></td>
				
					<td><%=model.getGrant_priv()%></td>
				
					<td><%=model.getReferences_priv()%></td>
				
					<td><%=model.getIndex_priv()%></td>
				
					<td><%=model.getAlter_priv()%></td>
				
					<td><%=model.getShow_db_priv()%></td>
				
					<td><%=model.getSuper_priv()%></td>
				
					<td><%=model.getCreate_tmp_table_priv()%></td>
				
					<td><%=model.getLock_tables_priv()%></td>
				
					<td><%=model.getExecute_priv()%></td>
				
					<td><%=model.getRepl_slave_priv()%></td>
				
					<td><%=model.getRepl_client_priv()%></td>
				
					<td><%=model.getCreate_view_priv()%></td>
				
					<td><%=model.getShow_view_priv()%></td>
				
					<td><%=model.getCreate_routine_priv()%></td>
				
					<td><%=model.getAlter_routine_priv()%></td>
				
					<td><%=model.getCreate_user_priv()%></td>
				
					<td><%=model.getEvent_priv()%></td>
				
					<td><%=model.getTrigger_priv()%></td>
				
					<td><%=model.getCreate_tablespace_priv()%></td>
				
					<td><%=model.getSsl_type()%></td>
				
					<td><%=model.getSsl_cipher()%></td>
				
					<td><%=model.getX509_issuer()%></td>
				
					<td><%=model.getX509_subject()%></td>
				
					<td><%=model.getMax_questions()%></td>
				
					<td><%=model.getMax_updates()%></td>
				
					<td><%=model.getMax_connections()%></td>
				
					<td><%=model.getMax_user_connections()%></td>
				
					<td><%=model.getPlugin()%></td>
				
					<td><%=model.getAuthentication_string()%></td>
				
				
				<td>
					<div class="btn-group">
						<button class="btn btn-primary dropdown-toggle"
							data-toggle="dropdown">
							<i class="icon-pencil icon-white"></i>Action
						</button>
						<ul class="dropdown-menu">
							<li><a
								href="<%=request.getContextPath()%>/app/user/update?idx=<%=model.getIdx().toString()%>">Update</a></li>
							<li><a
								href="<%=request.getContextPath()%>/app/user/delete?idx=<%=model.getIdx().toString()%>">Delete</a></li>
						</ul>
					</div>
				</td>
			</tr>
			<%
				}
			%>
		</tbody>
	</table>
	<%
		PagingNavigation pagingNavigation = new PagingNavigation();
		pagingNavigation.setCurrentPage(currentPage+1);
		pagingNavigation.setPagePerBlock(5L);
		pagingNavigation.setRecordPerPage(20L);
		pagingNavigation.setTotalRecord(allCount);
		pagingNavigation.setHref("list");
		pagingNavigation.setParamters("");
		out.println(pagingNavigation.getHtml());
	%>
	<div style="text-align: right">
		<a class="btn btn-primary "
			href="<%=request.getContextPath()%>/app/user/create"> <i
			class="icon-file icon-white"></i> Create
		</a>
	</div>
</body>
</html>
