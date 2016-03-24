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

import bean.ReviewBean;


public class ReviewDAO {

DataSource ds;
	
	public ReviewDAO() throws ClassNotFoundException{
		try {
			ds = (DataSource) (new InitialContext())
					.lookup("java:/comp/env/jdbc/EECS");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @add review to the review table
	 */
	public void addReview(ReviewBean review) throws SQLException{
		String query = "INSERT INTO reviews (bid, review, stars) VALUES ('" + review.getBid() + "', '" +
				review.getReview() + "', '" + review.getStars() + "')";
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		r.close();
		p.close();
		con.close();
	}
	
	
	/**
	 * @return reviews for specific bid (i.e ISBN)
	 */
	public LinkedList<ReviewBean> retriveReview(String bid) throws SQLException{
		LinkedList<ReviewBean> list = new LinkedList<ReviewBean>();
		String query = "SELECT * FROM reviews WHERE bid like '%"
				+ bid + "%'";
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		while (r.next()){
			String id = r.getString("id");
			String bidd = r.getString("BID");
			String review = r.getString("REVIEW");
			int stars = r.getInt("STARS");
			ReviewBean result = new ReviewBean(id, bidd, review, stars);
			list.add(result);
		}
		
		r.close();
		p.close();
		con.close();
		return list;
	}
}
