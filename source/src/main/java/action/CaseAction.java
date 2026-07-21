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
		String caseId = request.getParameter("case_id");
		String caseName = request.getParameter("case_name");
		String customerName = request.getParameter("customer_name");
		int pmId = Integer.parseInt(request.getParameter("pm_id")); 
		String memo = request.getParameter("memo");
		String startDate = request.getParameter("start_date");
		String endDate = request.getParameter("end_date");
		String priority = request.getParameter("priority");
		double budgetedManHours = Double.parseDouble(request.getParameter("budgeted_man_hours")); 
		String status = request.getParameter("status");
		
		CaseService service = new CaseService();
		int ans = service.registCase(caseId,caseName,customerName,status,priority,pmId,startDate,endDate,budgetedManHours,memo);
		
		ArrayList<AllDTO> caseList = service.selectCases();
		request.setAttribute("caseList", caseList);
		
		return page;
	}
	
	
	//案件編集メソッド
	public String updateCase() throws UnsupportedEncodingException {
		String page =  "/WEB-INF/jsp/case.jsp";
		
		
		return page;
	}
	
	//案件詳細メソッド
	public String selectCaseDetail() throws UnsupportedEncodingException{
		String page =  "/WEB-INF/jsp/case.jsp";
		
		return page;
	}
	//ステータス変更メソッド
	public String updateStatus() throws UnsupportedEncodingException{
		String page =  "/WEB-INF/jsp/case.jsp";
		
		return page;
	}

}
