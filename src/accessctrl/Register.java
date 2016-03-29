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
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Register() {
		super();
		// TODO Auto-generated constructor stub
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
			if(request.getParameter("username").equals("") || request.getParameter("password").equals("")){
				request.setAttribute("error_msg", "please enter both fields");
				request.getRequestDispatcher("/register.jspx").forward(request, response);
			}
			System.out.println("You have registered as " + request.getParameter("username"));
			System.out.println("with password " + request.getParameter("password"));
		}
		else { //Somehow came from another area, send themm to the registration form
			request.getRequestDispatcher("/register.jspx").forward(request, response);
		}
		//Insert into database now
		
		String dispatchLocation = "login.jspx";
		String err_msg = "Registration successful, please login to continue";
		
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		String type = "user"; //Registration through the form is always a regular user
		
		/*
		 * Need to check that username and password conform.
		 * Should have been checked client side with javascript to ease the load on the server
		 * but that is inherently insecure so it needs to be double checked here just in case
		*/
		UserBean newUser = new UserBean(userName, password, type);
		
		UserDAO newUserDAO = null;
		try { //Attempt to make an instance of UserDAO
			newUserDAO = new UserDAO();
		} catch(Exception e) {
			System.out.print("Error getting UserDAO for registration!");
			e.printStackTrace();
		}
		//Check if the username is already registered
		try {
			if(newUserDAO.hasUser(userName)) { //Username found
				dispatchLocation = "register.jspx";
				err_msg = "Username already in use, please try another one";
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
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
		request.getRequestDispatcher(dispatchLocation).forward(request, response);
	}
}
