//データテーブル作成
jQuery(function ($) {
  // デフォルトの設定を変更（日本語化）--------------------
  $.extend($.fn.dataTable.defaults, {
    language: {
      url: "http://cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/Japanese.json",
    },
  });
  //------------------------------------------------
  //データテーブルを使用
  $("#foo-table").DataTable();
});

//新規登録モーダル・引数なし
function openRegistModal() {
  document.getElementById("regist_modal").style.display = "flex";
}

//編集モーダル・引数(user_id)
function openEditModal(userId, name, mail, isAdmin, isActive) {
  document.getElementById("edit_modal").style.display = "flex";
  let form = document.getElementById("edit");
  form.elements["name"].value = name;
  form.elements["mail"].value = mail;
  form.elements["is_admin"].value = isAdmin;
  form.elements["is_active"].value = isActive;
}

//『×』新規登録モーダルを閉じる。
function closeRModal() {
  document.getElementById("regist_modal").style.display = "none";
}
//『×』編集モーダルを閉じる
function closeEModal() {
  document.getElementById("edit_modal").style.display = "none";
}
//入力チェック//仮
// 	if(document.getElementById("login").value == ""){
//		alert("入力必須項目です");
// 	 	}
// 	if(document.getElementById("name").value == ""){
//		alert("入力必須項目です");
// 	 	}
//	if(document.getElementById("pw").value == ""){
//		alert("入力必須項目です");
// 	 	}

//有効・無効表示形式;
document.querySelectorAll(".adFlag").forEach((el) => {
  const admin = "管理者";
  const normal = "一般";
  if (el.textContent == "1") {
    el.textContent = admin;
  } else {
    el.textContent = normal;
  }
});

//状態表示形式
document.querySelectorAll(".activeFlag").forEach((el) => {
  const active = "有効";
  const disable = "無効";
  if (el.textContent == "1") {
    el.textContent = active;
  } else {
    el.textContent = disable;
  }
});
