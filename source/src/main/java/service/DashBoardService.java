package service;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.CaseDAO;
import dao.TaskDAO;
import dto.AllDTO;

public class DashBoardService extends DBAccess {
	
	//コンストラクタ
	public DashBoardService() {
		super.access();
	}
	
	//期限超過タスク検索（ダッシュボード）------------------------
	public ArrayList<AllDTO> selectOverTasks(int userId) {
		super.access();
		
		ArrayList<AllDTO> overTaskList = new ArrayList<AllDTO>();
		
		TaskDAO dao = new TaskDAO(super.conn);
		try {
			overTaskList = dao.selectOverTasks(userId);
		} catch (SQLException e) {
			System.out.println("SQL文おかしい");
			e.printStackTrace();
		}
		super.close();
		return overTaskList;
	}
	
	//担当タスク検索（ダッシュボード）-----------------------------
	public ArrayList<AllDTO> selectAssignedTasks(int userId) {
		super.access();
		
		ArrayList<AllDTO> myTaskList = new ArrayList<AllDTO>();
		
		TaskDAO dao = new TaskDAO(super.conn);
		try {
			myTaskList = dao.selectAssignedTasks(userId);
		} catch (SQLException e) {
			System.out.println("SQL文おかしい");
			e.printStackTrace();
		}
		super.close();
		return myTaskList;
	}
	
	//進行中案件検索(ダッシュボード)--------------------------------------------
	public ArrayList<AllDTO> selectWorkingCases(int userId) {
		super.access();
		
		ArrayList<AllDTO> caseList = null;
		
		CaseDAO dao = new CaseDAO(super.conn);
		try {
			caseList = dao.selectWorkingCases();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		super.close();
		return caseList;
	}
}
