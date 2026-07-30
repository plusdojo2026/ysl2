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
		request.setCharacterEncoding("UTF-8");
		
		String page = "/WEB-INF/jsp/admin.jsp";
		String id = request.getParameter("login_id");
		String name = request.getParameter("name");
		String mail = request.getParameter("mail");
		String password = request.getParameter("pw");
		int admin = Integer.parseInt(request.getParameter("is_admin"));
		
		
		//dto
		UserDTO dto = new UserDTO();
		if (id !=null);
		dto.setLoginId(id);
		dto.setName(name);
		dto.setMail(mail);
		dto.setPw(password);
		dto.setIsAdmin(admin);
		
		UserService service = new UserService();
		int isSuccess = service.UserRegist(dto);
		
		if (isSuccess==1) {
			request.setAttribute("message", "ユーザーの登録に成功しました！");
		} else {
			request.setAttribute("message", "ユーザーの登録に失敗しました。");
		}
		
		ArrayList<UserDTO> userList = service.selectUsers();
		request.setAttribute("userList", userList);
		
		
		return page;
	}
		
	//ユーザー編集メソッド、updataUser
	public String updataUser() throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		
		String page = "/WEB-INF/jsp/admin.jsp";
		int userId = Integer.parseInt(request.getParameter("user_id"));
		String name = request.getParameter("name");
		String mail = request.getParameter("mail");
		int admin = Integer.parseInt(request.getParameter("is_admin"));
		int active = Integer.parseInt(request.getParameter("is_active"));
		
		//dto
		UserDTO dto = new UserDTO();
		//if (loginId != null);
		dto.setUserId(userId);
		dto.setName(name);
		dto.setMail(mail);
		dto.setIsAdmin(admin);
		dto.setIsActive(active);
		
		UserService service = new UserService();
		int isSuccess = service.updataUser(dto);
		
		if (isSuccess==1) {
			request.setAttribute("message", "ユーザーの編集に成功しました！");
		} else {
			request.setAttribute("message", "ユーザーの編集に失敗しました。");
		}
		
		ArrayList<UserDTO> userList = service.selectUsers();
		request.setAttribute("userList", userList);
		
		
		return page;
	}
	
}
