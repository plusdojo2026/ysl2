<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ダッシュボード|Task Manager</title>
</head>
<body>

<header> <%@ include file="/WEB-INF/jsp/sidebar.jsp" %> </header>
	
<div class="main">
		
<!-- ダッシュボード要素6つ（期限超過タスク数・担当タスク数・進行中案件数・期限超過タスク・担当タスク・進行中案件）を表示 -->
	<div class = "dash_boards"><!-- CSSで全カードをまとめるためのclass -->

	<!-- "3つのサマリーカード"をそれぞれカードで表示する -->
		<div class ="deadline_over_tasks_counts">
			<h3>期限超過タスク数</h3>
			<p>${overTasks}</p>
			
		</div>
		<div class ="my_tasks_counts">
			<h3>担当タスク数</h3>
			<p>${myTasks}</p>
		</div>	
		
		<div class ="now_cases_counts">
			<h3>進行中案件数</h3>
			<p>${cases}</p>
		</div>
	</div>
	<!-- 3つのサマリーカードここまで -->
	
	<!-- 下3つのカードでタスク内容を表示する。各カード内のデータの表示は、テーブルを用いる-->
	<!-- cタグを用いて超過分を一覧取得する"deadline_date"が前日の日付よりも古いタスクを絞り込む -->
		<div class ="deadline_over_tasks">
			<h3>期限超過タスク</h3>
		<!-- 項目 -->
		<table>
			<tr>
				<th>タスク名</th>
				<th>案件名</th>
				<th>開始日</th>
				<th>期限</th>
				<th>見積工数</th>
				<th>優先度</th>
			</tr>
			
			<c:forEach var="o" items="${overTaskList}">
			<!-- 変数"o" で一覧取得-->
			<!-- 期限超過分のタスクを一覧取得 -->
			<tr>
				<td><c:out value ="${o.taskName}"/></td>
				<td><c:out value ="${o.caseName}"/></td>
				<td><c:out value ="${o.taskStartDate}"/></td>
				<td><c:out value ="${o.deadlineDate}"/></td>
				<td><c:out value ="${o.estimatedManHours}"/></td>
				<td><c:out value ="${o.taskPriority}"/></td>				
			</tr>
			</c:forEach>
		</table>		
		</div>
		
		<div class ="my_tasks">		
			<h3>担当タスク</h3>
			<!-- 項目 -->
			<table>
			<tr>
				<th>タスク名</th>
				<th>案件名</th>
				<th>開始日</th>
				<th>期限</th>
				<th>見積工数</th>
				<th>優先度</th>
			</tr>
			
			<c:forEach var="m" items="${myTaskList}">
			<!-- 変数"m" で一覧取得-->
			<!-- "manager"が自分、つまりログイン中のセッションと一致するタスクを絞り込む -->
			<tr>
				<td><c:out value ="${m.taskName}"/></td>
				<td><c:out value ="${m.caseName}"/></td>
				<td><c:out value ="${m.taskStartDate}"/></td>
				<td><c:out value ="${m.deadlineDate}"/></td>
				<td><c:out value ="${m.estimatedManHours}"/></td>
				<td><c:out value ="${m.taskPriority}"/></td>		
			</tr>		
			</c:forEach>
			</table>
			<!-- 工数登録ボタン (ページIDとボタンIDを持たせる)-->
			<form method="POST" action="<c:url value='/Controller'/>">
				<input type="hidden" name="page_id" value="L002">
				<input type="submit" name="button_id" value="工数登録">
			</form>
			
		</div>
		
		<div class ="now_cases">
			<h3>進行中案件</h3>
			<table>
			<!-- 項目 -->
			<tr>
				<th>案件コード</th>
				<th>案件名</th>
				<th>顧客名</th>
				<th>担当者</th>
				<th>優先度</th>
			</tr>
			
			<c:forEach var="c" items="${caseList}">
			<!-- 変数"n" で一覧取得-->
			<!-- "status"が進行中の案件を絞り込む -->
			<tr>
				<td><c:out value="${c.caseId}"/></td>
				<td><c:out value="${c.caseName}"/></td>
				<td><c:out value="${c.customerName}"/></td>
				<td><c:out value="${c.name}"/></td>
				<td><c:out value="${c.casePriority}"/></td>		
			</tr>
			</c:forEach>
			</table>
		</div>
<!-- 6つのカードの表示ここまで -->
		
</div>

</body>
</html>