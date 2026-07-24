package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.AllDTO;
import dto.CaseDTO;

public class CaseDAO {

	public Connection conn = null;

	//コネクションを保持するコンストラクタ
	public CaseDAO(Connection conn) {
		this.conn = conn;
	}

	//案件全検索
	public ArrayList<AllDTO> selectCases() throws SQLException {
		ArrayList<AllDTO> caseList = new ArrayList<AllDTO>();

		String sql = "SELECT users.name , cases.case_id , case_name , customer_name , cases.status , cases.priority , cases.pm_id , cases.start_date , cases.end_date , budgeted_man_hours, cases.memo , SUM(today_man_hours) AS actual_man_hours,"
				+ "    COUNT(tasks.task_id) AS all_tasks,COUNT(CASE WHEN tasks.status='完了' THEN 1 ELSE NULL END) AS completed_tasks"
				+ "	   FROM tasks"
				+ "    LEFT JOIN man_hours"
				+ "    ON tasks.task_id = man_hours.task_id"
				+ "    LEFT JOIN cases"
				+ "    ON tasks.case_id = cases.case_id"
				+ "	   LEFT JOIN users"
				+ "    ON users.user_id = cases.pm_id"
				+ "    GROUP BY tasks.case_id;";

		PreparedStatement pStmt = conn.prepareStatement(sql);

		ResultSet rs = pStmt.executeQuery();

		while (rs.next()) {
			AllDTO dto = new AllDTO();

			dto.setCaseId(rs.getString("case_id"));
			dto.setCaseName(rs.getString("case_name"));
			dto.setCustomerName(rs.getString("customer_name"));
			dto.setCaseStatus(rs.getString("status"));
			dto.setCasePriority(rs.getString("priority"));
			dto.setPmId(rs.getInt("pm_id"));
			dto.setName(rs.getString("name"));
			dto.setCaseStartDate(rs.getString("start_date"));
			dto.setEndDate(rs.getString("end_date"));
			dto.setActualManHours(rs.getDouble("actual_man_hours"));
			dto.setAllTasks(rs.getInt("all_tasks"));
			dto.setCompletedTasks(rs.getInt("completed_tasks"));
			dto.setBudgetedManHours(rs.getDouble("budgeted_man_hours"));
			dto.setCaseMemo(rs.getString("memo"));

			caseList.add(dto);
		}
		
		return caseList;
	}

	//案件登録
	public int registCase(CaseDTO dto) throws SQLException {
		int ans = 0;

		String sql = "INSERT INTO cases (case_id, case_name, customer_name, status, priority, pm_id, start_date, end_date, budgeted_man_hours, memo) VALUES(?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pStmt = conn.prepareStatement(sql);

		pStmt.setString(1, dto.getCaseId());
		pStmt.setString(2, dto.getCaseName());
		pStmt.setString(3, dto.getCustomerName());
		pStmt.setString(4, dto.getStatus());
		pStmt.setString(5, dto.getPriority());
		pStmt.setInt(6, dto.getPmId());
		pStmt.setString(7, dto.getStartDate());
		pStmt.setString(8, dto.getEndDate());
		pStmt.setDouble(9, dto.getBudgetedManHours());
		pStmt.setString(10, dto.getMemo());

		ans = pStmt.executeUpdate();
		
		return ans;
	}

	//案件編集
	public int editCase(CaseDTO dto) throws SQLException {
		int ans = 0;

		String sql = "UPDATE cases SET case_id = ?,case_name = ?,customer_name = ?,status = ?,priority = ?, pm_id = ?, start_date = ?,end_date = ?, budgeted_man_hours = ?,memo = ? WHERE  case_id = ? ";
		PreparedStatement pStmt = conn.prepareStatement(sql);

		pStmt.setString(1, dto.getCaseId());
		pStmt.setString(2, dto.getCaseName());
		pStmt.setString(3, dto.getCustomerName());
		pStmt.setString(4, dto.getStatus());
		pStmt.setString(5, dto.getPriority());
		pStmt.setInt(6, dto.getPmId());
		pStmt.setString(7, dto.getStartDate());
		pStmt.setString(8, dto.getEndDate());
		pStmt.setDouble(9, dto.getBudgetedManHours());
		pStmt.setString(10, dto.getMemo());

		pStmt.setString(11, dto.getCaseId());

		ans = pStmt.executeUpdate();

		return ans;
	}

