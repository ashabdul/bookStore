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
			user = new UserBean();
			review = new ReviewDAO();
			System.out.println("search ready");
		}

		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	/* added by Michel
	public void init()throws ServletException{
    	 
    	try {
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }*/
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//When first visiting the website always redirect to home.
		System.out.println("User is: " + request.getRemoteUser() + " they are an admin: " + request.isUserInRole("admin"));
		response.sendRedirect("home.jspx");
		//System.out.println(request.getQueryString());
		System.out.println("hello");
		System.out.println(request.getParameter("addToCart"));
		
		if(request.getParameter("addToCart") != null)
		{
			System.out.println("in doGet");
			try {
				
				String bookISBN = request.getParameter("addToCart");
				Map<String, BookBean> map = new HashMap<String, BookBean>();
				BookBean book = new BookBean();
				map = search.retrieve(bookISBN);
				book = map.get(request.getParameter("addToCart"));

				//user.getCart().clear();
				user.getCart().add(book);
				
				for(BookBean b: user.getCart().getCart().values())
				{
					System.out.println("Book in cart = " + b.getTitle());
				}
				response.getWriter().write("true");
				
				System.out.println("Button value = " + request.getParameter("addToCart"));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
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
				Map<String, BookBean> map = new HashMap<String, BookBean>();
				BookBean book = new BookBean();
				map = search.retrieve(bookISBN);
				book = map.get(request.getParameter("addToCart"));

				user.getCart().add(book);

				System.out.println("Button value = " + request.getParameter("addToCart"));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		/* edited by Michel*/
		if(request.getParameter("imagesubmit") != null)
		{
			
			System.out.println("clicked," + request.getParameter("imagesubmit"));
			try {
				//create a book object to hold the info of the clicked book after retrieving it from the database
				BookBean book = search.retrieve(request.getParameter("imagesubmit")).get(request.getParameter("imagesubmit"));
				//create a linked list to hold all reviews for the book after retrieving them from the database
				LinkedList<ReviewBean> reviewList = review.retriveReview(request.getParameter("imagesubmit"));
				//calculate the average stars rating for the book
				double stars = 0;
				double tempStars = 0;
				for(int i = 0; i < reviewList.size(); i++){
					tempStars = tempStars + reviewList.get(i).getStars();
				}
				//handle the case for where there is no reviews so we avoid devide by 0 case
				if(reviewList.size() > 0){
				stars = tempStars / reviewList.size();
				}
				//set all request attributes to use in the display page
				request.setAttribute("bookTitle", book.getTitle());
				request.setAttribute("bookPrice", book.getPrice());
				request.setAttribute("bookCategory", book.getCategory());
				request.setAttribute("bookID", book.getBid());
				request.setAttribute("reviewList", reviewList);
				request.setAttribute("bookStars", stars);
				//check if user is loged in to decide whether to show the write review part or not
				if(user.getUserName() != null){
					request.setAttribute("isLogedIn", "ture");
				}
				else{
					request.setAttribute("isLogedIn", "false");
				}
				//redirect to the book display page
				request.getRequestDispatcher("book.jspx").forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}
