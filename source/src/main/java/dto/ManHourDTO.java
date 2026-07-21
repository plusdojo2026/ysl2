package dto;

public class ManHourDTO {
	
	private int manHoursId;			//工数ID
	private int taskId;				//タスクID
	private double todayManHours;	//登録する工数
	private String workDetails;		//作業内容
	private String workDate;		//作業日
	private int userId;				//ユーザーID(登録者)
	private String createdAt;		//作成日
	private String updateAt;		//更新日
	
	//ゲッタとセッタ
	public int getManHoursId() {
		return manHoursId;
	}
	public void setManHoursId(int manHoursId) {
		this.manHoursId = manHoursId;
	}
	public int getTaskId() {
		return taskId;
	}
	public void setTaskId(int taskId) {
		this.taskId = taskId;
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
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	public String getUpdateAt() {
		return updateAt;
	}
	public void setUpdateAt(String updateAt) {
		this.updateAt = updateAt;
	}
}