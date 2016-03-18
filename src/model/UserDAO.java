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
}
