package dao;

import java.sql.Connection;
import java.util.ArrayList;

import dto.AllDTO;
import dto.TaskDTO;

public class TaskDAO {
	
	public Connection conn = null;
	
	//コネクションを保持するコンストラクタ
	public TaskDAO(Connection conn) {
		this.conn=conn;
	}
	
	//タスク全検索
	public ArrayList<AllDTO> selectTasks() {
		ArrayList<AllDTO> taskList = new ArrayList<AllDTO>();
		
		return taskList;
	}
	
	//タスク登録
	public int registTask(TaskDTO dto) {
		int ans = 0;
		
		return ans;
	}
	
	//タスク編集
	public int updateTask(TaskDTO dto) {
		int ans = 0;
		
		return ans;
	}
	
	//タスク削除（案件詳細）
	public int deleteTask(int taskId) {
		int ans = 0;
		
		return ans;
	}
	
	//タスク進捗
	public ArrayList<AllDTO> countProgress() {
		ArrayList<AllDTO> taskList = new ArrayList<AllDTO>();
		
		return taskList;
	}
	
	//期限超過タスク検索（ダッシュボード）
	public ArrayList<TaskDTO> selectOverTasks(int userId) {
		ArrayList<TaskDTO> taskList = new ArrayList<TaskDTO>();
		
		return taskList;
	}
	
	//担当タスク検索（ダッシュボード）
	public ArrayList<TaskDTO> selectAssignedTasks(int userId) {
		ArrayList<TaskDTO> taskList = new ArrayList<TaskDTO>();
		
		return taskList;
	}

} 