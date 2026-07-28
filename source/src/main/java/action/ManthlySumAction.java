package action;

import java.io.UnsupportedEncodingException;
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

		
      request.setCharacterEncoding("UTF-8");
        //遷移時に今月分を自動取得する
        //リアルタイムの年月を取得し、検索のためStringに変換
      		//↓今月の分だけ取れる。念のために保存
			//YearMonth ym = YearMonth.now();
			//String yearManth = ym.format(DateTimeFormatter.ofPattern("yyyy-MM"));
      
//    YearMonth ym = YearMonth.now();
//	  String defaultYM = ym.format(DateTimeFormatter.ofPattern("yyyy-MM"));
//	  request.setAttribute("defaultYM",defaultYM);
       
        String yearManth = request.getParameter("month");//集計のためのgetParameter
       
		ManthlySumService service = new ManthlySumService();
		
		request.setAttribute("yearManth",yearManth);//集計時に年月ボックスを空にしないように、yearManthで返す。
		
		//集計案件一覧検索とその件数カウント
		ArrayList<AllDTO> TotalCasesAndManHours = service.selectManthlySum();
		request.setAttribute("TotalCasesAndManHours",TotalCasesAndManHours);
		int ManthlyCases = TotalCasesAndManHours.size();//今月の稼働案件数を取得
		Integer ManthlyCases_integer = ManthlyCases;//int型ではrequstにセット出来ないため、Integer型に変換する!!
		request.setAttribute("allCaseSum", ManthlyCases_integer);
		
		//月ごとの実績工数
		ArrayList<AllDTO> ManthlyManHours = service.sumManthlyCasesManHours(yearManth);
		//TotalCasesAndManHoursに詰めるため、MnnthlyManHoursは不要に。
		//request.setAttribute("ManthlyManHours",ManthlyManHours);
		
		//再梱包。上2つのArrayListを基に、case_idが一致するところでループしながら詰める。
		for(int i=0;i<TotalCasesAndManHours.size();i++) {
			for(int j=0;j<ManthlyManHours.size();j++) {
				if(TotalCasesAndManHours.get(i).getCaseId().equals(ManthlyManHours.get(j).getCaseId())){
					TotalCasesAndManHours.get(i).setActualManHours(ManthlyManHours.get(j).getActualManHours());
				}
			}
		}
		request.setAttribute("TotalCasesAndManHours",TotalCasesAndManHours);
		
		//担当者名ごとかつ月ごとの工数
		ArrayList<AllDTO> ManthAndMembers = service.sumUsersManHours(yearManth);
		request.setAttribute("ManthAndMembers",ManthAndMembers);
		int ManthlyMemberManHours = ManthAndMembers.size();//今月の稼働メンバー数を取得
		Integer ManthlyMemberManHours_integer = ManthlyMemberManHours;//int型ではrequstにセット出来ないため、Integer型に変換する!!
		request.setAttribute("allUserSum",ManthlyMemberManHours_integer);
		
        return page;
    }
}