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
                  <h2>Codebone - Install</h2>
                  <form action="<%=request.getContextPath()%>/app/manager" method="get">
                      <fieldset>
                      Already installed system. Please login.<br />
                      <br /><button class="btn btn-primary" style="float:right;" type="submit">Next</button>
                      </fieldset>
                  </form>
              </div>
          </div>
      </div>
  </div>
</body>
</html>
