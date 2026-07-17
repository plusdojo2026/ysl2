<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>タスク一覧</title>
	<link rel="stylesheet" href="<c:url value='/css/menu.css' />">
<link rel="stylesheet" href="<c:url value='/css/case.css' />">
</head>
<body>
<main>
	<h1>タスク一覧</h1>
	<div>
	<form method="POST" action="<c:url value='/Controller'/>">
		<input type="hidden" name="page_id" value="L006">
		<input type="text" name="seach_word" placeholder="キーワード検索">
		<select name="case_name">
			<c:forEach var="c" items="">
				<option value="${c.case_id}">${c.case_name}</option>
			</c:forEach>
		</select>
		<select name="manager">
			<c:forEach var="m" items="">
				<option value="${m.user_id}">${c.user_name}</option>
			</c:forEach>
		</select>
		<select name="status">
			<option value="未着手">未着手</option>
			<option value="進行中">進行中</option>
			<option value="完了">完了</option>
			<option value="保留">保留</option>
		</select>
		<input type="submit" name="button_id" value="検索">
	</form>
	</div>

	<div>
		<button>新規登録</button>
	</div>

	<div>
	<c:forEach var="t" items="">
		<p>案件名：${t.case_name}</p>
		<p>タスク名：${t.task_name}</p>
		<p>担当者：${t.manager}</p>
		<p>期限：${t.deadline_date}</p>
		<p>予算工数：${t.estimated_mon_hours}</p>
		<p>実績工数：${t.actual_man_hours}</p>
		<p>ステータス${t.task_status}</p>
		<p>優先度：${t.prioroty}</p>
		<p>進捗：${t.task_progress}</p>
		<button>編集</button>
	</c:forEach>
	</div>

</main>
</body>
</html>