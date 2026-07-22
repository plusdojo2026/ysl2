package action;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;


public class UserAction {
	HttpServletRequest request;
	//コンストラクタ
	public UserAction(HttpServletRequest request) {
		this.request=request;
	}
	
	//ログインメソッド
	public String login() throws UnsupportedEncodingException{
		String ans = null;
		UserDTO = null;
		
		request.setCharacterEncoding("UTF-8");
		int user_id = Integer.parseInt(request.getParameter("user_id"));
		String login_id = request.getParameter("login_id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String mail = request.getParameter("mail");
		int is_admin = Integer.parseInt(request.getParameter("is_admin"));
		int is_active = Integer.parseInt(request.getParameter("is_active"));
	
		
	}


}
