package dao;

import java.sql.Connection;
import java.util.ArrayList;

public class CaseDAO {
	
	public Connection conn = null;
	
	//コネクションを保持するコンストラクタ
	public CaseDAO(Connection conn) {
		this.conn = conn;
	}
	
	public ArrayList<AllDTO> selectCases() {
		ArrayList<AllDTO> caseList = new ArrayList<AllDTO>();
		
		return caseList;
	}
	
	public int registCase(CaseDTO) {
		int ans = 0;
		
		return ans;
	}
}