<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>タスク詳細</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css" />
	<link rel="stylesheet" href="<c:url value='/css/task_detail.css' />">
	<script src="<c:url value='js/task_detail.js'/>"></script>
</head>
<body>
<main>
<%@ include file="/WEB-INF/jsp/sidebar.jsp"%>
<div>
	<div>
    <h1>タスク詳細</h1>
    <div>${msg}</div>
    <table border="1">
    	<tr>
    		<th>案件名</th>
    		<td>${task.caseName}</td>
    	</tr>
    	<tr>
    		<th>タスク名</th>
    		<td>${task.taskName}</td>
    	</tr>
    	<tr>
    		<th>担当者</th>
    		<td>${task.name}</td>
    	</tr>
    	<tr>
    		<th>案件名</th>
    		<td>${task.caseName}</td>
    	</tr>
    	<tr>
    		<th>開始日</th>
		    <td>
	        	<c:if test="${empty task.taskStartDate}">未設定</c:if>
	        	${task.taskStartDate}
        	</td>
    	</tr>
    	<tr>
    		<th>期限</th>
    		<td>
				<c:if test="${empty task.deadlineDate}">未設定</c:if>
				${task.deadlineDate}
			</td>
    	</tr>
    	<tr>
    		<th>見積工数</th>
    		<td>${task.estimatedManHours}</td>
    	</tr>
    	<tr>
    		<th>実績工数</th>
    		<td>${task.actualManHours}</td>
    	</tr>
    	<tr>
    		<th>ステータス</th>
    		<!-- ステータス変更フォーム -->
			<td>
			<form method="POST" action="<c:url value='/Controller'/>">
				<input type="hidden" name="page_id" value="L007">
				<input type="hidden" name="task_id" value="${task.taskId}">
				<input type="hidden" name="progress" value="${task.taskProgress}">
				<select name="status" id="status">
					<option value="未着手" ${task.taskStatus == '未着手' ? 'selected' : ''}>未着手</option>
					<option value="進行中" ${task.taskStatus == '進行中' ? 'selected' : ''}>進行中</option>
					<option value="完了" ${task.taskStatus == '完了' ? 'selected' : ''}>完了</option>
					<option value="保留" ${task.taskStatus == '保留' ? 'selected' : ''}>保留</option>
				</select>
				<input type="submit" name="button_id" value="変更">
			</form>
			</td>
			<!-- --------------------------- -->
    	</tr>
    	<tr>
    		<th>優先度</th>
    		<td>${task.taskPriority}</td>
    	</tr>
    	<tr>
    		<th>進捗率</th>
    		<td>${task.taskProgress}</td>
    	</tr>
    	<tr>
    		<th>説明</th>
    		<td>${task.taskMemo}</td>
    	</tr>
		</table>
	<button onclick="openModal2('${task.caseId}','${task.taskId}','${task.taskName}','${task.manager}','${task.taskStartDate}','${task.deadlineDate}','${task.estimatedManHours}','${task.actualManHours}','${task.taskStatus}','${task.taskPriority}','${task.taskMemo}','${task.taskProgress }')">編集</button>
	<button><a href="${pageContext.request.contextPath}/Controller?page_id=L007&button_id=工数登録&task_id=${task.taskId}">工数登録</a></button>
	<div>
	<h2>工数ログ</h2>
	<table border="1">
		<tr>
			<th>作業日</th>
			<th>担当者</th>
			<th>工数</th>
			<th>作業内容</th>
		</tr>
	<c:forEach var="m" items="${manHoursList}">
	<form method="POST" action="<c:url value='/Controller'/>">
		<tr>
			<input type="hidden" name="page_id" value="L007">
			<input type="hidden" name="man_hours_id" value="${m.manHoursId}">
			<td>${m.workDate}</td>
			<td>${m.manager}</td>
			<td>${m.todayManHours}</td>
			<td>${m.workDetails}</td>
			<td><input type="submit" name="button_id" value="削除"><td>
		</tr>
	</form>
	</c:forEach>
	</table>	
	</div>
	<!------------以下モーダル表示---------->
	<!--タスク編集モーダル-->
	<div id="modal2" class="modal-background2">
	    <div class="modal-content2">
			<h2>タスク編集</h2>
		    <form method="POST" action="<c:url value='/Controller'/>" id="conform">
		        <input type="hidden" name="page_id" value="L006">
		        <input type="hidden" name="task_id">
				<label>案件名<br>
				<select name="case_id" id="case_name" required>
					<c:forEach var="c" items="${casesList}">
						<option value="${c.caseId}">${c.caseName}</option>
					</c:forEach>
				</select>
				</label>
		        <label>タスク名<br>
		            <input type="text" name="task_name" required>
		        </label>
		        <label for="manager">担当者<br></label>
				<select name="manager" id="manager">
					<c:forEach var="m" items="${activeUsersList}">
						<option value="${m.userId}">${m.name}</option>
					</c:forEach>
				</select>
		        <label>進捗率<br>
		            <input type="number" min="0" max="100" step="1" name="progress">
		        </label>
		        <label>開始日<br>
		            <input type="date" name="start_date">
		        </label>
		        <label>期限<br>
		        <input type="date" name="deadline_date">
		        </label>
		        <label for="priority">優先度<br></label>
		        <select name="priority" id="priority" required>
		            <option value="高">高</option>
		            <option value="中" selected>中</option>
		            <option value="低">低</option>
		        </select>
				</p>
				<p>
		        <label>見積工数<br>
		            <input type="number" min="0" step="0.5" name="estimated_man_hours">
		        </label>
				<label for="status">ステータス<br></label>
				<select name="status" id="status" required>
					<option value="未着手" selected>未着手</option>
					<option value="進行中">進行中</option>
					<option value="完了">完了</option>
					<option value="保留">保留</option>
				</select>
				<label>説明<input type="textarea" name="memo"></label></p>
				<input type="submit" name="button_id" value="保存">
				<input type="button" value="戻る" onclick="closeModal2()">
		    </form>
		</div>
	</div>
</div>
</main>
</body>
</html>