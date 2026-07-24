package action;



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

    public String selectManthlySum(){
        String page = "/WEB-INF/jsp/manthly_sum.jsp";

		//値の取得(年月)
		
		
		//集計案件一覧検索
		ManthlySumService service = new ManthlySumService();
		ArrayList<AllDTO> TotalCasesAndManHours = service.selectManthlySum();
		request.setAttribute("TotalCasesAndManHours",TotalCasesAndManHours);
		
		
		
		
		//月ごとの実績工数
		
		
		
		//担当者名ごとかつ月ごとの工数
		
		

        return page;
    }
}