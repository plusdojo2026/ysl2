<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>パスワード変更</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css" />
  </head>
  <body>
    <div>
      <form action="/ysl2/Controller" method="post">
        <div>
          <p>パスワード</p>
          <input type="text" name="password" id="password" required />
        </div>
        <div>
          <p>新パスワード</p>
          <input type="text" name="new_pw" id="new_pw" required />
        </div>
        <p id="pwd-match-msg"></p>
        <div>
          <p>新パスワード確認</p>
          <input type="text" name="new_pw_confirm" id="new_pw_confirm" required />
        </div>
        <input type="hidden" name="page_id" value="L010" />
        <input type="submit" name="button_id" value="変更" />
      </form>
      <button>キャンセル</button>
    </div>
  </body>
</html>
