package ctrl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BookBean;
import model.BookDAO;

/**
 * Servlet implementation class Start
 */
@WebServlet(urlPatterns = {"/Start", ""})
public class Start extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Start() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//When first visiting the website always redirect to home.
		response.sendRedirect("home.jspx");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("hello");
		if(request.getParameter("searchSubmit") != null)
		{
			System.out.println("Start: ");
			String searchParam = "";
			BookDAO search = null;
			Map<String, BookBean> books = new HashMap<String, BookBean>();

			if(request.getParameter("searchSubmit") != null)
			{
				System.out.println(request.getParameter("searchValue"));
				searchParam = request.getParameter("searchValue");
			}

			try
			{
				search = new BookDAO();
			}

			catch(ClassNotFoundException e)
			{
				e.printStackTrace();
			}

			try
			{
				books = search.retrieve(searchParam);
			}

			catch(SQLException e)
			{
				e.printStackTrace();
			}

			for(BookBean b: books.values())
			{
				System.out.println(b.getBid() + " " +  b.getTitle() + " " + b.getCategory() + " " + b.getPrice());
			}

			request.setAttribute("list", books.values());
			request.getRequestDispatcher("searchResult.jspx").forward(request, response);
		}
	}

}
