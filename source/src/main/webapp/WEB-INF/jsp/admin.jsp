<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メンバー管理 | TaskManager</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css" />
    <link rel="stylesheet" href="<c:url value='/css/admin.css' />">
</head>
<body>
<header> <!-- sidebar.jspをインクルードする --> </header>
<div class ="main">
	<h1>メンバー管理（管理者画面）</h1>
		
		<form method="POST" action="<c:url value='/Controller'/>">
			<!-- 検索 -->
			<input type="hidden" name="page_id" value="L009">
			<input type="text" name="member_search">
			<input type="submit" name="button_id" value = "検索">
		
			<!-- 並び替え -->
			<label for ="fillter">絞り込み(ユーザー状態)</label>
			<select name ="fillter">
				<option value ="有効">有効</option>
				<option value ="無効">無効</option>
			</select>
		</form>
		
		<!-- +新規登録モーダルを表示 -->
		<button onclick = "openRegistModal()" >+新規登録</button>
		<!-- 保留(完成時削除?)<input type="button" name="button_id" value="+新規登録"> -->

	<!-- メンバー一覧をテーブルで表示 -->
  
	<div class ="list">
	<h2>メンバー一覧</h2>
	    <!-- 表示項目 -->
		<table>
		<tr>
			<th>ユーザーID</th>
			<th>ログインID</th>	
			<th>氏名</th>
			<th>メールアドレス</th>
			<th>権限(一般/管理者)</th>
			<th>状態(有効/無効)</th>
			<th>登録日</th>
			<th>編集</th>
		</tr>
		<!-- 表示データ -->
		<c:forEach var="m" items="${memberList}">
		<tr>
			<td><c:out value ="${m.userId}"/></td>
			<td><c:out value ="${m.loginId}"/></td>	
			<td><c:out value ="${m.name}"/></td>
			<td><c:out value ="${m.mail}"/></td>
			<td><c:out value ="${m.isAddmin}"/></td>
			<td><c:out value ="${m.isActive}"/></td>
			<td><c:out value ="${m.createdAt}"/></td>
			
			<!-- 各編集ボタンに対応するユーザーidをセットする -->
			<td>
			<button onclick = "openEditModal('${m.userId}')" >編集</button>
			<!-- 
			<input type ="hidden" name ="page_id" value ="L009">
			<input type ="hidden" name ="id" value ="${m.userId}">
			<input type ="button" value= "編集"> 
			-->
			</td>
		</tr>	
		</c:forEach>
  </table>
 </div> 
<!-- メンバー登録モーダル -->
  <div id = "regist_modal" class = "modal_background">
   <div class = "r_modal">
	<h2>メンバー新規登録</h2>
		<button class = "close" onclick = "closeRModal()">×</button><!-- 関数"closeModal" -->
		<form action ="POST" action="<c:url value='/Controller'/>">
			<p>ログインID*</p><br>
			<input type = "text" id = "login" name="login_iod" value ="login_id" required>
			
			<p>氏名*</p><br>
			<input type = "text" class = "name" name="name" value ="name" required>
			
			<p>初期パスワード*(6文字以上)</p><br>
			<input type = "text" id = "pw" name="pw" value ="pw" required>
			
			<p>メールアドレス</p><br>
			<input type = "text" name="mail" value ="mail">
			
			<p>権限</p><br>
			<label><input type = "radio" name ="is_addmin" value ="1">一般</label>
			<label><input type = "radio" name ="is_addmin" value ="2">管理者</label>
			
			<input type ="hidden" name ="page_id" value ="L009">
			
			<input type ="submit" name ="save" value ="保存">
		</form>
	</div>
  </div>
<!-- メンバー編集モーダル -->
  <div id = "edit_modal" class = "modal_background">
   <div class = "e_modal">
	<h2>メンバー編集</h2>		 <!-- 関数"closeModal" -->
		<button class = "close" onclick = "closeEModal()">×</button>
		<form action="POST" id = "user" action="<c:url value='/Controller'/>">
			<p>ユーザーID: "${m.userId}"</p>
		
			<p>氏名</p>	<br>
			<input type = "text" class = "name" name="name" value ="${m.name}" required>
			
			<p>メールアドレス</p><br>
			<input type = "text" name="mail" value ="${m.mail}">
			
			<p>権限</p><br>
			<label><input type = "radio" name ="is_addmin" value ="1">一般</label>
			<label><input type = "radio" name ="is_addmin" value ="2">管理者</label>
			
			<p>状態</p><br>
			<label><input type = "radio" name ="is_active" value ="1">有効</label>
			<label><input type = "radio" name ="is_active" value ="2">無効</label>
			
			<input type ="hidden" name ="page_id" value ="L009">
			<input type ="hidden" name ="id" value ="${m.userId}">
			<input type ="submit" name ="save" value ="保存">
		</form>
	 </div>	
	</div>
  </div>
<!-- JavaScriptここから(jsファイルに後で移行) -->
 <script>
 	//新規登録モーダル・引数なし
 	function openRegistModal(){
		document.getElementById("regist_modal").style.display = "block";
 	 	}

 	//編集モーダル・引数(user_id)
 	function openEditModal(userId){
		document.getElementById("edit_modal").style.display = "block";
 	 	}

 	//『×』新規登録モーダルを閉じる
 	function closeRModal(){
		document.getElementById("regist_modal").style.display = "none";
 	 	}
	//『×』編集モーダルを閉じる
	function closeEModal(){
		document.getElementById("edit_modal").style.display = "none";
 	 	}
 	//入力チェック
 	if(document.getElementById("login").value == ""){
		alert("入力必須項目です");
 	 	}
 	if(document.getElementById("name").value == ""){
		alert("入力必須項目です");
 	 	}
	if(document.getElementById("pw").value == ""){
		alert("入力必須項目です");
 	 	}
 </script>
 <!-- JavaScriptここまで
</body>
</html>