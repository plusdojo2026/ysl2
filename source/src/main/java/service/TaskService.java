package service;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.TaskDAO;
import dto.AllDTO;
import dto.TaskDTO;

public class TaskService extends DBAccess{
	
	//コンストラクタ
	public TaskService() {
		super.access();
	}
	
	//タスクデータ登録メソッド-------------------------------
	public int registTask(TaskDTO dto) {
		int ans = 0;
		
		TaskDAO dao = new TaskDAO(super.conn);
		try {
			ans = dao.registTask(dto);
		} catch (SQLException e) {
			System.out.println("SQL文おかしい");
			e.printStackTrace();
		}
		return ans;
	}
	
	//タスクデータ編集メソッド-------------------------------
	public int updateTask(TaskDTO dto) {
		int ans = 0;
		
		TaskDAO dao = new TaskDAO(super.conn);
		try {
			ans = dao.updateTask(dto);
		} catch (SQLException e) {
			System.out.println("SQL文おかしい");
			e.printStackTrace();
		}
		return ans;
	}
	
	//タスク全検索メソッド-----------------------------------
	public ArrayList<AllDTO> selectTasks() {
		ArrayList<AllDTO> taskList = new ArrayList<AllDTO>();
		
		TaskDAO dao = new TaskDAO(super.conn);
		try {
			taskList = dao.selectTasks();
		} catch (SQLException e) {
			System.out.println("SQL文おかしい");
			e.printStackTrace();
		}
		return taskList;
	}
	
	//タスク削除メソッド--------------------------------------
	public int deleteTask(int taskId) {
		int ans = 0;
		
		TaskDAO dao = new TaskDAO(super.conn);
		try {
			ans = dao.deleteTask(taskId);
		} catch (SQLException e) {
			System.out.println("SQL文おかしい");
			e.printStackTrace();
		}
		return ans;
	}
	
	//タスク進捗のカウントメソッド-----------------------------
	public ArrayList<AllDTO> countProgress() {
		ArrayList<AllDTO> progressList = new ArrayList<AllDTO>();
		
		TaskDAO dao = new TaskDAO(super.conn);
		try {
			progressList = dao.countProgress();
		} catch (SQLException e) {
			System.out.println("SQL文おかしい");
			e.printStackTrace();
		}
		return progressList;
	}
	
	//期限超過タスク検索（ダッシュボード）------------------------s
	public ArrayList<TaskDTO> selectOverTasks(int userId){
		ArrayList<TaskDTO> taskList = new ArrayList<TaskDTO>();
		
		TaskDAO dao = new TaskDAO(super.conn);
		try {
			taskList = dao.selectOverTasks(userId);
		} catch (SQLException e) {
			System.out.println("SQL文おかしい");
			e.printStackTrace();
		}
		return taskList;
	}
	
	//担当タスク検索（ダッシュボード）-----------------------------
	public ArrayList<TaskDTO> selectAssignedTasks(int userId) {
		ArrayList<TaskDTO> taskList = new ArrayList<TaskDTO>();
		
		TaskDAO dao = new TaskDAO(super.conn);
		try {
			taskList = dao.selectAssignedTasks(userId);
		} catch (SQLException e) {
			System.out.println("SQL文おかしい");
			e.printStackTrace();
		}
		return taskList;
	}
}
