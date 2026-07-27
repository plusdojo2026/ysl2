<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!doctype html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>案件詳細</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css" />
    <link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/case_detail.css" />
    
  </head>
  <body>
  <main>
   <%@ include file="/WEB-INF/jsp/sidebar.jsp" %>
   <div>
    <h1>案件詳細</h1>
    <input type="hidden" name="page_id" value="L003" />
    <div class="case_detail">
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
         <td>進捗バー</td>
          <!-- <td>${taskProgress}</td> -->
        </tr>
        <tr>
          <td></td>
          <td></td>
          <td>ステータス</td>
          <td>${dedto.caseStatus}</td>
        </tr>
      </table>
       <button onclick="openEditModal('${dedto.caseId}','${dedto.casePriority}','${dedto.caseName}',
       '${dedto.caseStartDate}','${dedto.endDate}','${dedto.customerName}','${dedto.budgetedManHours}',
       '${dedto.name}','${dedto.caseMemo}','${dedto.caseStatus}',)">編集</button>
       
      <input type="submit" name="button_id" value="完了" />
      <input type="submit" name="button_id" value="中止" />
    </div>
    

    <div class="case_task_list">
      <table>
        <h3>案件タスク一覧</h3>
        <tr>
          <th>タスク名</th>
          <th>担当者</th>
          <th>ステータス</th>
          <th>優先度</th>
          <th>期限</th>
          <th>見積</th>
          <th>実績工数</th>
          <th>進捗バー</th>
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
          </tr>
        </c:forEach>
      </table>
      <button onclick="openRegistModal()">+タスク追加</button>
    </div>

    <div class="manhour_log">
      <h3>工数ログ(最新10件)</h3>
      <table>
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
 
 
 
 
    <div>
    <div id="case_regist_modal" class="modal_background">
    <div class="r_modal">
      <h2>タスク登録</h2>
      <form method="POST" action="<c:url value='/Controller'/>">
        <input type="hidden" name="page_id" value="L003" />
        <div class=casedetail_modal>
        <p>
          <label for="case_name">案件名*<br /></label>
          <select name="case_name" id="case_name">
            <c:forEach var="c" items="${caseList}">
              <option value="${c.caseId}">${c.caseName}</option>
            </c:forEach>
          </select>
        </p>
        <p>
          <label
            >タスク名*<br />
            <input type="text" name="task_name" />
          </label>
        </p>
        <p>
          <label for="manager">担当者<br /></label>
          <select name="manager" id="manager">
            <c:forEach var="m" items="${userList}">
              <option value="${m.userId}">${c.userName}</option>
            </c:forEach>
          </select>
        </p>
        <p>
          <label
            >進捗率<br />
            <input
              type="number"
              min="0"
              max="100"
              step="1"
              name="task_progress"
            />
          </label>
        </p>
        <p>
          <label
            >開始日<br />
            <input type="date" name="start_date" />
          </label>
        </p>
        <p>
          <label
            >期限<br />
            <input type="date" name="deadline_date" />
          </label>
        </p>
        <p>
          <label for="priority">優先度*<br /></label>
          <select name="priority" id="priority">
            <option value="高">高</option>
            <option value="中" selected>中</option>
            <option value="低">低</option>
          </select>
        </p>
        <p>
          <label
            >見積工数<br />
            <input
              type="number"
              min="0"
              max="24"
              step="0.5"
              name="estimated_mon_hours"
            />
          </label>
        </p>
        <p>
          <label for="status">ステータス*<br /></label>
          <select name="status">
            <option value="未着手" selected>未着手</option>
            <option value="進行中">進行中</option>
            <option value="完了">完了</option>
            <option value="保留">保留</option>
          </select>
        </p>
        </div>
        <input type="submit" name="button_id" value="保存" />
        <button class="close" onclick="closeRModal()">戻る</button>
      </form>
    </div>
    </div>
	</div>
    
    
    
    
    
	<div id="edit_modal" class="modal_background">
    <div class="e_modal">
        <h2>案件編集</h2>
		<form method="POST" action="<c:url value='/Controller'/>">
        <input type="hidden" name="page_id" value="L004" />
		<p>
			<label for="">案件コード*<br /></label>
			<input type="text" name="case_id">
		</p>
		<p>
			<label for="case_name">案件名*<br /></label>
			<input type="text" name="case_name"> 
		</p>
		<p>
			<label for="case_name">顧客名<br /></label>
			<input type="text" name="customer_name"> 
		</p>
		<p>
			<label for="manager">担当PM<br /></label>
          <select name="pm_id" id="pm_id" required>
            <c:forEach var="m" items="${userList}">
              <option value="${m.userId}">${m.name}</option>
            </c:forEach>
		  </select>
		</p>
		<p>
			<label for="memo">説明<br /></label>
			<input type="text" name="memo">
		</p>
		<p>
			<label
            >開始日<br />
            <input type="date" name="start_date" />
          </label>
		</p>
		<p>
		<label
            >終了予定日<br />
            <input type="date" name="end_date" />
          </label>
		</p>
		<p>
          <label for="priority">優先度*<br /></label>
          <select name="priority" id="priority">
            <option value="高">高</option>
            <option value="中" selected>中</option>
            <option value="低">低</option>
          </select>
        </p>
		 <p>
          <label
            >予算工数<br />
            <input
              type="number"
              min="0"
              max="24"
              step="0.5"
              name="budgetted_man_hours"
            />
          </label>
        </p>
		 <p>
          <label for="status">ステータス*<br /></label>
          <select name="status">
            <option value="未着手" selected>未着手</option>
            <option value="進行中">進行中</option>
            <option value="完了">完了</option>
            <option value="保留">保留</option>
          </select>
        </p>
		<input type="submit" name="button_id" value="保存">
		
		<button class="close" onclick="closeEModal()">戻る</button>
	</form>
	</div>
    </div>
    
       <script src="${pageContext.request.contextPath}/js/case_detail.js"></script>
   </main>
  </body>
</html>
