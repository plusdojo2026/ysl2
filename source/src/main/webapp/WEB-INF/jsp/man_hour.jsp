<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>工数登録</title>
<link rel="stylesheet" href=>
</head>
<body>
<div class="main">
<c:forEach var="" items="${}" >
<form method="POST" action="<c:url value='/Controller'/>">
	<div clas="pop">
		<p>${c.case_name}</p>
		<p>${t.task_name}</p>
	</div>	
	<div clas="man_h">
	<label>作業日<br>
		<input type="date" name="work_date" /><br>
	</label>	
	<label>工数<br>
		<input id="myNumber" name="today_man_hours" type="number" step="0.5" min="0" max="24"/><br>
	</label>
	<label>作業内容<br>
		<input type="text" name="work_details" /><br>
	</label>
	<input type="hidden" name="page_id" value="L005">
	<input type="submit" name="button_id" value="登録" />
	</div>
</form>
</c:forEach>
</div>
</body>
</html>