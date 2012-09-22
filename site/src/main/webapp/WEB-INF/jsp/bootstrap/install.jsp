<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	boolean passwordFail = false;
	String errorMessage = (String) request.getAttribute("error");
	if (errorMessage != null) {
		passwordFail = true;
	}
%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="description" content="">
<meta name="author" content="">
</head>
<body>
	<div class="container">
		<div class="content">
			<div class="row">
				<div class="login-form">
					<h2>Codebone - Install</h2>
					최초 접속시 관리자를 생성하는 페이지 입니다.<br /><br />
					<form action="<%=request.getContextPath()%>/app/bootstrap/install"
						method="post">
						<fieldset>
							<table class="table">
								<tbody>
									<tr>
										<td>아이디</td>
										<td><input type="text" name="id"></td>
									</tr>
									<%
										if (passwordFail) {
									%>
									<tr>
										<td>비밀번호</td>
										<td><input type="password" placeholder="비밀번호가 다릅니다."
											name="password"></td>
									</tr>
									<tr>
										<td>비밀번호 확인</td>
										<td><input type="password" placeholder="비밀번호가 다릅니다."
											name="passwordCheck"></td>
									</tr>
									<%
										} else {
									%>
									<tr>
										<td>비밀번호</td>
										<td><input type="password" name="password"></td>
									</tr>
									<tr>
										<td>비밀번호 확인</td>
										<td><input type="password" name="passwordCheck"></td>
									</tr>
									<%
										}
									%>
									<tr>
										<td>이름</td>
										<td><input type="text" name="name"></td>
									</tr>
									<tr>
										<td>이메일</td>
										<td><input type="text" name="email"></td>
									</tr>
								</tbody>
							</table>
							<button class="btn btn-primary" style="float: right;"
								type="submit">생성</button>
						</fieldset>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
