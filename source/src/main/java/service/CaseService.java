package service;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.CaseDAO;
import dto.AllDTO;
import dto.CaseDTO;

public class CaseService extends DBAccess {

	public CaseService() {
		super.access();
	}

	//案件全表示メソッド
	public ArrayList<AllDTO> selectCases() {
		super.access();
		ArrayList<AllDTO> caseList = null;

		CaseDAO dao = new CaseDAO(super.conn);
		try {
			caseList = dao.selectCases();
		} catch (SQLException e) {
			System.out.println("SQL文おかしいよ");
			e.printStackTrace();
		}
		super.close();

		return caseList;
	}

	//案件登録メソッド
	public int registCase(CaseDTO dto) {
		super.access();
		int ans = 0;

		CaseDAO dao = new CaseDAO(conn);

		String caseId = dto.getCaseId();
		try {
			ans = dao.checkDuplicateCaseId(caseId);
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		if (ans == 1) {
			return ans;
		} else {
			try {
				ans = dao.registCase(dto);
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
		super.close();
		System.out.println("サービス"+ans);
		return ans;
	}

	//案件編集メソッド
	public int editCase(CaseDTO dto) {
		super.access();
		int ans = 0;
		CaseDAO dao = new CaseDAO(conn);
		try {
			ans = dao.editCase(dto);
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		super.close();
		return ans;
	}

	//進行中案件検索
	public ArrayList<AllDTO> selectWorkingCases() {
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
	
	//ステータス変更メソッド
	public int updateStatus(String caseId,String status) {
		super.access();
		int ans = 0;
		
		CaseDAO dao = new CaseDAO(conn);
		try {
			ans = dao.updatestatus(caseId, status);
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		super.close();
		return ans;
		
	}
	//案件詳細用メソッド
	public AllDTO selectDetailCase(String caseId) {
		super.access();
		AllDTO dto = null;
		CaseDAO dao = new CaseDAO(super.conn);
		
		try {
			dto = dao.selectDetailCase(caseId);
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		super.close();
		return dto;
	}

}
	
	


