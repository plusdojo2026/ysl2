package dao;

import java.sql.Connection;

public class CaseDAO {
	
	public Connection conn = null;
	
	//コネクションを保持するコンストラクタ
	public CaseDAO(Connection conn) {
		this.conn = conn;
	}

}