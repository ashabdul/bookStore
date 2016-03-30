package testing;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author William
 *
 */
@WebServlet(urlPatterns = {"/TestCases", "/TestCases/BeanOnly", "/TestCases/ModelOnly"})
public class TestMain extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/*
	 * This class calls the other testers.  Implemented as a servlet for ease of use and practical testing.
	 * BeanTester.java tests all of the bean classes (in the package 'bean').
	 * ModelTester.java tests all the DAO and misc items (in package 'model').
	 * Start.java is not tested automatically.
	 * Register.java is not tested automatically because it requires a browser session and is better tested manually.
	 */
	
	//IMPORTANT: It is highly recommended that you completely clear the database of any and all "user" level entries in both accounts and
	//user_roles tables to avoid any collisions in the database during testing.

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response); //Allows us to write the instructions just once regardless of if its a GET or POST
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		boolean testBean = true; //By default test both sets
		boolean testModel = true;
		
		if(request.getServletPath().equals("/TestCases/ModelOnly")) {
			testBean = false;
		} else if(request.getServletPath().equals("/TestCases/BeanOnly")) {
			testModel = false;
		}

		out.println("<h1>Testing Results</h1>");
		if(testBean) {
			out.println("<h2>BeanTester:</h2>");

			BeanTester.runTests(out); //This tester prints its own messages to the user
		}
		if(testModel) {
			out.println("<h2>ModelTester:</h2>");

			ModelTester.runTests(out);
		}
	}
}
