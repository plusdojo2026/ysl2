package dto;

public class UserDTO {
	private int user_id;
	private String login_id;
	private String pw;
	private String name;
	private String mail;
	private int is_admin;
	private int is_active;
	private String created_at;
	private String updated_at;
	
	public int getUser_id() {
		return user_id;
	}
	
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
	public String getLogin_id() {
		return login_id;
	}
	
	public void setLogin_id(String login_id) {
		this.login_id = login_id;
	}
	
	public String getPw() {
		return pw;
	}
	
	public void setPw(String pw) {
		this.pw = pw;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getMail() {
		return mail;
	}
	
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public int getIs_admin() {
		return is_admin;
	}
	
	public void setIs_admin(int is_admin) {
		this.is_admin = is_admin;
	}
	
	public int getIs_active() {
		return is_active;
	}
	
	public void setIs_active(int is_active) {
		this.is_active = is_active;
	}
	
	public String getCreated_at() {
		return created_at;
	}
	
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	
	public String getUpdated_at() {
		return updated_at;
	}
	
	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}
	
}