package service;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.TaskDAO;
import dto.AllDTO;
import dto.TaskDTO;

public class TaskService extends DBAccess {

	//タスクデータ登録メソッド-------------------------------
	public int registTask(TaskDTO dto) {
		super.access();
		int ans = 0;

		TaskDAO dao = new TaskDAO(super.conn);
		try {
			ans = dao.registTask(dto);
		} catch (SQLException e) {
			System.out.println("SQL文おかしい");
			e.printStackTrace();
		}
		super.close();
		return ans;
	}

	//タスクデータ編集メソッド-------------------------------
	public int updateTask(TaskDTO dto) {
		super.access();
		int ans = 0;

		TaskDAO dao = new TaskDAO(super.conn);
		try {
			ans = dao.updateTask(dto);
		} catch (SQLException e) {
			System.out.println("SQL文おかしい");
			e.printStackTrace();
		}
		super.close();
		return ans;
	}

	//タスク全検索メソッド-----------------------------------
	public ArrayList<AllDTO> selectTasks() {
		super.access();
		ArrayList<AllDTO> taskList = new ArrayList<AllDTO>();

		TaskDAO dao = new TaskDAO(super.conn);
		try {
			taskList = dao.selectTasks();
		} catch (SQLException e) {
			System.out.println("SQL文おかしい");
			e.printStackTrace();
		}
		super.close();
		return taskList;
	}

	//タスク削除メソッド--------------------------------------
	public int deleteTask(int taskId) {
		super.access();
		int ans = 0;

		TaskDAO dao = new TaskDAO(super.conn);
		try {
			ans = dao.deleteTask(taskId);
		} catch (SQLException e) {
			System.out.println("SQL文おかしい");
			e.printStackTrace();
		}
		super.close();
		return ans;
	}

	//その案件のタスク一覧表示メソッド（案件詳細）
	public ArrayList<AllDTO> selectTaskOfCase(String caseId) {
		super.access();
		ArrayList<AllDTO> taskList = new ArrayList<AllDTO>();

		TaskDAO dao = new TaskDAO(super.conn);
		try {
			taskList = dao.selectTaskOfCase(caseId);
		} catch (SQLException e) {
			System.out.println("SQL文おかしい");
			e.printStackTrace();
		}
		super.close();
		return taskList;
	}

	//タスク1件表示メソッド（タスク詳細）----------------------
	public AllDTO selectTaskDetail(int taskId) {
		super.access();
		AllDTO dto = null;

		TaskDAO dao = new TaskDAO(super.conn);
		try {
			dto = dao.selectTaskDetail(taskId);
		} catch (SQLException e) {
			System.out.println("SQL文おかしい");
			e.printStackTrace();
		}
		super.close();
		return dto;
	}

	//タスク進捗のカウントメソッド-----------------------------
	public ArrayList<AllDTO> countProgress() {
		super.access();
		ArrayList<AllDTO> progressList = new ArrayList<AllDTO>();

		TaskDAO dao = new TaskDAO(super.conn);
		try {
			progressList = dao.countProgress();
		} catch (SQLException e) {
			System.out.println("SQL文おかしい");
			e.printStackTrace();
		}
		super.close();
		return progressList;
	}

	//期限超過タスク検索（ダッシュボード）------------------------
	public ArrayList<AllDTO> selectOverTasks(int userId) {
		super.access();
		ArrayList<AllDTO> taskList = new ArrayList<AllDTO>();

		TaskDAO dao = new TaskDAO(super.conn);
		try {
			taskList = dao.selectOverTasks(userId);
		} catch (SQLException e) {
			System.out.println("SQL文おかしい");
			e.printStackTrace();
		}
		super.close();
		return taskList;
	}

	//担当タスク検索（ダッシュボード）-----------------------------
	public ArrayList<AllDTO> selectAssignedTasks(int userId) {
		super.access();
		ArrayList<AllDTO> taskList = new ArrayList<AllDTO>();

		TaskDAO dao = new TaskDAO(super.conn);
		try {
			taskList = dao.selectAssignedTasks(userId);
		} catch (SQLException e) {
			System.out.println("SQL文おかしい");
			e.printStackTrace();
		}
		super.close();
		return taskList;
	}
}
