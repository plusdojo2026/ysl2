// モーダル表示２
function openModal2(caseId,taskId,taskName,manager,taskStartDate,deadlineDate,estimatedManHours,actualManHours,taskStatus,taskPriority,taskMemo, taskProgress) {
 	//編集モーダルの全体をformという名前とする
	const form = document.getElementById('conform');
 	//そのformの中のname="task_name"のところに値を入れる（引数）
 	form.elements['case_id'].value = caseId;
 	form.elements['task_id'].value = taskId;
 	form.elements['task_name'].value = taskName;
 	form.elements['progress'].value = taskProgress;
 	form.elements['start_date'].value = taskStartDate;
 	form.elements['deadline_date'].value = deadlineDate;
 	form.elements['priority'].value = taskPriority;
 	form.elements['status'].value = taskStatus;
 	form.elements['estimated_man_hours'].value = estimatedManHours;
 	form.elements['memo'].value = taskMemo;
 	form.elements['manager'].value = manager;
 	
 	//他にもたくさんあるけど後はよろしく。モーダルの整形もお願いね
	document.getElementById("modal2").style.display = "block";
}

// モーダル非表示２
function closeModal2() {
  document.getElementById("modal2").style.display = "none";
}