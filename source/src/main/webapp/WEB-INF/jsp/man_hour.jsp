<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>工数登録</title>
<link rel="stylesheet" href= "/css/man_hour.css">
</head>
<body>

<div class="main">
<%@ include file="/WEB-INF/jsp/sidebar.jsp" %>
<c:forEach >
	<div clas="pop">
		<p>${case_name}</p>
		<p>${task_name}</p>
	</div>	
	<form method="POST" action="/Controller">
	<div clas="man_h">
	<label>作業日<br>
		<input type="date" name="work_date"><br>
	</label>	
	<label>工数<br>
		<input id="myNumber" name="today_man_hours" type="number" step="0.5" min="0" max="24"/><br>
	</label>
	<label>作業内容<br>
		<input type="text" name="work_details" ><br>
	</label>
	
	<input type="hidden" name="page_id" value="L005">
	<input type="submit" name="button_id" value="登録" >
	</form>
	</div>

</c:forEach>
</div>
</body>
</html>