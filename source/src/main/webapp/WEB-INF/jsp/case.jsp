<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="UTF-8">
<title>案件一覧</title>
<link rel="stylesheet" 
  href="https://cdn.datatables.net/t/bs-3.3.6/jqc-1.12.0,dt-1.10.11/datatables.min.css"/>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/common.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/case.css" />
</head>
<body>
	<main>
		<%@ include file="/WEB-INF/jsp/sidebar.jsp"%>
		<div class="main">
		<div class="case">
			<h1>案件一覧</h1>
		<button onclick="openRegistModal()" class="btn">
      		<svg class="icon" xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-plus" viewBox="0 0 16 16">
  			<path d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4"/></svg>
  			新規登録
		</button>
      <p>${msg}</p>

      <div class="main">
        <table id="foo-table" class="table table-bordered">
          <thead>
            <tr>
              <th>案件コード</th>
              <th>案件名</th>
              <th>顧客</th>
              <th>優先度</th>
              <th>PM</th>
              <th>タスク進捗</th>
              <th>実績工数</th>
              <th>ステータス</th>
              <th>開始日</th>
              <th>終了日</th>
              <th></th>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="e" items="${caseList}">
              <tr>
                <td>
                  <a href="${pageContext.request.contextPath}/Controller?page_id=L004&button_id=case_link&case_id=${e.caseId}">${e.caseId}</a>
                </td>
                <td>${e.caseName}</td>
                <td>${e.customerName}</td>
                <td>${e.casePriority}</td>
                <td>${e.name}</td>
                <td>${e.completedTasks}/${e.allTasks}</td>
                <td>${e.actualManHours}</td>
                <td>${e.caseStatus}</td>
                <td class="nowrap">${e.caseStartDate}</td>
                <td class="nowrap">${e.endDate}</td>
               <input type="hidden" name= "pm_id" value="${e.pmId}">
                <td><button class="btn" onclick="openEditModal('${e.caseId}','${e.customerName}','${e.caseName}','${e.name}','${e.caseMemo}','${e.caseStartDate}','${e.endDate}','${e.casePriority}','${e.budgetedManHours}','${e.caseStatus}', '${e.pmId}')">編集</button></td>
              </tr>
            </c:forEach> 
          </tbody>
        </table>
      </div>
    </div>

    <!-- 案件登録モーダル -->
    <div id="case_regist_modal" class="modal_background">
      <div class="r_modal">
        <h2>案件登録</h2>
        <form method="POST" action="<c:url value='/Controller'/>">
          <div class="modal-contents-2">
            <div class="modal_left">
              <div class="form-group">
                <label for="">案件コード*</label> 
                <input type="text" name="case_id" required>
              </div>
              <div class="form-group">
                <label for="case_name">案件名*</label> 
                <input type="text" name="case_name" required>
              </div>
              <div class="form-group">
                <label for="case_name">顧客名</label> 
                <input type="text" name="customer_name">
              </div>
              <div class="form-group">
                <label>予算工数</label>
                <input type="number" min="0" max="24" step="0.5" name="budgeted_man_hours" value="0" />
              </div>
              <div class="form-group">
                <label for="memo">説明</label> 
                <input type="text" name="memo">
              </div>
            </div>
            <div>
              <div class="form-group">
                <label>開始日</label>
                <input type="date" name="start_date"/>
              </div>
              <div class="form-group">
                <label>終了予定日</label>
                <input type="date"name="end_date"/>
              </div>
              <div class="form-group">
                <label for="priority">優先度*</label> 
                <select name="priority" id="priority">
                  <option value="高">高</option>
                  <option value="中" selected>中</option>
                  <option value="低">低</option>
                </select>
              </div>
              <div class="form-group">
                <label for="manager">担当PM</label> 
                <select name="pm_id" id="pm_id">
                  <c:forEach var="m" items="${userList}">
                    <option value="${m.userId}">${m.name}</option>
                  </c:forEach>
                </select>
              </div>
              <div class="form-group">
                <label for="status">ステータス*</label> 
                <select name="status">
                  <option value="未着手" selected>未着手</option>
                  <option value="進行中">進行中</option>
                  <option value="完了">完了</option>
                  <option value="保留">保留</option>
                </select>
              </div>
            </div>
          </div>
          <input type="hidden" name="page_id" value="L004" />
          <input type="submit" name="button_id" value="登録">
        </form>
        <button class="close" onclick="closeRModal()">×</button>
      </div>
    </div>

    <!-- 案件編集モーダル -->
    <div id="edit_modal" class="modal_background">
      <div class="e_modal">
        <h2>案件編集</h2>
        <form method="POST" action="<c:url value='/Controller'/>" id="edit">
          <div class=modal-contents-2>
           <div class="modal_left">
            <div class="form-group">
              <label for="case_id">案件コード*</label> 
              <input type="text" name="case_id" required>
            </div>
            <div class="form-group">
              <label for="case_name">案件名*</label> 
              <input type="text" name="case_name" required>
            </div>
            <div class="form-group">
              <label for="case_name">顧客名</label> 
              <input type="text" name="customer_name">
            </div>
            <div class="form-group">
              <label for="manager">担当PM</label> 
              <select name="pm_id" id="pm_id" required>
                <c:forEach var="m" items="${userList}">
                  <option value="${m.userId}">${m.name}</option>
                </c:forEach>
              </select>
            </div>
            <div class="form-group">
              <label for="memo">説明</label> 
              <input type="text" name="memo">
            </div>
            
            </div>
           <div>
            <div class="form-group">
              <label>開始日</label>
              <input type="date" name="start_date"/>
            </div>
            <div class="form-group">
              <label>終了予定日</label>
              <input type="date" name="end_date"/>
            </div>
            <div class="form-group">
              <label for="priority">優先度*</label> 
              <select name="priority" id="priority">
                <option value="高">高</option>
                <option value="中" selected>中</option>
                <option value="低">低</option>
              </select>
            </div>
            <div class="form-group">
              <label>予算工数</label>
              <input type="number" min="0" max="24" step="0.5" name="budgeted_man_hours" />
            </div>
            <div class="form-group">
              <label for="status">ステータス*</label> 
              <select name="status">
                <option value="未着手" selected>未着手</option>
                <option value="進行中">進行中</option>
                <option value="完了">完了</option>
                <option value="保留">保留</option>
              </select>
            </div>
           </div>
          </div>
          <input type="hidden" name="page_id" value="L004"/>
          <input type="submit" name="button_id" value="保存">
        </form>
        <button class="close" onclick="closeEModal()">×</button>
      </div>
    </div>
    </div>

    <script src="https://cdn.datatables.net/t/bs-3.3.6/jqc-1.12.0,dt-1.10.11/datatables.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/case.js"></script>
	</main>
</body>
</html>