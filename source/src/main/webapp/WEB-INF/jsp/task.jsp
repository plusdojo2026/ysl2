
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>タスク一覧</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css" />
	<link rel="stylesheet" href="<c:url value='/css/menu.css' />">
	<link rel="stylesheet" href="<c:url value='/css/task.css' />">
	<link rel="stylesheet" href="https://cdn.datatables.net/t/bs-3.3.6/jqc-1.12.0,dt-1.10.11/datatables.min.css"/>
 	<script src="https://cdn.datatables.net/t/bs-3.3.6/jqc-1.12.0,dt-1.10.11/datatables.min.js"></script>
	 <script>
	    jQuery(function($){
	    	 // デフォルトの設定を変更（日本語化）--------------------
	        $.extend( $.fn.dataTable.defaults, {
	            language: {
	                url: "http://cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/Japanese.json"
	            }
	        });
	    	 //------------------------------------------------
	    	//データテーブルを使用
	        $("#foo-table").DataTable();
	    });
		 // モーダル表示１
	    function openModal() {
	      document.getElementById("modal").style.display = "block";
	    }

	    // モーダル非表示１
	    function closeModal() {
	      document.getElementById("modal").style.display = "none";
	    }
	    // モーダル表示２
	    function openModal2(caseName,taskName,manager,deadlineDate,estimatedManHours,actualManHours,taskStatus,taskPriority,taskMemo) {
	     	//編集モーダルの全体をformという名前とする
	    	const form = document.getElementById('conform');
	     	//そのformの中のname="task_name"のところに値を入れる（引数）
	     	form.elements['task_name'].value = taskName;
	     	//他にもたくさんあるけど後はよろしく。モーダルの整形もお願いね
	    	document.getElementById("modal2").style.display = "block";
	    }

	    // モーダル非表示２
	    function closeModal2() {
	      document.getElementById("modal2").style.display = "none";
	    }
	 </script>
