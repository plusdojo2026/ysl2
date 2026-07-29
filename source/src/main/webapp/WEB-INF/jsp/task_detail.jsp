<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>タスク詳細 | TaskManager</title>
  <link rel="stylesheet" href="https://cdn.datatables.net/t/bs-3.3.6/jqc-1.12.0,dt-1.10.11/datatables.min.css"/>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css" />
	<link rel="stylesheet" href="<c:url value='/css/task_detail.css' />">
</head>
<body>
<main>
<%@ include file="/WEB-INF/jsp/sidebar.jsp"%>
<div class="main">
    <div>${msg}</div>
    <div class="task_details">
    <h3>タスク詳細</h3>
    <div class="tables">
	    <div class="table1">
		    <table>
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
		    </table>
	    </div>
	    <div class="table2">
	    	<table>
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
						<input type="submit" name="button_id" value="変更" class="btn">
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
		    	<tr class="memo">
		    		<th>説明</th>
		    		<td>${task.taskMemo}</td>
		    	</tr>
			</table>
		</div>
		<div></div>
		<div class="buttons">
			<button class="btn" onclick="openModal2('${task.caseId}','${task.taskId}','${task.taskName}','${task.manager}','${task.taskStartDate}','${task.deadlineDate}','${task.estimatedManHours}','${task.actualManHours}','${task.taskStatus}','${task.taskPriority}','${task.taskMemo}','${task.taskProgress }')">編集</button>
			<button class="btn"><a href="${pageContext.request.contextPath}/Controller?page_id=L007&button_id=工数登録&task_id=${task.taskId}">工数登録</a></button>	
		</div>
		</div>	
	</div>
	<div class="task_details">
		<h3>工数ログ</h3>
		<div class="man-log">
			<c:if test="${empty manHoursList}">
				<p>工数は登録されていません。</p>
			</c:if>
			<c:if test="${not empty manHoursList}">
			<table>
				<tr>
					<th class="date">作業日</th>
					<th class="nowrap">担当者</th>
					<th class="nowrap">工数</th>
					<th>作業内容</th>
				</tr>
			<c:forEach var="m" items="${manHoursList}">
			<form method="POST" action="<c:url value='/Controller'/>">
				<tr>
					<input type="hidden" name="page_id" value="L007">
					<input type="hidden" name="man_hours_id" value="${m.manHoursId}">
					<input type="hidden" name="task_id" value="${task.taskId}">
					<td>${m.workDate}</td>
					<td>${m.name}</td>
					<td>${m.todayManHours}</td>
					<td class="memo">${m.workDetails}</td>
					<td><input type="submit" name="button_id" value="削除" class="btn"><td>
				</tr>
			</form>
			</c:forEach>
			</table>
			</c:if>
		</div>
	</div>
	<!------------以下モーダル表示---------->
	<!--タスク編集モーダル-->
	<div id="modal2" class="modal_background">
    <div class="r_modal">
      <h2>タスク編集</h2>
      <form method="POST" action="<c:url value='/Controller'/>" id="conform">
        <div class="modal-contents-2">
          <div class="modal_left">
            <div class="form-group">
              <label for="case_name">案件名</label>
              <select name="case_id" id="case_name" required>
                <c:forEach var="c" items="${casesList}">
                  <option value="${c.caseId}">${c.caseName}</option>
                </c:forEach>
              </select>
            </div>
            <div class="form-group">
              <label>タスク名</label>
              <input type="text" name="task_name" required>
            </div>
            <div class="form-group">
              <label for="manager">担当者</label>
              <select name="manager" id="manager">
                <c:forEach var="m" items="${activeUsersList}">
                  <option value="${m.userId}">${m.name}</option>
                </c:forEach>
              </select>
            </div>
            <div class="form-group">
              <label>開始日</label>
              <input type="date" name="start_date">
            </div>
            <div class="form-group">
              <label>期限</label>
              <input type="date" name="deadline_date">
            </div>
          </div>
          <div>
        	<div class="form-group">
              <label>進捗率</label>
              <input type="number" min="0" max="100" step="1" name="progress">
            </div>
            <div class="form-group">
              <label for="priority">優先度</label>
              <select name="priority" id="priority">
                <option value="高">高</option>
                <option value="中" selected>中</option>
                <option value="低">低</option>
              </select>
            </div>
            <div class="form-group">
              <label>見積工数</label>
              <input type="number" min="0" step="0.5" name="estimated_man_hours">
            </div>
            <div class="form-group">
              <label for="status">ステータス</label>
              <select name="status" id="status" required>
                <option value="未着手" selected>未着手</option>
                <option value="進行中">進行中</option>
                <option value="完了">完了</option>
                <option value="保留">保留</option>
              </select>
            </div>
            <div class="form-group">
              <label>説明</label>
              <textarea name="memo"></textarea>
            </div>  
          </div>
        </div>
        <input type="hidden" name="page_id" value="L006">
        <input type="hidden" name="task_id">
        <input type="submit" name="button_id" value="保存" class="btn">
      </form>
      <button class="close" onclick="closeModal2()">×</button>
		</div>
	</div>
</div>
<script src="https://cdn.datatables.net/t/bs-3.3.6/jqc-1.12.0,dt-1.10.11/datatables.min.js"></script>
<script src="<c:url value='js/task_detail.js'/>"></script>
</main>
</body>
</html>