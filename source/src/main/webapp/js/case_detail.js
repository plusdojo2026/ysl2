//新規登録モーダル・引数なし
function openRegistModal() {
  document.getElementById("case_regist_modal").style.display = "block";
}

//編集モーダル・引数
function openEditModal(caseId,casePriority,caseName,caseStartDate,endDate,customerName,budgetedManHours,name,caseMemo,caseStatus,pmId) {
  document.getElementById("edit_modal").style.display = "flex";
  let form = document.getElementById("edit");
  form.elements["case_id"].value = caseId;
  form.elements["case_name"].value = caseName;
  form.elements["customer_name"].value = customerName;
  form.elements["pm_id"].value = pmId;
  form.elements["memo"].value = caseMemo;
  form.elements["start_date"].value = caseStartDate;
  form.elements["end_date"].value = endDate;
  form.elements["priority"].value = casePriority;
  form.elements["budgetted_man_hours"].value = budgetedManHours;
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