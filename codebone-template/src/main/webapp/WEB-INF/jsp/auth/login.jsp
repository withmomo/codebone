<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
                  <h2>Codebone - Login</h2>
                  <form action="<%=request.getContextPath()%>/app/authentication" method="post">
                      <fieldset>
                          <div class="clearfix">
                              <input type="text" placeholder="아이디" name="username">
                          </div>
                          <div class="clearfix">
                              <input type="password" placeholder="비밀번호" name="password">
                          </div>
                          <button class="btn btn-primary" type="submit">로그인</button>
                      </fieldset>
                  </form>
              </div>
          </div>
      </div>
  </div>
</body>
</html>
