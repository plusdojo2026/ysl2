package dao;

import java.sql.Connection;

public class ManthlySumDAO {
	
	public Connection conn = null;
	
	//コネクションを保持するコンストラクタ
	public ManthlySumDAO(Connection conn) {
		this.conn=conn;
	}
}