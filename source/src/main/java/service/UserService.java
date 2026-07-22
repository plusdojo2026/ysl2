package service;

import java.util.ArrayList;

import dto.UserDTO; 

public class UserService extends DBAccess{
	
	
	//DBアクセス
	public UserService() {
		super.access();
	}
	
	//ユーザー登録メソッド
	public int UserRegist(UserDTO Dto) {
		int result = 0;
		
		return result;
	}
	
	//ログインメソッド
	public UserDTO login(String loginId, String pw) {
		UserDTO user = null;
		
		return user;
	}
	
	//メンバー編集メソッド
	public int updataUser(UserDTO dto) {
		int result = 0;
		
		return result;
	}
	
	//パスワード変更メソッド
	public int updatepassword(String loginId, String currentPassword, String newPassword) {
		int result = 0;
		
		return result;
	}
	
	//ユーザー検査メソッド
	public ArrayList<UserDTO>selectUsers(){
		ArrayList<UserDTO> userList = new ArrayList<>();
		
		return userList;
	}
	
	//id重複チェックメソッド
	public int checkDuplicateLoginId(String loginId) {
		int result = 0;
		
		return result;
	}
}