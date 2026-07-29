package service;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.ManHourDAO;
import dto.AllDTO;

public class ManHourService extends DBAccess{
	
	public ManHourService() {
		super.access();  //DBAccessのaccessを継承
	}
	
	//実績工数
	public ArrayList<AllDTO> sumCaseManHours(int tsak_id) {
		super.access();
		ArrayList<AllDTO> list = null;
		ManHourDAO dao = new ManHourDAO(conn);
		try {
			list = dao.sumCaseManHours(tsak_id);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		super.close();
		return list;
	}
	
	//工数登録メソッド
	public int registManHour(double todayManHours, String workDetails, String work_date, int task_id, int user_id){
		super.access();
		ManHourDAO dao = new ManHourDAO(conn);
		int ans = 0;
		try {
			ans = dao.registManHour(todayManHours, workDetails, work_date, task_id, user_id);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		super.close();
		return ans;
		
	}
	
	//工数ログ削除メソッド
	public int deleteManHour(int man_hours_id) {
		super.access();
		ManHourDAO dao = new ManHourDAO(conn);
		int ans = 0;
		try {
			ans = dao.deleteManHour(man_hours_id);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		super.close();
		return ans;
		
	}
	
	//工数ログ
	public ArrayList<AllDTO> selectManHours(int task_id){
		super.access();
		ArrayList<AllDTO> list = null;
		ManHourDAO dao = new ManHourDAO(conn);
		try {
			list = dao.selectManHours(task_id);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		super.close();
		return list;
	}
	
	//案件名・タスク名（工数登録）
	public AllDTO selectCaseName(int task_id){
		super.access();
		AllDTO list = null;
		ManHourDAO dao = new ManHourDAO(conn);
		try {
			list = dao.selectCaseName(task_id);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		super.close();
		return list;
	}
	
	//タスクごとの実績工数
	public ArrayList<AllDTO> sumTaskManHours(){
		super.access();
		ArrayList<AllDTO> list = null;
		ManHourDAO dao = new ManHourDAO(conn);
		try {
			list = dao.sumTaskManHours();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		super.close();
		return list;
		
	}
	//工数ログ(案件詳細)
	public ArrayList<AllDTO> selectCaseManHours(String cas_id){
		super.access();
		ArrayList<AllDTO> list = null;
		ManHourDAO dao = new ManHourDAO(conn);
		try {
			list = dao.selectCaseManHours(cas_id);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		super.close();
		return list;
	}

	
	
}