</head>
<body>
<main>
	<h1>タスク一覧</h1>
	<div>${msg}</div>
	<div>
	<%-- <form method="POST" action="<c:url value='/Controller'/>">
		<input type="hidden" name="page_id" value="L006">
		<input type="text" name="seach_word" placeholder="キーワード検索">
		<select name="case_name">
			<c:forEach var="c" items="${allTaskList}">
				<option value="${c.caseId}">${c.caseName}</option>
			</c:forEach>
		</select>
		<select name="manager">
			<c:forEach var="m" items="">
				<option value="${m.userId}">${c.userName}</option>
			</c:forEach>
		</select>
		<select name="status">
			<option value="">ステータス</option>
			<option value="未着手">未着手</option>
			<option value="進行中">進行中</option>
			<option value="完了">完了</option>
			<option value="保留">保留</option>
		</select>
		<input type="submit" name="button_id" value="検索">
	</form>
	</div> --%>

	<div>
		<button onclick="openModal()">新規登録</button>
		
	</div>

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
				<th>説明</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="t" items="${allTasksList}">
			<tr>
				<td>${t.caseName}</td>
				<td>
					<a href="/Controller/?page_id=L006&button_id=task_link&task_id=${t.taskId}">${t.taskName}</a>
				</td>
				<td>${t.manager}</td>
				<td>${t.deadlineDate}</td>
				<td>${t.estimatedManHours}</td>
				<td>${t.actualManHours}</td>
				<td>${t.taskStatus}</td>
				<td>${t.taskPriority}</td>
				<td>${t.taskMemo}</td>
				<td><button onclick="openModal2('${t.caseName}','${t.taskName}','${t.manager}','${t.deadlineDate}','${t.estimatedManHours}','${t.actualManHours}','${t.taskStatus}','${t.taskPriority}','${t.taskMemo}')">編集</button></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</div>

    <!----------------以下モーダル表示--------------->
    <!--タスク登録モーダル-->
	<div id="modal" class="modal-background">
	    <div class="modal-content">
			<h2>タスク登録</h2>
		    <form method="POST" action="<c:url value='/Controller'/>">
		        <input type="hidden" name="page_id" value="L006">
				<table class = "new_task" border="1">
					<tr>
						<td colspan="2">
							<label for="case_name">案件名</label>
							<select name="case_name" id="case_name" required>
							<c:forEach var="c" items="${casesList}">
								<option value="${c.caseId}">${c.caseName}</option>
							</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<label>タスク名
		           				<input type="text" name="task_name" required>
		       				</label>
		       			</td>
		       		</tr>
					<tr>
						<td>
							<label for="manager">担当者</label>
							<select name="manager" id="manager">
								<c:forEach var="m" items="${activeUsersList}">
									<option value="${m.userId}">${c.userName}</option>
								</c:forEach>
							</select>
						</td>
						<td>
							<label>進捗率
					            <input type="number" min="0" max="100" step="1" name="task_progress">
					        </label>
						</td>
					</tr>
		        	<tr>
		        		<td>
		        			<label>開始日<input type="date" name="start_date"></label>
					        
		        		</td>
		        		<td>
		        			<label>期限
					   		    <input type="date" name="deadline_date">
					        </label>
		        		</td>
		        	</tr>
		        	<tr>
		        		<td>
			        		<label for="priority">優先度</label>
					        <select name="priority" id="priority">
					            <option value="高">高</option>
					            <option value="中" selected>中</option>
					            <option value="低">低</option>
					        </select>
		        		</td>
		        		<td>
		        			<label>見積工数
					            <input type="number" min="0" max="24" step="0.5" name="estimated_man_hours">
					        </label>
		        		</td>
		        	</tr>
		        	<tr>
		        		<td>
		        			<label for="status">ステータス</label>
							<select name="status" required>
								<option value="未着手" selected>未着手</option>
								<option value="進行中">進行中</option>
								<option value="完了">完了</option>
								<option value="保留">保留</option>
							</select>
						</td>
		        		<td>
							 <label>進捗率
					            <input type="number" min="0" max="100" step="1" name="progress">
					        </label>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<label>説明<br><textarea name="memo"></textarea></label>
						</td>
					</tr>
		  			<tr>
		  				<td>
		  					<button class="close-btn" onclick="closeModal()">戻る</button>
		  				</td>
		  				<td>
		  					<input type="submit" name="button_id" value="登録">
		  				</td>
		  			</tr>	
				</table>
		    </form>
		</div>
	</div>

	<!--タスク編集モーダル-->
	<div id="modal2" class="modal-background2">
	    <div class="modal-content2">
			<h2>タスク編集</h2>
		    <form method="POST" action="<c:url value='/Controller'/>" id="conform">
		        <input type="hidden" name="page_id" value="L006">
				<p>
				<label for="case_name">案件名<br></label>
				<select name="case_name" id="case_name" required>
					<c:forEach var="c" items="${casesList}">
						<option value="${c.caseId}">${c.caseName}</option>
					</c:forEach>
				</select>
				</p>
				<p>
		        <label>タスク名<br>
		            <input type="text" name="task_name" required>
		        </label>
				</p>
				<p>
		        <label for="manager">担当者<br></label>
				<select name="manager" id="manager">
					<c:forEach var="m" items="${activeUserList}">
						<option value="${m.userId}">${m.userName}</option>
					</c:forEach>
				</select>
				</p>
				<p>
		        <label>進捗率<br>
		            <input type="number" min="0" max="100" step="1" name="progress">
		        </label>
				</p>
				<p>
		        <label>開始日<br>
		            <input type="date" name="start_date">
		        </label>
				</p>
				<p>
		        <label>期限<br>
		        <input type="date" name="deadline_date">
		        </label>
				</p>
				<p>
		        <label for="priority">優先度<br></label>
		        <select name="priority" id="priority" required>
		            <option value="高">高</option>
		            <option value="中" selected>中</option>
		            <option value="低">低</option>
		        </select>
				</p>
				<p>
		        <label>見積工数<br>
		            <input type="number" min="0" max="24" step="0.5" name="estimated_man_hours">
		        </label>
				</p>
				<p>
				<label for="status">ステータス<br></label>
				<select name="status" required>
					<option value="未着手" selected>未着手</option>
					<option value="進行中">進行中</option>
					<option value="完了">完了</option>
					<option value="保留">保留</option>
				</select>
				</p>
				<p><label>説明<input type="textarea" name="memo"></label></p>
				<input type="submit" name="button_id" value="保存">
				<input type="button" value="戻る">
		    </form>
		</div>
	</div>
</main>
</body>
</html>