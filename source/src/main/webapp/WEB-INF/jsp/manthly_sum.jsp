<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>月次集計</title>
<link rel="stylesheet" action="/servlet/Controller">
</head>
<body>
<div class ="mon">
<c:forEach >

	<div>
		<input type="date" name="work_date" ><br>
	    <input type="submit" value="集計" ><br>
    </div>
    
    <a href="">CSV出力→</a>
    
    <div>
	    <label>工数</label>
	    <p>${all_man_hours}</p>
    </div>
    <div>
	    <label>案件数</label>
	    <p>${all_case_sum}</p>
    </div>
    <div>
	    <label>稼働メンバー数</label>
	    <p>${all_user_sum}</p>
    </div>
    
    <div>
    	<label>案件別集計テーブル</label>
    		<table>
    			 <thead>
    			 	<tr>
    			 		<th scope="col">案件コード</th>
      					<th scope="col">案件名</th>
      					<th scope="col">実績工数</th>
      					<th scope="col">予算工数</th>
      					<th scope="col">達成率プログレスバー</th>
    			 	</tr>
    			 </thead>
    			 <tbody>
    			 	<tr>
      					<th scope="row">${case_id}</th>
					    <td>${case_name}</td>
					    <td>${}</td>
					    <td>${budgeted_man_hours}</td>
					    <td>${}</td>
    				</tr>
    			 </tbody>
    		</table>
    </div>
    <div>
    	<label>メンバー別集計テーブル</label>
    		<table>
    			 <thead>
    			 	<tr>
    			 		<th scope="col">担当者名</th>
      					<th scope="col">工数</th>
      					<th scope="col">割合プログレスバー</th>
    			 	</tr>
    			 </thead>
    			 <tbody>
    			 	<tr>
      					<th scope="row">${}</th>
					    <td>${}</td>
					    <td>${}</td>
    				</tr>
    			 </tbody>
    		</table>
    </div>
</c:forEach>
</div>
</body>
</html>