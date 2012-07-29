<%@ page language="java"
	contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"

%><%@ page

	import="java.util.*,<PACKAGE>.*,org.codebone.framework.BaseModel,java.util.Date,org.codebone.framework.util.PagingNavigation"

%><%

	BaseModel baseModel = (BaseModel) request.getAttribute("data");
	List<<TABLE_NAME_CAMELCASE>> list = (List<<TABLE_NAME_CAMELCASE>>) baseModel.getData();
	boolean hasNext = baseModel.isHasNext();
	int allCount = baseModel.getAllCount();
	int currentPage = (Integer) request.getAttribute("page");
%>
<!DOCTYPE HTML>
<html>
<head>
<title><SITE_TITLE></title>
</head>
<body>
	
	<SEARCH>
	<form class="well form-search"
		action="<%=request.getContextPath()%>/app/<MAPPING_URI>/search" method="post">
		<div class="controls" style="text-align: right">
			<select name="property">
				<COLUMN_LOOP_SEARCH>
				<option value="<COLUMN_NAME>"><COLUMN_DESCRIPTION></option>
				</COLUMN_LOOP_SEARCH>
			</select>
			<input type="text" class="input-xlarge search-query" name="keyword">
			<button type="submit" class="btn">Search</button>
		</div>
	</form>
	</SEARCH>
	
	<table class="table">
		<thead>
			<tr>
			<COLUMN_LOOP>
				<th><COLUMN_DESCRIPTION></th>
			</COLUMN_LOOP>
			</tr>
		</thead>
		<tbody>
			<%
				for (<TABLE_NAME_CAMELCASE> model : list) {
			%>
			<tr>
				<COLUMN_LOOP>
					<td><%=model.get<COLUMN_NAME_CAMELCASE>()%></td>
				</COLUMN_LOOP>
				
				<td>
					<div class="btn-group">
						<button class="btn btn-primary dropdown-toggle"
							data-toggle="dropdown">
							<i class="icon-pencil icon-white"></i>Action
						</button>
						<ul class="dropdown-menu">
							<li><a
								href="<%=request.getContextPath()%>/app/<MAPPING_URI>/update?idx=<%=model.getIdx().toString()%>">Update</a></li>
							<li><a
								href="<%=request.getContextPath()%>/app/<MAPPING_URI>/delete?idx=<%=model.getIdx().toString()%>">Delete</a></li>
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
			href="<%=request.getContextPath()%>/app/<MAPPING_URI>/create"> <i
			class="icon-file icon-white"></i> Create
		</a>
	</div>
</body>
</html>