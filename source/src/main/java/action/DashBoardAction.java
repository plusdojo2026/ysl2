package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import dto.AllDTO;
import dto.UserDTO;
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
		
//		※要修正
//		ユーザーIDを取得
		
		HttpSession session = request.getSession();
		UserDTO user = (UserDTO) session.getAttribute("user");
		
		int userId = user.getUserId();
		
		//期限超過タスク
		DashBoardService service = new DashBoardService();//タスクserviceをnewでインスタンス化
		ArrayList<AllDTO> overTaskList = service.selectOverTasks(userId);//タスクのデータをTaskListに格納
		request.setAttribute("overTaskList",overTaskList);//taskListの名前でtaskListをセット
		int overTasks = overTaskList.size();//該当レコード数を計算 int型の変数"overTasks"にレコード数を格納
		request.setAttribute("overTasks",overTasks);
		
		//担当タスク
		ArrayList<AllDTO> myTaskList = service.selectAssignedTasks(userId);//タスクのデータをTaskListに格納
		request.setAttribute("myTaskList",myTaskList);//taskListの名前でtaskListをセット
		int myTasks = myTaskList.size();//該当レコード数を計算 int型の変数"myTasks"にレコード数を格納
		request.setAttribute("myTasks",myTasks);
		
		//進行中案件
		DashBoardService caseService = new DashBoardService();//ケースserviceをnewでインスタンス化
		ArrayList<AllDTO> caseList = caseService.selectWorkingCases(userId);//案件のデータをCaseListに格納
		request.setAttribute("caseList",caseList);//caseListの名前でcaseListをセット
		int cases = caseList.size();//該当レコード数を計算 int型の変数"caseList"にレコード数を格納
		request.setAttribute("cases",cases);
		
		return page;
	} 

}
