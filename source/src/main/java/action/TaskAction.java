package action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import dto.AllDTO;
import dto.TaskDTO;
import dto.UserDTO;
import service.ManHourService;
import service.TaskService;
import service.UserService;

public class TaskAction {
	
	HttpServletRequest request;
	//コンストラクタ
	public TaskAction(HttpServletRequest request) {
		this.request = request;
	}
	
	//タスク一覧メソッド----------------------------------------------
	public String selectTask() throws UnsupportedEncodingException {
		//戻り値（遷移先）のURL
		String page = null;
		//DTOの箱
		ArrayList<AllDTO> tDto = null;
		ArrayList<AllDTO> mDto = null;
		ArrayList<UserDTO> uDTO = null;
		
		//Serviceを実体化
		TaskService tService = new TaskService();
		tDto = tService.selectTasks();
		
		ManHourService mService = new ManHourService();
		
		UserService uService = new UserService();
		
		return page;
	}
	
	//タスク登録メソッド----------------------------------------------
	public String registTask() throws UnsupportedEncodingException {
		//戻り値（遷移先）のURL
		String ans = null;
		//DTOの箱
		TaskDTO dto = null;
		
		return ans;
	}
	
	//タスク編集メソッド----------------------------------------------
	public String updateTask() throws UnsupportedEncodingException {
		//戻り値（遷移先）のURL
		String ans = null;
		//DTOの箱
		TaskDTO dto = null;
		
		return ans;
	}
	
	//タスク削除メソッド----------------------------------------------
	public String deleteTask() throws UnsupportedEncodingException {
		//戻り値（遷移先）のURL
		String ans = null;
		//DTOの箱
		TaskDTO dto = null;
		
		return ans;
	}
	
	//タスクステータス変更メソッド----------------------------------------------
	public String updateStatus() throws UnsupportedEncodingException {
		//戻り値（遷移先）のURL
		String ans = null;
		//DTOの箱
		TaskDTO dto = null;
		
		return ans;
	}

}
