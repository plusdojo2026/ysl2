package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import dto.AllDTO;
import dto.CaseDTO;
import service.DashBoardService;

public class DashBoardAction {
	
	HttpServletRequest request ;
	//コンストラクタ
	public DashBoardAction(HttpServletRequest request){
		this.request=request;
	}
	
	//タスク・案件表示メソッド
	//jspにはデータを絞り込んだ状態で渡し、表示だけを任せる。
	public String selectTask(){
		
		String page = "/WEB-INF/jsp/dash_board.jsp";//ページを定義
		
//		//ユーザーIDをセッションから取得
//		HttpSession session = request.getSession();
//		session.setAttribute("user_id", userId);
		int userId = Integer.parseInt(request.getParameter("userId"));
		
		//期限超過タスク
		DashBoardService service = new DashBoardService();//タスクserviceをnewでインスタンス化
		ArrayList<AllDTO> OvertaskList = service.selectOverTasks(userId);//タスクのデータをTaskListに格納
		request.setAttribute("OvertaskList",OvertaskList);//taskListの名前でtaskListをセット
		int overTasks = OvertaskList.size();//該当レコード数を計算 int型の変数"overTasks"にレコード数を格納
		
		//担当タスク
		ArrayList<AllDTO> MytaskList = service.selectAssignedTasks(userId);//タスクのデータをTaskListに格納
		request.setAttribute("MytaskList",MytaskList);//taskListの名前でtaskListをセット
		int myTasks = MytaskList.size();//該当レコード数を計算 int型の変数"myTasks"にレコード数を格納
		
		
		//進行中案件
		DashBoardService caseService = new DashBoardService();//ケースserviceをnewでインスタンス化
		ArrayList<CaseDTO> caseList = caseService.selectWorkingCases(userId);//案件のデータをCaseListに格納
		request.setAttribute("caseList",caseList);//caseListの名前でcaseListをセット
		int caseLists = caseList.size();//該当レコード数を計算 int型の変数"caseList"にレコード数を格納
		request.setAttribute()
		
		return page;
	} 

}
