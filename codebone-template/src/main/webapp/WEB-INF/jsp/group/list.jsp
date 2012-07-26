<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page
	import="org.codebone.framework.BaseModel,java.util.List,org.codebone.security.group.Group,org.codebone.security.authorities.Authorities"%>
<%@ page
	import="java.lang.reflect.Field,java.lang.reflect.Method,javax.persistence.Id,java.util.Date,org.codebone.framework.util.PagingNavigation"%>
<%
	BaseModel model = (BaseModel) request.getAttribute("data");
	List<Group> list = (List<Group>) model.getData();
	BaseModel authModel = (BaseModel) request.getAttribute("authorities");
	List<Authorities> authList = (List<Authorities>) authModel.getData();
	boolean hasNext = model.isHasNext();
	int allCount = model.getAllCount();
	int currentPage = (Integer) request.getAttribute("page");
	Long groupIdx = (Long) request.getAttribute("groupIdx");
%>
<!DOCTYPE HTML>
<html>
<head>
<title><%=Group.class.getSimpleName()%> List</title>
</head>
<body>
	<div class="row">
		<form class="well form-search"
			action="<%=request.getContextPath()%>/app/group/search"
			method="post">
			<div class="controls" style="text-align: right">
				<select id="group_search_select" name="property">
					<option>idx</option>
					<option>name</option>
					<option>description</option>
				</select> <input type="text" class="input-xlarge search-query" name="keyword">
				<button type="submit" class="btn">Search</button>
			</div>
		</form>
		<div class="span6">
			<table class="table">
				<thead>
					<tr>
						<th>idx</th>
						<th>name</th>
						<th>description</th>
						<th>Action</th>
						<th>View Authorities</th>
					</tr>
				</thead>
				<tbody>
					<%
						for (Group groupModel : list) {
					%>
					<tr>
						<%
							String idx = Long.toString(groupModel.getIdx());
						%>
						<td><%=groupModel.getIdx()%></td>
						<td><%=groupModel.getName()%></td>
						<td><%=groupModel.getDescription()%></td>
						<td>
							<div class="btn-group">
								<button class="btn btn-primary dropdown-toggle"
									data-toggle="dropdown">
									<i class="icon-pencil icon-white"></i>Action
								</button>
								<ul class="dropdown-menu">
									<li><a
										href="<%=request.getContextPath()%>/app/group/update?idx=<%=idx%>">Update</a></li>
									<li><a
										href="<%=request.getContextPath()%>/app/group/delete?idx=<%=idx%>">Delete</a></li>
								</ul>
							</div>
						</td>
						<td>
						<%
							String buttonToggle = "btn btn-primary";
										if(groupIdx==Long.parseLong(idx)){
											buttonToggle = "btn btn-primary disabled";
										}
						%>
						<a class="<%=buttonToggle%>" data-toggle="button" href="<%=request.getContextPath()%>/app/group/list?groupIdx=<%=idx%>">
							<i class="icon-chevron-right icon-white"></i>
							</a>
						</td>
					</tr>
					<%
						}
					%>
				</tbody>
			</table>
		</div>
		<div class="span5">
			<table class="table">
				<thead>
					<tr>
						<th>idx</th>
						<th>authority</th>
						<th>url?</th>
						<th>Checked</th>
					</tr>
				</thead>
				<tbody>
					<%
						for (Authorities authoritiesModel : authList) {
					%>
					<tr>
						<td><%=authoritiesModel.getIdx() %></td>
						<td><%=authoritiesModel.getAuthority() %></td>
						<td>test</td>
						<td>1</td>
					</tr>
					<%} %>
				</tbody>
			</table>
		</div>
	</div>
	<%
		PagingNavigation pagingNavigation = new PagingNavigation();
		pagingNavigation.setCurrentPage(currentPage + 1);
		pagingNavigation.setPagePerBlock(5L);
		pagingNavigation.setRecordPerPage(20L);
		pagingNavigation.setTotalRecord(allCount);
		pagingNavigation.setHref("list");
		pagingNavigation.setParamters("");
		out.println(pagingNavigation.getHtml());
	%>

	<div style="text-align: right">
		<a class="btn btn-primary "
			href="<%=request.getContextPath()%>/app/group/create"> <i
			class="icon-file icon-white"></i> Create
		</a>
	</div>

</body>
</html>
