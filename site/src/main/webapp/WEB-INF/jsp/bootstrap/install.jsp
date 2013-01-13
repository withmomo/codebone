<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	boolean passwordFail = false;
	String errorMessage = (String) request.getAttribute("error");
	if (errorMessage != null) {
		passwordFail = true;
	}
%>
<section id="form">
	<form class="form-horizontal"
		action="<%=request.getContextPath()%>/app/bootstrap/install"
		method="post">
		<div class="row-fluid">
			<div class="span12">

				<div class="control-group">
					<label class="control-label" for="id">ID</label>
					<div class="controls">
						<input type="text" class="input-large" id="id" name="id">
					</div>
				</div>

				<%
					if (passwordFail) {
				%>

				<div class="control-group">
					<label class="control-label" for="password">Password</label>
					<div class="controls">
						<input type="text" class="input-large" name="password"
							id="password" placeholder="diffrent password">
					</div>
				</div>

				<div class="control-group">
					<label class="control-label" for="passwordCheck">Password</label>
					<div class="controls">
						<input type="text" class="input-large" name="passwordCheck"
							id="passwordCheck" placeholder="diffrent password">
					</div>
				</div>

				<%
					} else {
				%>

				<div class="control-group">
					<label class="control-label" for="password">Password</label>
					<div class="controls">
						<input type="password" class="input-large" name="password"
							id="password" placeholder="password">
					</div>
				</div>

				<div class="control-group">
					<label class="control-label" for="passwordCheck">Password</label>
					<div class="controls">
						<input type="password" class="input-large" name="passwordCheck"
							id="passwordCheck" placeholder="check password">
					</div>
				</div>

				<%
					}
				%>

				<div class="control-group">
					<label class="control-label" for="name">Name</label>
					<div class="controls">
						<input type="text" class="input-large" id="name" name="name">
					</div>
				</div>

				<div class="control-group">
					<label class="control-label" for="email">E-mail</label>
					<div class="controls">
						<input type="text" class="input-large" id="email" name="email">
					</div>
				</div>
			</div>
		</div>

		<div class="form-actions">
			<button type="submit" class="btn btn-primary">Create</button>
		</div>

	</form>
</section>
