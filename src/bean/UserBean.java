/**
	 * @author Michel Abd Alsamad
	 * 	
	 */

package bean;

import model.Cart;

public class UserBean {

	String userName;
	String password;
	String type;
	Cart cart;
	int spent;
	
	
	/**
	 * @constructor this constructor is used when we need to set the spent attribute only used for analysis
	 */
	public UserBean(String userName, int spent) {
		this.userName = userName;
		this.spent = spent;
	}
	
	/**
	 * @constructor this constructor is used when we do not need to set the spent attribute
	 */
	public UserBean(String userName, String password, String type) {
		this.userName = userName;
		this.password = password;
		this.type = type;
		this.cart = new Cart();
	}
	
	/**
	 * @constructor this constructor is used when we want a null user with cart object created
	 */
	public UserBean() {
		// TODO Auto-generated constructor stub\
		this.userName = null;
		this.password = null;
		this.type = null;
		this.cart = new Cart();
	}
	/**
	 * @return the cart
	 */
	public Cart getCart() {
		return cart;
	}
	/**
	 * @param cart the cart to set
	 */
	public void setCart(Cart cart) {
		this.cart = cart;
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
