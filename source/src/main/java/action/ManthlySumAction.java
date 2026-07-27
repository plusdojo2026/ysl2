package action;

import java.io.UnsupportedEncodingException;
import java.time.YearMonth;//リアルタイムの年月を取得
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import dto.AllDTO;
import service.ManthlySumService;


public class ManthlySumAction {

    HttpServletRequest request;
	
	//コンストラクタを定義
	public ManthlySumAction(HttpServletRequest request) {
		this.request=request;
	}

    public String selectManthlySum() throws UnsupportedEncodingException {
        String page = "/WEB-INF/jsp/manthly_sum.jsp";

		//遷移時に今月分を自動取得する
        request.setCharacterEncoding("UTF-8");
        
        //リアルタイムの年月を取得し、検索のためStringに変換
        YearMonth ym = YearMonth.now();
        String yearManth = ym.format(DateTimeFormatter.ofPattern("yyyy/MM"));
        
		
		//String yearManth = request.getParameter("work_date");
		
		ManthlySumService service = new ManthlySumService();
		
		//集計案件一覧検索とその件数カウント
		ArrayList<AllDTO> TotalCasesAndManHours = service.selectManthlySum();
		request.setAttribute("TotalCasesAndManHours",TotalCasesAndManHours);
		int ManthlyCases = TotalCasesAndManHours.size();//今月の稼働案件数を取得
		request.setAttribute("all_case_sum", ManthlyCases);
		
		//月ごとの実績工数
		ArrayList<AllDTO> ManthlyManHours = service.sumManthlyCasesManHours(yearManth);
		request.setAttribute("ManthlyManHours",ManthlyManHours);
		
		//担当者名ごとかつ月ごとの工数
		ArrayList<AllDTO> ManthAndMembers = service.sumUsersManHours(yearManth);
		request.setAttribute("ManthlyManHours",ManthlyManHours);
		int ManthlyMemberManHours = ManthAndMembers.size();//今月の稼働メンバー数を取得
		request.setAttribute("all_user_sum",ManthlyMemberManHours);
		
        return page;
    }
}