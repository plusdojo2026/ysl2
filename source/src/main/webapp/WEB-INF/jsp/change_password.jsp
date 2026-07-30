<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<meta charset="UTF-8" />
<title>パスワード変更 | Task Manager</title>
<link rel="stylesheet" href="https://cdn.datatables.net/t/bs-3.3.6/jqc-1.12.0,dt-1.10.11/datatables.min.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/change_password.css" />
</head>
<body>
	<main>
		<%@ include file="/WEB-INF/jsp/sidebar.jsp"%>

		<div class="login main">
      <form action="/ysl2/Controller" method="post" class="change-password-form">
        <h1>パスワード変更</h1>
				<div class="message">${message}</div>
				<div class="login-form-group">
					<label class="form-label">パスワード</label>
					<input type="text" name="password" id="password" class="form-control" required />
				</div>
				<div class="login-form-group">
					<label class="form-label">新パスワード</label>
					<input type="text" name="new_pw" id="new_pw" class="form-control" required />
				</div>
				<div class="login-form-group">
					<label class="form-label">新パスワード確認</label>
					<input type="text" name="new_pw_confirm" id="new_pw_confirm" class="form-control" required />
				</div>
				<div id="pwd-match-msg" class="pwd-match-msg"></div>
				<input type="hidden" name="page_id" value="L010" /> 
        <input type="submit" name="button_id" value="変更" class="btn" />
			</form>
		</div>
	</main>
	<script src="/ysl2/js/change_password.js"></script>
</body>
</html>
