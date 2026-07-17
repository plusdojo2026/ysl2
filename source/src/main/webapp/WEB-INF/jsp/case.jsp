<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>案件一覧</title>
</head>
<body>
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
		<c:forEach var="e" items="${}" >
			
		<tr>
				<td>${e.case_id}</td>
				<td>${e.case_name}</td>
				<td>顧客:${e.customer_name}</td>
				<td>優先度${e.priority}</td><br>
				<td>PM:${e.name}</td>
				<td>タスク進捗:${e.completed_tasks}/${e.all_tasks}</td>
				<td>実績工数:${e.actual_man_hours}</td>
				<td>ステータス${e.status}</td><br>
				<td>開始日:${e.start_date}</td>
				<td>終了日:${e.end_date}</td>
		</tr>	
		<input type="button" name="back_regist" value="編集">
		</c:forEach>
	</from>
	
	
	</div>
</body>
</html>