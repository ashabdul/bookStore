package bean;

public class UserBean {

	String userName;
	String password;
	String type;
	
	public UserBean(String userName, String password, String type) {
		this.userName = userName;
		this.password = password;
		this.type = type;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the isAdmin
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param isAdmin the isAdmin to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	public String toString(){
		String s = "User Name= " + this.getUserName() + ", " + "Password= " + this.getPassword() + ", " + "Type= " 
				+ this.getType();
		return s;
	}
	
	
	
	
	
}
