package service;

import java.util.ArrayList;

import dto.AllDTO;
import dto.TaskDTO;

public class TaskService extends DBAccess{
	
	//コンストラクタ
	public TaskService() {
		super.access();
	}
	
	//タスクデータ登録メソッド
	public int registTask(TaskDTO dto) {
		int ans = 0;
		
		return ans;
	}
	
	//タスクデータ編集メソッド
	public int updateTask(TaskDTO dto) {
		int ans = 0;
		
		return ans;
	}
	
	//タスク全検索メソッド
	public ArrayList<AllDTO> selectTasks() {
		ArrayList<AllDTO> taskList = new ArrayList<AllDTO>();
		
		return taskList;
	}
	
	//タスク削除メソッド
	public int deleteTask(int taskId) {
		int ans = 0;
		
		return ans;
	}
	
	//タスク進捗のカウントメソッド
	public ArrayList<AllDTO> countProgress() {
		ArrayList<AllDTO> progressList = new ArrayList<AllDTO>();
		
		return progressList;
	}
	
	//期限超過タスク検索
}
