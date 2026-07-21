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

		String sql = "SELECT * FROM cases";

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
			dto.setCaseStartDate(rs.getString("start_date"));
			dto.setEndDate(rs.getString("end_date"));
			dto.setBudgetedManHours(rs.getDouble("budgeted_man_hours"));
			dto.setCaseMemo(rs.getString("memo"));

			caseList.add(dto);
		}

		return caseList;
	}

	//案件登録
	public int registCase(CaseDTO dto) throws SQLException {
		int ans = 0;

		String sql = "INSERT INTO cases VALUES(?,?,?,?,?,?,?,?,?,?)";
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
	public ArrayList<CaseDTO> selectWorkingCases() throws SQLException {
		ArrayList<CaseDTO> caseList = new ArrayList<CaseDTO>();

		String sql = "SELECT * FROM cases WHERE status = '進行中'";
		PreparedStatement pStmt = conn.prepareStatement(sql);

		ResultSet rs = pStmt.executeQuery();

		while (rs.next()) {
			CaseDTO dto = new CaseDTO();

			dto.setCaseId(rs.getString("case_id"));
			dto.setCaseName(rs.getString("case_name"));
			dto.setCustomerName(rs.getString("customer_name"));
			dto.setStatus(rs.getString("status"));
			dto.setPriority(rs.getString("priority"));
			dto.setPmId(rs.getInt("pm_id"));
			dto.setStartDate(rs.getString("start_date"));
			dto.setEndDate(rs.getString("end_date"));
			dto.setBudgetedManHours(rs.getDouble("budgeted_man_hours"));
			dto.setMemo(rs.getString("memo"));

			caseList.add(dto);
		}

		return caseList;
	}

}