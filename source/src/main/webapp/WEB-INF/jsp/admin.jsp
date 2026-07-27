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
  <link rel="stylesheet" href="https://cdn.datatables.net/t/bs-3.3.6/jqc-1.12.0,dt-1.10.11/datatables.min.css"/>
</head>
<body>
<main>
<!-- sidebar.jspをインクルード -->
<%@ include file="/WEB-INF/jsp/sidebar.jsp"%>
<div class ="main">
	<h1>メンバー管理（管理者画面）</h1>
	<button onclick = "openRegistModal()" >+新規登録</button>

	<!-- メンバー一覧をテーブルで表示 -->
	<div class ="list">
    <h2>メンバー一覧</h2>
    <!-- 表示項目 -->
    <table border="1" id="foo-table" class="table table-bordered">
      <thead>
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
      </thead>
      <tbody>
      <c:forEach var="m" items="${userList}">
        <tr>
          <td><c:out value ="${m.userId}"/></td>
          <td><c:out value ="${m.loginId}"/></td>	
          <td><c:out value ="${m.name}"/></td>
          <td><c:out value ="${m.mail}"/></td>
          <td class="adFlag"><c:out value ="${m.isAdmin}"/></td>
          <td class="activeFlag"><c:out value ="${m.isActive}"/></td>
          <td><c:out value ="${m.createdAt}"/></td>
					<td><button onclick = "openEditModal('${m.userId}','${m.name}','${m.mail}','${m.isAdmin}','${m.isActive}')" >編集</button></td>
        </tr>	
      </c:forEach>
      </tbody>
    </table>
  </div> 
</div>

<!-- メンバー登録モーダル -->
<div id = "regist_modal" class = "modal_background">
  <div class = "r_modal">
	<h2>メンバー新規登録</h2>
		<button class = "close" onclick = "closeRModal()">×</button><!-- 関数"closeModal" -->
		<form action ="POST" action="<c:url value='/Controller'/>">
      <div>
        <label>ログインID*</label>
        <input type = "text" id = "login" name="login_iod" required>
			</div>
      <div>
        <label>氏名*</label>
        <input type = "text" class = "name" name="name" required>
			</div>
      <div>
        <label>初期パスワード*(6文字以上)</label>
        <input type = "text" id = "pw" name="pw" required>
			</div>
      <div>
        <label>メールアドレス</label>
        <input type = "text" name="mail">
			</div>
      <div>
			<label>権限</label>
        <label><input type = "radio" name ="is_addmin" value ="1">一般</label>
        <label><input type = "radio" name ="is_addmin" value ="2">管理者</label>
			</div>
			<input type ="hidden" name ="page_id" value ="L009">
			<input type ="submit" name ="save" value ="保存">
		</form>
	</div>
</div>

<!-- メンバー編集モーダル -->
<div id = "edit_modal" class = "modal_background">
  <div class = "e_modal">
    <h2>メンバー編集</h2>
		<button class = "close" onclick = "closeEModal()">×</button>
		<form action="POST" id = "edit" action="<c:url value='/Controller'/>">
      <div>
        <label>ユーザーID: "${userId}"</label>
      </div>
      <div>
        <label>氏名</label>
        <input type = "text" class = "name" name="name" value ="${m.name}" required>
      </div>
      <div>
        <label>メールアドレス</label>
        <input type = "text" name="mail" value ="${m.mail}">
			</div>
      <div>
        <label>権限</label> 
        <input type = "radio" name ="is_admin" value ="1">一般
        <input type = "radio" name ="is_admin" value ="2">管理者
			</div>
      <div>
        <label>状態</label>
        <input type = "radio" name ="is_active" value ="1">有効
        <input type = "radio" name ="is_active" value ="2">無効
			</div>
			<input type ="hidden" name ="page_id" value ="L009">
			<input type ="hidden" name ="id" value ="${m.userId}">
			<input type ="submit" name ="save" value ="保存">
		</form>
    </div>	
	</div>

  <script src="https://cdn.datatables.net/t/bs-3.3.6/jqc-1.12.0,dt-1.10.11/datatables.min.js"></script>
  <script src="${pageContext.request.contextPath}/js/admin.js"></script>
</main>
</body>
</html>