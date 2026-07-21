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
	
	//集計案件一覧検索メソッド
	public ArrayList<ManHourDTO> selectManthlySum() throws SQLException{

		ArrayList<ManHourDTO> TotalCasesAndManHours = new ArrayList<ManHourDTO>();//変数TotalManHoursで工数DTOをnewする
		
		String sql ="";//SQL文を準備
		System.out.println(sql);//SQL文の確認用
		PreparedStatement pStmt = conn.prepareStatement(sql);//connとSQLをpStmtにまとめる
		
		ResultSet rs = pStmt.executeQuery();//結果をrsにまとめる
		
	}
	
	//月ごとの実績工数計算メソッド
	public ArrayList<AllDTO> sumManthlyCasesManHours(String yearManth) throws SQLException{
		
		ArrayList<AllDTO> ManthlyCases = new ArrayList<AllDTO>();//変数ManthlyCasesでAllDTOをnewする
		
		String sql ="";//SQL文を準備
		System.out.println(sql);//SQL文の確認用
		PreparedStatement pStmt = conn.prepareStatement(sql);//connとSQLをpStmtにまとめる
		
		ResultSet rs = pStmt.executeQuery();//結果をrsにまとめる
		
		//DTOに取得したデータをセットする
		while(rs.next()) {
			
		}
		//Serviceに返却
		return ManthlyCases;
	}
	
	//月別、担当者ごとの工数集計メソッド（メンバー別集計テーブル）
	public ArrayList <AllDTO> sumUsersManHours(String yearManth) throws SQLException{
		
		ArrayList<AllDTO> ManthAndMembers = new ArrayList<AllDTO>();//変数ManthAndMemberでAllDTOをnewする
		
		String sql ="";//SQL文を準備
		System.out.println(sql);//SQL文の確認用
		PreparedStatement pStmt = conn.prepareStatement(sql);//connとSQLをpStmtにまとめる
		
		ResultSet rs = pStmt.executeQuery();//結果をrsにまとめる
		
		//DTOに取得したデータをセットする
		while(rs.next()) {
			
		}
		//Serviceに返却
		return ManthAndMembers;
	}
	
}