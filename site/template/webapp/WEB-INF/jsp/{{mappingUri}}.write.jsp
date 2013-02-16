<%@ page language="java"
	contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"

%><%@ page

	import="java.util.*,{{package}}.*,org.codebone.framework.BaseModel,java.util.Date,org.codebone.framework.util.PagingNavigation"

%><%

	String isCreate = (String) request.getAttribute("isCreate");
	{{tableNameCamelcase}} model = null;
	String key = null;
	if (isCreate.equals("Y")) {
		model = new {{tableNameCamelcase}}();
		isCreate = "Create";
	} else {
		BaseModel baseModel = (BaseModel) request.getAttribute("data");
		key = (String) request.getAttribute("id");
		model = ({{tableNameCamelcase}}) baseModel.getData();
		isCreate = "Update";
	}
%>
<section id="form">
	<form class="form-horizontal"
		action="<%=request.getContextPath()%>/app/{{mappingUri}}/<%=isCreate.toLowerCase()%>"
		method="post">
		<div class="row-fluid">
			<div class="span12">
				{{#columns}}
				{{^foreignKey}}
				{{#primaryKey}}
				<% if(isCreate.equals("Create")){ %>
				{{/primaryKey}}
				<div class="control-group">
					<label class="control-label" for="{{name}}">{{description}}</label>
					<div class="controls">
						<input type="text" class="input-large" id="{{name}}" name="{{nameCamelcase}}"
							value="<%=model.get{{nameCamelcase}}()%>">
					</div>
				</div>
				{{#primaryKey}}
				<% } %>
				{{/primaryKey}}
				{{/foreignKey}}
				{{/columns}}
			</div>
			
		</div>

		<div class="form-actions">
			<button type="submit" class="btn btn-primary"><%=isCreate%></button>
			<button type="reset" class="btn">Cancel</button>
		</div>

		<%
			if (isCreate.equals("Update")) {
		%>
		<input type="hidden" class="form-vertical" name="{{primaryKeyCamelcase}}" value="<%=model.get{{primaryKeyCamelcase}}()%>">
		<%
			}
		%>

	</form>
</section>