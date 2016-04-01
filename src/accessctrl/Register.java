package accessctrl;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.UserDAO;
import bean.UserBean;

/**
 * Servlet implementation class Login
 * @author William
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/*
	 * This class handles all requests having to do with registering a new user and redirecting around the login/register pages.
	 * The login system consists mainly of two possible paths.
	 * Case 1: The user entered a page that requires logging in.  An example is the partners.jspx page which brings up the SOAP panel for partners.
	 * In this case a form (reallogin.jspx) is presented by Tomcat which is then used to verify the user against the database.  This is done entirely
	 * by Tomcat (see WEB-INF/web.xml and META-INF/context.xml for realm and role setup).  Thus in case 1 this servlet need not be involved.
	 * Case 2: The user clicked the "click here to register" button on any login page.
	 * In this case the user enters credentials into the registration form and then presses the "register" button.  This button triggers this
	 * servlet which manages insertion of credentials into the database and directing them to the login form to now use those credentials.
	 */
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Register() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Should never enter via get
		//If it entered via get assume it's not a login request, redirect to home.
		response.sendRedirect("home.jspx");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (Boolean.parseBoolean(request.getParameter("Register"))) { //Make sure it came from the registration form
			if(request.getParameter("username").equals("") || request.getParameter("password").equals("")){ //If the user provided blank credentials
				request.setAttribute("error_msg", "please enter both fields"); //Provide an error message
				request.getRequestDispatcher("/register.jspx").forward(request, response); //Return them to the registration page
			}
			//System.out.println("You have registered as " + request.getParameter("username"));
			//System.out.println("with password " + request.getParameter("password"));
		}
		else if (Boolean.parseBoolean(request.getParameter("logout"))) { //Came from a logout button
			request.logout();
		}
		else { //Somehow came from another area, send them to the registration form
			request.getRequestDispatcher("/register.jspx").forward(request, response);
		}
		//Insert into database now
		// Default return location is the login.jspx page (which displays the 'login success' page).
		String dispatchLocation = "login.jspx";
		String err_msg = "Registration successful, please login to continue"; //Desired message to display -- No longer done on redirect, can remove.
		
		String userName = request.getParameter("username");
		String password = request.getParameter("password"); //Grab the username and password fields.
		String type = "user"; //Registration through the form is always a regular user
		
		/*
		 * Need to check that username and password conform.
		 * Could have been checked client side with javascript to ease the load on the server
		 * but that is inherently insecure so it needs to be double checked here just in case
		*/
		UserBean newUser = new UserBean(userName, password, type); //Create a userBean for insertion to the database
		
		UserDAO newUserDAO = null; //This DAO interfaces with the accounts table, takes in a userBean.
		try { //Attempt to make an instance of UserDAO
			newUserDAO = new UserDAO();
		} catch(Exception e) {
			System.out.print("Error getting UserDAO for registration!");
			e.printStackTrace();
		}
		//Check if the username is already registered
		try {
			System.out.print("WOLOLOLOLOLOLOLOLOLOL");
			if(newUserDAO.hasUser(userName)) { //Username found
				dispatchLocation = "register.jspx"; //Since the username was already taken we send them back to register instead of logging in.
				err_msg = "Username already in use, please try another one";
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		//Username is free to register
		try {
			newUserDAO.addUser(newUser);
		} catch (SQLException e) {
			System.out.println("Error inserting user for registration!");
			e.printStackTrace();
		}
		
		//Registration went fine
		request.setAttribute("error_msg", err_msg);
		response.sendRedirect(dispatchLocation);
		//request.getRequestDispatcher(dispatchLocation).forward(request, response);
	}
}
