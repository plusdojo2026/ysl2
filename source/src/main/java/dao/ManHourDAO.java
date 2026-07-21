package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
	public int registManHour(Double today_man_hours, String work_details, Date work_date) throws SQLException{
		int ans = 0;
		String sql ="INSERT INTO man_hours (today_man_hours, work_details, work_date) VALUES(?,?,?)";
		System.out.println(sql);
		// まとめる
		PreparedStatement pStmt = conn.prepareStatement(sql);
		pStmt.executeQuery();
		pStmt.setDouble(1, today_man_hours);
		pStmt.setString(2, work_details);
		pStmt.setDate(3, work_date);
		pStmt.executeUpdate();
		return ans;
	}

	//削除
	public int deleteManHour(int man_hours_id) throws SQLException {
		int ans = 0;
		String sql ="DELETE FROM man_hours WHERE man_hours_id=?";
		System.out.println(sql);
		PreparedStatement pStmt = conn.prepareStatement(sql);
		pStmt.setInt(1, man_hours_id);
		pStmt.executeUpdate();
		
		return ans;
	}

	
	//案件名・タスク名（工数登録）
	public ArrayList<AllDTO> selectCaseName(int task_id) throws SQLException {
		ArrayList<AllDTO> list = new ArrayList<AllDTO>();
		String sql ="SELECT case_name, task_name FROM tasks t JOIN cases c ON t.case_id = c.case_id WHERE t.task_id = ?";
		System.out.println(sql);
		PreparedStatement pStmt = conn.prepareStatement(sql);
		pStmt.setInt(1, task_id);
		
		//SELECT文を実行し結果票を取得
		ResultSet rs = pStmt.executeQuery();
		
		//移し替え
		while(rs.next()) {
			AllDTO dto = new AllDTO();
			dto.setTaskId(rs.getInt("task_id"));
			list.add(dto);
		}
		return list;
	}


	//工数ログ(タスク詳細)
	public ArrayList<AllDTO> sumCaseManHours(int task_id) throws SQLException {
		ArrayList<AllDTO> list = new ArrayList<AllDTO>();
		String sql ="SELECT m.work_date AS '作業日', t.manager AS '担当者', m.today_man_hours AS '工数', m.work_details AS '作業内容' FROM man_hours m"
					+ "JOIN tasks t ON m.task_id = t.task_id WHERE m.task_id LIMIT 4";
		System.out.println(sql);
		PreparedStatement pStmt = conn.prepareStatement(sql);
		pStmt.setInt(1,task_id);
		
		//セレクト文を実行し結果票を取得
		ResultSet rs = pStmt.executeQuery();
		
		while(rs.next()) {
			AllDTO dto = new AllDTO();
			dto.setTaskId(rs.getInt("task_id"));
			list.add(dto);
		}
		return list;
		
	}

	//実績工数
	public ArrayList<AllDTO> selectManHours(String case_id) throws SQLException {
		ArrayList<AllDTO> list = new ArrayList<AllDTO>();
		String sql ="SELECT t.case_id AS '案件コード', c.case_name AS '案件名', SUM(today_man_hours) AS '実績工数'"
					+"FROM tasks t JOIN man_hours m ON t.task_id = m.task_id JOIN cases c ON t.case_id = c.case_id GROUP BY t.case_id";
		System.out.println(sql);
		PreparedStatement pStmt = conn.prepareStatement(sql);
		pStmt.setString(1, case_id);
		
		//SELECT文を実行し結果票を取得
		ResultSet rs = pStmt.executeQuery();
		
		//移し替え
		while(rs.next()) {
			AllDTO dto = new AllDTO();
			dto.setCaseId(rs.getString("case_id"));
			list.add(dto);
		}
		return list;
		
	}

	
	
}