<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>サイドバー</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/sidebar.css" />
  </head>

  <body>
    <div class="sidebar">
      <div class="user-profile">
        <div class="avatar"></div>
        <span class="username">ユーザー</span>
      </div>

      <ul class="nav-menu">
        <li class="nav-item active">
          <a href="${pageContext.request.contextPath}/Controller?page_id=side&button_id=ダッシュボード" class="sidebar-link">
            <span class="nav-badge">ダッシュボード</span>
          </a>
        </li>

        <li class="nav-item active">
          <a href="${pageContext.request.contextPath}/Controller?page_id=side&button_id=案件一覧" class="sidebar-link">
            <span class="nav-badge">案件一覧</span>
          </a>
        </li>

        <li class="nav-item active">
          <a href="${pageContext.request.contextPath}/Controller?page_id=side&button_id=タスク一覧" class="sidebar-link">
            <span class="nav-badge">タスク一覧</span>
          </a>
        </li>

        <li class="nav-item active">
          <a href="${pageContext.request.contextPath}/Controller?page_id=side&button_id=月次集計" class="sidebar-link">
            <span class="nav-badge">月次集計</span>
          </a>
        </li>

        <li class="nav-item active">
          <a href="${pageContext.request.contextPath}/Controller?page_id=side&button_id=メンバー管理" class="sidebar-link">
            <span class="nav-badge">メンバー管理</span>
          </a>
        </li>
      </ul>

      <div class="sidebar-footer">
        <a href="${pageContext.request.contextPath}/Controller?page_id=side&button_id=ログアウト" class="footer-link">ログアウト</a>
      </div>

      <div class="sidebar-footer">
        <a href="${pageContext.request.contextPath}/Controller?page_id=L010&button_id=変更">パスワード変更</a>
      </div>
    </div>
  </body>
</html>
