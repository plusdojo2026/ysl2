package dao;

import java.sql.Connection;

public class TaskDAO {
	
	public Connection conn = null;
	
	//コネクションを保持するコンストラクタ
	public TaskDAO(Connection conn) {
		this.conn=conn;
	}

}