/**
 * 
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import bean.UserBean;

/**
 * @author Basit
 *
 */
public class BookDAO {
	
private DataSource ds;
	
	public BookDAO() throws ClassNotFoundException {
		// TODO Auto-generated constructor stub
		
		try
		{
			ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/EECS");
		} 
		
		catch (NamingException e)
		{
			e.printStackTrace();
		}
	}
	
	public Map<String, BookBean> retrieve(String name) throws
	SQLException{
		String query = "select * from book where UPPER(title) like UPPER('%"+ name + "%') OR UPPER(bid) like UPPER('%"+ name + "%') OR UPPER(category) like UPPER('%"+ name + "%')";
		//String query = "select * from book where UPPER(title) like UPPER('%trump%')";
		
		Map<String, BookBean> rv = new HashMap<String, BookBean>();
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		
		while (r.next()){
			String bidd = r.getString("BID");
			String title = r.getString("TITLE");
			int price = r.getInt("PRICE");
			String category = r.getString("CATEGORY");
			
			BookBean book = new BookBean(bidd, title, category, price);
			rv.put(bidd, book);
		}
		r.close();
		p.close();
		con.close();
		return rv;
	}

	/*added by Michel*/
	public BookBean retriveByBID(String bid) throws SQLException{
		String query = "SELECT * FROM books WHERE bid like '%"
				+ bid + "%'";
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		BookBean book = new BookBean(r.getString("bid"), r.getString("title"), r.getString("category"), Integer.parseInt(r.getString("price")));
		r.close();
		p.close();
		con.close();
		return book;
	}
}
