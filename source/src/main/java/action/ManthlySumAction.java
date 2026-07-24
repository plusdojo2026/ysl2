package action;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
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

		//値の取得(年月)
		request.setCharacterEncoding("UTF-8");
		
		
		
		ManthlySumService service = new ManthlySumService();
		
		//集計案件一覧検索とその件数カウント
		ArrayList<AllDTO> TotalCasesAndManHours = service.selectManthlySum();
		request.setAttribute("TotalCasesAndManHours",TotalCasesAndManHours);
		int ManthlyCases = TotalCasesAndManHours.size();//今月の稼働案件数を取得
		request.setAttribute("ManthlyCases", ManthlyCases);
		
		//月ごとの実績工数
		ArrayList<AllDTO> 
		
		
		//担当者名ごとかつ月ごとの工数
		
		

        return page;
    }
}