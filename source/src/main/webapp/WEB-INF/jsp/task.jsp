
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
			<option value="">ステータス</option>
			<option value="未着手">未着手</option>
			<option value="進行中">進行中</option>
			<option value="完了">完了</option>
			<option value="保留">保留</option>
		</select>
		<input type="submit" name="button_id" value="検索">
	</form>
	</div>

	<div>
		<input type="button" value="新規登録">
	</div>

	<div>
	<c:forEach var="t" items="">
		<p>案件名：${t.case_name}</p>
		<a href="/Controller/?page_id=L006&button_id=task_link&task_id=${t.task_id}">タスク名：${t.task_name}</a>
		<p>担当者：${t.manager}</p>
		<p>期限：${t.deadline_date}</p>
		<p>見積工数：${t.estimated_mon_hours}</p>
		<p>実績工数：${t.actual_man_hours}</p>
		<p>ステータス${t.task_status}</p>
		<p>優先度：${t.priority}</p>
		<p>説明：${t.memo}</p>
		<input type="button" value="編集">
	</c:forEach>
	</div>

    <!----------------以下モーダル表示--------------->
    <!--タスク登録モーダル-->
	<div>
	<h2>タスク登録</h2>
    <form method="POST" action="<c:url value='/Controller'/>">
        <input type="hidden" name="page_id" value="L006">
		<p>
		<label for="case_name">案件名*<br></label>
		<select name="case_name" id="case_name">
			<c:forEach var="c" items="">
				<option value="${c.case_id}">${c.case_name}</option>
			</c:forEach>
		</select>
		</p>
		<p>
        <label>タスク名*<br>
            <input type="text" name="task_name">
        </label>
		</p>
		<p>
        <label for="manager">担当者<br></label>
		<select name="manager" id="manager">
			<c:forEach var="m" items="">
				<option value="${m.user_id}">${c.user_name}</option>
			</c:forEach>
		</select>
		</p>
		<p>
        <label>進捗率<br>
            <input type="number" min="0" max="100" step="1" name="task_progress">
        </label>
		</p>
		<p>
        <label>開始日<br>
            <input type="date" name="start_date">
        </label>
		</p>
		<p>
        <label>期限<br>
        <input type="date" name="deadline_date">
        </label>
		</p>
		<p>
        <label for="priority">優先度*<br></label>
        <select name="priority" id="priority">
            <option value="高">高</option>
            <option value="中" selected>中</option>
            <option value="低">低</option>
        </select>
		</p>
		<p>
        <label>見積工数<br>
            <input type="number" min="0" max="24" step="0.5" name="estimated_mon_hours">
        </label>
		</p>
		<p>
		<label for="status">ステータス*<br></label>
		<select name="status">
			<option value="未着手" selected>未着手</option>
			<option value="進行中">進行中</option>
			<option value="完了">完了</option>
			<option value="保留">保留</option>
		</select>
		</p>
		<input type="submit" name="button_id" value="登録">
		<input type="button" value="戻る">
    </form>
	</div>

	<!--タスク編集モーダル-->
	<div>
	<h2>タスク編集</h2>
    <form method="POST" action="<c:url value='/Controller'/>">
        <input type="hidden" name="page_id" value="L006">
		<p>
		<label for="case_name">案件名*<br></label>
		<select name="case_name" id="case_name">
			<c:forEach var="c" items="">
				<option value="${c.case_id}">${c.case_name}</option>
			</c:forEach>
		</select>
		</p>
		<p>
        <label>タスク名*<br>
            <input type="text" name="task_name">
        </label>
		</p>
		<p>
        <label for="manager">担当者<br></label>
		<select name="manager" id="manager">
			<c:forEach var="m" items="">
				<option value="${m.user_id}">${c.user_name}</option>
			</c:forEach>
		</select>
		</p>
		<p>
        <label>進捗率<br>
            <input type="number" min="0" max="100" step="1" name="task_progress">
        </label>
		</p>
		<p>
        <label>開始日<br>
            <input type="date" name="start_date">
        </label>
		</p>
		<p>
        <label>期限<br>
        <input type="date" name="deadline_date">
        </label>
		</p>
		<p>
        <label for="priority">優先度*<br></label>
        <select name="priority" id="priority">
            <option value="高">高</option>
            <option value="中" selected>中</option>
            <option value="低">低</option>
        </select>
		</p>
		<p>
        <label>見積工数<br>
            <input type="number" min="0" max="24" step="0.5" name="estimated_mon_hours">
        </label>
		</p>
		<p>
		<label for="status">ステータス*<br></label>
		<select name="status">
			<option value="未着手" selected>未着手</option>
			<option value="進行中">進行中</option>
			<option value="完了">完了</option>
			<option value="保留">保留</option>
		</select>
		</p>
		<p>
		<label></label>説明
		</p>
		<input type="submit" name="button_id" value="保存">
		<input type="button" value="戻る">
    </form>
	</div>
</main>
</body>
</html>