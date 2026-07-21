package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.AllDTO;

public class CaseDAO {
	
	public Connection conn = null;
	
	//コネクションを保持するコンストラクタ
	public CaseDAO(Connection conn) {
		this.conn = conn;
	}
	
	//案件全検索
	public ArrayList<AllDTO> selectCases()throws SQLException{
		
	}
	
	//案件登録
	public int registCase() {
		
	}
	//案件編集
	
	//案件コードの重複チェック
	
	//進行中案件検索(ダッシュボード)
}