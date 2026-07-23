package service;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.ManthlySumDAO;
import dto.AllDTO;


public class ManthlySumService extends DBAccess {

	//コンストラクタ
	public ManthlySumService() {
		super.access(); 
	}
	
	//集計案件一覧検索
	public ArrayList<AllDTO> selectManthlySum() throws SQLException{
		
		super.access();
		ArrayList<AllDTO> TotalCasesAndManHours = new ArrayList<AllDTO>();
		ManthlySumDAO dao = new ManthlySumDAO(super.conn);
		
		try {
			TotalCasesAndManHours = dao.selectManthlySum();
		}catch(SQLException e) {
			System.out.println("SQL文おかしいよ");
			e.printStackTrace();
		}
		
		super.close();
		return TotalCasesAndManHours;
	}
	
	//月ごとの実績工数
	public ArrayList<AllDTO> sumManthlyCasesManHours(String yearManth) throws SQLException{
		
		super.access();
		ArrayList<AllDTO> ManthlyManHours = new ArrayList<AllDTO>();
		ManthlySumDAO dao = new ManthlySumDAO(super.conn);
		
		try {
			ManthlyManHours = dao.sumManthlyCasesManHours(yearManth);
		}catch(SQLException e) {
			System.out.println("SQL文おかしいよ");
			e.printStackTrace();
		}
		
		super.close();
		return ManthlyManHours;
	}
	
	//担当者名・月ごと
	public ArrayList <AllDTO> sumUsersManHours(String yearManth) throws SQLException{
		
		super.access();
		ArrayList<AllDTO> ManthAndMembers = new ArrayList<AllDTO>();
		ManthlySumDAO dao = new ManthlySumDAO(super.conn);
		
		try {
			ManthAndMembers = dao.sumUsersManHours(yearManth);
		}catch(SQLException e) {
			System.out.println("SQL文おかしいよ");
			e.printStackTrace();
		}
		
		super.close();
		return ManthAndMembers;
	}
}
