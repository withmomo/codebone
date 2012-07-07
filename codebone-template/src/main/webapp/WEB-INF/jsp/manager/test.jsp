<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="org.codebone.framework.BaseModel,java.util.List,org.codebone.security.manager.ManagerModel,org.codebone.security.menu.MenuModel"%>
<%
ManagerModel currentLoginManager = (ManagerModel) request.getAttribute("loginManager");
	List<MenuModel> list = (List<MenuModel>) request.getAttribute("menu");
%>
<html>
<%@ include file="/WEB-INF/jsp/import/config.jsp"%>
<head>
<script>
	$(document).ready(function() {
		$('.dropdown-toggle').dropdown()
		$(".collapse").collapse()
	});
</script>
<title>asdf</title>
</head>


<body>
<div class="span2">
				<div class="well sidebar-nav">
					<ul class="nav nav-list">
					<%
						for(int i=0;i<list.size();i++){
							MenuModel menu = list.get(i);
							MenuModel nextMenu;
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
			
</body>
</html>