<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ page import="java.time.LocalDate, java.time.format.DateTimeFormatter" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>月次集計</title>
	
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/manthly_sum.css" />
</head>
<body>
<main>
<%@ include file="/WEB-INF/jsp/sidebar.jsp" %>
<div class ="main">

	<form method="POST" action="<c:url value='/Controller'/>">
		<input type="month" name="month" value = "${yearManth}" required>
		<input type="hidden" name="page_id" value="L008"> 
	    <input type="submit" name = "button_id" value="集計" >
    </form>
    
    <div class = "total_mh">
	    <h3>合計工数</h3>
	    <c:set var = "totalmh" value = "0"/>
	    <c:forEach var="k" items="${ManthAndMembers}">
	    <c:set var = "totalmh" value = "${total + k.actualManHours}"/>
	    </c:forEach>
	    <c:out value ="${k.actualManHours}"/>
    </div>
    <div class = "total_case">
	    <h3>合計案件数</h3>
	    <p>${TotalCasesAndManHours.size()}</p>
    </div>
    <div class = "total_member">
	    <h3>合計稼働メンバー数</h3>
	    <p>${ManthAndMembers.size()}</p>
    </div>
    
    <div class = "case_table">
    	<h3>案件別集計テーブル</h3>
 			<table>
    			 <tr>
    				<th>案件コード</th>
      				<th>案件名</th>
      				<th>月実績工数</th>
      				<th>総実績工数</th>
      				<th>予算工数</th>
    			 </tr>
    			 <c:forEach var="c" items="${TotalCasesAndManHours}">
    			 <tr>
      				<td><c:out value = "${c.caseId}"/></td>
				    <td><c:out value = "${c.caseName}"/></td>
				    <td><c:out value = "${c.actualManHours}"/></td>
				    <td><c:out value = "${c.todayManHours}"/></td>
				    <td><c:out value = "${c.budgetedManHours}"/></td>
    			</tr>
    			</c:forEach>
   			 </table>
    </div>
    
    <div class = "user_table">
      <h3>メンバー別集計テーブル</h3>
    	<table>
    		<tr>
    			<td>担当者名</td>
    			<td>工数</td>
    			<td>進捗率</td> 		
    		</tr>
    		<c:forEach var="m" items="${ManthAndMembers}">
    		<tr>
    			<td><c:out value = "${m.name}"/></td>
    			<td><c:out value = "${m.actualManHours}"/></td>
    			<td>ここで進捗率表示</td>
    		</tr>
    		</c:forEach>
		</table>    
    </div>
    
<script src="${pageContext.request.contextPath}/js/manthly_sum.js"></script>
</main>
</div>
</body>
</html>