<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<html>
<head>
	<meta charset="UTF-8">
	<title>案件一覧</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css" />
</head>
<body>
	<%@ include file="/WEB-INF/jsp/sidebar.jsp" %>
	<div class="case">
	<h1>案件一覧</h1>
	<from  method="POST" action="<c:url value='/Controller'/>">
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
		
		<input type="button" name="new_regist" value="+新規登録">
		
		<hr>
		<c:forEach var="e" items="${caseList}" >
			
		<tr>
				<td><a href="${pageContext.request.contextPath}/Controller?page_id=L004&button_id=case_link&caseId=${e.caseId}">${e.caseId}</a></td>
				<td>${e.caseName}</td>
				<td>顧客:${e.customerName}</td>
				<td>優先度${e.casePriority}</td>
				<td>PM:${e.name}</td>
				<td>タスク進捗:${e.completedTasks}/${e.allTasks}</td>
				<td>実績工数:${e.actualManHours}</td>
				<td>ステータス${e.caseStatus}</td>
				<td>開始日:${e.caseStartDate}</td>
				<td>終了日:${e.endDate}</td>
		</tr>	
		<input type="button" name="back_regist" value="編集">
		</c:forEach>
	</from>


	<div>
		<h2>案件登録</h2>
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
			 <select name="pm_id" id="pm_id">
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
              name="budgeted_man_hours"
              value="0"
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
		<input type="submit" name="button_id" value="登録">
		<input type="button" name="back_button" value="戻る">



	</div>
	
	<div>
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
		<input type="button" name="back_button" value="戻る">



	</div>
	
	</div>
</body>
</html>