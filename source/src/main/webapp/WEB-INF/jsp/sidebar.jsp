<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>サイドバー</title>
 <link rel="stylesheet" type="text/html" href="${pageContext.request.contextPath}/css/sidebar.css">
</head>

<body>

<div class="sidebar">
  <div class="user-profile">
  
    <div class="avatar"></div>
    <span class="username">ユーザー</span>
  </div>
  

  <ul class="nav-menu">
  
    <li class="nav-item active">
      <a href="${pageContext.request.contextPath}/jsp/dash_board.jsp" class="sidebar-link">
      <span class="nav-badge">ダッシュボード</span>
      </a>
    </li>
       
    <li class="nav-item active">
      <a href="${pageContext.request.contextPath}/jsp/case.jsp" class="sidebar-link">
      <span class="nav-badge">案件</span>
      </a>
    </li>
    
    <li class="nav-item active">
      <a href="${pageContext.request.contextPath}/jsp/task.jsp" class="sidebar-link">
      <span class="nav-badge">タスク管理</span>
      </a>
    </li>
    
        <li class="nav-item active">
      <a href="${pageContext.request.contextPath}/jsp/manthly_sum.jsp" class="sidebar-link">
      <span class="nav-badge">月次集計</span>
      </a>
    </li>

        <li class="nav-item active">
      <a href="${pageContext.request.contextPath}/jsp/admin.jsp" class="sidebar-link">
      <span class="nav-badge">メンバー管理</span>
      </a>
    </li>
    
  </ul>
  
  <div class="sidebar-footer">
    <a href="#" class="footer-link">ログアウト</a>
  </div>
  
  <div class="sidebar-footer">
    <a href="#" class="footer-link">パスワード変更</a>
  </div>
</div>

</body>
</html>