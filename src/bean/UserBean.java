package bean;

public class UserBean {

	String userName;
	String password;
	boolean isAdmin;
	
	public UserBean(String userName, String password, boolean isAdmin) {
		this.userName = userName;
		this.password = password;
		this.isAdmin = isAdmin;
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
	public boolean getisAdmin() {
		return isAdmin;
	}
	/**
	 * @param isAdmin the isAdmin to set
	 */
	public void setisAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	public String toString(){
		String s = "User Name= " + this.getUserName() + ", " + "Password= " + this.getPassword() + ", " + "isAdmin= " 
				+ this.getisAdmin();
		return s;
	}
	
	
	
	
	
}
