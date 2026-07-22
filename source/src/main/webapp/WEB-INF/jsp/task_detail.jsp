<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>タスク詳細</title>
<link rel="stylesheet" href="<c:url value='/css/menu.css' />">
<link rel="stylesheet" href="<c:url value='/css/case.css' />">
</head>
<body>
<main>
    <h1>タスク詳細</h1>
        <p>案件名：${task.case_name}</p>
        <p>タスク名：${task.task_name}</p>
        <p>担当者：${task.manager}</p>
        <p>実績工数：${task.actual_man_hours}</p>
        <p>開始日：${task.start_date}</p>
        <p>案件名：${task.case_name}</p>
		<p>タスク名：${task.task_name}</a>
		<p>担当者：${task.manager}</p>
		<p>期限：${task.deadline_date}</p>
		<p>見積工数：${task.estimated_mon_hours}</p>
		<p>実績工数：${task.actual_man_hours}</p>
		<form method="POST" action="<c:url value='/Controller'/>">
			<label for="status">ステータス<br></label>
			<input type="hidden" name="page_id" value="L007">
			<select name="status" id="status">
				<option value="未着手" selected>未着手</option>
				<option value="進行中">進行中</option>
				<option value="完了">完了</option>
				<option value="保留">保留</option>
			</select>
			<input type="submit" name="button_id" value="変更">
		</form>
		<p>優先度：${t.priority}</p>
		<p>進捗率：${t.task_progress}</p>
		<input type="button" value="編集">
	<button><a href="/Controller/?page_id=L007&button_id=mon_hours_link&task_id=${t.task_id}">工数登録</a></button>
	<div>
	<h2>工数ログ</h2>
	<c:forEach var="m" items="">
	<form method="POST" action="<c:url value='/Controller'/>">
		<p>${m.work_date}</p>
		<p>${manager}</p>
		<p>${todays_man_hours}</p>
		<p>${work_detail}</p>
		<input type="submit" name="button_id" value="削除">
	</form>
	</c:forEach>
	</div>
	<!------------以下モーダル表示---------->
	<!--タスク編集モーダル-->
	<h2>タスク編集</h2>
	タスク一覧ページのモーダル部分を後でコピペする予定です。
</body>
</html>