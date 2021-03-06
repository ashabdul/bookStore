/**
 * @author Michel Abd Alsamad
 * 	
 */


package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import bean.VisitEventBean;

public class VisitEventDAO {

DataSource ds;
	
	public VisitEventDAO() throws ClassNotFoundException{
		try {
			ds = (DataSource) (new InitialContext())
					.lookup("java:/comp/env/jdbc/EECS");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @return LinkedList of View events in a specific month
	 * month should passed as int of value from 01 - 12
	 */
	public LinkedList<VisitEventBean> retrieveViewEventByMonth(int month) throws SQLException{
		String query = "SELECT * FROM visitevent WHERE month(date) =" + month  + "AND event_type='VIEW'";
		LinkedList<VisitEventBean> list = new LinkedList<VisitEventBean>();
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		while (r.next()){
			String date = r.getString("date").toString();
			String bid = r.getString("bid");
			String eventType = r.getString("event_type");
			VisitEventBean event = new VisitEventBean(date, bid, eventType);
			list.add(event);
		}
		r.close();
		p.close();
		con.close();
		return list;
	}
	
	/**
	 * @return LinkedList of Purchase events in a specific month
	 * month should passed as int of value from 01 - 12
	 */
	public LinkedList<VisitEventBean> retrievePurchaseEventByMonth(int month) throws SQLException{
		String query = "SELECT * FROM visitevent WHERE month(date) =" + month  + "AND event_type='PURCHASE'";
		LinkedList<VisitEventBean> list = new LinkedList<VisitEventBean>();
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		while (r.next()){
			String date = r.getString("date").toString();
			String bid = r.getString("bid");
			String eventType = r.getString("event_type");
			VisitEventBean event = new VisitEventBean(date, bid, eventType);
			list.add(event);
		}
		r.close();
		p.close();
		con.close();
		return list;
	}
	
	/**
	 * @return LinkedList of Cart events in a specific month
	 * month should passed as int of value from 01 - 12
	 */
	public LinkedList<VisitEventBean> retrieveCartEventByMonth(int month) throws SQLException{
		String query = "SELECT * FROM visitevent WHERE month(date) =" + month  + "AND event_type='CART'";
		LinkedList<VisitEventBean> list = new LinkedList<VisitEventBean>();
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		while (r.next()){
			String date = r.getString("date").toString();
			String bid = r.getString("bid");
			String eventType = r.getString("event_type");
			VisitEventBean event = new VisitEventBean(date, bid, eventType);
			list.add(event);
		}
		r.close();
		p.close();
		con.close();
		return list;
	}
	
	/**
	 * @return LinkedList of number of book sold of each book id
	 */
	public LinkedList<BookBean> retrieveNumberPurchasedInMonth(int month) throws SQLException{
		String query = "SELECT date, bid, COUNT(bid) AS count FROM visiteventT WHERE month(date) =" + month + "AND event_type='PURCHASE' GROUP BY bid, date";
		LinkedList<BookBean> list = new LinkedList<BookBean>();
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		while (r.next()){
			String bid = r.getString("bid");
			int sold = r.getInt("count");
			BookBean event = new BookBean(bid, sold);
			list.add(event);
		}
		r.close();
		p.close();
		con.close();
		return list;
	}
	
	/**
	 * @return LinkedList of the top 10 carted booked
	 */
	public LinkedList<BookBean> retrieveMostPopularBook() throws SQLException{
		String query = "SELECT bid, COUNT(bid) AS count FROM VISITEVENT WHERE event_type='CART' GROUP BY bid ORDER BY count DESC FETCH NEXT 2 ROWS ONLY;";
		LinkedList<BookBean> list = new LinkedList<BookBean>();
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		while (r.next()){
			String bid = r.getString("bid");
			int sold = r.getInt("count");
			BookBean event = new BookBean(bid, sold);
			list.add(event);
		}
		r.close();
		p.close();
		con.close();
		return list;
	}
	
	/**
	 * @add visitEvent to the table
	 */
	public void addEvent(VisitEventBean visit) throws SQLException{
		String query = "INSERT INTO visitevent (bid, event_type) values ('" + visit.getBid() + "', '"+ visit.getEventType() + "')"; 
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		p.execute();
		p.close();
		con.close();
	}
	
}
