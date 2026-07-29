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

//ステータスと進捗
const sta1 = document.getElementById("status1");
const pro1 = document.getElementById("progress1");
sta1.addEventListener("change", () => changeProgress(sta1, pro1));
pro1.addEventListener("change", () => changeStatus(sta1, pro1));

const sta = document.getElementById("status");
const pro = document.getElementById("progress");
sta.addEventListener("change", () => changeProgress(sta, pro));
pro.addEventListener("change", () => changeStatus(sta, pro));

function changeProgress(status,progress){
	if(status.value == '未着手'){
		progress.value = 0;
	}else if (status.value == '完了') {
		progress.value = 100;
	}
}
function changeStatus(status, progress) {
	if(progress.value == 0) {
		status.value = '未着手';
	}else if (progress.value == 100) {
		status.value = '完了';
	}
}