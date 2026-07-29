
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>タスク一覧 | TaskManager</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css" />
	<link rel="stylesheet" href="<c:url value='/css/task.css' />">
	<link rel="stylesheet" href="https://cdn.datatables.net/t/bs-3.3.6/jqc-1.12.0,dt-1.10.11/datatables.min.css"/>
</head>
<body>
<main>
<%@ include file="/WEB-INF/jsp/sidebar.jsp"%>
<div class="main">
	<h1>タスク一覧</h1>
	<div>${msg}</div>

	<p>
		<button onclick="openModal()" class="btn">
      		<svg class="icon" xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-plus" viewBox="0 0 16 16">
  			<path d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4"/></svg>
  			新規登録
		</button>
	</p>

	<div>
	<table border="1" id="foo-table" class="table table-bordered">
		<thead>
			<tr>
				<th>案件名</th>
				<th>タスク</th>
				<th>担当者</th>
				<th>期限</th>
				<th>見積工数</th>
				<th>実績工数</th>
				<th>ステータス</th>
				<th>優先度</th>
				<th>進捗率</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="t" items="${allTasksList}">
			<tr id="task">
				<td>${t.caseName}</td>
				<td>
					<a href="${pageContext.request.contextPath}/Controller?page_id=L006&button_id=task_link&task_id=${t.taskId}">${t.taskName}</a>
				</td>
				<td>${t.name}</td>
				<td class="nowrap" id="deadlinedate">
					<c:if test="${empty t.deadlineDate}">未設定</c:if>
					${t.deadlineDate}
				</td>
				<td>${t.estimatedManHours}</td>
				<td>${t.actualManHours}</td>
				<td>${t.taskStatus}</td>
				<td>${t.taskPriority}</td>
				<td>${t.taskProgress}%</td>
				<td><button class="btn" onclick="openModal2('${t.caseId}','${t.taskId}','${t.taskName}','${t.manager}','${t.taskStartDate}','${t.deadlineDate}','${t.estimatedManHours}','${t.actualManHours}','${t.taskStatus}','${t.taskPriority}','${t.taskMemo}','${t.taskProgress }')">編集</button></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</div>
</div>

<!----------------以下モーダル表示--------------->
<!--タスク登録モーダル-->
<div id="modal" class="modal_background">
  <div class="r_modal">
    <h2>タスク登録</h2>
      <form method="POST" action="<c:url value='/Controller'/>">
        <div class="modal-contents-2">
          <div class="modal_left">
            <div class="form-group">
              <label for="case_name">案件名*</label>
              <select name="case_id" id="case_name" required>
                <c:forEach var="c" items="${casesList}">
                  <option value="${c.caseId}">${c.caseName}</option>
                </c:forEach>
              </select>
            </div>
            <div class="form-group">
              <label>タスク名*</label>
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
              <label>進捗率</label>
              <input type="number" min="0" max="100" step="1" name="task_progress" value="0">
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
              <label for="priority">優先度*</label>
              <select name="priority" id="priority">
                <option value="高">高</option>
                <option value="中" selected>中</option>
                <option value="低">低</option>
              </select>
            </div>
            <div class="form-group">
              <label>見積工数</label>
              <input type="number" min="0" step="0.5" name="estimated_man_hours" value="0">
            </div>
            <div class="form-group">
              <label for="status">ステータス*</label>
              <select name="status" id="status" required>
                <option value="未着手" selected>未着手</option>
                <option value="進行中">進行中</option>
                <option value="完了">完了</option>
                <option value="保留">保留</option>
              </select>
            </div>
            <div class="form-group">
              <label>進捗率</label>
              <input type="number" min="0" max="100" step="1" name="progress" value="0">
            </div>
            <div class="form-group">
              <label>説明</label>
              <textarea name="memo"></textarea>
            </div>
          </div>
        </div>
        <input type="hidden" name="page_id" value="L006">
        <input type="submit" name="button_id" value="登録" class="btn">
      </form>
      <button class="close" onclick="closeModal()">×</button>
  </div>
</div>

<!--タスク編集モーダル-->
<div id="modal2" class="modal_background">
  <div class="r_modal">
    <h2>タスク編集</h2>
    <form method="POST" action="<c:url value='/Controller'/>" id="conform">
      <div class="modal-contents-2">
        <div class="modal_left">
          <div class="form-group">
            <label>案件名*</label>
            <select name="case_id" id="case_name" required>
              <c:forEach var="c" items="${casesList}">
                <option value="${c.caseId}">${c.caseName}</option>
              </c:forEach>
            </select>
          </div>
          <div class="form-group">
            <label>タスク名*</label>
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
            <label>進捗率</label>
            <input type="number" min="0" max="100" step="1" name="progress">
          </div>
          <div class="form-group">
            <label>開始日</label>
            <input type="date" name="start_date">
          </div>
        </div>
        <div>
          <div class="form-group">
            <label>期限</label>
            <input type="date" name="deadline_date">
          </div>
          <div class="form-group">
            <label for="priority">優先度*</label>
            <select name="priority" id="priority" required>
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
            <label for="status">ステータス*</label>
            <select name="status" id="status" required>
              <option value="未着手" selected>未着手</option>
              <option value="進行中">進行中</option>
              <option value="完了">完了</option>
              <option value="保留">保留</option>
            </select>
          </div>
          <div class="form-group">
            <label>説明</label>
            <input type="textarea" name="memo">
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

<script src="https://cdn.datatables.net/t/bs-3.3.6/jqc-1.12.0,dt-1.10.11/datatables.min.js"></script>
<script src="${pageContext.request.contextPath}/js/task.js"></script>
</main>
</body>
</html>