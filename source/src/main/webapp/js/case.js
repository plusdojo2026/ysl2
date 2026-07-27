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
  document.getElementById("case_regist_modal").style.display = "flex";
}

//編集モーダル・引数
function openEditModal(caseId, caseName, customerName, name, caseMemo, caseStartDate, endDate, casePriority, budgetedManHours, caseStatus) {
  document.getElementById("edit_modal").style.display = "flex";
  let form = document.getElementById("edit");
  form.elements["case_id"].value = caseId;
  form.elements["case_name"].value = caseName;
  form.elements["customer_name"].value = customerName;
  form.elements["pm_id"].value = name;
  form.elements["memo"].value = caseMemo;
  form.elements["start_date"].value = caseStartDate;
  form.elements["end_date"].value = endDate;
  form.elements["priority"].value = casePriority;
  form.elements["budgeted_man_hours"].value = budgetedManHours;
  form.elements["status"].value = caseStatus;
}

//『×』新規登録モーダルを閉じる。
function closeRModal() {
  document.getElementById("case_regist_modal").style.display = "none";
}
//『×』編集モーダルを閉じる
function closeEModal() {
  document.getElementById("edit_modal").style.display = "none";
}
