package dto;

public class TaskDTO {
	
	private int taskId;			//タスクID
	private String caseId;		//案件コード
	private String taskName;	//タスク名
	private int manager;		//担当者
	private String status;		//ステータス
	private String priority;	//優先度
	private String startDate;	//開始日
	private String deadlineDate;		//期限
	private double estimatedManHours;	//見積工数
	private int taskProgress;	//進捗率
	private String memo;		//説明
	private String createdAt;	//作成日
	private String updatedAt;	//更新日
	
	
	//ゲッターセッター
	public int getTaskId() {
		return taskId;
	}
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
	public String getCaseId() {
		return caseId;
	}
	public void setCaseId(String caseId) {
		this.caseId = caseId;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
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
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	public String getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	

}