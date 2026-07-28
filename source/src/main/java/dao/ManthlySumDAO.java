package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.AllDTO;

public class ManthlySumDAO {
	
	public Connection conn = null;
	
	//コネクションを保持するコンストラクタ
	public ManthlySumDAO(Connection conn) {
		this.conn=conn;
	}
	
	//集計案件一覧検索メソッド(後でserviceで詰めなおす中身1/2)
	public ArrayList<AllDTO> selectManthlySum() throws SQLException{
					
		ArrayList<AllDTO> TotalCasesAndManHours = new ArrayList<AllDTO>();//変数TotalManHoursで工数DTOをnewする
		
		//目的→案件コード(case_id)ごとに案件名、実績工数、予算工数(プログレスバー?)を取得
		String sql ="SELECT tasks.case_id , cases.case_name, SUM(today_man_hours) AS today_man_hours, "
					+ "cases.budgeted_man_hours "
					+ "FROM tasks "
					+ "LEFT JOIN man_hours "
					+ "ON tasks.task_id = man_hours.task_id "
					+ "LEFT JOIN cases "
					+ "ON tasks.case_id = cases.case_id "
					+ "GROUP BY tasks.case_id;";
		
		System.out.println(sql);//SQL文の確認用
		PreparedStatement pStmt = conn.prepareStatement(sql);//connとSQLをpStmtにまとめる
		ResultSet rs = pStmt.executeQuery();//結果をrsにまとめる
		
		//DTOに取得したデータをセットする
		while(rs.next()) {
			AllDTO dto = new AllDTO();
			dto.setCaseId(rs.getString("case_id"));						//案件コード
			dto.setCaseName(rs.getString("case_name"));					//案件名
			dto.setTodayManHours(rs.getDouble("today_man_hours"));		//案件ごとの累計工数
			dto.setBudgetedManHours(rs.getDouble("budgeted_man_hours"));//予算工数
			TotalCasesAndManHours.add(dto);								//詰め直す工程を忘れずに!!
		}
		return TotalCasesAndManHours;
	}
	
	//月ごとの実績工数計算メソッド(後でserviceで詰めなおす中身2/2)
	public ArrayList<AllDTO> sumManthlyCasesManHours(String yearManth) throws SQLException{
		
		ArrayList<AllDTO> ManthlyManHours = new ArrayList<AllDTO>();//変数ManthlyCasesでAllDTOをnewする
		
		//月ごとの実績工数を取得する
		//SQL文を準備 //今月分だけをヒットさせる
		String sql = "SELECT case_id ,SUM(today_man_hours) AS actual_man_hours FROM tasks LEFT JOIN man_hours ON tasks.task_id = man_hours.task_id  WHERE work_date LIKE ? GROUP BY tasks.case_id";	
		System.out.println(sql);//SQL文の確認用
		PreparedStatement pStmt = conn.prepareStatement(sql);//connとSQLをpStmtにまとめる
		pStmt.setString(1,'%'+yearManth+'%');//?に年月のデータを入れる
		ResultSet rs = pStmt.executeQuery();//結果をrsにまとめる
		//DTOに取得したデータをセットする
		while(rs.next()) {
			AllDTO dto = new AllDTO();
			dto.setCaseId(rs.getString("case_id"));					//案件コード
			dto.setActualManHours(rs.getDouble("actual_man_hours"));//月ごとかつ案件ごとの実績工数
			ManthlyManHours.add(dto);								//詰め直す工程を忘れずに!!
		}
		//Serviceに返却
		return ManthlyManHours;
	}
	
	//月別、担当者ごとの工数集計メソッド（メンバー別集計テーブル）
	public ArrayList <AllDTO> sumUsersManHours(String yearManth) throws SQLException{
		
		ArrayList<AllDTO> ManthAndMembers = new ArrayList<AllDTO>();//変数ManthAndMemberでAllDTOをnewする
		
		//担当者名と、検索月におけるそのメンバーのその月の合計工数
		//割合プログレスバーは、各メンバーの実績工数/各メンバーの実績工数の合計
		//user_idをもとに、担当者名とその人の今月の工数を表示
		String sql ="SELECT users.name , SUM(man_hours.today_man_hours) AS actual_man_hours "
				+ "	FROM users LEFT JOIN man_hours ON users.user_id = man_hours.user_id "
				+ "WHERE work_date LIKE ? GROUP BY users.user_id;";
		
		System.out.println(sql);//SQL文の確認用
		PreparedStatement pStmt = conn.prepareStatement(sql);//connとSQLをpStmtにまとめる
		pStmt.setString(1,'%'+yearManth+'%');//?に年月のデータを入れる
		ResultSet rs = pStmt.executeQuery();//結果をrsにまとめる
		
		//DTOに取得したデータをセットする
		while(rs.next()) {
			AllDTO dto = new AllDTO();
			dto.setName(rs.getString("name"));						//担当者(ユーザーIDで自動登録されているもの)
			dto.setActualManHours(rs.getDouble("actual_man_hours"));//集計月における、その担当者のタスクの工数
			ManthAndMembers.add(dto);								//詰め直す工程を忘れずに!!
		}
		//Serviceに返却
		return ManthAndMembers;
	}
	
}