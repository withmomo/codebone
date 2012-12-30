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
					<ul class="nav">
						<li class="active"><a href="#">Home</a></li>
						<li><a href="#about">About</a></li>
						<li><a href="#contact">Contact</a></li>
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
			<div class="span2">
				<div class="well sidebar-nav">
					<ul class="nav nav-list">
					<%
						for(int i=0;i<list.size();i++){
										Menu menu = list.get(i);
										Menu nextMenu;
										if(list.size()!=i+1){
											nextMenu = list.get(i+1);
										}else{
											nextMenu = menu;
										}
										String classDef = "";
										if(menu.getUrl().equals(request.getRequestURI())){
											classDef = "active";
										}
										if(menu.getIsSeparate().equals("Y")){
											if(menu.getName().isEmpty()){
												//메뉴 문자열이 비어있음 - 구분자
												classDef = classDef + " divider";
											}else{
												//메뉴 문자열이 비어있지 않음 - 헤더
												classDef = classDef + " nav-header";
											}
					%><li class="<%=classDef %>"><%
							}else{
								%><li><%
							}
							if(menu!=nextMenu && nextMenu.getPriOrder().equals(menu.getPriOrder()) && menu.getSubOrder().equals(0)){
								//현재 메뉴와 다음 메뉴의 기본 정렬순서가 같은데, 현재메뉴 order는 0 -> 서브메뉴 시작
								if(menu.getUrl().isEmpty()){
									%><a href="#" data-toggle="collapse" data-target="#sub<%=menu.getPriOrder()%>"><%=menu.getName() %></a></li>
									<%
								}else if(menu.getIsExternal().equals("Y")){
									%><a href="<%=menu.getUrl() %>" data-toggle="collapse" data-target="#sub<%=menu.getPriOrder()%>"><%=menu.getName() %></a></li>
									<%
								}else{
									%><a href="<%=request.getContextPath()+menu.getUrl() %>" data-toggle="collapse" data-target="#sub<%=menu.getPriOrder()%>"><%=menu.getName() %></a></li>
									<%
								}
								%><div id="sub<%=menu.getPriOrder() %>" class="nav nav-list collapse in">
								<%
							}else if(menu!=nextMenu && nextMenu.getPriOrder().equals(menu.getPriOrder()) && !(menu.getSubOrder().equals(0))){
								//현재 메뉴와 다음 메뉴의 기본 정렬순서가 같고, 현재메뉴 order가 0이아님 -> 서브메뉴 연속
								if(menu.getUrl().isEmpty()){
									%><a href="#"><%=menu.getName()%></a></li>
									<%
								}else if(menu.getIsExternal().equals("Y")){
									%><a href="<%=menu.getUrl() %>"><%=menu.getName() %></a></li>
									<%
								}else{
									%><a href="<%=request.getContextPath()+menu.getUrl() %>"><%=menu.getName() %></a></li>
									<%
								}
							}else if(menu!=nextMenu && menu.getSubOrder() > nextMenu.getSubOrder()){
								//현재 메뉴와 다음 메뉴의 기본 정렬 순서가 다르고, 다음 메뉴 order가 0 -> 서브메뉴에서 메인메뉴로 나감
								if(menu.getUrl().isEmpty()){
									%><a href="#"><%=menu.getName()%></a></li>
									</div>
									<%
								}else if(menu.getIsExternal().equals("Y")){
									%><a href="<%=menu.getUrl() %>"><%=menu.getName() %></a></li>
									</div>
									<%
								}else{
									%><a href="<%=request.getContextPath()+menu.getUrl() %>"><%=menu.getName() %></a></li>
									</div>
									<%
								}
							}else{
								//일반메뉴가 계속되고있음
								if(menu.getUrl().isEmpty()){
									//링크정보가 비어있다 - 링크없음. 바로 이름 보여주고 끝
									%><a href="#"><%=menu.getName()%></a></li>
									<%
								}else if(menu.getIsExternal().equals("Y")){
									%><a href="<%=menu.getUrl() %>"><%=menu.getName()%></a></li>
									<%
								}else{
									%><a href="<%=request.getContextPath()+menu.getUrl() %>"><%=menu.getName()%></a></li>
									<%
								}
							}
						}
					%>
					</ul>
				</div>
			</div>
			<div class="span10">
				<decorator:body />
			</div>
		</div>
	</div>
</body>
</html>
