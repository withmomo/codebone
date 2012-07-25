<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page
	import="org.codebone.framework.BaseModel,java.util.List,org.codebone.security.menu.MenuModel"%>
<%@ page
	import="java.lang.reflect.Field,java.lang.reflect.Method,javax.persistence.Id,java.util.Date,org.codebone.framework.util.PagingNavigation"%>
<%
	BaseModel model = (BaseModel) request.getAttribute("data");
	List<MenuModel> list = (List<MenuModel>) model.getData();
	boolean hasNext = model.isHasNext();
	int allCount = model.getAllCount();
	int currentPage = (Integer) request.getAttribute("page");
%>
<!DOCTYPE HTML>
<html>
<head>
<title><%=MenuModel.class.getSimpleName()%> List</title>
</head>
<body>

	<form class="well form-search"
		action="<%=request.getContextPath()%>/admin/manager/search"
		method="post">
		<div class="controls" style="text-align: right">
			<select id="manager_search_select" name="property">
				<option>idx</option>
				<option>name</option>
				<option>priOrder</option>
				<option>subOrder</option>
				<option>isSeperate</option>
				<option>isExternal</option>
				<option>url</option>
				<option>managerIdx</option>
				<option>createdDate</option>
			</select> <input type="text" class="input-xlarge search-query" name="keyword">
			<button type="submit" class="btn">Search</button>
		</div>
	</form>
	<table class="table">
		<thead>
			<tr>
				<th>idx</th>
				<th>name</th>
				<th>priOrder</th>
				<th>subOrder</th>
				<th>menuAction</th>
				<th>isSeperate</th>
				<th>isExternal</th>
				<th>url</th>
				<th>managerIdx</th>
				<th>createdDate</th>
			</tr>
		</thead>
		<tbody>
			<%
				for (MenuModel menuModel : list) {
			%>
			<tr>
				<%
					String idx = Long.toString(menuModel.getIdx());
				%>
				<td><%=menuModel.getIdx()%></td>
				<td><%=menuModel.getName()%></td>
				<td><%=menuModel.getPriOrder()%></td>
				<td><%=menuModel.getSubOrder()%></td>
				<td>
				<div class="btn-group">
						<%if (menuModel.getSubOrder() == 0) {%> <!-- 메인메뉴 -> 서브메뉴 격하 -->
							<a class="btn btn-primary" href="<%=request.getContextPath()%>/admin/menu/changeLevel?priOrder=<%=menuModel.getPriOrder()%>&subOrder=<%=menuModel.getSubOrder()%>&idx=<%=menuModel.getIdx()%>"><i 
								class="icon-arrow-right icon-white"></i></a>
						<%} else {%><!-- 서브메뉴 -> 메인메뉴 격상 -->
							<a class="btn btn-warning" href="<%=request.getContextPath()%>/admin/menu/changeLevel?priOrder=<%=menuModel.getPriOrder()%>&subOrder=<%=menuModel.getSubOrder()%>&idx=<%=menuModel.getIdx()%>"><i 
							class="icon-arrow-left icon-white"></i></a>
						<%}%>
						<a class="btn btn-success" href="<%=request.getContextPath()%>/admin/menu/changeOrder?priOrder=<%=menuModel.getPriOrder()%>&subOrder=<%=menuModel.getSubOrder()%>&up=Y"><i 
							class="icon-arrow-up icon-white"></i></a>
						<a class="btn btn-danger" href="<%=request.getContextPath()%>/admin/menu/changeOrder?priOrder=<%=menuModel.getPriOrder()%>&subOrder=<%=menuModel.getSubOrder()%>&up=N"><i 
							class="icon-arrow-down icon-white"></i></a>
					</div>
				</td>
				<td><%=menuModel.getIsSeparate()%></td>
				<td><%=menuModel.getIsExternal()%></td>
				<td><%=menuModel.getUrl()%></td>
				<td><%=menuModel.getManagerIdx()%></td>
				<td><%=menuModel.getCreatedDate()%></td>
				<td>
					<div class="btn-group">
						<button class="btn btn-primary dropdown-toggle"
							data-toggle="dropdown">
							<i class="icon-pencil icon-white"></i>Action
						</button>
						<ul class="dropdown-menu">
							<li><a
								href="<%=request.getContextPath()%>/admin/menu/update?idx=<%=idx%>">Update</a></li>
							<li><a
								href="<%=request.getContextPath()%>/admin/menu/delete?idx=<%=idx%>">Delete</a></li>
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
			href="<%=request.getContextPath()%>/admin/menu/create"> <i
			class="icon-file icon-white"></i> Create
		</a>
	</div>
</body>
</html>
