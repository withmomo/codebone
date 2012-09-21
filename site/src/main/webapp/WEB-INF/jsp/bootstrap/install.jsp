<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
boolean passwordFail = false;
String errorMessage = (String) request.getAttribute("error");
if(errorMessage!=null){
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
                  <form action="<%=request.getContextPath()%>/app/bootstrap/install" method="post">
                      <fieldset>
                          <div class="clearfix">
                              아이디 : <input type="text" name="id">
                          </div>
                          <%
                          	if(passwordFail){
                          %>
                          <div class="clearfix">
                              비밀번호 : <input type="password" placeholder="비밀번호가 다릅니다." name="password">
                          </div>
                          <div class="clearfix">
                              비밀번호 확인 : <input type="password" placeholder="비밀번호가 다릅니다." name="passwordCheck">
                          </div>
                          <%
                          	}else{
                          %>
                          <div class="clearfix">
                              비밀번호 : <input type="password" name="password">
                          </div>
                          <div class="clearfix">
                              비밀번호 확인 : <input type="password" name="passwordCheck">
                          </div>
                          <%
                          	}
                          %>
                          <div class="clearfix">
                              이름 : <input type="text" name="name">
                          </div>
                          <div class="clearfix">
                              이메일 : <input type="text" name="email">
                          </div>
                          <button class="btn btn-primary" style="float:right;" type="submit">생성</button>
                      </fieldset>
                  </form>
              </div>
          </div>
      </div>
  </div>
</body>
</html>
