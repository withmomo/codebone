<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page
	import="org.codebone.framework.BaseModel,java.util.List,org.codebone.security.menu.Menu,org.codebone.security.manager.Manager"%>
<%@ page
	import="java.lang.reflect.Field,java.lang.reflect.Method,javax.persistence.Id,java.util.Date"%>
<%
	String isCreate = (String) request.getAttribute("isCreate");
	Manager currentLoginManager = (Manager) request
			.getAttribute("loginManager");
	Menu menuModel = null;
	String key = null;
	if (isCreate.equals("Y")) {
		menuModel = new Menu();
		isCreate = "Create";
	} else {
		BaseModel model = (BaseModel) request.getAttribute("data");
		key = (String) request.getAttribute("id");
		menuModel = (Menu) model.getData();
		isCreate = "Update";
	}
%>
<section id="form">
	<form class="form-horizontal"
		action="<%=request.getContextPath()%>/app/menu/<%=isCreate.toLowerCase()%>"
		method="post">
		<div class="row-fluid">
			<div class="span12">
				<div class="control-group">
					<label class="control-label" for="name">name</label>
					<div class="controls">
						<input type="text" class="input-large" id="name" name="name"
							value="<%=menuModel.getName()%>">
					</div>
				</div>

				<div class="control-group">
					<label class="control-label" for="url">url</label>
					<div class="controls">
						<input type="text" class="input-large" id="url" name="url"
							value="<%=menuModel.getUrl()%>">
					</div>
				</div>

				<div class="control-group">
					<label class="control-label" for="priOrder">priOrder</label>
					<div class="controls">
						<input type="text" class="input-large" id="priOrder"
							name="priOrder" value="<%=menuModel.getPriOrder()%>">
					</div>
				</div>

				<div class="control-group">
					<label class="control-label" for="subOrder">subOrder</label>
					<div class="controls">
						<input type="text" class="input-large" id="subOrder"
							name="subOrder" value="<%=menuModel.getSubOrder()%>">
					</div>
				</div>

				<div class="control-group">
					<label class="control-label" for="isSeparate">isSeparate</label>
					<div class="controls">
						<input type="text" class="input-large" id="isSeparate"
							name="isSeparate" value="<%=menuModel.getIsSeparate()%>">
					</div>
				</div>

				<div class="control-group">
					<label class="control-label" for="isExternal">isExternal</label>
					<div class="controls">
						<input type="text" class="input-large" id="isExternal"
							name="isExternal" value="<%=menuModel.getIsExternal()%>">
					</div>
				</div>

				<div class="control-group">
					<label class="control-label" for="managerIdx">managerIdx</label>
					<div class="controls">
						<%
							Long value = null;
							if (isCreate.equals("Create"))
								value = currentLoginManager.getIdx();
							else
								value = menuModel.getManagerIdx();
						%>

						<input type="text" class="input-large" name="managerIdx"
							value="<%=value%>">
					</div>
				</div>

			</div>
		</div>

		<div class="form-actions">
			<button type="submit" class="btn btn-primary"><%=isCreate%></button>
			<button type="reset" class="btn">Cancel</button>
		</div>

		<%
			if (isCreate.equals("Update")) {
		%>
		<input type="hidden" class="form-vertical" name="idx"
			value="<%=menuModel.getIdx()%>">
		<%
			}
		%>

	</form>
</section>