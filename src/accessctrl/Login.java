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
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
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
			System.out.println("You are logged in as " + request.getParameter("username"));
			System.out.println("with password " + request.getParameter("password"));
		}else if(Boolean.parseBoolean(request.getParameter("Register"))) {
			System.out.println("You have registered as " + request.getParameter("username"));
			System.out.println("with password " + request.getParameter("password"));
		}
		//Insert into database now
	}
}
