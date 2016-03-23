package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import bean.UserBean;

public class UserDAO {

	DataSource ds;
	
	public UserDAO() throws ClassNotFoundException{
		try {
			ds = (DataSource) (new InitialContext())
					.lookup("java:/comp/env/jdbc/EECS");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * retrives a user by user name
	 */
	public UserBean retriveUser(String username) throws SQLException{
		String query = "select * from accounts where username like '%"
				+ username + "%'";
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		System.out.println(r);
		UserBean user = new UserBean(r.getString("username"),r.getString("password"),Boolean.parseBoolean(r.getString("isAdmin")));
		System.out.println(user.toString());
		r.close();
		p.close();
		con.close();
		return user;
	}
	
	/*
	 * update user's password 
	 */
	public UserBean updatePassword(String username, String password) throws SQLException{
		String query = "update accounts set password ='" + password + "' where username like '%"
				+ username + "%'"; 
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		System.out.println(r);
		UserBean user = new UserBean(r.getString("username"),r.getString("password"),Boolean.parseBoolean(r.getString("isAdmin")));
		System.out.println("UPDATED USER" + user.toString());
		r.close();
		p.close();
		con.close();
		return user;
	}
	
	/*
	 * update user's isAdmin status
	 */
	public UserBean updateIsAdmin(String username, String isAdmin) throws SQLException{
		String query = "update accounts set isadmin ='" + isAdmin + "' where username like '%"
				+ username + "%'"; 
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		System.out.println(r);
		UserBean user = new UserBean(r.getString("username"),r.getString("password"),Boolean.parseBoolean(r.getString("isAdmin")));
		System.out.println("UPDATED USER" + user.toString());
		r.close();
		p.close();
		con.close();
		return user;
	}
	
	/*
	 * add a new account
	 */
	public void addUser(UserBean user) throws SQLException{
		String query = "insert into accounts(username, password, isadmin) values ('" + user.getUserName() + "', '" + 
					user.getPassword() + "', '" + Boolean.toString(user.getisAdmin()) + "')"; 
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		r.close();
		p.close();
		con.close();
	}
	
	/*
	 * delete account by username
	 */
	public void removeUser(String username) throws SQLException{
		String query = "DELETE FROM ACCOUNTS WHERE username LIKE '%" + username + "%'"; 
	Connection con = this.ds.getConnection();
	PreparedStatement p = con.prepareStatement(query);
	ResultSet r = p.executeQuery();
	r.close();
	p.close();
	con.close();
	}
}
