/**
	 * @author Michel Abd Alsamad
	 * 	
	 */

package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import bean.POBean;

public class PODAO {

DataSource ds;
	
	public PODAO() throws ClassNotFoundException{
		try {
			ds = (DataSource) (new InitialContext())
					.lookup("java:/comp/env/jdbc/EECS");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * add a new po 
	 */
	public void addPO(POBean po) throws SQLException{
		String query = "INSERT INTO po (lname, fname, status, address) VALUES ('" + po.getLname() + "', '" +
				po.getFname() + "', '" + po.getStatus() + "', " + po.getAddress() + ")";
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		p.execute();
		p.close();
		con.close();
	}
	
	/*
	 * remove po by id
	 */
	public void removePO(String id) throws SQLException{
		String query = "DELETE FROM po WHERE id=" + id;
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		r.close();
		p.close();
		con.close();
	}
	
	//implement the retrieval method
	public POBean retriveLast() throws SQLException{
		String query = "SELECT * FROM po WHERE id= (SELECT MAX(id) FROM PO)" ;
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		r.next();
		POBean po = new POBean(r.getInt("id"),r.getString("lname"),
				r.getString("fname"),r.getString("status"),r.getInt("address"));
		System.out.println("retrived PO: " + po.toString());
		r.close();
		p.close();
		con.close();
		return po;
	}
}
