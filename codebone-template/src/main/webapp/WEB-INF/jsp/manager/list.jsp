<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page
	import="org.codebone.framework.BaseModel,java.util.List,org.codebone.security.manager.Manager"%>
<%@ page
	import="java.lang.reflect.Field,java.lang.reflect.Method,javax.persistence.Id,java.util.Date,org.codebone.framework.util.PagingNavigation"%>
<%
	BaseModel model = (BaseModel) request.getAttribute("data");
	List<Manager> list = (List<Manager>) model.getData();
	boolean hasNext = model.isHasNext();
	int allCount = model.getAllCount();
	int currentPage = (Integer) request.getAttribute("page");
%>
<!DOCTYPE HTML>
<html>
<head>
<title><%=Manager.class.getSimpleName()%> List</title>
</head>
<body>

	<form class="well form-search"
		action="<%=request.getContextPath()%>/app/manager/search" method="post">
		<div class="controls" style="text-align: right">
			<select id="manager_search_select" name="property">
				<option>idx</option>
				<option>id</option>
				<option>email</option>
				<option>name</option>
				<option>phoneNumber</option>
				<option>organizationIdx</option>
				<option>level</option>
				<option>createdDate</option>
			</select> <input type="text" class="input-xlarge search-query" name="keyword">
			<button type="submit" class="btn">Search</button>
		</div>
	</form>
	<table class="table">
		<thead>
			<tr>
				<th>idx</th>
				<th>id</th>
				<th>email</th>
				<th>name</th>
				<th>phoneNumber</th>
				<th>organizationIdx</th>
				<th>createdDate</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
			<%
				for (Manager managerModel : list) {
			%>
			<tr>
				<%
					String idx = Long.toString(managerModel.getIdx());
				%>
				<td><%=managerModel.getIdx()%></td>
				<td><%=managerModel.getId()%></td>
				<td><%=managerModel.getEmail()%></td>
				<td><%=managerModel.getName()%></td>
				<td><%=managerModel.getPhoneNumber()%></td>
				<td><%=managerModel.getOrganizationIdx()%></td>
				<td><%=managerModel.getCreatedDate()%></td>
				<td>
					<div class="btn-group">
						<button class="btn btn-primary dropdown-toggle"
							data-toggle="dropdown">
							<i class="icon-pencil icon-white"></i>Action
						</button>
						<ul class="dropdown-menu">
							<li><a
								href="<%=request.getContextPath()%>/app/manager/update?idx=<%=idx%>">Update</a></li>
							<li><a
								href="<%=request.getContextPath()%>/app/manager/delete?idx=<%=idx%>">Delete</a></li>
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
			href="<%=request.getContextPath()%>/app/manager/create"> <i
			class="icon-file icon-white"></i> Create
		</a>
	</div>
</body>
</html>
