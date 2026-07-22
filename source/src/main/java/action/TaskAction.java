package action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import dto.AllDTO;
import dto.TaskDTO;
import dto.UserDTO;
import service.TaskService;

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
		ArrayList<AllDTO> tDTO = null;
		ArrayList<AllDTO> mDTO = null;
		ArrayList<UserDTO> uDTO = null;
		
		return page;
	}
	
	//タスク登録メソッド----------------------------------------------
	public String registTask() throws UnsupportedEncodingException {
		//戻り値（遷移先）のURL
		String page = null;
		//DTOの箱
		TaskDTO dto = null;
		
		//入力値の取得
		request.setCharacterEncoding("UTF-8");
		String caseId = request.getParameter("caseId");
		String taskName = request.getParameter("taskName");
		int manager = Integer.parseInt(request.getParameter("manager"));
		String startDate = request.getParameter("startDate");
		String deadlineDate = request.getParameter("deadlineDate");
		String priority = request.getParameter("priority");
		String status = request.getParameter("status");
		double estimatedManHours = Double.parseDouble(request.getParameter("estimatedManHours"));
		int taskProgress = Integer.parseInt(request.getParameter("taskProgress"));
		String memo = request.getParameter("memo");
		
		//dtoにまとめる
		dto.setCaseId(caseId);
		dto.setTaskName(taskName);
		dto.setManager(manager);
		dto.setStartDate(startDate);
		dto.setDeadlineDate(deadlineDate);
		dto.setPriority(priority);
		dto.setStatus(status);
		dto.setEstimatedManHours(estimatedManHours);
		dto.setTaskProgress(taskProgress);
		dto.setMemo(memo);
		
		//Serviceを実体化
		TaskService tService = new TaskService();
		int ans = tService.registTask(dto);
		
		if(ans == 1) {
			request.setAttribute("msg", "案件を登録しました");
		}else {
			request.setAttribute("msg", "案件の登録に失敗しました");
		}
		
		//タスク一覧情報を取得
		
		
		return page;
	}
	
	//タスク編集メソッド----------------------------------------------
	public String updateTask() throws UnsupportedEncodingException {
		//戻り値（遷移先）のURL
		String page = null;
		//DTOの箱
		TaskDTO dto = null;
		
		//入力値の取得
		request.setCharacterEncoding("UTF-8");
		String caseId = request.getParameter("caseId");
		String taskName = request.getParameter("taskName");
		int manager = Integer.parseInt(request.getParameter("manager"));
		String startDate = request.getParameter("startDate");
		String deadlineDate = request.getParameter("deadlineDate");
		String priority = request.getParameter("priority");
		String status = request.getParameter("status");
		double estimatedManHours = Double.parseDouble(request.getParameter("estimatedManHours"));
		int taskProgress = Integer.parseInt(request.getParameter("taskProgress"));
		String memo = request.getParameter("memo");
		int taskId = Integer.parseInt(request.getParameter("taskId"));
		
		//dtoにまとめる
		dto.setCaseId(caseId);
		dto.setTaskName(taskName);
		dto.setManager(manager);
		dto.setStartDate(startDate);
		dto.setDeadlineDate(deadlineDate);
		dto.setPriority(priority);
		dto.setStatus(status);
		dto.setEstimatedManHours(estimatedManHours);
		dto.setTaskProgress(taskProgress);
		dto.setMemo(memo);
		dto.setTaskId(taskId);
		
		//Serviceを実体化
		TaskService tService = new TaskService();
		int ans = tService.updateTask(dto);
		
		if (ans == 1) {
			request.setAttribute("ans", "タスクを編集しました");
		}else {
			request.setAttribute("ans", "タスクの編集に失敗しました");
		}
		
		return page;
	}
	
	//タスク削除メソッド----------------------------------------------
	public String deleteTask() throws UnsupportedEncodingException {
		//戻り値（遷移先）のURL
		String page = null;
		
		//入力値取得
		int taskId = Integer.parseInt(request.getParameter("taskId"));
		
		//Serviceを実体化
		TaskService tService = new TaskService();
		int ans = tService.deleteTask(taskId);
		
		if (ans == 1) {
			request.setAttribute("ans", "タスクを削除しました");
		}else {
			request.setAttribute("ans", "タスクの削除に失敗しました");
		}
		
		return page;
	}
	
	//タスクステータス変更メソッド----------------------------------------------
	public String updateStatus() throws UnsupportedEncodingException {
		//戻り値（遷移先）のURL
		String page = null;
		//DTOの箱
		TaskDTO dto = null;
		
		//入力値取得
		int taskId = Integer.parseInt(request.getParameter("taskId"));
		String status = request.getParameter("status");
		
		//dtoにまとめる
		dto.setTaskId(taskId);
		dto.setStatus(status);
		
		//Serviceを実体化
		TaskService tService = new TaskService();
		int ans = tService.updateTask(dto);
		
		if (ans == 1) {
			request.setAttribute("ans", "タスクを削除しました");
		}else {
			request.setAttribute("ans", "タスクの削除に失敗しました");
		}
		
		return page;
	}

}
