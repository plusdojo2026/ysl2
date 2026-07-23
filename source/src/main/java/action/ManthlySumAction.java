package action;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

public class ManthlySumAction {

    HttpServletRequest request;
	
	//コンストラクタを定義
	public ManthlySumAction(HttpServletRequest request) {
		this.request=request;
	}

    public String selectManthlySum() throws UnsupportedEncodingException {
        String page = "/WEB-INF/jsp/manthly_sum.jsp";

		//値の取得
		request.setCharacterEncoding("UTF-8");

        return page;
    }
}