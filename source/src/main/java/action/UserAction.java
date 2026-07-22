package action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import dto.AllDTO;
import dto.TaskDTO;
import dto.UserDTO;
import service.DashBoardService;
import service.UserService;


public class UserAction {
	HttpServletRequest request;
	//コンストラクタ
	public UserAction(HttpServletRequest request) {
		this.request=request;
	}
	
	//ログインメソッド
	public String login() throws UnsupportedEncodingException{
		String ans = null;
		UserDTO dto = null;
		
		//入力値の取得
		request.setCharacterEncoding("UTF-8");
		String loginId = request.getParameter("login_id");
		String pw = request.getParameter("pw");

		//serviceの実体化
		UserService loginService = new UserService();
		dto = loginService.login(loginId, pw);

		//dtoがnullなら
		if (dto == null) {
			request.setAttribute("errMsg","※ID,またはPWが違います");
			ans = "/WEB-INF/jsp/login.jsp";
			return ans;
		}
		
		//正常にユーザー情報が入っていたら
		//ログインできた人の情報をsessionに保存
		HttpSession session = request.getSession();
		session.setAttribute("user", dto);

		//ダッシュボードで使用するデータの取得
		DashBoardService service = new DashBoardService();
		//期限超過タスク
		ArrayList<AllDTO> OvertaskList = service.selectOverTasks(dto.getUserId());
		request.setAttribute("OvertaskList",OvertaskList);
		
		//担当タスク
		ArrayList<AllDTO> MytaskList = service.selectAssignedTasks(dto.getUserId());
		request.setAttribute("MytaskList",MytaskList);
		
		//進行中案件
		ArrayList<TaskDTO> caseList = service.selectWorkingCases();
		request.setAttribute("caseList",caseList);

		ans = "/WEB-INF/jsp/dash_board.jsp";//ページを定義
		
		return ans;
	}

	public String updatePassword() throws UnsupportedEncodingException {
		String ans = null;

		//入力値の取得
		request.setCharacterEncoding("UTF-8");
		String loginId = request.getParameter("login_id");
		String pw = request.getParameter("pw");
		String newPw = request.getParameter("new_pw");

		//serviceの実体化
		UserService loginService = new UserService();
		int result = loginService.updatePassword(loginId, pw, newPw);

		if (result == 0) {
			request.setAttribute("errMsg","パスワード変更ができませんでした。");
		}
		
		//正常にパスワード変更出来たら
		ans = "/WEB-INF/jsp/change_password.jsp";
		return ans;
	}
}
