package service;

import java.util.ArrayList;

import dto.AllDTO;
import dto.TaskDTO;

public class CaseService extends DBAccess {
	
	public CaseService() {
		super.access();
	}
	
	//案件全表示メソッド
	public ArrayList<AllDTO> selectCases(){
		ArrayList<AllDTO> caseList = null;
		
		return caseList;
	}
	
	//案件登録メソッド
	public int registCase() {
		int ans = 0;
		
		return ans;
	}
	//案件編集メソッド
	public int editCase() {
		int ans = 0;
		
		return ans;
	}
	//案件コードの重複チェック
	public int checkDuplicateCaseId(String caseId) {
		int ans =0;
		
		return ans;
	}
	//進行中案件検索
	public ArrayList<TaskDTO> selectWorkingCases(){
		ArrayList<TaskDTO> taskList = null;
		
		return taskList;
	}
}
