package dao;

import java.sql.Connection;
import java.util.ArrayList;

import dto.UserDTO;

public class UserDAO {
	
	public Connection conn = null;
	
	//コネクションを保持するコンストラクタ
	public UserDAO(Connection conn) {
		this.conn=conn;
	}
	
	//ログインメソッド
	public UserDTO login(String loginId, String pw) {
		UserDTO dto = null;
		
		return dto;	
	}
	
	//ユーザー全検索・アクティブユーザー検索メソッド
	public ArrayList<UserDTO> selectUsers(UserDTO user) {
		ArrayList<UserDTO> userList = new ArrayList<UserDTO>();
		
		return userList;
	}
	
	//ユーザー編集・パスワード変更
	public int updateUser(UserDTO user) {
		int ans = 0;
		
		return ans;
	}

	//ログインidの重複チェック
	public int checkDuplicateLoginId(String loginId) {
		int ans = 0;
		
		return ans;
	}
}