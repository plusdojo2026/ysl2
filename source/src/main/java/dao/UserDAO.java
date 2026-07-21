package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.UserDTO;

public class UserDAO {
	
	public Connection conn = null;
	
	//コネクションを保持するコンストラクタ
	public UserDAO(Connection conn) {
		this.conn = conn;
	}
	
	//ログインメソッド
	public UserDTO login(String loginId, String pw) throws SQLException {
		UserDTO dto = null;
		
		String sql = "SELECT * FROM users WHERE login_id = ? and pw = ?";
		//デバック用（SQL文の確認用）
		System.out.println(sql);
		
		//まとめる
		PreparedStatement pStmt = conn.prepareStatement(sql);
		
		// ?に値をセット
		pStmt.setString(1, loginId);
		pStmt.setString(2, pw);
		
		// SELECT文を実行し、結果表を取得する
		ResultSet rs = pStmt.executeQuery();
		
		//移し替え
		if (rs.next()) {
			dto = new UserDTO();
			dto.setUserId(rs.getInt("user_id"));
			dto.setLoginId(rs.getString("login_id"));
			dto.setName(rs.getString("name"));
			dto.setMail(rs.getString("mail"));
			dto.setIsAdmin(rs.getInt("is_admin"));
			dto.setIsActive(rs.getInt("is_active"));
			dto.setCreatedAt(rs.getString("created_at"));
		}
		//serviceに返却
		return dto;
	}
	
	//ユーザー全検索メソッド
	public ArrayList<UserDTO> selectUsers() throws SQLException {
		ArrayList<UserDTO> userList = new ArrayList<UserDTO>();
		
		String sql = "SELECT * FROM users";
		//デバッグ（SQL文の確認用）
		System.out.println(sql);
		
		// まとめる
		PreparedStatement pStmt = conn.prepareStatement(sql);
		
		// SELECT文を実行し、結果表を取得する
		ResultSet rs = pStmt.executeQuery();
		
		while (rs.next()) {
			UserDTO dto = new UserDTO();
			dto.setUserId(rs.getInt("user_id"));
			dto.setLoginId(rs.getString("login_id"));
			dto.setName(rs.getNString("name"));
			dto.setMail(rs.getString("mail"));
			dto.setIsAdmin(rs.getInt("is_admin"));
			dto.setIsActive(rs.getInt("is_active"));
			dto.setCreatedAt(rs.getString("created_at"));
			userList.add(dto);
		}
		//serviceに返却
		return userList;
	}
	
	//アクティブユーザー一覧取得メソッド
	public ArrayList<UserDTO> selectActiveUSers() throws SQLException {
		ArrayList<UserDTO> userList = new ArrayList<UserDTO>();
		
		String sql = "SELECT user_id, name FROM users WHERE is_active = 1";
		//デバッグ（SQL文の確認用）
		System.out.println(sql);
		
		// まとめる
		PreparedStatement pStmt = conn.prepareStatement(sql);
		
		// SELECT文を実行し、結果表を取得する
		ResultSet rs = pStmt.executeQuery();
		
		//移し替え
		while (rs.next()) {
			UserDTO dto = new UserDTO();
			dto.setUserId(rs.getInt("user_id"));
			dto.setName(rs.getString("name"));
			userList.add(dto);
		}
		//serviceに返却
		return userList;
	}
	
	//ユーザー登録メソッド
	public int registUser(UserDTO dto) throws SQLException {
		int ans = 0;
		
		String sql = "INSERT INTO users(login_id, pw, name, mail, is_admin, is_active)"
				+ "VALUES(?,?,?,?,?,?)";
		//デバッグ（SQL文の確認用）
		System.out.println(sql);
		
		// まとめる
		PreparedStatement pStmt = conn.prepareStatement(sql);
		
		//?に値をセットする
		pStmt.setString(1, dto.getLoginId());
		pStmt.setString(2, dto.getPw());
		pStmt.setString(3, dto.getName());
		pStmt.setString(4, dto.getMail());
		pStmt.setInt(5, dto.getIsAdmin());
		pStmt.setInt(6, dto.getIsActive());
		
		// SELECT文を実行し、結果表を取得する
		ans = pStmt.executeUpdate();
		
		//serviceに返却する
		return ans;
	}
	
	//ユーザー編集
	public int updateUser(UserDTO dto) throws SQLException {
		int ans = 0;
		
		String sql = "UPDATE users SET name = ?, mail = ?, is_admin = ?, is_active = ? WHERE user_id = ?";
		//デバッグ（SQL文の確認用）
		System.out.println(sql);
		
		// まとめる
		PreparedStatement pStmt = conn.prepareStatement(sql);
		
		//?に値をセット
		pStmt.setString(1, dto.getName());
		pStmt.setString(2, dto.getMail());
		pStmt.setInt(3, dto.getIsActive());
		pStmt.setInt(4, dto.getIsActive());
		
		pStmt.setInt(5, dto.getUserId());
		
		// SELECT文を実行し、結果表を取得する
		ans = pStmt.executeUpdate();
		
		//serviceに返却する
		return ans;
	}
	
	//パスワード変更
	public int updatePassword(String loginId, String pw) throws SQLException {
		int ans = 0;
		
		String sql = "UPDATE users SET pw = ? WHERE login_id = ?";
		//デバッグ（SQL文の確認用）
		System.out.println(sql);
		
		// まとめる
		PreparedStatement pStmt = conn.prepareStatement(sql);
		
		//?に値をセット
		pStmt.setString(1, pw);
		pStmt.setString(2, loginId);
		
		// SELECT文を実行し、結果表を取得する
		ans = pStmt.executeUpdate();
		
		//serviceに返却する
		return ans;
	}
	
	//ログインidの重複チェック
	public int checkDuplicateLoginId(String loginId) throws SQLException {
		int ans = 0;
		
		String sql = "SELECT * FROM users WHERE login_id = ?";
		//デバッグ（SQL文の確認用）
		System.out.println(sql);
		
		// まとめる
		PreparedStatement pStmt = conn.prepareStatement(sql);
		
		//?に値をセットする
		pStmt.setString(1, loginId);
		
		return ans;
	}
}