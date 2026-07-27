<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>工数登録</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/man_hour.css" />
</head>
<body>

<div class="main">
    <%@ include file="/WEB-INF/jsp/sidebar.jsp" %>

    <div class="pop" var="c" items="${list}">
	    <p>案件名:${c.caseName}</p>
	    <p>タスク名:${c.taskName}</p>
	</div>
	<form method="POST" action="<c:url value='/Controller'/>">
    <a href="${pageContext.request.contextPath}/Controller"></a>

        <div class="man_h">
        
            <label>
                作業日<br>
                <input type="date" name="work_date" required>
            </label>
            <br>

            <label>
                工数<br>
                <input id="todayManHours"
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
                <input type="text" name="work_details" required>
            </label>
            <br>

            <input type="hidden" name="page_id" value="L005">
            <input type="submit" name="button_id" value="登録">
		
        </div>

    </form>
</div>

</body>
</html>