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

      <form action="/Controller">
        <div class="err-message">エラーメッセージ表示エリア</div>
        <div>
          <p>ログインID</p>
          <input type="text" name="login_id" id="login_id" required />
        </div>
        <div>
          <p>パスワード</p>
          <input type="password" name="pw" id="pw" req />
        </div>
        <input type="hidden" name="page_id" value="L001" />
        <button id="button_id" value="ログイン">ログイン</button>
      </form>
    </div>
  </body>
</html>
