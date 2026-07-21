<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メンバー管理 | TaskManager</title>
</head>
<body>
<header> <!-- sidebar.jspをインクルードする --> </header>
<div class ="main">
	<h1>メンバー管理（管理者画面）</h1>
		
		<form method="POST" action="<c:url value='/Controller'/>">
			<!-- 検索 -->
			<input type="hidden" name="page_id" value="L009">
			<input type="text" name="member_search">
			<input type="submit" name="button_id" value = "xxxx">
		
			<!-- 並び替え -->
			<label for ="fillter">絞り込み(ユーザー状態)</label>
			<select name ="fillter">
				<option value ="有効">有効</option>
				<option value ="無効">無効</option>
			</select>
		</form>
		
		<!-- +新規登録モーダルを表示 -->
		<input type="button" name="button_id" value="+新規登録">

	<!-- メンバー一覧をテーブルで表示 -->
	<h2>メンバー一覧</h2>
	<div class ="list">
		<!-- 表示項目 -->
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
		<c:forEach var="m" m="${menberList}">
		<tr>
			<td><c:out value ="${m.userId}"></c:out>
			<td><c:out value ="${m.loginId}"></td>	
			<td><c:out value ="${m.name}"></td>
			<td><c:out value ="${m.mail}"></td>
			<td><c:out value ="${m.isAddmin}"></td>
			<td><c:out value ="${m.isActive}"></td>
			<td><c:out value ="${m.createdAt}"></td>
			<td><input type ="button" value= "編集"></td>
		</tr>	
	</div>
	
<!-- メンバー登録モーダル -->
<h2>メンバー新規登録</h2>
	<form action ="POST">
	
		<p>ログインID(必須)</p><br>
		<input type = "text" name="login_iod" value ="login_id" required>
		
		<p>氏名(必須)</p><br>
		<input type = "text" name="name" value ="name" required>
		
		<p>初期パスワード(必須・6文字以上)</p><br>
		<input type = "text" name="pw" value ="pw" required>
		
		<p>メールアドレス</p><br>
		<input type = "text" name="mail" value ="mail">
		
		<p>権限</p><br>
		<label><input type = "radio" name ="is_addmin" value ="1">一般</label>
		<label><input type = "radio" name ="is_addmin" value ="2">管理者</label>
		
		<input type ="hidden" name ="page_id" value ="L009">
		
		<input type ="submit" name ="save" value ="保存">
	</form>

<!-- メンバー編集モーダル -->
<h2>メンバー編集</h2>
	<form action="POST">
		<p>ユーザーID: "${m.userId}"</p>
	
		<p>氏名</p>	<br>
		<input type = "text" name="name" value ="${m.name}" required>
		
		<p>メールアドレス</p><br>
		<input type = "text" name="mail" value ="${m.mail}">
		
		<p>権限</p><br>
		<label><input type = "radio" name ="is_addmin" value ="1">一般</label>
		<label><input type = "radio" name ="is_addmin" value ="2">管理者</label>
		
		<p>状態</p><br>
		<label><input type = "radio" name ="is_active" value ="1">有効</label>
		<label><input type = "radio" name ="is_active" value ="2">無効</label>
		
		<input type ="hidden" name ="page_id" value ="L009">
		<input type ="hidden" name ="id" value ="${m.userId}"><!-- 各編集ボタンに対応するユーザーidをセットする -->
		<input type ="submit" name ="save" value ="保存">
	</form>

</div>
</body>
</html>