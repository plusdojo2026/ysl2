package service;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.TaskDAO;
import dto.AllDTO;
import dto.TaskDTO;

public class DashBoardService {
	
	//コンストラクタ
		public DashBoardService() {
			super.access();
		}
	
	//期限超過タスク検索（ダッシュボード）------------------------
	public ArrayList<AllDTO> selectOverTasks(int userId){
		ArrayList<AllDTO> taskList = new ArrayList<AllDTO>();
		
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
	public ArrayList<AllDTO> selectAssignedTasks(int userId) {
		ArrayList<AllDTO> taskList = new ArrayList<AllDTO>();
		
		TaskDAO dao = new TaskDAO(super.conn);
		try {
			taskList = dao.selectAssignedTasks(userId);
		} catch (SQLException e) {
			System.out.println("SQL文おかしい");
			e.printStackTrace();
		}
		return taskList;
	}
	
	
	//進行中案件検索(ダッシュボード)--------------------------------------------
		public ArrayList<TaskDTO> selectWorkingCases(){
			ArrayList<TaskDTO> taskList = null;
			
			return taskList;
		}

}
