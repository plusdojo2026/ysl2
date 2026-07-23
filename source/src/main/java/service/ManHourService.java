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
	public int registManHour(Double today_man_hours, String work_details, String work_date){
		super.access(); 
		ManHourDAO dao = new ManHourDAO(conn);
		int ans = 0;
		try {
			ans = dao.registManHour(today_man_hours, work_details, work_date);
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
	public ArrayList<AllDTO> selectCaseName(int task_id){
		super.access(); 
		ArrayList<AllDTO> list = null;
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
}
