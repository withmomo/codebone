<%@ page language="java"
	contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"

%><%@ page

	import="java.util.*,{{package}}.*,org.codebone.framework.BaseModel,java.util.Date,org.codebone.framework.util.PagingNavigation"

%><%BaseModel baseModel = (BaseModel) request.getAttribute("data");
	List<{{tableNameCamelcase}}> list = (List<{{tableNameCamelcase}}>) baseModel.getData();
	boolean hasNext = baseModel.isHasNext();
	int allCount = baseModel.getAllCount();
	int currentPage = (Integer) request.getAttribute("page");%>
<!DOCTYPE HTML>
<html>
<head>
<title>{{siteTitle}}</title>
</head>
<body>
	
	<form class="well form-search"
		action="<%=request.getContextPath()%>/app/{{mappingUri}}/search" method="post">
		<div class="controls" style="text-align: right">
			<select name="property">
				{{#columns}}
				<option value="{{name}}">{{description}}</option>
				{{/columns}}
			</select>
			<input type="text" class="input-xlarge search-query" name="keyword">
			<button type="submit" class="btn">Search</button>
		</div>
	</form>
	
	<table class="table">
		<thead>
			<tr>
			{{#columns}}
				<th>{{description}}</th>
			{{/columns}}
			</tr>
		</thead>
		<tbody>
			<%
				for ({{tableNameCamelcase}} model : list) {
			%>
			<tr>
				{{#columns}}
					<td><%=model.get{{nameCamelcase}}()%></td>
				{{/columns}}
				
				<td>
					<div class="btn-group">
						<button class="btn btn-primary dropdown-toggle"
							data-toggle="dropdown">
							<i class="icon-pencil icon-white"></i>Action
						</button>
						<ul class="dropdown-menu">
							<li><a
								href="<%=request.getContextPath()%>/app/{{mappingUri}}/update?idx=<%=model.getIdx().toString()%>">Update</a></li>
							<li><a
								href="<%=request.getContextPath()%>/app/{{mappingUri}}/delete?idx=<%=model.getIdx().toString()%>">Delete</a></li>
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
			href="<%=request.getContextPath()%>/app/{{mappingUri}}/create"> <i
			class="icon-file icon-white"></i> Create
		</a>
	</div>
</body>
</html>