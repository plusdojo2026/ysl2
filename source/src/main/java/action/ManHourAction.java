package action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import dto.AllDTO;
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
		int  taskId = Integer.parseInt(request.getParameter("task_id"));
		
		ManHourService service = new ManHourService();
		ArrayList<AllDTO> list = service.selectManHours(taskId);
		request.setAttribute("list", list);
		
		return page;
	}
	
	//工数登録メソッド
	public String registManHour() throws UnsupportedEncodingException{
		String page ="/WEB=INF/jsp/task_detail.jsp";
		//入力値の取得
		request.setCharacterEncoding("UTF-8");
		Double todayManHours =  Double.parseDouble(request.getParameter("today_man_hours"));
		String workDetails = request.getParameter("work_details");
		String workDate = request.getParameter("work_date");
		
		ManHourService service = new ManHourService();
		service.registManHour(todayManHours, workDetails, workDate);
		int list = service.registManHour(todayManHours, workDate, workDate);
		request.setAttribute("list", list);
		
		return page;
		
	}
	
	//工数削除メソッド
	public String deleteManHour() throws UnsupportedEncodingException{
		String page ="/WEB-INF/jsp/task_detail.jsp";
		request.setCharacterEncoding("UTF-8");
		int  manHourId = Integer.parseInt(request.getParameter("man_hour_id"));
		
		ManHourService service = new ManHourService();
		service.deleteManHour(manHourId);
		int list = service.deleteManHour(manHourId);
		request.setAttribute("list", list);
		return page;
		
	}

}
