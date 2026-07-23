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
		<c:forEach var="e" items="${caseList}" >
			
		<tr>
				<td><a href="/Controller/?page_id=L004&button_id=case_link&case_id=${e.case_id}">${e.case_id}</a></td>
				<td>${e.case_name}</td>
				<td>顧客:${e.customer_name}</td>
				<td>優先度${e.priority}</td>
				<td>PM:${e.name}</td>
				<td>タスク進捗:${e.completed_tasks}/${e.all_tasks}</td>
				<td>実績工数:${e.actual_man_hours}</td>
				<td>ステータス${e.status}</td>
				<td>開始日:${e.start_date}</td>
				<td>終了日:${e.end_date}</td>
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
          <select name="manager" id="manager">
            <c:forEach var="m" items="">
              <option value="${m.user_id}">${e.user_name}</option>
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
		<input type="submit" name="button_id" value="登録">
		<input type="button" name="back_button" value="戻る">



	</div>
	
	
	</div>
</body>
</html>