<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="UTF-8">
<title>案件一覧</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/common.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/case.css" />
</head>
<body>
	<main>
		<%@ include file="/WEB-INF/jsp/sidebar.jsp"%>
		<div class="case">
			<h1>案件一覧</h1>
			<form method="POST" action="<c:url value='/Controller'/>"> 
        <input type="hidden" name="page_id" value="L004"> 
        <input type="text" name="case_search" value="検索"> 
        <select class="priority_select">
          <option value="高">高</option>
          <option value="中">中</option>
          <option value="低">低</option>
        </select> 
        <select class="status_search">
          <option value="進行中">進行中</option>
          <option value="完了">完了</option>
          <option value="中止">中止</option>
        </select> 
        <input type="button" name="search_button" value="検索">
        <button onclick="openRegistModal()">+新規登録</button>
      </form>

			<hr>

      <div>
        <table border="1" id="foo-table" class="table table-bordered">
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
              <th>メモ</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="e" items="${caseList}">
              <tr>
                <td>
                  <a href="${pageContext.request.contextPath}/Controller?page_id=L004&button_id=case_link&case_id=${e.caseId}">${e.caseId}</a>
                </td>
                <td>${e.caseName}</td>
                <td>顧客:${e.customerName}</td>
                <td>優先度${e.casePriority}</td>
                <td>PM:${e.name}</td>
                <td>タスク進捗:${e.completedTasks}/${e.allTasks}</td>
                <td>実績工数:${e.actualManHours}</td>
                <td>ステータス${e.caseStatus}</td>
                <td>開始日:${e.caseStartDate}</td>
                <td>終了日:${e.endDate}</td>
                <td>${e.caseMemo}</td>
              </tr>
              <button onclick="openEditModal('${e.caseName}','${e.customerName}','${e.caseId}','${e.name}','${e.caseMemo}','${e.caseStartDate}','${e.endDate}','${e.casePriority}','${e.budgetedManHours}','${e.caseStatus}')">編集</button>
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
          <input type="hidden" name="page_id" value="L004" />
          <div class="case_modal">
            <div>
              <label for="">案件コード*<br/></label> 
              <input type="text" name="case_id">
            </div>
            <div>
              <label for="case_name">案件名*<br/></label> 
              <input type="text" name="case_name">
            </div>
            <div>
              <label for="case_name">顧客名<br/></label> 
              <input type="text" name="customer_name">
            </div>
            <div>
              <label for="manager">担当PM<br/></label> 
              <select name="pm_id" id="pm_id">
                <c:forEach var="m" items="${userList}">
                  <option value="${m.userId}">${m.name}</option>
                </c:forEach>
              </select>
            </div>
            <div>
              <label for="memo">説明<br/></label> 
              <input type="text" name="memo">
            </div>
            <div>
              <label>開始日<br/></label>
              <input type="date" name="start_date"/>
            </div>
            <div>
              <label>終了予定日<br/></label>
              <input type="date"name="end_date"/>
            </div>
            <div>
              <label for="priority">優先度*<br/></label> 
              <select name="priority" id="priority">
                <option value="高">高</option>
                <option value="中" selected>中</option>
                <option value="低">低</option>
              </select>
            </div>
            <div>
              <label>予算工数<br/></label>
              <input type="number" min="0" max="24" step="0.5" name="budgeted_man_hours" value="0" />
            </div>
            <div>
              <label for="status">ステータス*<br/></label> 
              <select name="status">
                <option value="未着手" selected>未着手</option>
                <option value="進行中">進行中</option>
                <option value="完了">完了</option>
                <option value="保留">保留</option>
              </select>
            </div>
          </div>
          <input type="submit" name="button_id" value="登録">
        </form>
        <button class="close" onclick="closeRModal()">戻る</button>
      </div>
    </div>

    <!-- 案件編集モーダル -->
    <div id="edit_modal" class="modal_background">
      <div class="e_modal">
        <h2>案件編集</h2>
        <form method="POST" action="<c:url value='/Controller'/>" id="edit">
          <input type="hidden" name="page_id" value="L004"/>
          <div class=case_modal>
            <div>
              <label for="case_id">案件コード*<br/></label> 
              <input type="text" name="case_id">
            </div>
            <div>
              <label for="case_name">案件名*<br/></label> 
              <input type="text" name="case_name">
            </div>
            <div>
              <label for="case_name">顧客名<br/></label> 
              <input type="text" name="customer_name">
            </div>
            <div>
              <label for="manager">担当PM<br/></label> 
              <select name="pm_id" id="pm_id" required>
                <c:forEach var="m" items="${userList}">
                  <option value="${m.userId}">${m.name}</option>
                </c:forEach>
              </select>
            </div>
            <div>
              <label for="memo">説明<br/></label> 
              <input type="text" name="memo">
            </div>
            <div>
              <label>開始日<br/> 
                <input type="date" name="start_date"/>
              </label>
            </div>
            <div>
              <label>終了予定日<br/></label>
              <input type="date" name="end_date"/>
            </div>
            <div>
              <label for="priority">優先度*<br/></label> 
              <select name="priority" id="priority">
                <option value="高">高</option>
                <option value="中" selected>中</option>
                <option value="低">低</option>
              </select>
            </div>
            <div>
              <label>予算工数<br/></label>
              <input type="number" min="0" max="24" step="0.5" name="budgetted_man_hours" />
            </div>
            <div>
              <label for="status">ステータス*<br/></label> 
              <select name="status">
                <option value="未着手" selected>未着手</option>
                <option value="進行中">進行中</option>
                <option value="完了">完了</option>
                <option value="保留">保留</option>
              </select>
            </div>
          </div>
          <input type="submit" name="button_id" value="保存">
        </form>
        <button class="close" onclick="closeEModal()">戻る</button>
      </div>
    </div>

    <script src="${pageContext.request.contextPath}/js/case.js"></script>
	</main>
</body>
</html>