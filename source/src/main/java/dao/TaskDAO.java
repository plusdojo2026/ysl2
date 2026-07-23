package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.AllDTO;
import dto.TaskDTO;

public class TaskDAO {
	
	public Connection conn = null;
	
	//コネクションを保持するコンストラクタ
	public TaskDAO(Connection conn) {
		this.conn=conn;
	}
	
	//タスク全検索
	public ArrayList<AllDTO> selectTasks() throws SQLException{
		ArrayList<AllDTO> taskList = new ArrayList<AllDTO>();
		
		//SELECT文準備
		String sql = "SELECT tasks.case_id, cases.case_name, tasks.task_id, task_name, users.name,"
				+ " tasks.status, tasks.priority, deadline_date, estimated_man_hours, task_progress,"
				+ " COALESCE (SUM(today_man_hours), 0) AS actual_man_hours, tasks.memo"
				+ " FROM tasks"
				+ " JOIN cases"
				+ " ON tasks.case_id=cases.case_id"
				+ " JOIN users"
				+ " ON tasks.manager=users.user_id"
				+ " LEFT JOIN man_hours"
				+ " ON tasks.task_id = man_hours.task_id"
				+ " GROUP BY tasks.task_id"
				+ " ORDER BY tasks.deadline_date";
		
		//まとめる
		PreparedStatement pStmt = conn.prepareStatement(sql);
		
		//SELECT文を実行し、結果表を取得
		ResultSet rs = pStmt.executeQuery();
		
		//結果の移し替え
		while(rs.next()) {
			AllDTO dto = new AllDTO();
			
			dto.setCaseId(rs.getString("case_id"));
			dto.setCaseName(rs.getString("case_name"));
			dto.setTaskId(rs.getInt("task_id"));
			dto.setTaskName(rs.getString("task_name"));
			dto.setName(rs.getString("name"));
			dto.setTaskStatus(rs.getString("status"));
			dto.setTaskPriority(rs.getString("priority"));
			dto.setDeadlineDate(rs.getString("deadline_date"));
			dto.setEstimatedManHours(rs.getDouble("estimated_man_hours"));
			dto.setTaskProgress(rs.getInt("task_progress"));
			dto.setActualManHours(rs.getDouble("actual_man_hours"));
			dto.setTaskMemo(rs.getString("memo"));
			
			taskList.add(dto);
		}
	
		
		return taskList;
	}
	
	//タスク登録
	public int registTask(TaskDTO dto) throws SQLException{
		int ans = 0;
		
		//INSERT文準備
		String sql = "INSERT INTO tasks"
				+ " (case_id, task_name, manager, status, priority, start_date,"
				+ " deadline_date, estimated_man_hours, task_progress, memo)"
				+ " VALUES"
				+ " (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		//まとめる
		PreparedStatement pStmt = conn.prepareStatement(sql);
		
		//?に値をセット
		pStmt.setString(1, dto.getCaseId());
		pStmt.setString(2, dto.getTaskName());
		pStmt.setInt(3, dto.getManager());
		pStmt.setString(4, dto.getStatus());
		pStmt.setString(5, dto.getPriority());
		pStmt.setString(6, dto.getStartDate());
		pStmt.setString(7, dto.getDeadlineDate());
		pStmt.setDouble(8, dto.getEstimatedManHours());
		pStmt.setInt(9, dto.getTaskProgress());
		pStmt.setString(10, dto.getMemo());
		
		//INSERT文を実行し、結果表を取得
		ans = pStmt.executeUpdate();
		
		return ans;
	}
	
	//タスク編集
	public int updateTask(TaskDTO dto) throws SQLException{
		int ans = 0;
		
		//UPDATE文準備
		String sql = "UPDATE tasks SET"
				+ " case_id=?, task_name=?, manager=?, status=?, priority=?, start_date=?, deadline_date=?,"
				+ " estimated_man_hours=?, task_progress=?, memo=?"
				+ " WHERE task_id=?";
		
		//まとめる
		PreparedStatement pStmt = conn.prepareStatement(sql);
		
		//?に値をセット
		pStmt.setString(1, dto.getCaseId());
		pStmt.setString(2, dto.getTaskName());
		pStmt.setInt(3, dto.getManager());
		pStmt.setString(4, dto.getStatus());
		pStmt.setString(5, dto.getPriority());
		pStmt.setString(6, dto.getStartDate());
		pStmt.setString(7, dto.getDeadlineDate());
		pStmt.setDouble(8, dto.getEstimatedManHours());
		pStmt.setInt(9, dto.getTaskProgress());
		pStmt.setString(10, dto.getMemo());
		pStmt.setInt(11, dto.getTaskId());
		
		//INSERT文を実行し、結果表を取得
		ans = pStmt.executeUpdate();
		
		return ans;
	}
	
