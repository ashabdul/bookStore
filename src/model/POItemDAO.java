package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import bean.POBean;
import bean.POItemBean;

public class POItemDAO {
	
DataSource ds;
	
	public POItemDAO() throws ClassNotFoundException{
		try {
			ds = (DataSource) (new InitialContext())
					.lookup("java:/comp/env/jdbc/EECS");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * add a poitem 
	 */
	public void addPOItem(POItemBean item) throws SQLException{
		String query = "INSERT INTO poitem (id, bid, price) VALUES ('" + item.getId() + "', '" +
				item.getBid() + "', '" + item.getPrice() + "')";
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		r.close();
		p.close();
		con.close();
	}
	
	/*
	 * remove POItem by id
	 */
	public void removePOItem(String id) throws SQLException{
		String query = "DELETE FROM po WHERE id=" + id;
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		r.close();
		p.close();
		con.close();
	}
	
	//implement the retrieval method
}
