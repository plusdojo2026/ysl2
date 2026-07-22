package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.AllDTO;
import dto.ManHourDTO;



public class ManthlySumDAO {
	
	public Connection conn = null;
	
	//コネクションを保持するコンストラクタ
	public ManthlySumDAO(Connection conn) {
		this.conn=conn;
	}
	
	//集計案件一覧検索メソッド(後でserviceで詰めなおす中身1/2)
	public ArrayList<ManHourDTO> selectManthlySum() throws SQLException{
					//↑<AllDTOに変更?>
		ArrayList<ManHourDTO> TotalCasesAndManHours = new ArrayList<ManHourDTO>();//変数TotalManHoursで工数DTOをnewする
		
		//SQLメモ
		//目的→作業日(work_date)が検索月と一致する案件コード(case_id)を検索し、案件名、プログレスバー(実績工数、予算工数)を取得
		//SQL文を準備・↓casesテーブルから案件コードと案件名、予算工数を取得
		String sql ="SELECT tasks.case_id AS '案件コード',SUM(today_man_hours) AS '実績工数'"
				+ "FROM tasks"
				+ "JOIN man_hours"
				+ "ON tasks.task_id = man_hours.task_id"
				+ "GROUP BY tasks.case_id;";
		
		System.out.println(sql);//SQL文の確認用
		PreparedStatement pStmt = conn.prepareStatement(sql);//connとSQLをpStmtにまとめる
		
		ResultSet rs = pStmt.executeQuery();//結果をrsにまとめる
		
		//DTOに取得したデータをセットする
		while(rs.next()) {
			ManHourDTO dto = new ManHourDTO();
			dto.setCaseId(rs.getString("case_id"));						//案件コード
			dto.setCaseName(rs.getString("case_name"));					//案件名
			dto.setBudgetedManHours(rs.getDouble("budgeted_man_hours"));//実績工数
			TotalCasesAndManHours.add(dto);
		}
		
		
		
		return TotalCasesAndManHours;
	}
	
	//月ごとの実績工数計算メソッド(後でserviceで詰めなおす中身2/2)
	public ArrayList<AllDTO> sumManthlyCasesManHours(String yearManth) throws SQLException{
		
		ArrayList<AllDTO> ManthlyCases = new ArrayList<AllDTO>();//変数ManthlyCasesでAllDTOをnewする
		
		//SQLメモ
		//月ごとの実績工数を取得する(method.sql参照 + 加工)
		//SQL文を準備
		String sql ="SELECT tasks.case_id AS '案件コード', cases.case_name AS '案件名', SUM(today_man_hours) AS '実績工数',"
				+ "cases.budgeted_man_hours AS '予算工数'"
				+ "FROM tasks"
				+ "LEFT JOIN man_hours"
				+ "ON tasks.task_id = man_hours.task_id"
				+ "LEFT JOIN cases"
				+ "ON tasks.case_id = cases.case_id"
				+ "WHERE work_date LIKE '2___-__%'"//今月分だけをヒットさせる
				+ "GROUP BY tasks.case_id;";
			
		System.out.println(sql);//SQL文の確認用
		PreparedStatement pStmt = conn.prepareStatement(sql);//connとSQLをpStmtにまとめる
		
		ResultSet rs = pStmt.executeQuery();//結果をrsにまとめる
		
		//DTOに取得したデータをセットする
		while(rs.next()) {
			AllDTO dto = new AllDTO();
			dto.setCaseId(rs.getString("case_id"));						//案件コード
			dto.setCaseName(rs.getString("case_name"));					//案件名
			dto.setTodayManHours(rs.getDouble("today_man_hours"));		//月ごとかつ案件ごとの実績工数
			dto.setBudgetedManHours(rs.getDouble("budgeted_man_hours"));//予算工数	
		}
		//Serviceに返却
		return ManthlyCases;
	}
	
	//月別、担当者ごとの工数集計メソッド（メンバー別集計テーブル）
	public ArrayList <AllDTO> sumUsersManHours(String yearManth) throws SQLException{
		
		ArrayList<AllDTO> ManthAndMembers = new ArrayList<AllDTO>();//変数ManthAndMemberでAllDTOをnewする
		
		//SQLメモ
		//担当者名と、検索月におけるそのメンバーのその月の合計工数、割合プログレスバー(実績タスク/総タスク)を表示
		//user_idをもとに、userテーブルのuser_idを持ってくる?
		//SQL文を準備
		String sql ="SELECT user.user_name FROM ON user;";
		
		
		System.out.println(sql);//SQL文の確認用
		PreparedStatement pStmt = conn.prepareStatement(sql);//connとSQLをpStmtにまとめる
		
		ResultSet rs = pStmt.executeQuery();//結果をrsにまとめる
		
		//DTOに取得したデータをセットする
		while(rs.next()) {
			AllDTO dto = new AllDTO();
			dto.setUserId(rs.getInt("user_id"));						//担当者(ユーザーIDで自動登録されているもの)
			dto.setActualManHours(rs.getDouble("actual_man_hours"));	//集計月における、その担当者のタスクの工数
			ManthAndMembers.add(dto);
		}
		//Serviceに返却
		return ManthAndMembers;
	}
	
}