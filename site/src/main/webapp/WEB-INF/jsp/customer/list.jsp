<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ page
	import="java.util.*,org.codebone.domain.customer.*,org.codebone.framework.BaseModel,java.util.Date,org.codebone.framework.util.PagingNavigation"%>
<%
	BaseModel baseModel = (BaseModel) request.getAttribute("data");
	List<Customer> list = (List<Customer>) baseModel.getData();
	boolean hasNext = baseModel.isHasNext();
	int allCount = baseModel.getAllCount();
	int currentPage = (Integer) request.getAttribute("page");
%>

<section id="contents">
	<table class="table">
		<thead>
			<tr>
				<th>SID</th>
				<th>Last_Name</th>
				<th>First_Name</th>
			</tr>
		</thead>

		<tbody>
			<%
				for (Customer model : list) {
			%>
			<tr>
				<td><%=model.getSid()%></td>
				<td><%=model.getLastName()%></td>
				<td><%=model.getFirstName()%></td>

				<td>
					<div class="btn-group">
						<a class="btn btn-small"
							href="<%=request.getContextPath()%>/app/customer/update?idx=<%=model.getSid().toString()%>">
							<i class="icon-edit"></i> Update
						</a> <a class="btn btn-small"
							href="<%=request.getContextPath()%>/app/customer/delete?idx=<%=model.getSid().toString()%>">
							<i class="icon-trash"></i> Delete
						</a>
					</div>
				</td>
			</tr>
			<%
				}
			%>
		</tbody>
	</table>
</section>

<section id="operation">
	<div class="row-fluid">
		<div class="pull-left">
			<a class="btn"
				href="<%=request.getContextPath()%>/app/customer/create">
				<i class="icon-pencil"></i> Create
			</a>
		</div>
		
		<div class="pull-right">
		<form class="form-search"
			action="<%=request.getContextPath()%>/app/customer/search"
			method="post">

			<div class="input-append">
				<div>
					<label class="checkbox inline">
						<input name="property" type="checkbox" value="SID">SID
					</label>
					<label class="checkbox inline">
						<input name="property" type="checkbox" value="Last_Name">Last_Name
					</label>
					<label class="checkbox inline">
						<input name="property" type="checkbox" value="First_Name">First_Name
					</label>
				</div>
				<input type="text" class="search-query" name="keyword">
				<button type="submit" class="btn">Search</button>
			</div>
		</form>
		</div>
	</div>
</section>

<section id="navigation">
	<div class="row-fluid">
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
	</div>
</section>
