package accessctrl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		System.out.println(Boolean.parseBoolean(request.getParameter("Login")));
		System.out.println(Boolean.parseBoolean(request.getParameter("Register")));
		if(Boolean.parseBoolean(request.getParameter("Login"))) {
			if(request.getParameter("username").equals("") || request.getParameter("password").equals("")){
				request.setAttribute("error_msg", "please enter both fields");
				request.getRequestDispatcher("/login.jspx").forward(request, response);
			}
			System.out.println("You are logged in as " + request.getParameter("username"));
			System.out.println("with password " + request.getParameter("password"));
		}else if(Boolean.parseBoolean(request.getParameter("Register"))) {
			if(request.getParameter("username").equals("") || request.getParameter("password").equals("")){
				request.setAttribute("error_msg", "please enter both fields");
				request.getRequestDispatcher("/login.jspx").forward(request, response);
			}
			System.out.println("You have registered as " + request.getParameter("username"));
			System.out.println("with password " + request.getParameter("password"));
		}
		//Insert into database now
	}
}