	//タスク削除（案件詳細）
	public int deleteTask(int taskId) throws SQLException{
		int ans = 0;
		
		//DELETE文準備
		String sql = "DELETE FROM tasks WHERE task_id=?";
		
		//まとめる
		PreparedStatement pStmt = conn.prepareStatement(sql);
		
		//?に値をセット
		pStmt.setInt(1, taskId);
		
		//INSERT文を実行し、結果表を取得
		ans = pStmt.executeUpdate();
		
		return ans;
	}
	
	//その案件のタスク一覧（案件詳細）
	public ArrayList<AllDTO> selectTaskOfCase(String caseId) throws SQLException{
		ArrayList<AllDTO> taskList = new ArrayList<AllDTO>();
		
		//SELECT文準備
		String sql = "SELECT tasks.case_id, cases.case_name, tasks.task_id, task_name, users.name,"
				+ " tasks.status, tasks.priority, deadline_date, estimated_man_hours, task_progress,"
				+ " COALESCE (SUM(today_man_hours), 0) AS actual_man_hours, tasks.memo"
				+ " FROM tasks"
				+ " JOIN cases"
				+ " ON tasks.case_id=cases.case_id"
				+ " JOIN users"
				+ " ON tasks.manager=users.user_id"
				+ " LEFT JOIN man_hours"
				+ " ON tasks.task_id = man_hours.task_id"
				+ " WHERE tasks.case_id = ?"
				+ " GROUP BY tasks.task_id"
				+ " ORDER BY tasks.deadline_date";
		
		//まとめる
		PreparedStatement pStmt = conn.prepareStatement(sql);
		
		//?に値をセット
		pStmt.setString(1, caseId);
		
		//SELECT文を実行し、結果表を取得
		ResultSet rs = pStmt.executeQuery();
		
		//結果の移し替え
		while(rs.next()) {
			AllDTO dto = new AllDTO();
			
			dto.setCaseId(rs.getString("case_id"));
			dto.setCaseName(rs.getString("case_name"));
			dto.setTaskId(rs.getInt("task_id"));
			dto.setTaskName(rs.getString("task_name"));
			dto.setName(rs.getString("name"));
			dto.setTaskStatus(rs.getString("status"));
			dto.setTaskPriority(rs.getString("priority"));
			dto.setDeadlineDate(rs.getString("deadline_date"));
			dto.setEstimatedManHours(rs.getDouble("estimated_man_hours"));
			dto.setTaskProgress(rs.getInt("task_progress"));
			dto.setActualManHours(rs.getDouble("actual_man_hours"));
			dto.setTaskMemo(rs.getString("memo"));
			
			taskList.add(dto);
		}
		
		return taskList;
	}
	//タスク1件検索（タスク詳細）
	public AllDTO selectTaskDetail(int taskId) throws SQLException{
		AllDTO dto = new AllDTO();
		
		//SELECT文準備
		String sql = "SELECT tasks.case_id, cases.case_name, tasks.task_id, task_name, users.name,"
				+ " tasks.status, tasks.priority, deadline_date, estimated_man_hours, task_progress,"
				+ " COALESCE (SUM(today_man_hours), 0) AS actual_man_hours, tasks.memo"
				+ " FROM tasks"
				+ " JOIN cases"
				+ " ON tasks.case_id=cases.case_id"
				+ " JOIN users"
				+ " ON tasks.manager=users.user_id"
				+ " LEFT JOIN man_hours"
				+ " ON tasks.task_id = man_hours.task_id"
				+ " WHERE tasks.task_id = ?"
				+ " GROUP BY tasks.task_id"
				+ " ORDER BY tasks.deadline_date";
		
		//まとめる
		PreparedStatement pStmt = conn.prepareStatement(sql);
		
		//?に値をセット
		pStmt.setInt(1, taskId);
		
		//SELECT文を実行し、結果表を取得
		ResultSet rs = pStmt.executeQuery();
		
		//結果の移し替え
		while(rs.next()) {	
			
			dto.setCaseId(rs.getString("case_id"));
			dto.setCaseName(rs.getString("case_name"));
			dto.setTaskId(rs.getInt("task_id"));
			dto.setTaskName(rs.getString("task_name"));
			dto.setName(rs.getString("name"));
			dto.setTaskStatus(rs.getString("status"));
			dto.setTaskPriority(rs.getString("priority"));
			dto.setDeadlineDate(rs.getString("deadline_date"));
			dto.setEstimatedManHours(rs.getDouble("estimated_man_hours"));
			dto.setTaskProgress(rs.getInt("task_progress"));
			dto.setActualManHours(rs.getDouble("actual_man_hours"));
			dto.setTaskMemo(rs.getString("memo"));
		}
		
		return dto;
	}
	
