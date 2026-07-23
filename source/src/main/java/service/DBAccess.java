package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBAccess {

	//コネクションを保持する変数connを定義
	protected Connection conn = null;
	
	//データベースと接続するaccessメソッド
	protected void access(){
		try {
				// JDBCドライバを読み込む
				Class.forName("com.mysql.cj.jdbc.Driver");
				//データベースに接続                 
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ysl2?"
						+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
						"ysl2", "ZwH54wxWJQt378SN");
				
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	//データベースを切断するcloseメソッド
	protected void close(){
				if(conn != null){
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
		}
	}
}
