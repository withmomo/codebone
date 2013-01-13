<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="org.codebone.framework.BaseModel,java.util.List,org.codebone.security.manager.Manager"%>
<%
	Manager completedManager = (Manager) request
			.getAttribute("manager");
%>
<section id="contents">
	<div class="row-fluid">
		<div class="login-form">
			<form action="<%=request.getContextPath()%>/app/manager" method="get">
				<fieldset>
					Install Complete!<br /> ID :
					<%=completedManager.getId()%><br /> Password : --Secret--<br />
					Name :
					<%=completedManager.getName()%><br /> Email :
					<%=completedManager.getEmail()%><br /> <br />
					<button class="btn btn-primary" style="float: right;" type="submit">Complete</button>
				</fieldset>
			</form>
		</div>
	</div>
</section>