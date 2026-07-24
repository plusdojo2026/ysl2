package service;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.UserDAO;
import dto.UserDTO; 

public class UserService extends DBAccess{
	
	
	//DBアクセス
//	public UserService() {
//		super.access();
//	}
	
	//ユーザー登録メソッド
	public int UserRegist(UserDTO dto) {
		super.access();
		int result = 0;
		
		UserDAO dao = new UserDAO(super.conn);
		try {
			result = dao.registUser(dto);
		} catch (SQLException e) {
			System.out.println("SQL文おかしい");
			e.printStackTrace();
		}
		
		super.close();
		return result;
	}
	
	//ログインメソッド
	public UserDTO login(String loginId, String pw) {
		super.access();
		UserDTO user = null;
		
		UserDAO dao = new UserDAO(super.conn);
		try {
			user = dao.login(loginId,pw);
		} catch (SQLException e) {
			System.out.println("SQL文おかしい");
			e.printStackTrace();
		}
		
		return user;
	}
	
	//ユーザー編集メソッド
	public int updataUser(UserDTO dto) {
		super.access();
		int result = 0;
		
		UserDAO dao = new UserDAO(super.conn);
		try {
			result = dao.updateUser(dto);
		} catch (SQLException e) {
			System.out.println("SQL文おかしい");
			e.printStackTrace();
		}
		
		super.close();
		return result;
	}
	
	//パスワード変更メソッド
	public int updatePassword(String loginId, String currentPassword, String newPassword) {
		super.access();
		int result = 0;
		UserDTO dto = null;
				
		UserDAO dao = new UserDAO(super.conn);
		try {
			dto = dao.login(loginId,currentPassword);
		} catch (SQLException e) {
			System.out.println("SQL文おかしい");
			e.printStackTrace();
		}
		if (dto == null) {
			return result;
		}else {
			try {
				result = dao.updatePassword(loginId, newPassword);
			}catch (SQLException e) {
				System.out.println("SQL文おかしい");
				e.printStackTrace();
			}
		}
		
		super.close();
		return result;
	}
	
	//ユーザー検査メソッド
	public ArrayList<UserDTO>selectUsers(){
		super.access();
		ArrayList<UserDTO> userList = new ArrayList<>();
		
		UserDAO dao = new UserDAO(super.conn);
		try {
			userList = dao.selectUsers();
		} catch (SQLException e) {
			System.out.println("SQL文おかしい");
			e.printStackTrace();
		}
		
		super.close();
		return userList;
	}
	
	//id重複チェックメソッド
	public int checkDuplicateLoginId(String loginId) {
		super.access();
		int result = 0;
		
		UserDAO dao = new UserDAO(super.conn);
		try {
			result = dao.checkDuplicateLoginId(loginId);
		} catch (SQLException e) {
			System.out.println("SQL文おかしい");
			e.printStackTrace();
		}
		
		super.close();
		return result;
	}
	
	//アクティブユーザー一覧取得メソッド
	public ArrayList<UserDTO> selectActiveUsers(){
		super.access();
		ArrayList<UserDTO> userList = new ArrayList<>();
		
		UserDAO dao = new UserDAO(super.conn);
		try {
			userList = dao.selectActiveUsers();
		} catch (SQLException e) {
			System.out.println("SQL文おかしい");
			e.printStackTrace();
		}
		
		super.close();
		return userList;
	}
		
	
}