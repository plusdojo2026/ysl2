package dao;

import java.sql.Connection;

public class ManHourDAO {
	
	public Connection conn = null;
	
	//コネクションを保持するコンストラクタ
	public ManHourDAO(Connection conn) {
		this.conn=conn;
	}
}