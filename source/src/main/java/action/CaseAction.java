package action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import dto.AllDTO;
import service.CaseService;

public class CaseAction {
	

	HttpServletRequest request;

	//コンストラクタ
	public CaseAction(HttpServletRequest request) {
		//自分のリクエスト部分にサーブレットからもらったリクエストを入れる。
		this.request = request;
	}
	
	//案件一覧メソッド
	public String selectCase() {
		String page = "/WEB-INF/jsp/case.jsp";
		
		CaseService service = new CaseService();
		ArrayList<AllDTO> caseList = service.selectCases();
		request.setAttribute("caseList", caseList);
		
		return page;
	}
	
	//案件登録メソッド
	public String registCase() throws UnsupportedEncodingException {
		String page = "/WEB-INF/jsp/case.jsp";
		
		request.setCharacterEncoding("UTF-8");
		String case_id = request.getParameter("case_id");
		String case_name = request.getParameter("case_name");
		String customer_name = request.getParameter("customer_name");
		int pm_id = Integer.parseInt(request.getParameter("pm_id")); 
		String memo = request.getParameter("memo");
		String start_date = request.getParameter("start_date");
		String end_date = request.getParameter("end_date");
		String priority = request.getParameter("priority");
		double estimated_man_hours = Double.parseDouble(request.getParameter("estimated_man_hours")); 
		String status = request.getParameter("status");
		
		CaseService service = new CaseService();
		int ans = service.registCase(case_id,case_name,customer_name,pm_id,memo,start_date,end_date,priority,estimated_man_hours,status);
		
		ArrayList<AllDTO> caseList = service.selectCases();
		request.setAttribute("caseList", caseList);
		
		return page;
	}
	
	
	//案件編集メソッド
	
	
	
	
	//案件詳細メソッド
	
	//ステータス変更メソッド

}