	//案件コードの重複チェック
	public int checkDuplicateCaseId(String caseId) throws SQLException {
		int ans = 0;

		String sql = "SELECT COUNT(*) FROM cases WHERE case_id = ?";
		PreparedStatement pStmt = conn.prepareStatement(sql);

		pStmt.setString(1, caseId);

		ResultSet rs = pStmt.executeQuery();

		//(1)は左側から1個目を指定している。serviceの方でansの値を使用してエラーチェックを行う
		while (rs.next()) {
			ans = rs.getInt(1); //0か1が入る。
		}

		return ans;
	}

	//進行中案件検索(ダッシュボード)
	public ArrayList<AllDTO> selectWorkingCases() throws SQLException {
		ArrayList<AllDTO> caseList = new ArrayList<AllDTO>();

		String sql = "SELECT users.name,case_id,case_name,customer_name,pm_id,status,priority "
					+ "FROM cases LEFT JOIN users ON users.user_id = cases.pm_id WHERE status = '進行中';";
		PreparedStatement pStmt = conn.prepareStatement(sql);

		ResultSet rs = pStmt.executeQuery();

		while (rs.next()) {
			AllDTO dto = new AllDTO();

			dto.setCaseId(rs.getString("case_id"));
			dto.setCaseName(rs.getString("case_name"));
			dto.setCustomerName(rs.getString("customer_name"));
			dto.setPmId(rs.getInt("pm_id"));
			dto.setName(rs.getString("name"));
			dto.setCaseStatus(rs.getString("status"));
			dto.setCasePriority(rs.getString("priority"));
			caseList.add(dto);
		}

		return caseList;
	}

	//ステータス変更メソッド
	public int updatestatus(String caseId, String status) throws SQLException {
		int ans = 0;
		String sql = "UPDATE cases SET status=? WHERE case_id = ?";
		PreparedStatement pStmt = conn.prepareStatement(sql);

		pStmt.setString(1, status);
		pStmt.setString(2, caseId);
		ans = pStmt.executeUpdate();

		return ans;
	}

	//案件詳細用メソッド
	public AllDTO selectDetailCase(String caseId) throws SQLException {
		AllDTO dto = new AllDTO();

		String sql = "SELECT name , cases.case_id , case_name , customer_name , cases.status , cases.priority , cases.pm_id , cases.start_date , cases.end_date , cases.memo , budgeted_man_hours , SUM(today_man_hours) AS actual_man_hours,"
				+ "    COUNT(tasks.task_id) AS all_tasks ,COUNT(CASE WHEN tasks.status='完了' THEN 1 ELSE NULL END) AS completed_tasks"
				+ "	FROM tasks JOIN man_hours"
				+ "    ON tasks.task_id = man_hours.task_id"
				+ "    JOIN cases"
				+ "    ON tasks.case_id = cases.case_id"
				+ "    JOIN users"
				+ "    ON users.user_id = cases.pm_id"
				+ "    GROUP BY tasks.case_id"
				+ "    HAVING case_id = ?";
		
		PreparedStatement pStmt = conn.prepareStatement(sql);

		pStmt.setString(1, caseId);

		ResultSet rs = pStmt.executeQuery();
		
		while (rs.next()) {
		
		dto.setName(rs.getString("name"));
		dto.setCaseId(rs.getString("case_id"));
		dto.setCaseName(rs.getString("case_name"));
		dto.setCustomerName(rs.getString("customer_name"));
		dto.setCaseStatus(rs.getString("status"));
		dto.setCasePriority(rs.getString("priority"));
		dto.setPmId(rs.getInt("pm_id"));
		dto.setCaseStartDate(rs.getString("start_date"));
		dto.setEndDate(rs.getString("end_date"));
		dto.setBudgetedManHours(rs.getDouble("budgeted_man_hours"));
		dto.setCaseMemo(rs.getString("memo"));
		dto.setActualManHours(rs.getDouble("actual_man_hours"));
		dto.setAllTasks(rs.getInt("all_tasks"));
		dto.setCompletedTasks(rs.getInt("completed_tasks"));
		
		}

		return dto;
	}

}