package service;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.ManHourDAO;
import dto.AllDTO;

public class ManHourService {
	
	public ManHourService() {
		super.access();  //DBAccessのaccessを継承
	}
	
	//実績工数
	public ArrayList<AllDTO> selectManHours(String case_id) {
		ArrayList<AllDTO> list = null;
		ManHourDAO dao = new ManHourDAO(conn);
		try {
			list = dao.selectManHours(case_id);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		super.close();
		return list;
	}
	
	//工数登録メソッド
	public int registManHour(Double today_man_hours, String work_details, Date work_date){
		ManHourDAO dao = new ManHourDAO(conn);
		int ans = 0;
		try {
			ans = dao.registManHour(today_man_hours, work_details, work_date);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return 0;
		
	}
	
	//工数ログ削除メソッド
	public int deleteManHour(int man_hours_id) {
		ManHourDAO dao = new ManHourDAO(conn);
		int ans = 0;
		try {
			ans = dao.deleteManHour(man_hours_id);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return 0;
		
	}
	
	//工数ログ
	public ArrayList<AllDTO> sumCaseManHours(int task_id){
		ArrayList<AllDTO> list = null;
		ManHourDAO dao = new ManHourDAO(conn);
		try {
			list = dao.sumCaseManHours(task_id);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//案件名・タスク名（工数登録）
	public ArrayList<AllDTO> selectCaseName(int task_id){
		ArrayList<AllDTO> list = null;
		ManHourDAO dao = new ManHourDAO(conn);
		try {
			list = dao.selectCaseName(task_id);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
