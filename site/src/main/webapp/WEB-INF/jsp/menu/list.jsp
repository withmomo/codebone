<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page
	import="org.codebone.framework.BaseModel,java.util.List,org.codebone.security.menu.Menu"%>
<%@ page
	import="java.lang.reflect.Field,java.lang.reflect.Method,javax.persistence.Id,java.util.Date,org.codebone.framework.util.PagingNavigation"%>
<%
	BaseModel model = (BaseModel) request.getAttribute("data");
	List<Menu> list = (List<Menu>) model.getData();
	boolean hasNext = model.isHasNext();
	int allCount = model.getAllCount();
	int currentPage = (Integer) request.getAttribute("page");
	Long menuIdx = (Long) request.getAttribute("menuIdx");
%>
<script language="javascript">
function toggle(idx, size) {
	for(i=1;i<=size;i++){
		if(i==idx){
			$('#data'+i).show();
		}else{
			$('#data'+i).hide();
		}
	}
}
</script>

<section id="contents">
	<div class="row-fluid">
		<div class="pull-left">
			<table class="table">
				<thead>
					<tr>
						<th>이름</th>
						<th>URL</th>
						<th>위치변경</th>
					</tr>
				</thead>
				<tbody>
					<%
						for (Menu menuModel : list) {
					%>
					<tr>
						<%
							String classDef = null;
								if (!menuModel.getSubOrder().equals(0)) {
									classDef = "padding-left:20px";
								}
						%>
						<td style="<%=classDef%>"><%=menuModel.getName()%></td>
						<td><%=menuModel.getUrl()%></td>
						<td>
							<div class="btn-group">
								<%
									if (menuModel.getSubOrder() == 0) {
								%>
								<!-- 메인메뉴 -> 서브메뉴 격하 -->
								<a class="btn btn-primary"
									href="<%=request.getContextPath()%>/app/menu/changeLevel?priOrder=<%=menuModel.getPriOrder()%>&subOrder=<%=menuModel.getSubOrder()%>&idx=<%=menuModel.getIdx()%>"><i
									class="icon-arrow-right icon-white"></i></a>
								<%
									} else {
								%><!-- 서브메뉴 -> 메인메뉴 격상 -->
								<a class="btn btn-warning"
									href="<%=request.getContextPath()%>/app/menu/changeLevel?priOrder=<%=menuModel.getPriOrder()%>&subOrder=<%=menuModel.getSubOrder()%>&idx=<%=menuModel.getIdx()%>"><i
									class="icon-arrow-left icon-white"></i></a>
								<%
									}
								%>
								<a class="btn btn-success"
									href="<%=request.getContextPath()%>/app/menu/changeOrder?priOrder=<%=menuModel.getPriOrder()%>&subOrder=<%=menuModel.getSubOrder()%>&up=Y"><i
									class="icon-arrow-up icon-white"></i></a> <a class="btn btn-danger"
									href="<%=request.getContextPath()%>/app/menu/changeOrder?priOrder=<%=menuModel.getPriOrder()%>&subOrder=<%=menuModel.getSubOrder()%>&up=N"><i
									class="icon-arrow-down icon-white"></i></a>
							</div>
						</td>
						<td>
							<button class="btn btn-primary"
								onclick="toggle(<%=menuModel.getIdx()%>, <%=list.size()%>);">
								<i class="icon-chevron-right icon-white"></i>
							</button>
						</td>
					</tr>
					<%
						}
					%>
				</tbody>
			</table>
		</div>
		<%
			for (Menu menuModel : list) {
		%>
		<div class="pull-right span4" style="display: none"
			id="data<%=menuModel.getIdx()%>">
			<form class="form-search"
				action="<%=request.getContextPath()%>/app/menu/update" method="post">
				<table class="table table-striped">
					<thead>
						<tr>
							<th>필드</th>
							<th>값</th>
						</tr>
					</thead>
					<tr>
						<th>name</th>
						<td><input type="text" class="input-medium" name="name"
							value="<%=menuModel.getName()%>"></td>
					</tr>
					<tr>
						<th>url</th>
						<td><input type="text" class="input-medium" name="url"
							value="<%=menuModel.getUrl()%>"></td>
					</tr>
					<tr>
						<th>priOrder</th>
						<td><input type="text" class="input-medium" name="priOrder"
							value="<%=menuModel.getPriOrder()%>"></td>
					</tr>
					<tr>
						<th>subOrder</th>
						<td><input type="text" class="input-medium" name="subOrder"
							value="<%=menuModel.getSubOrder()%>"></td>
					</tr>
					<tr>
						<th>isSeparate</th>
						<td><input type="text" class="input-medium" name="isSeparate"
							value="<%=menuModel.getIsSeparate()%>"></td>
					</tr>
					<tr>
						<th>isExternal</th>
						<td><input type="text" class="input-medium" name="isExternal"
							value="<%=menuModel.getIsExternal()%>"></td>
					</tr>
					<tr>
						<th>managerIdx</th>
						<td><input type="text" class="input-medium" name="managerIdx"
							value="<%=menuModel.getManagerIdx()%>"></td>
					</tr>
					</tbody>
				</table>
				<div style="text-align: right">
					<button class="btn btn-primary" type="submit">
						<i class="icon-pencil icon-white"></i>Update
					</button>
					<a class="btn btn-danger"
						href="<%=request.getContextPath()%>/app/menu/delete?idx=<%=menuModel.getIdx()%>">
						<i class="icon-remove-circle icon-white"></i>Delete
					</a>
				</div>
				<input type="hidden" class="form-vertical" name="idx"
					value="<%=menuModel.getIdx()%>">
				</td>
			</form>
		</div>
		<%
			}
		%>
	</div>
</section>


<section id="operation">
	<div class="row-fluid">
		<div class="pull-left">
			<a class="btn" href="<%=request.getContextPath()%>/app/menu/create">
				<i class="icon-pencil"></i> Create
			</a>
		</div>
	</div>
</section>


<section id="navigation">
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
</section>