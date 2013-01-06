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
<section id="contents">
	<table class="table">
		<thead>
			<tr>
				<th></th>
				<th>ID</th>
				<th>이메일</th>
				<th>이름</th>
				<th>전화번호</th>
				<th>그룹</th>
				<th>생성일</th>
				<th></th>
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
				<td><%=managerModel.getCreateDate()%></td>
				<td>
					<div class="btn-group">
						<a class="btn btn-small"
							href="<%=request.getContextPath()%>/app/manager/update?idx=<%=idx%>">
							<i class="icon-edit"></i> Update
						</a> <a class="btn btn-small"
							href="<%=request.getContextPath()%>/app/manager/delete?idx=<%=idx%>">
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
				href="<%=request.getContextPath()%>/app/manager/create"> <i
				class="icon-pencil"></i> Create
			</a>
		</div>
		
		<div class="pull-right">
		<form class="form-search"
			action="<%=request.getContextPath()%>/app/organization/search"
			method="post">
			<div class="input-append">
				<div>
					<label class="checkbox inline"> <input name="property"
						type="checkbox" value="id">id
					</label> <label class="checkbox inline"> <input name="property"
						type="checkbox" value="email">email
					</label> <label class="checkbox inline"> <input name="property"
						type="checkbox" value="organizationIdx">organization
					</label> <label class="checkbox inline"> <input name="property"
						type="checkbox" value="name">name
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