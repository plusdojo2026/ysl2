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

		ArrayList<ManHourDTO> TotalCasesAndManHours = new ArrayList<ManHourDTO>();//変数TotalManHoursで工数DTOをnewする
		
		//SQLメモ
		//目的→作成日(created_at)が検索月と一致する案件コード(case_id)を検索し、案件名、予算工数、プログレスバーを取得
		//SQL文を準備・↓casesテーブルから案件コードと案件名、予算工数を取得
		String sql ="SELECT case_id,case_name,budgeted_man_hours FROM cases;";
		
		System.out.println(sql);//SQL文の確認用
		PreparedStatement pStmt = conn.prepareStatement(sql);//connとSQLをpStmtにまとめる
		
		ResultSet rs = pStmt.executeQuery();//結果をrsにまとめる
		
		//DTOに取得したデータをセットする
		while(rs.next()) {
			ManHourDTO dto = new ManHourDTO();
			dto.setCaseId(rs.getString("case_id"));						//案件コード
			dto.setCaseName(rs.getString("case_name"));					//案件名
			dto.setBudgetedManHours(rs.getDouble("budgeted_man_hours"));//予算工数
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
		String sql ="SELECT tasks.case_id AS '案件コード',SUM(today_man_hours) AS '今月の実績工数'"
					+ "FROM tasks"
					+ "JOIN man_hours"
					+ "ON tasks.task_id = man_hours.task_id"
					+ "JOIN cases"
					+ "ON tasks.case_id = cases.case_id"
					+ "GROUP BY tasks.case_id;"
					+ "HAVING MONTH(work_date) = ?;";//今月分だけをヒットさせる関数で絞る
		
		
		System.out.println(sql);//SQL文の確認用
		PreparedStatement pStmt = conn.prepareStatement(sql);//connとSQLをpStmtにまとめる
		
		ResultSet rs = pStmt.executeQuery();//結果をrsにまとめる
		
		
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