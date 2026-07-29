<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!doctype html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>案件詳細</title>
    <link rel="stylesheet" href="https://cdn.datatables.net/t/bs-3.3.6/jqc-1.12.0,dt-1.10.11/datatables.min.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/case_detail.css" />
  </head>
  <body>
  <main>
   <%@ include file="/WEB-INF/jsp/sidebar.jsp" %>
   <div class="main">
   <div class = "case_detail">
    <h1>案件詳細</h1>
    <input type="hidden" name="page_id" value="L003" />
    <div class="one_case_detail">
      <table>
        <h3>案件詳細</h3>
        <tr>
          <td>案件コード</td>
          <td>${dedto.caseId}</td>
          <td>優先度</td>
          <td>${dedto.casePriority}</td>
        </tr>

        <tr>
          <td>名称</td>
          <td>${dedto.caseName}</td>
          <td>期間</td>
          <td>${dedto.caseStartDate}~${dedto.endDate}</td>
        </tr>
        <tr>
          <td>顧客名</td>
          <td>${dedto.customerName}</td>
          <td>予算</td>
          <td>${dedto.budgetedManHours}</td>
        </tr>
        <tr>
          <td>担当PM</td>
          <td>${dedto.name}</td>
          <td>実績工数</td>
          <td>${dedto.actualManHours}</td>
        </tr>
        <tr>
          <td>説明</td>
          <td>${dedto.caseMemo}</td>
          <td>ステータス</td>
          <td>${dedto.caseStatus}</td>
        </tr>
        	<input type="hidden" name="pm_id" value="${dedto.pmId}">
      </table>
      <class="case_edit_button">
       <button onclick="openEditModal('${dedto.caseId}','${dedto.casePriority}','${dedto.caseName}',
       '${dedto.caseStartDate}','${dedto.endDate}','${dedto.customerName}','${dedto.budgetedManHours}',
       '${dedto.name}','${dedto.caseMemo}','${dedto.caseStatus}','${dedto.pmId}')" class="btn">編集</button>
       </class>
       
       <div class="case_button">
       <form action="Controller" method="post">
       <input type="hidden" name="page_id" value="L003" />
       <input type="hidden" name="case_id" value="${dedto.caseId}" >
      <button type="submit" name="button_id" value="完了" class="btn"/>完了</button>
      <button type="submit" name="button_id" value="中止" class="btn"/>中止</button>
       </form>
       </div>
    </div>
    </div>

    <div class="case_task_list">
      <table id="foo-table" class="table table-bordered">
        <h3>案件タスク一覧</h3>
        <tr>
          <th>タスク名</th>
          <th>担当者</th>
          <th>ステータス</th>
          <th>優先度</th>
          <th>期限</th>
          <th>見積</th>
          <th>実績工数</th>
          <th>進捗率</th>
        </tr>
        <c:forEach var="e" items="${taskList}">
          <tr>
            <th>${e.taskName}</th>
            <th>${e.name}</th>
            <th>${e.taskStatus}</th>
            <th>${e.taskPriority}</th>
            <th>${e.deadlineDate}</th>
            <th>${e.estimatedManHours}</th>
            <th>${e.actualManHours}</th>
            <th>${e.taskProgress}</th>
            <form action="Controller" method="post">
            <th><input type="submit" name="button_id" value="削除" class="btn"></th>
            <input type="hidden" name="task_id" value="${e.taskId}" >
            <input type="hidden" name="case_id" value="${dedto.caseId}">
            <input type="hidden" name="page_id" value="L003" />
       		</form>
          </tr>
        </c:forEach>
      </table>
      <button onclick="openRegistModal()" class="btn">+タスク追加</button>
    </div>

    <div class="manhour_log">
      <h3>工数ログ(最新10件)</h3>
      <table id="log-table">
      	  <tr>
      	  	<th>作業日
      	  	<th>タスク名
      	  	<th>担当者
      	  	<th>工数
      	  	<th>作業内容
      	  </tr>
        <c:forEach var="e" items="${manList}">
          <tr>
            <th>${e.workDate}</th>
            <th>${e.taskName}</th>
            <th>${e.manager}</th>
            <th>${e.todayManHours}</th>
            <th>${e.workDetails}</th>
          </tr>
        </c:forEach>
      </table>
    </div>
    </div>

    <!-- タスク追加モーダル -->
    <div id="case_regist_modal" class="modal_background">
    <div class="r_modal">
      <h2>タスク登録</h2>
      <form method="POST" action="<c:url value='/Controller'/>">
        <div class="modal-contents-2">
          <div class="modal_left">
            <div class="form-group">
              <label for="case_name">案件名*</label>
              <select name="case_id" id="case_name">
                <c:forEach var="c" items="${caseList}">
                  <option value="${c.caseId}"<c:if test="${c.caseId==dedto.caseId}">
                  selected</c:if> >${c.caseName}
                  </option>
                </c:forEach>
              </select>
            </div>
            <div class="form-group">
              <label>タスク名*</label>
              <input type="text" name="task_name" />
            </div>
            <div class="form-group">
              <label for="manager">担当者</label>
              <select name="manager" id="manager">
                <c:forEach var="m" items="${userList}">
                  <option value="${m.userId}"
                  >${m.name}</option>
                </c:forEach>
              </select>
            </div>
            <div class="form-group">
              <label>進捗率</label>
                <input type="number" min="0" max="100" step="1" name="progress"/>
            </div>
            <div class="form-group">
              <label>開始日</label>
                <input type="date" name="start_date"/>
            </div>
          </div>
          <div>
            <div class="form-group">
              <label>期限</label>
              <input type="date" name="deadline_date"/>
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
              <label>見積工数</label>
              <input type="number" min="0" max="24" step="0.5" name="estimated_man_hours"/>
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
            <div class="form-group">
            <label>説明</label>
            <input type="text" name="memo">
            </div>
          </div>
        </div>
        <input type="hidden" name="page_id" value="L003" />
        <input type="submit" name="button_id" value="登録" />
      </form>
      <button class="close" onclick="closeRModal()">×</button>
    </div>
  </div>

  <!-- 案件編集モーダル -->
	<div id="edit_modal" class="modal_background">
    <div class="e_modal">
      <h2>案件編集</h2>
      <form method="POST" action="<c:url value='/Controller'/>">
        <div class=modal-contents-2>
          <div class="modal_left">
            <div class="form-group">
              <label for="case_id">案件コード*</label>
              <input type="text" name="case_id" value="${dedto.caseId}" maxlength="20">
            </div>
            <div class="form-group">
              <label for="case_name">案件名*</label>
              <input type="text" name="case_name"value="${dedto.caseName}"> 
            </div>
            <div class="form-group">
              <label for="case_name">顧客名</label>
              <input type="text" name="customer_name" value="${dedto.customerName}"> 
            </div>
            <div class="form-group">
              <label for="manager">担当PM</label>
                <select name="pm_id" id="pm_id" required>
                  <c:forEach var="m" items="${userList}">
                    <option value="${m.userId}" 
                    	<c:if test="${m.userId==dedto.pmId}">
                    		selected
                    	</c:if>   >
                    	${m.name}
                    </option>
                  </c:forEach>
                </select>
              </div>
              <div class="form-group">
                <label for="memo">説明</label>
                <input type="text" name="memo" value="${dedto.caseMemo}">
              </div>
            </div>
            <div>
              <div class="form-group">
                <label>開始日</label>
                <input type="date" name="start_date" value="${dedto.caseStartDate}" />
              </div>
              <div class="form-group">
                <label>終了予定日</label>
                <input type="date" name="end_date" value="${dedto.endDate}"/>
              </div>
              <div class="form-group">
                <label for="priority">優先度*</label>
                <select name="priority" id="priority" value="${dedto.casePriority}">
                  <option value="高" <c:if test= "${dedto.casePriority=='高'}">selected</c:if>>高</option>
                  <option value="中" <c:if test= "${dedto.casePriority=='中'}">selected</c:if>>中</option>
                  <option value="低" <c:if test= "${dedto.casePriority=='低'}">selected</c:if>>低</option>
                </select>
              </div>
              <div class="form-group">
                <label>予算工数</label>
                <input type="number" min="0" max="24" step="0.5" name="budgeted_man_hours" value="${dedto.budgetedManHours}"/>
              </div>
            <div class="form-group">
              <label for="status">ステータス*</label>
              <select name="status" value="${dedto.caseStatus}">
                <option value="未着手" <c:if test= "${dedto.caseStatus=='未着手'}">selected</c:if>>未着手</option>
                <option value="進行中" <c:if test= "${dedto.caseStatus=='進行中'}">selected</c:if>>進行中</option>
                <option value="完了"   <c:if test= "${dedto.caseStatus=='完了'}">selected</c:if>>完了</option>
                <option value="保留"   <c:if test= "${dedto.caseStatus=='保留'}">selected</c:if>>保留</option>
              </select>
            </div>
          </div>
        </div>
        <input type="hidden" name="page_id" value="L003" />
        <input type="submit" name="button_id" value="変更">
      </form>
      <button class="close" onclick="closeEModal()">×</button>
      </div>
    </div>
    <script src="${pageContext.request.contextPath}/js/case_detail.js"></script>
  </main>
  </body>
</html>
