<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>パスワード変更</title>
  </head>
  <body>
    <div>
      <form action="<c:url value='/Controller'/>">
        <div>
          <p>パスワード</p>
          <input type="text" name="pw" id="pw" required />
        </div>
        <div>
          <p>新パスワード</p>
          <input type="text" name="new_pw" id="new_pw" required />
        </div>
        <div>
          <p>新パスワード確認</p>
          <input type="text" name="new_pw_confirm" id="new_pw_confirm" required />
        </div>
        <input type="hidden" name="page_id" value="L010" />
        <button name="button_id" value="変更">変更</button>
      </form>
      <button>キャンセル</button>
    </div>
  </body>
</html>
