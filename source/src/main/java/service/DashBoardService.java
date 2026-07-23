package service;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.CaseDAO;
import dao.TaskDAO;
import dto.AllDTO;
import dto.CaseDTO;

public class DashBoardService extends DBAccess{
	
	//コンストラクタ
		public DashBoardService() {
			super.access();
		}
	
	//期限超過タスク検索（ダッシュボード）------------------------
	public ArrayList<AllDTO> selectOverTasks(int userId){
		ArrayList<AllDTO> OvertaskList = new ArrayList<AllDTO>();
		
		TaskDAO dao = new TaskDAO(super.conn);
		try {
			OvertaskList = dao.selectOverTasks(userId);
		} catch (SQLException e) {
			System.out.println("SQL文おかしい");
			e.printStackTrace();
		}
		super.close();
		return OvertaskList;
	}

	//担当タスク検索（ダッシュボード）-----------------------------
	public ArrayList<AllDTO> selectAssignedTasks(int userId) {
		ArrayList<AllDTO> MytaskList = new ArrayList<AllDTO>();
		
		TaskDAO dao = new TaskDAO(super.conn);
		try {
			MytaskList = dao.selectAssignedTasks(userId);
		} catch (SQLException e) {
			System.out.println("SQL文おかしい");
			e.printStackTrace();
		}
		super.close();
		return MytaskList;
	}
	
	
	//進行中案件検索(ダッシュボード)--------------------------------------------
		public ArrayList<CaseDTO> selectWorkingCases(int userId){
			ArrayList<CaseDTO> caseList = null;
			
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
