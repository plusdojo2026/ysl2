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
        <p>案件名：${task.caseName}</p>
        <p>タスク名：${task.taskaName}</p>
        <p>担当者：${task.manager}</p>
        <p>開始日：${task.startDate}</p>
        <p>案件名：${task.caseName}</p>
		<p>タスク名：${task.taskName}</a>
		<p>担当者：${task.manager}</p>
		<p>期限：${task.deadlineDate}</p>
		<p>見積工数：${task.estimatedMonHours}</p>
		<p>実績工数：${task.actualManHours}</p>
		<form method="POST" action="<c:url value='/Controller'/>">
			<label for="status">ステータス<br></label>
			<input type="hidden" name="page_id" value="L007">
			<input type="hidden" name="task_id" value="${task.taskId}">
			<select name="status" id="status">
				<option value="未着手" selected>未着手</option>
				<option value="進行中">進行中</option>
				<option value="完了">完了</option>
				<option value="保留">保留</option>
			</select>
			<input type="submit" name="button_id" value="変更">
		</form>
		<p>優先度：${task.priority}</p>
		<p>進捗率：${task.taskProgress}</p>
		<input type="button" value="編集">
	<button><a href="/Controller/?page_id=L007&button_id=mon_hours_link&task_id=${t.task_id}">工数登録</a></button>
	<div>
	<h2>工数ログ</h2>
	<c:forEach var="m" items="manHoursList">
	<form method="POST" action="<c:url value='/Controller'/>">
		<input type="hidden" name="man_hours_id" value="${m.manHoursId}"
		<p>${m.workDate}</p>
		<p>${m.manager}</p>
		<p>${m.todaysManHours}</p>
		<p>${m.workDetail}</p>
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