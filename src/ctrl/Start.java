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

import bean.AddressBean;
import bean.POBean;
import bean.POItemBean;
import bean.ReviewBean;
import bean.UserBean;
import model.AddressDAO;
import model.BookBean;
import model.BookDAO;
import model.PODAO;
import model.POItemDAO;
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().setAttribute("LoggedInUserName", request.getRemoteUser());
		user.setUserName(request.getRemoteUser());
		System.out.println("is loged is as: "+ request.getRemoteUser());
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
		
		/* edited by Michel */
		if(request.getParameter("addToCart") != null)
		{
			try {
				String bookISBN = request.getParameter("addToCart");
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
					System.out.println("user is loged in..show review");
				}
				else{
					request.setAttribute("isLogedIn", "false");
					System.out.println("user is NOT loged in.. DONT show review");
				}
				//redirect to the book display page
				request.getRequestDispatcher("book.jspx").forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	
		/*Added by Ashfaq*/
		
		String status;
		if (request.getParameter("placeOrder") != null){
			RequestCount++;
			
			if(RequestCount == 3){
				System.out.println("Request denied");
				request.getRequestDispatcher("home.jspx").forward(request, response);//Need to change the home page to Request denied Page
				status = "DENIED";
				RequestCount = 0;
			}
			else{
				status = "PROCESSED";
			}
			
			String BStreet = request.getParameter("j_Bstreet");
			String SStreet = request.getParameter("j_Sstreet");
			String BProvince = request.getParameter("j_Bprovince");
			String SProvince = request.getParameter("j_Sprovince");
			String BCountry = request.getParameter("j_Bcountry");
			String SCountry = request.getParameter("j_Scountry");
			String BZip = request.getParameter("j_Bzip");
			String SZip = request.getParameter("j_Szip");
			String BPhone = request.getParameter("j_Bphone");
			String SPhone = request.getParameter("j_Sphone");
			
			//System.out.println(BStreet + SStreet + BProvince + SProvince + BCountry + SCountry + BZip + SZip + BPhone + SPhone);
			
			
			if (!(BStreet.equals(SStreet)) || !(BProvince.equals(SProvince)) || !(BCountry.equals(SCountry)) || !(BZip.equals(SZip)) || !(BPhone.equals(SPhone))){
				System.out.println("Addresses do not match");
				
				request.setAttribute("address_error", "Billing and Shipping Adresses do not match!");
				request.getRequestDispatcher("requestDenied.jspx").forward(request, response);
				
			}
			
			
			String LName = request.getParameter("lname");
			String FName = request.getParameter("fname");
			
			AddressBean newAddress = new AddressBean(null, SStreet, SProvince, SCountry, SZip, SPhone);
			
			AddressDAO address = null;
			try{
				address = new AddressDAO();
			}
			catch(Exception e){
				System.out.print("Error getting AddressDAO for registration!");
				e.printStackTrace();
			}
			
			try{
				address.addAddress(newAddress);
			}
			catch (SQLException e) {
				System.out.println("Error inserting new Address!");
				e.printStackTrace();
			}
			
			PODAO po = null;
			try {
				POBean newPO = new POBean(0, LName, FName, status, Integer.parseInt(address.retriveLast().getId()));
				po = new PODAO();
				po.addPO(newPO);
			} 
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Error Creating bean");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			/*POItemBean newPOItem = new POItembean(0, user.bid, user.price);
		    POItemDAO poItem = null;
		    
		    try{
				poItem = new POItemDAO();
			}
			catch(Exception e){
				System.out.print("Error getting POItemDAO for registration!");
				e.printStackTrace();
			}
		    
		    try{
				poItem.addPOItem(POItemBean);
			}
			catch (SQLException e) {
				System.out.println("Error inserting new POItem!");
				e.printStackTrace();
			}*/
			
			/*
			VisitEventBean VisitBean = new VisitBean();
			VisitEventDAO visit = null;
			
			try{
				visit = new VisitEventDAO();
			}
			catch(Exception e){
				System.out.print("Error getting VisitEventDAO for registration!");
				e.printStackTrace();
			}
		    
		    try{
				visit.addVisit(VisitBean);
			}
			catch (SQLException e) {
				System.out.println("Error inserting new Visit!");
				e.printStackTrace();
			}*/
			
			request.getRequestDispatcher("paymentSuccess.jspx").forward(request, response);
			
			
			
		}//Ashfaq's end
		
		
	}

}
