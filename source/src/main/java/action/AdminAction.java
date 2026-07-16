package action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import dto.UserDTO;
import service.UserService;

public class AdminAction {

	HttpServletRequest request;

	//コンストラクタ
	public AdminAction(HttpServletRequest request) {
		//自分のリクエスト部分にサーブレットからもらったリクエストを入れる。
		this.request = request;
	}

	//ユーザ一覧メソッド
	public String selectUser() throws UnsupportedEncodingException {
		String page = "/WEB-INF/jsp/admin.jsp";

		UserService service = new UserService();
		ArrayList<UserDTO> userList = service.selectUsers();
		request.setAttribute("userList", userList);

		return page;
	}

	//ユーザ追加メソッド
	public String registUser() throws UnsupportedEncodingException {
		String page = "/WEB-INF/jsp/admin.jsp";
		
		

		return page;
	}

	//ユーザ編集メソッド

}
