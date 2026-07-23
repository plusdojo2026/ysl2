package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.AdminAction;
import action.CaseAction;
import action.DashBoardAction;
import action.ManHourAction;
import action.ManthlySumAction;
import action.TaskAction;
import action.UserAction;

@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String page = "/WEB-INF/jsp/login.jsp";
		//ページIDを取得
		String pageId = request.getParameter("page_id");
		//ボタンの詳細を取得
		String buttonId = request.getParameter("button_id");

		System.out.println("ページ：" + pageId + " ボタン：" + buttonId);

		
		//何もわたってきて無ければログイン画面へ
		if (pageId == null && buttonId == null) {
			page = "/WEB-INF/jsp/login.jsp";
		} else if (pageId.equals("L004") && buttonId.equals("case_link")) {
			//案件一覧の案件詳細への遷移リンク
			CaseAction action = new CaseAction(request);
			page = action.selectCaseDetail();
		} else if ((pageId.equals("L001") && pageId.equals("L007")) && buttonId.equals("工数登録")) {
			//ダッシュボード、タスク編集ページからの工数登録ボタン
			ManHourAction action = new ManHourAction(request);
			page = action.registManHour();
		} else if (pageId.equals("L006") && buttonId.equals("tasl_link")) {
			//タスク一覧のタスク詳細への遷移リンク
			TaskAction action = new TaskAction(request);
			page = action.selectTaskDetail();
		}

		System.out.println("ページ：" + pageId + " ボタン：" + buttonId);

		//ログイン画面へフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String page = null;
		
		//ページIDを取得
		String pageId = request.getParameter("page_id");
		//ボタンの詳細を取得
		String buttonId = request.getParameter("button_id");
		
		//何が入っているか確認
		System.out.println("ページ：" + pageId + " ボタン：" + buttonId);
		
		if (pageId.equals("L001") && buttonId.equals("ログイン")) {
			//ログインボタン
			UserAction action = new UserAction(request);
			page = action.login();
		} else if (pageId.equals("side") && buttonId.equals("ログアウト")) {
			//ログアウトボタン
			HttpSession session = request.getSession();
			session.invalidate();
			page = "/WEB-INF/jsp/login.jsp";
		} else if (pageId.equals("side") && buttonId.equals("ダッシュボード")) {
			//サイドバーのダッシュボード
			DashBoardAction action = new DashBoardAction(request);
			page = action.selectTask();
		} else if (pageId.equals("side") && buttonId.equals("案件一覧")) {
			//サイドバーの案件一覧
			CaseAction action = new CaseAction(request);
			page = action.selectCase();
		} else if (pageId.equals("side") && buttonId.equals("タスク一覧")) {
			//サイドバーのタスク一覧
			TaskAction action = new TaskAction(request);
			page = action.selectTask();
		} else if (pageId.equals("side") && buttonId.equals("月次集計")) {
			//サイドバーの月次集計
			ManthlySumAction action = new ManthlySumAction(request);
			page = action.selectManthlySum();
		} else if (pageId.equals("side") && buttonId.equals("メンバー管理")) {
			//サイドバーのメンバー管理（管理者）
			AdminAction action = new AdminAction(request);
			page = action.selectUser();
		} else if (pageId.equals("L002") && buttonId.equals("工数登録")) {
			//ダッシュボードの工数登録ボタン
			ManHourAction action = new ManHourAction(request);
			page = action.registManHour();
		} else if (pageId.equals("L003") && (buttonId.equals("完了") || buttonId.equals("中止"))) {
			//案件詳細のステータスボタン(完了 or 中止)
			CaseAction action = new CaseAction(request);
			page = action.updateStatus();
		} else if (pageId.equals("L003") && buttonId.equals("削除")) {
			//案件詳細の削除ボタン
			TaskAction action = new TaskAction(request);
			page = action.deleteTask();
		} else if (pageId.equals("L003") && buttonId.equals("変更")) {
			//案件詳細の案件編集モーダルの変更ボタン
			CaseAction action = new CaseAction(request);
			page = action.updateCase();
		} else if (pageId.equals("L003") && buttonId.equals("登録")) {
			//案件詳細のタスク登録モーダルの登録ボタン
			TaskAction action = new TaskAction(request);
			page = action.registTask();
		} else if (pageId.equals("L004") && buttonId.equals("登録")) {
			//案件一覧の案件登録モーダルの登録ボタン
			TaskAction action = new TaskAction(request);
			page = action.registTask();
		} else if (pageId.equals("L004") && buttonId.equals("保存")) {
			//案件一覧の案件編集モーダルの保存ボタン
			CaseAction action = new CaseAction(request);
			page = action.updateCase();
		} else if (pageId.equals("L005") && buttonId.equals("登録")) {
			//工数登録の登録ボタン
			ManHourAction action = new ManHourAction(request);
			page = action.registManHour();
		} else if (pageId.equals("L006") && buttonId.equals("保存")) {
			//タスク一覧のタスク編集モーダルの保存ボタン
			TaskAction action = new TaskAction(request);
			page = action.updateTask();
		} else if (pageId.equals("L006") && buttonId.equals("登録")) {
			//タスク一覧のタスク登録モーダルの登録ボタン
			TaskAction action = new TaskAction(request);
			page = action.registTask();
		} else if (pageId.equals("L007") && buttonId.equals("変更")) {
			//タスク詳細のステータス変更ボタン
			TaskAction action = new TaskAction(request);
			page = action.updateStatus();
		} else if (pageId.equals("L007") && buttonId.equals("削除")) {
			//タスク詳細の工数削除ボタン
			ManHourAction action = new ManHourAction(request);
			page = action.deleteManHour();
		} else if (pageId.equals("L007") && buttonId.equals("保存")) {
			//タスク詳細のタスク編集モーダルの保存ボタン
			TaskAction action = new TaskAction(request);
			page = action.updateTask();
		} else if (pageId.equals("L008") && buttonId.equals("集計")) {
			//月次集計の集計ボタン
			ManthlySumAction action = new ManthlySumAction(request);
			page = action.selectManthlySum();
		} else if (pageId.equals("L009") && buttonId.equals("登録")) {
			//メンバー一覧のメンバー登録モーダルの保存ボタン
			AdminAction action = new AdminAction(request);
			page = action.registUser();
		} else if (pageId.equals("L009") && buttonId.equals("保存")) {
			//メンバー一覧のメンバー編集モーダルの保存ボタン
			AdminAction action = new AdminAction(request);
			page = action.updataUser();
		} else if (pageId.equals("L010") && buttonId.equals("変更")) {
			//パスワード変更画面の変更ボタン
			UserAction action = new UserAction(request);
			page = action.updatePassword();
		}

		System.out.println("ページ：" + pageId + " ボタン：" + buttonId);
		//ログイン画面へフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}
}