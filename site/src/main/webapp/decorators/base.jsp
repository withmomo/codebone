<%@page import="org.apache.commons.lang.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@
	taglib prefix="form"
	uri="http://www.springframework.org/tags/form"%><%@
	taglib
	prefix="spring" uri="http://www.springframework.org/tags"%><%@
	taglib
	uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%><%@ page
	import="org.codebone.framework.BaseModel,java.util.List,java.util.ArrayList,org.codebone.security.manager.Manager,org.codebone.security.menu.Menu"%>
<%
	Object obj1 = request.getAttribute("loginManager");
	Manager currentLoginManager = null;
	if (obj1 != null) {
		currentLoginManager = (Manager) obj1;
	}
	
	boolean visibleSubNavigation = false;
	Object obj2 = request.getAttribute("menu");
	List<Menu> list = null;
	if (obj2 != null) {
		list = (List<Menu>) obj2;
		visibleSubNavigation = true;
	} else {
		list = new ArrayList<Menu>();
	}
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title><decorator:title default="codebone - rapidly build application" /></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="rapidly build application">
<meta name="author" content="">

<!-- Le HTML5 shim, for IE6-8 support of HTML elements -->
<!--[if lt IE 9]>
<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->

<link href="<%=request.getContextPath()%>/css/inspiritas.css"
	rel="stylesheet">
<decorator:head />
</head>

<body>

	<!-- Navbar -->
	<div class="navbar navbar-static-top navbar-inverse">
		<div class="navbar-inner">
			<div class="container">
				<a class="btn btn-navbar" data-toggle="collapse"
					data-target=".nav-collapse"> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span>
				</a> <a class="brand" href="#">codebone</a> <span class="tagline">rapidly
					build application</span>

				<div class="nav-collapse collapse" id="main-menu">
					<div class="auth pull-right">
					
					<%if (currentLoginManager==null) { %>
					<form class="form-inline"
						action="<%=request.getContextPath()%>/app/authentication"
						method="post">
						<input type="text" class="input-small" name="username" placeholder="username">
						<input type="password" class="input-small" name="password" placeholder="password">
						<button type="submit" class="btn">Sign in</button>
					</form>
					<% } else { %>
						<img class="avatar"
							src="<%=currentLoginManager.getPicture()%>?s=40">
						<span class="name"><%=currentLoginManager.getName()%></span><br />
						<span class="links">
							<a href="<%=request.getContextPath()%>/app/auth/logout">Sign Out</a>
						</span>
					<% } %>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	
	<div class="container-wapper">
	<div class="container">
		<div class="row-fluid">
			<div class="span3">
				<aside>
					<nav>
					
					<ul class="nav">
					<%
					String navigation = "&nbsp;";
					for(Menu menu : list){
						String dropdownDef = "";
						String tagDef = "";
						if(menu.getUrl().equals("")){
							menu.setUrl("#");
						}
						if(menu.getSubMenus().size() > 0){
							dropdownDef += "dropdown";
							tagDef = "class=\"dropdown-toggle\" data-toggle=\"dropdown\"";
						}
						if(menu.getIsSeparate().equals("Y")){
							%><li class="divider-vertical"></li><%
							continue;
						}
						
					%>
					<li class="<%=dropdownDef%>">
						<a id="menu<%=menu.getPriOrder()%>" href="<%=menu.getUrl()%>" <%=tagDef%>>
						<%=menu.getName() %>
						</a>
						<%
						if(menu.getSubMenus().size()!=0){
							%>
							<ul class="dropdown-menu">
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
						%>
					</li>
					<%	
					}
					%>
					</ul>
					</nav>
				</aside>
			</div>

			<div class="span9" id="content-wrapper">
				<div id="content">
					<section id="stats">
						<header>
							<h1><%=navigation%></h1>
						</header>
					</section>

					<decorator:body />

				</div>
			</div>
		</div>
	</div>
	</div>
	<!--/container -->

	<footer class="footer">
		<p class="pull-right">
			<a href="#">Back to top</a>
		</p>
		<p>
			Copyright 2013 <a href="https://github.com/withmomo/codebone"
				target="_blank">codebone</a>. codebone is a ORM scaffolding tools.
		</p>
		<p>
			Designed by <a href="https://github.com/littke/inspiritas-bootstrap"
				target="_blank">inspiritas</a>.
		</p>
	</footer>

	<!-- Placed at the end of the document so the pages load faster -->
	<script
		src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/highcharts.js"></script>
	<script src="<%=request.getContextPath()%>/js/inspiritas.js"></script>
	<script src="<%=request.getContextPath()%>/js/bootstrap-dropdown.js"></script>
	<script src="<%=request.getContextPath()%>/js/bootstrap-collapse.js"></script>

</body>
</html>