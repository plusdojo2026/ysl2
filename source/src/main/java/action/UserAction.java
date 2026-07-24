package action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import dto.AllDTO;
import dto.UserDTO;
import service.DashBoardService;
import service.UserService;

public class UserAction {
	HttpServletRequest request;
	
	//コンストラクタ
	public UserAction(HttpServletRequest request) {
		this.request = request;
	}
	
	//ログインメソッド
	public String login() throws UnsupportedEncodingException {
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
			request.setAttribute("errMsg", "※ID,またはPWが違います");
			ans = "/WEB-INF/jsp/login.jsp";
			return ans;
		}
		
		//正常にユーザー情報が入っていたら
		//ログインできた人の情報をsessionに保存
		HttpSession session = request.getSession();
		session.setAttribute("user", dto);
		
		//userIDの取得
		int userId = dto.getUserId();
		
		//期限超過タスク
		DashBoardService service = new DashBoardService();//タスクserviceをnewでインスタンス化
		ArrayList<AllDTO> overTaskList = service.selectOverTasks(userId);//タスクのデータをTaskListに格納
		request.setAttribute("overTaskList", overTaskList);//taskListの名前でtaskListをセット
		int overTasks = overTaskList.size();//該当レコード数を計算 int型の変数"overTasks"にレコード数を格納
		request.setAttribute("overTasks", overTasks);
		
		//担当タスク
		ArrayList<AllDTO> myTaskList = service.selectAssignedTasks(userId);//タスクのデータをTaskListに格納
		request.setAttribute("myTaskList", myTaskList);//taskListの名前でtaskListをセット
		int myTasks = myTaskList.size();//該当レコード数を計算 int型の変数"myTasks"にレコード数を格納
		request.setAttribute("myTasks", myTasks);
		
		//進行中案件
		DashBoardService caseService = new DashBoardService();//ケースserviceをnewでインスタンス化
		ArrayList<AllDTO> caseList = caseService.selectWorkingCases(userId);//案件のデータをCaseListに格納
		request.setAttribute("caseList", caseList);//caseListの名前でcaseListをセット
		int cases = caseList.size();//該当レコード数を計算 int型の変数"caseList"にレコード数を格納
		request.setAttribute("cases", cases);
		
		ans = "/WEB-INF/jsp/dash_board.jsp";//ページを定義
		
		return ans;
	}
	
	public String updatePassword() throws UnsupportedEncodingException {
		String ans = null;
		
		//入力値の取得
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		//セッションスコープからUserDTOを取り出す
		UserDTO user = (UserDTO) session.getAttribute("user");
		String loginId = user.getLoginId();
		
		String pw = request.getParameter("password");
		String newPw = request.getParameter("new_pw");
		
		System.out.println("取得値" + loginId + pw + newPw);
		
		//serviceの実体化
		UserService loginService = new UserService();
		int result = loginService.updatePassword(loginId, pw, newPw);
		
		if (result == 0) {
			request.setAttribute("errMsg", "パスワード変更ができませんでした。");
		}
		
		//正常にパスワード変更出来たら
		ans = "/WEB-INF/jsp/change_password.jsp";
		return ans;
	}
}
