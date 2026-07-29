package dao;

import java.sql.Connection;
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
	public int registManHour(double todayManHours, String workDetails, String work_date, int task_id,int user_id) throws SQLException{
 		int ans = 0;
		String sql ="INSERT INTO man_hours (today_man_hours, work_details, work_date, task_id, user_id) VALUES(?,?,?,?,?)";
		System.out.println(sql);
		// まとめる
		PreparedStatement pStmt = conn.prepareStatement(sql);
		System.out.println("today_man_hours = " + todayManHours);
		System.out.println("work_details = " + workDetails);
		System.out.println("work_date = " + work_date);
		pStmt.setDouble(1, todayManHours);
		pStmt.setString(2, workDetails);
		pStmt.setString(3, work_date);
		pStmt.setInt(4, task_id);
		pStmt.setInt(5, user_id);
		ans = pStmt.executeUpdate();
		return ans;

	}

	//削除
	public int deleteManHour(int man_hours_id) throws SQLException {
		int ans = 0;
		String sql ="DELETE FROM man_hours WHERE man_hours_id=?";
		System.out.println(sql);
		PreparedStatement pStmt = conn.prepareStatement(sql);
		
		pStmt.setInt(1, man_hours_id);
		ans = pStmt.executeUpdate();
		
		return ans;
	}

	
	//案件名・タスク名（工数登録）
	public AllDTO selectCaseName(int task_id) throws SQLException {
		AllDTO list = new AllDTO();
		String sql ="SELECT t.task_id, c.case_name, t.task_name FROM tasks t JOIN cases c ON t.case_id = c.case_id WHERE t.task_id = ?";
		System.out.println(sql);
		PreparedStatement pStmt = conn.prepareStatement(sql);
		pStmt.setInt(1, task_id);
		//SELECT文を実行し結果票を取得
		ResultSet rs = pStmt.executeQuery();
		pStmt.setInt(1, task_id);
		//移し替え
		while(rs.next()) {
			System.out.println("case_name=" + rs.getString("case_name"));
			System.out.println("task_name=" + rs.getString("task_name"));
			
			
			list.setCaseName(rs.getString("case_name"));
			list.setTaskName(rs.getString("task_name"));
			list.setTaskId(rs.getInt("task_id"));
			
		}

		return list;
	}


	//工数ログ(タスク詳細)
	public ArrayList<AllDTO> selectManHours(int task_id) throws SQLException {
		ArrayList<AllDTO> list = new ArrayList<AllDTO>();
		String sql ="SELECT m.work_date, u.name, m.today_man_hours, m.work_details,task_name ,m.man_hours_id FROM man_hours m JOIN tasks t ON m.task_id = t.task_id JOIN users u ON u.user_id = m.user_id WHERE t.task_id = ? ORDER BY m.work_date ";
		System.out.println(sql);
		PreparedStatement pStmt = conn.prepareStatement(sql);
		pStmt.setInt(1, task_id);
		
		//セレクト文を実行し結果票を取得
		ResultSet rs = pStmt.executeQuery();
		
		while(rs.next()) {
			AllDTO dto = new AllDTO();
			dto.setWorkDate(rs.getString("work_date"));
			dto.setTaskName(rs.getString("task_name"));
			dto.setName(rs.getString("name"));
			dto.setTodayManHours(rs.getDouble("today_man_hours"));
			dto.setWorkDetails(rs.getString("work_details"));
			dto.setManHoursId(rs.getInt("man_hours_id"));
			list.add(dto);
		}
		

		return list;
		
	}

	//実績工数
	public ArrayList<AllDTO> sumCaseManHours(int task_id) throws SQLException {
		ArrayList<AllDTO> list = new ArrayList<AllDTO>();
		String sql ="SELECT t.case_id AS '案件コード', c.case_name AS '案件名', SUM(today_man_hours) AS '実績工数'"
					+" FROM tasks t JOIN man_hours m ON t.task_id = m.task_id JOIN cases c ON t.case_id = c.case_id GROUP BY t.case_id";
		System.out.println(sql);
		PreparedStatement pStmt = conn.prepareStatement(sql);
		pStmt.setInt(1,task_id);
		//SELECT文を実行し結果票を取得
		ResultSet rs = pStmt.executeQuery();
		
		//移し替え
		while(rs.next()) {
			AllDTO dto = new AllDTO();
			dto.setCaseId(rs.getString("案件コード"));
			dto.setCaseName(rs.getString("案件名"));
			dto.setTodayManHours(rs.getDouble("実績工数"));
			list.add(dto);
		}
		

		return list;
		
	}

	//タスクごとの実績工数
		public ArrayList<AllDTO> sumTaskManHours() throws SQLException {
			ArrayList<AllDTO> list = new ArrayList<AllDTO>();
			String sql ="SELECT tasks.task_id, task_name , SUM(today_man_hours) "
					+ " FROM tasks"
					+ " JOIN man_hours"
					+ " ON tasks.task_id = man_hours.task_id"
					+ " GROUP BY tasks.task_id";
			System.out.println(sql);
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			//セレクト文を実行し結果票を取得
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next()) {
				AllDTO dto = new AllDTO();
				dto.setTaskId(rs.getInt("task_id"));
				dto.setTaskName(rs.getString("task_name"));
				dto.setTodayManHours(rs.getDouble("today_man_hours"));
				list.add(dto);
			}
			

			return list;
			
		}
	
		//工数ログ(案件詳細)
		public ArrayList<AllDTO>selectCaseManHours(String case_id)throws SQLException{
			ArrayList<AllDTO> list = new ArrayList<AllDTO>();
			
			String sql = "SELECT m.work_date, u.name, m.today_man_hours, m.work_details, t.task_name, t.case_id FROM man_hours m JOIN tasks t ON m.task_id = t.task_id JOIN users u ON u.user_id = m.user_id HAVING t.case_id = ? ORDER BY m.work_date";
			
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			pStmt.setString(1,case_id);
			
			
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next()) {
				AllDTO dto = new AllDTO();
				dto.setWorkDate(rs.getString("work_date"));
				dto.setTaskName(rs.getString("task_name"));
				dto.setTodayManHours(rs.getDouble("today_man_hours"));
				dto.setWorkDetails(rs.getString("work_details"));
				dto.setName(rs.getString("name"));
				list.add(dto);
			}
			
			return list;
		}
		
	
}