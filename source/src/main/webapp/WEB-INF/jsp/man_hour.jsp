<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>工数登録 | Task Manager</title>
<link rel="stylesheet" href="https://cdn.datatables.net/t/bs-3.3.6/jqc-1.12.0,dt-1.10.11/datatables.min.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/man_hour.css" />
</head>
<body>
<main>
<%@ include file="/WEB-INF/jsp/sidebar.jsp" %>
<div class="main">
<div class="kousu">
<h1>工数登録</h1>
</div>
    <div class="pop" >
    	<div class="case">
	    <p>案件名: ${list.caseName}</p>
	    </div>
	    <div class="task">
	    <p>タスク名: ${list.taskName}</p>
		</div>
	</div>
	<form method="POST" action="<c:url value='/Controller'/>">
    <a href="${pageContext.request.contextPath}/Controller"></a>

        <div class="man_h">
        
            <label>
                作業日<br>
                <input type="date" name="work_date" id="today" required>
            </label>
            <br>
            <div class="pulus" id="pulus">
            <label>
                工数<br>
                <input 
                       name="today_man_hours"
                       type="number"
                       step="0.5"
                       min="0"
                       max="24"
                       required>
            </label>
            <br>

            <label>
                作業内容<br>
                <textarea name="work_details" class="detail"></textarea>
            </label>
            <br>
            </div>
            
            <input type="hidden" name="page_id" value="L005">
            <input type="hidden" name="task_id" value="${list.taskId}">
            <input type="submit" name="button_id" value="登録" class="bot" >
		
        </div>

    </form>
</div>
</main>
</body>
</html>

<script>
// 現在の日付を取得
const today = new Date();

// YYYY-MM-DD形式に変換
const formattedDate = today.getFullYear() + '-' +
String(today.getMonth() + 1).padStart(2, '0') + '-' +
String(today.getDate()).padStart(2, '0');

// 初期値として設定
document.getElementById("today").value = formattedDate;
</script>
