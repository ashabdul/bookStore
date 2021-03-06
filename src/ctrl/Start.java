package ctrl;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.AddressBean;
import bean.POBean;
import bean.POItemBean;
import bean.ReviewBean;
import bean.UserBean;
import bean.VisitEventBean;
import model.AddressDAO;
import model.BookBean;
import model.BookDAO;
import model.PODAO;
import model.POItemDAO;
import model.ReviewDAO;
import model.UserDAO;
import model.VisitEventDAO;

/**
 * Servlet implementation class Start
 */
@WebServlet(urlPatterns = {"/Start", "", "/ScienceBooks", "/FictionBooks", "/EngineeringBooks", "/Logout"})
public class Start extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BookDAO search = null;
	private UserBean user;
	private ReviewDAO review;
	private VisitEventDAO visit;
	int RequestCount = 0;

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
			visit = new VisitEventDAO();
			System.out.println("search ready");
		}

		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("addToCart") != null){
			String bookISBN = request.getParameter("addToCart");
			Map<String, BookBean> map = new HashMap<String, BookBean>();
			BookBean book = new BookBean();
			try {
				map = search.retrieve(bookISBN);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			book = map.get(request.getParameter("addToCart"));
		
			//user.getCart().clear();
			user.getCart().add(book);
			
			for(BookBean b: user.getCart().getCartItems().values())
			{
			System.out.println("Book in cart = " + b.getTitle());
			}
			response.getWriter().write("true");
			
			System.out.println("Button value = " + request.getParameter("addToCart"));
		}
		/*
		 * Segment authored by William
		 * This is to handle the drop down menu on the navigation bar.  getRequestURI returns everything after the domain+port.
		 * So if you press the Science category it directs to localhost:8080/Project/ScienceBooks (see home.jspx).  The getRequestURI
		 * would return on this input "/Project/ScienceBooks".  The next line takes that return, finds the last index of '/' and
		 * grabs the substring from that character + 1 to the end.  Thus /Project/ScienceBooks becomes Sciencebooks.
		 * Now check and see if its any of the category buttons.  If so search for that category and direct to thre results page
		 */
		String subString = request.getRequestURI().substring(request.getRequestURI().lastIndexOf('/') + 1);
		if(subString.equals("ScienceBooks")) {
			try {
				Map<String, BookBean> books = search.retrieve("science");
				request.setAttribute("list", books.values());
			} catch (SQLException e) {
				e.printStackTrace();
			}
			request.getRequestDispatcher("scienceBooks.jspx").forward(request, response);
			return;
		}
		if(subString.equals("FictionBooks")) {
			try {
				Map<String, BookBean> books = search.retrieve("fiction");
				request.setAttribute("list", books.values());
			} catch (SQLException e) {
				e.printStackTrace();
			}
			request.getRequestDispatcher("fictionBooks.jspx").forward(request, response);
			return;
		}
		if(subString.equals("Logout")) {
			request.getSession().invalidate(); //Invalidates the login
			request.getRequestDispatcher("").forward(request, response); //Forwards back to this server
			return; //Guarantees an exit
		}
		if(subString.equals("EngineeringBooks")) {
			try {
				Map<String, BookBean> books = search.retrieve("engineering");
				request.setAttribute("list", books.values());

			} catch (SQLException e) {
				e.printStackTrace();
			}
			request.getRequestDispatcher("engineeringBooks.jspx").forward(request, response);
			return;
		}

		//if the user is loged in set attribute isLogedIn = true
		if(request.getRemoteUser() != null){
			request.setAttribute("isLogedIn", "true");
		}
		else{
			request.setAttribute("isLogedIn", "false");
		}

		
		if(request.getParameter("submitReview") != null && request.getParameter("bid") != null)
		{
			try {
				System.out.println("review submitted: "+ request.getParameter("submitReview"));
				ReviewBean review;
				//handle the case when the user does not submit rating
				if(request.getParameter("stars") == null){
					review = new ReviewBean(request.getParameter("bid"), request.getParameter("textArea"), 0);
				}
				else{
					review = new ReviewBean(request.getParameter("bid"), request.getParameter("textArea"), Integer.parseInt(request.getParameter("stars")));
				}
				ReviewDAO reviewDAO = new ReviewDAO();
				reviewDAO.addReview(review);


			}catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		user.setUserName(request.getRemoteUser());
		//When first visiting the website always redirect to home.
		request.getRequestDispatcher("home.jspx").forward(request, response);


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * William
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//added by michel
		//set the user name based on the username from the login system
		user.setUserName(request.getRemoteUser());
		//set isLogedIn attribute based on the user name not bein null indicating that the user did log in
		if(user.getUserName() != null){
			request.setAttribute("isLogedIn", "true");
		}
		else{
			request.setAttribute("isLogedIn", "false");
		}
		//-------------------------------------------
		System.out.println("is loged is as: "+ request.getRemoteUser());
		if(request.getParameter("searchSubmit") != null) //If the user arrived via the search box
		{
			//check if the user is loged in to set the isLogedIn attribute accordingly 
			if(user.getUserName() != null){
				request.setAttribute("isLogedIn", "true");
			}
			else{
				request.setAttribute("isLogedIn", "false");
			}

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
				books = search.retrieve(searchParam); //Conduct the search
			}

			catch(SQLException e)
			{
				e.printStackTrace();
			}

			for(BookBean b: books.values())
			{
				System.out.println(b.getBid() + " " +  b.getTitle() + " " + b.getCategory() + " " + b.getPrice());
			}

			request.setAttribute("list", books.values()); //Store the results of the search as an attribute to be grabbed in jspx
			request.getRequestDispatcher("searchResult.jspx").forward(request, response); //Forward to the results pags
		}
		
		if(request.getParameter("cartsubmit") != null)
		{
			request.setAttribute("list", user.getCart().getCartItems().values());
			request.getRequestDispatcher("cart.jspx").forward(request, response);
		}

		/* edited by Michel */
		/*this will handle the event of pressing add review button in book.jspx page*/
		if(request.getParameter("submitReview") != null)
		{
			try {
				String ISBN = request.getParameter("submitReview");
				System.out.println("isbn to review=" + request.getParameter("submitReview"));
				System.out.println("review submitted: "+ request.getParameter("textArea"));
				ReviewBean review;
				//handle the case when the user does not submit rating
				if(request.getParameter("stars") == null){
					review = new ReviewBean(ISBN,request.getParameter("textArea"), 0);
				}
				else{
					review = new ReviewBean(ISBN,request.getParameter("textArea"), Integer.parseInt(request.getParameter("stars")));
				}
				ReviewDAO reviewDAO = new ReviewDAO();
				reviewDAO.addReview(review);

			}catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}


		/* edited by Michel */
		if(request.getParameter("addToCart") != null)
		{
			//check if the user is loged in to set the isLogedIn attribute accordingly 
			if(user.getUserName() != null){
				request.setAttribute("isLogedIn", "true");
			}
			else{
				request.setAttribute("isLogedIn", "false");
			}
			try {
				String bookISBN = request.getParameter("addToCart");
				Map<String, BookBean> map = new HashMap<String, BookBean>();
				BookBean book = new BookBean();
				map = search.retrieve(bookISBN);
				book = map.get(request.getParameter("addToCart"));
				user.getCart().add(book);
				//insert a CART visit event to the VisitEvent table
				VisitEventDAO visitEvent = new VisitEventDAO();
				VisitEventBean event = new VisitEventBean(book.getBid(),"CART");
				visitEvent.addEvent(event);
				System.out.println("Button value = " + request.getParameter("addToCart"));
			} catch (SQLException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		/* edited by Michel*/
		if(request.getParameter("imagesubmit") != null)
		{

			//check if the user is loged in to set the isLogedIn attribute accordingly 
			if(user.getUserName() != null){
				request.setAttribute("isLogedIn", "true");
			}
			else{
				request.setAttribute("isLogedIn", "false");
			}

			System.out.println("clicked," + request.getParameter("imagesubmit"));
			try {
				//create a book object to hold the info of the clicked book after retrieving it from the database
				BookBean book = search.retrieve(request.getParameter("imagesubmit")).get(request.getParameter("imagesubmit"));
				//create a linked list to hold all reviews for the book after retrieving them from the database
				LinkedList<ReviewBean> reviewList = review.retriveReview(request.getParameter("imagesubmit"));
				//calculate the average stars rating for the book
				double stars = 0;
				double tempStars = 0;
				DecimalFormat format = new DecimalFormat("#.0");

				for(int i = 0; i < reviewList.size(); i++){
					int currentStar = reviewList.get(i).getStars();
					/*if the book have rating 0 then don't include it in the average calculator because it indicates no 
					 *input from user for rating */
					if(currentStar == 0){
						continue;
					}
					else{
						tempStars = tempStars + currentStar;
					}

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
				request.setAttribute("bookStars", format.format(stars));
				/*//check if user is loged in to decide whether to show the write review part or not
				if(user.getUserName() != null){
					request.setAttribute("isLogedIn", "true");
				}
				else{
					request.setAttribute("isLogedIn", "false");
				}*/
				//insert a VIEW visit event to the VisitEvent table
				VisitEventDAO visitEvent = new VisitEventDAO();
				VisitEventBean event = new VisitEventBean(book.getBid(),"VIEW");
				visitEvent.addEvent(event);
				//redirect to the book display page
				request.getRequestDispatcher("book.jspx").forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		//added by michel with help from ashfaq

		String status;
		if (request.getParameter("placeOrder") != null){
			RequestCount++;

			if(RequestCount == 3){
				System.out.println("Request denied");
				request.getRequestDispatcher("requestDenied.jspx").forward(request, response);//Need to change the home page to Request denied Page
				status = "DENIED";
				RequestCount = 0;
			}
			else{
				status = "PROCESSED";

				String SStreet = request.getParameter("j_Sstreet");
				String SProvince = request.getParameter("j_Sprovince");
				String SCountry = request.getParameter("j_Scountry");
				String SZip = request.getParameter("j_Szip");
				String SPhone = request.getParameter("j_Sphone");
				String LName = request.getParameter("j_lastName");
				String FName = request.getParameter("j_firstName");

					
				try {	
				//create addressBean from the form and add it to the DB
				AddressBean newAddress = new AddressBean(null, SStreet, SProvince, SCountry, SZip, SPhone);
				AddressDAO address;
				
					address = new AddressDAO();
				 
				address.addAddress(newAddress);
				//create poBean and add it to the table, use address.getLast to retrieve the id of the address we just added
				PODAO po = new PODAO();
				//create a linked list of the cart
				LinkedList<BookBean> books = (LinkedList<BookBean>) user.getCart().getCartItems().values();

				//for each element in the cart, creat a PO item and add it to the db and create a POItem and add it to db
				for(int i = 0; i < books.size(); i++){
					POBean newPO = new POBean(0, LName, FName, status, Integer.parseInt(address.retriveLast().getId()));
					po.addPO(newPO);
					int quantity = 1;
					String bookID = books.get(i).getBid();
					int bookPrice = (int) books.get(i).getPrice();
					String userName = request.getRemoteUser();
					//the poItem id should be equal to the ID of the last entered po
					POItemBean poItem = new POItemBean(po.retriveLast().getId(),bookID, quantity, bookPrice, userName);
					POItemDAO itemDAO = new POItemDAO();
					itemDAO.addPOItem(poItem);
					//adding the visitEvent
					VisitEventDAO visitEventDAO = new VisitEventDAO();
					VisitEventBean visitBean = new VisitEventBean( bookID,"PURCHASE");
					visitEventDAO.addEvent(visitBean);
				}		
				}
				catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				request.getRequestDispatcher("paymentSuccess.jspx").forward(request, response);

			}
		}
		
		//added by Ashfaq
		if (request.getParameter("generateReport")!= null){
			
			request.getRequestDispatcher("booksSold.jspx").forward(request, response);
			
				if(request.getParameter("enterMonth")!= null){
					try{
						
						LinkedList<VisitEventBean> PurchaseList = visit.retrievePurchaseEventByMonth(Integer.parseInt(request.getParameter("month")));
					}
					catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					}
				}
		}
		//added by Ashfaq
		if(request.getParameter("popularBooks")!= null){
			request.getRequestDispatcher("popularBook.jsp").forward(request, response);
			
			try{
				
				LinkedList<BookBean> PopularBook = visit.retrieveMostPopularBook();
			}
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}
		}
		//added by Ashfaq
		if(request.getParameter("buyingStatistics")!= null){ 
			request.getRequestDispatcher("buyerStats.jsp").forward(request, response);
			
			try{
				UserDAO username = new UserDAO();
				
				
				LinkedList<UserBean> BuyerStats = username.retrieveUserSpent();
			}
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
}
}
