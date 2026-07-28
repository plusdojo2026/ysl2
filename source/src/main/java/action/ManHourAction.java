package action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import dto.AllDTO;
import dto.ManHourDTO;
import dto.UserDTO;
import service.CaseService;
import service.ManHourService;
import service.TaskService;
import service.UserService;

public class ManHourAction {
	
	HttpServletRequest request;
	
	//コンストラクタ
	public ManHourAction(HttpServletRequest request) {
		this.request=request;
	}
	
	//工数取得メソッド(工数ログ)
	public String selectManHour() throws UnsupportedEncodingException {
		
		String page ="/WEB-INF/jsp/case_detail.jsp";
		
		//値の取得
		request.setCharacterEncoding("UTF-8");
		String caseId = request.getParameter("task_id");
		System.out.println("case_id=" + caseId);
		int taskId = Integer.parseInt(request.getParameter("task_id"));
		System.out.println("task_id=" + taskId);
		
		AllDTO dto = new AllDTO();
		dto.setTaskId(taskId);
		ManHourService service = new ManHourService();
		ArrayList<AllDTO> list = service.selectManHours(taskId);
		ArrayList<AllDTO> clist = service.selectCaseManHours(caseId);
		request.setAttribute("list", list);
		request.setAttribute("clist", clist);
		
		return page;
	}
	
	//casename,taskname
	public String selectName() throws UnsupportedEncodingException {
		String page ="/WEB-INF/jsp/man_hour.jsp";
		
		request.setCharacterEncoding("UTF-8");
		int taskId = Integer.parseInt(request.getParameter("task_id"));
		AllDTO dto = new AllDTO();
		dto.setTaskId(taskId);
		ManHourService service = new ManHourService();
		AllDTO list = service.selectCaseName(taskId);
		request.setAttribute("list", list);
		return page;
		
	}
	
	//工数登録メソッド
	public String registManHour() throws UnsupportedEncodingException{
		String page ="/WEB-INF/jsp/task_detail.jsp";
		//入力値の取得
		request.setCharacterEncoding("UTF-8");
		
		double todayManHours =  Double.parseDouble(request.getParameter("today_man_hours"));
		String workDetails = request.getParameter("work_details");
		String workDate = request.getParameter("work_date");
		int taskId = Integer.parseInt(request.getParameter("task_id"));
		HttpSession session = request.getSession();
		UserDTO user = (UserDTO) session.getAttribute("user");
		int userId = user.getUserId();
		
		ManHourDTO dto = new ManHourDTO();
		dto.setTodayManHours(todayManHours);
		dto.setWorkDetails(workDetails);
		dto.setWorkDate(workDate);
		dto.setTaskId(taskId);
		dto.setUserId(userId);
		
		ManHourService service = new ManHourService();
		int list = service.registManHour(todayManHours, workDetails, workDate, taskId, userId);
		request.setAttribute("list", list);
		
		if (list==1) {
			System.out.println("登録成功");
		}else {
			System.out.println("登録失敗");
		}
		
		//以下、タスク詳細表示用データの取得と格納↓
				//dtoの箱
				AllDTO tdto = null;
				ArrayList<AllDTO> mdto = null;
				ArrayList<UserDTO> uList = null;
				ArrayList<AllDTO> cList = null;
				//Serviceの実体化
				TaskService tService = new TaskService();
				tdto = tService.selectTaskDetail(taskId);
				ManHourService mService = new ManHourService();
				mdto  = mService.selectManHours(taskId);
				UserService uService = new UserService();
				uList = uService.selectActiveUsers();
				request.setAttribute("activeUsersList", uList);
				CaseService cService = new CaseService();
				cList = cService.selectCases();
				request.setAttribute("casesList", cList);
				
				System.out.println(taskId);
				//リクエストスコープに格納
				request.setAttribute("task", tdto);
				request.setAttribute("manHoursList", mdto);
		return page;
		
	}
	
	//工数削除メソッド
	public String deleteManHour() throws UnsupportedEncodingException{
		String page ="/WEB-INF/jsp/task_detail.jsp";
		request.setCharacterEncoding("UTF-8");
		System.out.println(
			    "man_hours_id=[" +
			    request.getParameter("man_hours_id") +
			    "]"
			);

		int  manHourId = Integer.parseInt(request.getParameter("man_hours_id"));
		int  taskId = Integer.parseInt(request.getParameter("task_id"));
		AllDTO dto = new AllDTO();
		
		dto.setTaskId(taskId);
		dto.setManHoursId(manHourId);
		ManHourService service = new ManHourService();
		
		int list = service.deleteManHour(manHourId);
		ArrayList<AllDTO> man =  service.selectManHours(taskId);
		request.setAttribute("list", list);
		if (list==1) {
			System.out.println("削除成功");
		}else {
			System.out.println("削除失敗");
		}
		request.setAttribute("man", man);
		return page;
		
	}

}
