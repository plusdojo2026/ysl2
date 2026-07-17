package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.AllDTO;

public class ManHourDAO {
	
	public Connection conn = null;
	
	//コネクションを保持するコンストラクタ
	public ManHourDAO(Connection conn) {
		this.conn=conn;
	}
	
	//工数登録
	public int registManHour() throws SQLException{
		// TODO 自動生成されたメソッド・スタブ
		int ans = 0;
		String sql ="INSERT INTO man_hours (task_id, today_man_hours, work_details, user_id)";
		System.out.println(sql);
		//まとめる
		PreparedStatement pStmt =conn.prepareStatement(sql);
		
		return 0;
	}

	public int deleteManHour(int man_hours_id) {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}

	

	public ArrayList<AllDTO> selectCaseName(int task_id) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}



	public ArrayList<AllDTO> sumCaseManHours(int task_id) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}



	public ArrayList<AllDTO> selectManHoursDAO() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	
	
}