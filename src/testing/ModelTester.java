package testing;

import java.io.PrintWriter;
import java.sql.SQLException;

import model.*;
import bean.*;

/**
 * @author William
 *
 */
public class ModelTester {
	public static boolean runTests(PrintWriter out) {
		//Each class gets its own method for maintainability
		out.print("<h3>AddressDAO:</h3>");
		AddressDAOTest(out);

		out.print("<h3>UserDAO:</h3>");
		UserDAOTest(out);

		out.print("<h3>BookDAO:</h3>");
		BookDAOTest(out);
		return true;
	}

	private static void BookDAOTest(PrintWriter out) {
		BookDAO testDAO = null;
		try {
			testDAO = new BookDAO();
		} catch (ClassNotFoundException e) {
			out.print("<br />Failed to create bookDAO<br />");
			e.printStackTrace();
		}

		//Test if we can get books out (should be in database at all times)
		try {
			if(!testDAO.retrieve("engineering").isEmpty()) { //We know we have engineering books
				out.print("<br />Able to retrieve books by keyword<br />");
			} else {
				out.print("<br />No books found on keyword, make sure database has been populated<br />");
			}
		} catch (SQLException e) {
			out.print("<br />Was unable to retrieve books by keyword<br />");
			e.printStackTrace();
		}
	}

	private static void UserDAOTest(PrintWriter out) {
		/*
		 * In order to test the DAO class we have to create a UserBean.
		 * This will be used to insert into the database via the DAO, then operated on
		 * then finally removed.  If all go properly all tests will pass.
		 */
		String username = "TestingName";
		String password = "TestingPassword";
		String type = "user";
		UserBean testBean = new UserBean(username, password, type); //UserBean is created.

		UserDAO testDAO = null;

		try {
			testDAO = new UserDAO();
		} catch (ClassNotFoundException e) {
			out.print("<br />Failed to create the UserDAO<br />");
			return;
		}

		try {
			out.print("<br />Inserting test user into database");
			testDAO.addUser(testBean);

			out.print("<br />Checking if test user is in database...");
			if(testDAO.hasUser(username)) {
				out.print("User in database<br />");
			} else {
				out.print("Test Failed, user not in database<br />");
				return;
			}

			out.print("<br />Retrieving user, checking against inserted");
			UserBean retrieved = testDAO.retriveUser(username);
			if(retrieved.getUserName().equals(username) && retrieved.getPassword().equals(password) && retrieved.getType().equals(type))
			{
				out.print("<br />User was successfuly retrieved<br />");
			} else {
				out.print("<br />Test Failed, user is not same as inserted one<br />");
			}

			out.print("<br />Updating user with new type and password");
			testDAO.updateAccount_type(username, "admin");
			testDAO.updatePassword(username, "TestingTesting");

			retrieved = testDAO.retriveUser(username);
			if(retrieved.getUserName().equals(username) && retrieved.getPassword().equals("TestingTesting") && retrieved.getType().equals("admin"))
			{
				out.print("<br />User was successfuly updated<br />");
			} else {
				out.print("<br />Test Failed, user not updated<br />");
			}

			out.print("<br />Removing user from database...");
			testDAO.removeUser(username);
			if(!testDAO.hasUser(username)) {
				out.print("User removed from database<br />");
			} else {
				out.print("Test Failed, user still in database<br />");
				return;
			}
		} catch (SQLException e) {
			out.print("<br />Failed on database operation<br />");
			e.printStackTrace();
			return;
		}
	}

	private static void AddressDAOTest(PrintWriter out) {
		//First have to create an instance of the class
		String id = "987654321";
		String street = "Keele Street";
		String province = "Ontario";
		String country = "Canada";
		String zip = "M6N 3E0";
		String phone = "9055555555";
		AddressDAO test = null;
		AddressBean testBean = null;
		try {
			test = new AddressDAO();
		} catch (ClassNotFoundException e) {
			out.print("<br>Failed to create AddressDAO<br>");
			return;
		}
		testBean = new AddressBean(id, street, province, country, zip, phone);
		out.print("<br />Adding new address<br />");
		try {
			test.addAddress(testBean);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		out.print("<br />Trying to retrieve Address<br />");

		testBean = null;
		try {
			testBean = test.retriveLast();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(testBean.equals(new AddressBean(id, street, province, country, zip, phone))) {
			out.print("Retrieved Successfuly");
		}
		else {
			out.print(testBean.toString());
			out.print("<br />Expected: " + new AddressBean(id, street, province, country, zip, phone).toString());
		}		
	}
}
