<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="org.codebone.framework.BaseModel,java.util.List,org.codebone.security.manager.Manager"%>
<%
Manager completedManager = (Manager) request.getAttribute("manager");
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
                  <form action="<%=request.getContextPath()%>/app/manager" method="get">
                      <fieldset>
                      Install Complete!<br />
                      ID : <%=completedManager.getId() %><br />
                      Password : --Secret--<br />
                      Name : <%=completedManager.getName() %><br />
                      Email : <%=completedManager.getEmail() %><br />
                          <button class="btn btn-primary" type="submit">Complete</button>
                      </fieldset>
                  </form>
              </div>
          </div>
      </div>
  </div>
</body>
</html>