	//タスク進捗（案件一覧）
	public ArrayList<AllDTO> countProgress() throws SQLException{
		ArrayList<AllDTO> pList = new ArrayList<AllDTO>();
		
		//SELECT文準備
		String sql = "SELECT case_id,"
				+ "	COUNT(task_id) AS 'all_tasks',"
				+ " COUNT(CASE WHEN status='完了' THEN 1 ELSE NULL END) AS 'completed_tasks'"
				+ " FROM tasks"
				+ " GROUP BY case_id";
		
		//まとめる
		PreparedStatement pStmt = conn.prepareStatement(sql);
		
		System.out.println("SQL:" + sql);
		
		//SELECT文を実行し、結果表を取得
		ResultSet rs = pStmt.executeQuery();
		
		//移し替え
		while(rs.next()) {
			AllDTO dto = new AllDTO();
			
			dto.setCaseId(rs.getString("case_id"));
			dto.setAllTasks(rs.getInt("all_tasks"));
			dto.setCompletedTasks(rs.getInt("completed_tasks"));
			
			pList.add(dto);
		}
		
		return pList;
	}
	
	//期限超過タスク検索（ダッシュボード）
	public ArrayList<AllDTO> selectOverTasks(int userId) throws SQLException{
		ArrayList<AllDTO> taskList = new ArrayList<AllDTO>();
		
		//SELECT文準備
		String sql = "SELECT tasks.case_id, case_name, task_id, task_name, tasks.status,"
				+ " tasks.priority, deadline_date, estimated_man_hours, task_progress, tasks.memo"
				+ " FROM tasks"
				+ " JOIN cases"
				+ " ON tasks.case_id=cases.case_id"
				+ " WHERE manager=?"
				+ " AND deadline_date < CURRENT_DATE()"
				+ " AND tasks.status!='完了'";
		
		//まとめる
		PreparedStatement pStmt = conn.prepareStatement(sql);
		
		//?に値をセット
		pStmt.setInt(1, userId);
		
		System.out.println("SQL:"+ sql);
		
		//SELECT文を実行し、結果表を取得
		ResultSet rs = pStmt.executeQuery();
		
		//結果の移し替え
		while(rs.next()) {
			AllDTO dto = new AllDTO();
			
			dto.setCaseId(rs.getString("case_id"));
			dto.setCaseName(rs.getString("case_name"));
			dto.setTaskId(rs.getInt("task_id"));
			dto.setTaskName(rs.getString("task_name"));
			dto.setTaskStatus(rs.getString("status"));
			dto.setTaskPriority(rs.getString("priority"));
			dto.setDeadlineDate(rs.getString("deadline_date"));
			dto.setEstimatedManHours(rs.getDouble("estimated_man_hours"));
			dto.setTaskProgress(rs.getInt("task_progress"));
			dto.setTaskMemo(rs.getString("memo"));
			
			taskList.add(dto);
		}
		System.out.println("期限超過タスク検索結果：" + taskList.size());
		
		return taskList;
	}
	
	//担当タスク検索（ダッシュボード）
	public ArrayList<AllDTO> selectAssignedTasks(int userId) throws SQLException{
		ArrayList<AllDTO> taskList = new ArrayList<AllDTO>();
		
		//SELECT文準備
		String sql = "SELECT tasks.case_id, case_name, task_id, task_name, tasks.status,"
				+ " tasks.priority, deadline_date, estimated_man_hours, task_progress, tasks.memo"
				+ " FROM tasks"
				+ " JOIN cases"
				+ " ON tasks.case_id=cases.case_id"
				+ " WHERE manager=?"
				+ " AND tasks.status!='完了'";
		
		//まとめる
		PreparedStatement pStmt = conn.prepareStatement(sql);
		
		System.out.println("SQL文:" + sql);
		
		//?に値をセット
		pStmt.setInt(1, userId);
		
		//SELECT文を実行し、結果表を取得
		ResultSet rs = pStmt.executeQuery();
		
		//結果の移し替え
		while(rs.next()) {
			AllDTO dto = new AllDTO();
			
			dto.setCaseId(rs.getString("case_id"));
			dto.setCaseName(rs.getString("case_name"));
			dto.setTaskId(rs.getInt("task_id"));
			dto.setTaskName(rs.getString("task_name"));
			dto.setTaskStatus(rs.getString("status"));
			dto.setTaskPriority(rs.getString("priority"));
			dto.setDeadlineDate(rs.getString("deadline_date"));
			dto.setEstimatedManHours(rs.getDouble("estimated_man_hours"));
			dto.setTaskProgress(rs.getInt("task_progress"));
			dto.setTaskMemo(rs.getString("memo"));
			
			taskList.add(dto);
		}
		
		System.out.println("担当タスク検索結果の行数：" + taskList.size());
		return taskList;
	}

} 