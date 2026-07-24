package action;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import dto.ManHourDTO;
import service.ManHourService;

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
		String id = request.getParameter("task_id");System.out.println("task_id=" + id);
		int taskId = Integer.parseInt(request.getParameter("task_id"));
		
		ManHourService service = new ManHourService();
		service.selectManHours(taskId);
		request.setAttribute("task_id", taskId);
		
		return page;
	}
	
	//工数登録メソッド
	public String registManHour() throws UnsupportedEncodingException{
		String page ="/WEB-INF/jsp/man_hour.jsp";
		//入力値の取得
		request.setCharacterEncoding("UTF-8");
		System.out.println("page_id=" + request.getParameter("page_id"));
		System.out.println("button_id=" + request.getParameter("button_id"));
		System.out.println("today_man_hours=" + request.getParameter("today_man_hours"));
		System.out.println("work_date=" + request.getParameter("work_date"));
		System.out.println("work_details=" + request.getParameter("work_details"));
		Double todayManHours =  Double.parseDouble(request.getParameter("today_man_hours"));
		String workDetails = request.getParameter("work_details");
		String workDate = request.getParameter("work_date");
		
		ManHourDTO dto = new ManHourDTO();
		dto.setTodayManHours(todayManHours);
		dto.setWorkDetails(workDetails);
		dto.setWorkDate(workDate);
		
		ManHourService service = new ManHourService();
		service.registManHour(todayManHours, workDetails, workDate);
		request.setAttribute("today_man_hours", todayManHours);
		request.setAttribute("work_details", workDetails);
		request.setAttribute("work_date", workDate);
		return page;
		
	}
	
	//工数削除メソッド
	public String deleteManHour() throws UnsupportedEncodingException{
		String page ="/WEB-INF/jsp/task_detail.jsp";
		request.setCharacterEncoding("UTF-8");
		int  manHourId = Integer.parseInt(request.getParameter("man_hour_id"));
		
		ManHourService service = new ManHourService();
		service.deleteManHour(manHourId);
		request.setAttribute("man_hour_id", manHourId);
		return page;
		
	}

}
