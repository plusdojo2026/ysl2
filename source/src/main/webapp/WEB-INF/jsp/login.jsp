<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>ログイン | Task Manager</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css" />
  </head>

  <body>
    <div class="login">
      <h2>ログイン</h2>
      <form action="/ysl2/Controller" method="post">
        <div class="err-message">${errMsg}</div>
        <div class="login-form-group">
          <label class="form-label">ログインID</label>
          <input type="text" name="login_id" id="login_id" class="form-control" required />
        </div>
        <div class="login-form-group">
          <label class="form-label">パスワード</label>
          <input type="password" name="pw" id="pw" class="form-control" required />
        </div>
        <input type="hidden" name="page_id" value="L001" />
        <input type="submit" name="button_id" value="ログイン" class="btn-login" />
      </form>
    </div>
  </body>
</html>
