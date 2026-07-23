<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>ログイン</title>
  </head>
  <body>
    <div>
      <h1>ログイン</h1>

      <form action="/ysl2/Controller" method="post">
        <div class="err-message">エラーメッセージ表示エリア</div>
        <div>
          <p>ログインID</p>
          <input type="text" name="login_id" id="login_id" required />
        </div>
        <div>
          <p>パスワード</p>
          <input type="password" name="pw" id="pw" required />
        </div>
        <input type="hidden" name="page_id" value="L001" />
        <input type="submit" name="button_id" value="ログイン" />
      </form>
    </div>
  </body>
</html>
