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

import bean.AddressBean;

public class AddressDAO {

DataSource ds;
	
	public AddressDAO() throws ClassNotFoundException{
		try {
			ds = (DataSource) (new InitialContext())
					.lookup("java:/comp/env/jdbc/EECS");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	
	/*
	 * retrives address by ID
	 */
	public AddressBean retriveByID(String id) throws SQLException{
		String query = "select * from address where id=" + id;
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		r.next();
		AddressBean address = new AddressBean(r.getString("id"),r.getString("street"),
				r.getString("province"),r.getString("country"),r.getString("zip"), r.getString("phone"));
		System.out.println("retrived address: " + address.toString());
		r.close();
		p.close();
		con.close();
		return address;
	}
	
	public AddressBean retriveLast() throws SQLException{
		String query = "select * from address where id= (SELECT MAX(id) FROM address)" ;
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		r.next();
		AddressBean address = new AddressBean(r.getString("id"),r.getString("street"),
				r.getString("province"),r.getString("country"),r.getString("zip"), r.getString("phone"));
		System.out.println("retrived address: " + address.toString());
		r.close();
		p.close();
		con.close();
		return address;
	}
	
	
	/*
	 * add a new address row to the table
	 */
	public void addAddress(AddressBean address) throws SQLException{
		String query = "INSERT INTO Address (street, province, country, zip, phone) VALUES ('" + address.getStreet() + "', '" +
				address.getProvince() + "', '" + address.getCountry() + "', '" + address.getZip() + "', '" + address.getPhone() + "')";
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		p.execute();
		p.close();
		con.close();
	}
	
	/*
	 * remove an address by id
	 */
	public void removeAddress(String id) throws SQLException{
		String query = "delete from address where id=" + id;
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		p.execute();
		p.close();
		con.close();
	}
}
