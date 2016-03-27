package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import bean.EventBean;
import bean.POBean;

public class PaymentDAO {

	private DataSource ds;

	public PaymentDAO() throws ClassNotFoundException {
		// TODO Auto-generated constructor stub

		try {
			ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/EECS");
		}

		catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public void addPayee(POBean po) throws SQLException{
		String query = "INSERT INTO po (lname, fname, status, address) VALUES ('" + po.getLname() + "', '" +
				po.getFname() + "', '" + po.getStatus() + "', '" + po.getAddress() + "')";
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		r.close();
		p.close();
		con.close();
	}
	
	public void addEvent(EventBean event) throws  SQLException{
		String query = "INSERT INTO event (day, bid, eventType) VALUES ('" + event.getDay() + "', '" +
				event.getBid() + "', '" + event.getEventType() + "')";
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		r.close();
		p.close();
		con.close();
	}
	
}
