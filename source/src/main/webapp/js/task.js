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
// モーダル表示１
function openModal() {
  document.getElementById("modal").style.display = "flex";
}

// モーダル非表示１
function closeModal() {
  document.getElementById("modal").style.display = "none";
}

// モーダル表示２
function openModal2(
  caseId,
  taskId,
  taskName,
  manager,
  taskStartDate,
  deadlineDate,
  estimatedManHours,
  actualManHours,
  taskStatus,
  taskPriority,
  taskMemo,
  taskProgress
) {
  //編集モーダルの全体をformという名前とする
  const form = document.getElementById("conform");
  //そのformの中のname="task_name"のところに値を入れる（引数）
  form.elements["case_id"].value = caseId;
  form.elements["task_id"].value = taskId;
  form.elements["task_name"].value = taskName;
  form.elements["progress"].value = taskProgress;
  form.elements["start_date"].value = taskStartDate;
  form.elements["deadline_date"].value = deadlineDate;
  form.elements["priority"].value = taskPriority;
  form.elements["status"].value = taskStatus;
  form.elements["estimated_man_hours"].value = estimatedManHours;
  form.elements["memo"].value = taskMemo;
  form.elements["manager"].value = manager;

  //他にもたくさんあるけど後はよろしく。モーダルの整形もお願いね
  document.getElementById("modal2").style.display = "flex";
}

// モーダル非表示２
function closeModal2() {
  document.getElementById("modal2").style.display = "none";
}
