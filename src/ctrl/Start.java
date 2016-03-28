package ctrl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ReviewBean;
import bean.UserBean;
import model.BookBean;
import model.BookDAO;
import model.ReviewDAO;


/**
 * Servlet implementation class Start
 */
@WebServlet(urlPatterns = {"/Start", ""})
public class Start extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BookDAO search = null;
	private UserBean user;
	private ReviewDAO review;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Start() {
		super();
		// TODO Auto-generated constructor stub
		try
		{
			search = new BookDAO();
		}

		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	/* added by Michel*/
	public void init()throws ServletException{
    	 user = new UserBean();
    	try {
			review = new ReviewDAO();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		
		if(request.getParameter("searchSubmit") != null)
		{
			System.out.println("Start: ");
			String searchParam = "";
			Map<String, BookBean> books = new HashMap<String, BookBean>();

			if(request.getParameter("searchSubmit") != null)
			{
				System.out.println(request.getParameter("searchValue"));
				searchParam = request.getParameter("searchValue");
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
		
		/* edited by Michel*/
		if(request.getParameter("addToCart") != null)
		{
			try {
				String bookISBN = request.getParameter("addToCart").substring(5);
				BookBean book = new BookBean();
				book = search.retriveByBID(bookISBN);
				user.getCart().add(book);
				System.out.println("Button value = " + request.getParameter("addToCart"));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(request.getParameter("imagesubmit") != null)
		{
			System.out.println("Image value = " + request.getParameter("imagesubmit"));
		}
	}

}
