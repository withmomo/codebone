<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>
<%@ page
	import="org.codebone.framework.BaseModel,java.util.List,java.util.ArrayList,org.codebone.security.manager.Manager,org.codebone.security.menu.Menu"%>
<%
Object obj1 = request.getAttribute("loginManager");
Manager currentLoginManager = null;
if(obj1 != null){
	currentLoginManager = (Manager) obj1;
}else{
	currentLoginManager = new Manager();
	currentLoginManager.setName("Error!");
}
Object obj2 = request.getAttribute("menu");
List<Menu> list = null;
if(obj2 != null){
	list = (List<Menu>) obj2;
}else{
	list = new ArrayList<Menu>();
}
%>
<!DOCTYPE HTML>
<html>
<%@ include file="/WEB-INF/jsp/import/config.jsp"%>
<head>
<script>
	$(document).ready(function() {
		$('.dropdown-toggle').dropdown()
	});
</script>
<decorator:head />
<title><decorator:title default="Codebone" /></title>
</head>


<body>
	<div class="navbar">
		<div class="navbar-inner">
			<div class="container-fluid">
				<a class="btn btn-navbar" data-toggle="collapse"
					data-target=".nav-collapse"> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span>
				</a> <a class="brand" href="#">CodeBone</a>
				<div class="btn-group pull-right">
					<a class="btn" data-toggle="modal" href="#myModal"><i
						class="icon-user"></i><%=currentLoginManager.getName()%></a> <a
						class="btn dropdown-toggle" data-toggle="dropdown" href="#"> <span
						class="caret"></span>
					</a>
					<ul class="dropdown-menu">
						<li><a href="#">Profile</a></li>
						<li class="divider"></li>
						<li><a href="<%=request.getContextPath()%>/app/auth/logout">Sign
								Out</a></li>
					</ul>
				</div>
				<div class="nav-collapse">
					<ul class="nav" role="navigation">
					<% 
					for(Menu menu : list){
						String dropdownDef = "";
						String tagDef = "";
						if(menu.getUrl().equals("")){
							menu.setUrl("#");
						}
						if(menu.getSubMenus().size()!=0){
							dropdownDef += "dropdown";
							tagDef = "class=\"dropdown-toggle\" data-toggle=\"dropdown\"";
						}
						if(menu.getIsSeparate().equals("Y")){
							%><li class="divider-vertical"></li><%
						}else{
					%>
					<li class="<%=dropdownDef%>">
						<a id="menu<%=menu.getPriOrder()%>" href="<%=menu.getUrl()%>" <%=tagDef%>>
						<%=menu.getName() %><b class="caret"></b>
						</a>
						<%
						if(menu.getSubMenus().size()!=0){
							%>
							<ul class="dropdown-menu" role="menu" aria-labelledby="menu<%=menu.getPriOrder()%>">
							<%
							for(Menu subMenu : menu.getSubMenus()){
								if(subMenu.getUrl().equals("")){
									subMenu.setUrl("#");
								}
								if(subMenu.getIsSeparate().equals("Y")){
									String dividerDef = "class=\"divider\"";
									%><li<%=dividerDef%> /><%
								}else{%>
									<li>
									<a href="<%=subMenu.getUrl()%>"><%=subMenu.getName() %></a>
									</li>
								<%}
							}
							%>
							</ul>
							<%
						}
						}
						%>
					</li>
					<%	
					}
					%>
						<!-- <li class="active"><a href="#">Home</a></li>
						<li><a href="#about">About</a></li>
						<li><a href="#contact">Contact</a></li> -->
					</ul>
				</div>
				<div class="modal hide" id="myModal">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">×</button>
						<h3>매니저 정보</h3>
					</div>
					<div class="modal-body">
						<p>One fine body…</p>
					</div>
					<div class="modal-footer">
						<a href="#" class="btn" data-dismiss="modal">Close</a> <a href="#"
							class="btn btn-primary">Save changes</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="container-fluid">
		<div class="row-fluid">
				<decorator:body />
		</div>
	</div>
</body>
</html>
