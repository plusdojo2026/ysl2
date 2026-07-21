package dto;

public class AllDTO {
	
	private int userId;					//ユーザーID
	private String loginId;				//ログインID
	private String pw;					//パスワード
	private String name;				//氏名
	private String mail;				//メールアドレス
	private int isAdmin;				//管理者フラグ
	private int isActive;				//有効無効フラグ
	
	private String caseId;				//案件コード
	private String caseName;			//案件名
	private String customerName;		//顧客名
	private String caseStatus;			//ステータス（案件）
	private String casePriority;		//優先度（案件）
	private int pmId;					//PMID
	private String caseStartDate;		//開始日（案件）
	private String endDate;				//終了予定日
	private double budgetedManHours;	//予算工数
	private String caseMemo;			//説明（案件）
	
	private int taskId;					//タスクID
	private String taskName;			//タスク名
	private int manager;				//タスク担当者（ユーザーID）
	private String taskStatus;			//ステータス（タスク）
	private String taskPriority;		//優先度（タスク）
	private String taskStartDate;		//開始日（タスク）
	private String deadlineDate;		//期限
	private double estimatedManHours;	//見積工数
	private int taskProgress;			//進捗率
	private String taskMemo;			//説明（タスク）
	
	private int manHoursId;				//工数ID
	private double todayManHours;		//今日の工数
	private String workDetails;			//作業内容
	private String workDate;			//作業日
	private int manHoursUserId;			//登録者（ユーザーID）
	private double actualManHours;		//実績工数
	
	private int allTasks;				//総タスク数
	private int completedTasks;			//完了タスク数
	
	private	 String createdAt;			//作成日
	private String apdatedAt;			//更新日
	
	//ゲッターセッター
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public int getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}
	public int getIsActive() {
		return isActive;
	}
	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}
	public String getCaseId() {
		return caseId;
	}
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	public String getCaseName() {
		return caseName;
	}
	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCaseStatus() {
		return caseStatus;
	}
	public void setCaseStatus(String caseStatus) {
		this.caseStatus = caseStatus;
	}
	public String getCasePriority() {
		return casePriority;
	}
	public void setCasePriority(String casePriority) {
		this.casePriority = casePriority;
	}
	public int getPmId() {
		return pmId;
	}
	public void setPmId(int pmId) {
		this.pmId = pmId;
	}
	public String getCaseStartDate() {
		return caseStartDate;
	}
	public void setCaseStartDate(String caseStartDate) {
		this.caseStartDate = caseStartDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public double getBudgetedManHours() {
		return budgetedManHours;
	}
	public void setBudgetedManHours(double budgetedManHours) {
		this.budgetedManHours = budgetedManHours;
	}
	public String getCaseMemo() {
		return caseMemo;
	}
	public void setCaseMemo(String caseMemo) {
		this.caseMemo = caseMemo;
	}
	public int getTaskId() {
		return taskId;
	}
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public int getManager() {
		return manager;
	}
	public void setManager(int manager) {
		this.manager = manager;
	}
	public String getTaskStatus() {
		return taskStatus;
	}
	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}
	public String getTaskPriority() {
		return taskPriority;
	}
	public void setTaskPriority(String taskPriority) {
		this.taskPriority = taskPriority;
	}
	public String getTaskStartDate() {
		return taskStartDate;
	}
	public void setTaskStartDate(String taskStartDate) {
		this.taskStartDate = taskStartDate;
	}
	public String getDeadlineDate() {
		return deadlineDate;
	}
	public void setDeadlineDate(String deadlineDate) {
		this.deadlineDate = deadlineDate;
	}
	public double getEstimatedManHours() {
		return estimatedManHours;
	}
	public void setEstimatedManHours(double estimatedManHours) {
		this.estimatedManHours = estimatedManHours;
	}
	public int getTaskProgress() {
		return taskProgress;
	}
	public void setTaskProgress(int taskProgress) {
		this.taskProgress = taskProgress;
	}
	public String getTaskMemo() {
		return taskMemo;
	}
	public void setTaskMemo(String taskMemo) {
		this.taskMemo = taskMemo;
	}
	public int getManHoursId() {
		return manHoursId;
	}
	public void setManHoursId(int manHoursId) {
		this.manHoursId = manHoursId;
	}
	public double getTodayManHours() {
		return todayManHours;
	}
	public void setTodayManHours(double todayManHours) {
		this.todayManHours = todayManHours;
	}
	public String getWorkDetails() {
		return workDetails;
	}
	public void setWorkDetails(String workDetails) {
		this.workDetails = workDetails;
	}
	public String getWorkDate() {
		return workDate;
	}
	public void setWorkDate(String workDate) {
		this.workDate = workDate;
	}
	public int getManHoursUserId() {
		return manHoursUserId;
	}
	public void setManHoursUserId(int manHoursUserId) {
		this.manHoursUserId = manHoursUserId;
	}
	public double getActualManHours() {
		return actualManHours;
	}
	public void setActualManHours(double actualManHours) {
		this.actualManHours = actualManHours;
	}
	public int getAllTasks() {
		return allTasks;
	}
	public void setAllTasks(int allTasks) {
		this.allTasks = allTasks;
	}
	public int getCompletedTasks() {
		return completedTasks;
	}
	public void setCompletedTasks(int completedTasks) {
		this.completedTasks = completedTasks;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	public String getApdatedAt() {
		return apdatedAt;
	}
	public void setApdatedAt(String apdatedAt) {
		this.apdatedAt = apdatedAt;
	}
	
	

}
