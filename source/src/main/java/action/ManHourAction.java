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
		request.getParameter("work_date");
		request.getParameter("task_name");
		request.getParameter("task_name");
		request.getParameter("today_man_hours");
		request.getParameter("work_details");
		
		ManHourService service = new ManHourService();
		ArrayList<AllDTO> list = service.sumCaseManHours();
		request.setAttribute("list", list);
		
		return page;
	}
	
	//工数登録メソッド
	public String registManHour() throws UnsupportedEncodingException{
		String page ="/WEB=INF/jsp/task_detail.jsp";
		int ans  = 0;
		//入力値の取得
		request.setCharacterEncoding("UTF-8");
		Double todayManHours =  Double.parseDouble(request.getParameter("today_man_hours"));
		String workDetails = request.getParameter("work_details");
		String workDate = request.getParameter("work_date");
		
		ManHourService service = new ManHourService();
		ans = service.registManHour(todayManHours, workDetails, workDate);
		request.setAttribute("ans", ans);
		
		return page;
		
	}
	
	//工数削除メソッド
	public String deleteManHour() throws UnsupportedEncodingException{
		String page ="/WEB-INF/jsp/task_detail.jsp";
		int ans = 0;
		request.setCharacterEncoding("UTF-8");
		int id = Integer.parseInt(request.getParameter("man_hours_id"));
		
		ManHourService service = new ManHourService();
		ans = service.deleteManHour(id);
		request.setAttribute("ans", ans);
		return page;
		
	}

}
