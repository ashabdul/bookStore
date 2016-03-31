package webservices.soap;

import java.sql.SQLException;

import model.BookBean;
import model.BookDAO;

public class Partners {
	public String getProductInfo(String productId) {
		BookDAO bookInfo = null;
		try {
			bookInfo = new BookDAO();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		//Get the book out of the collection returned by the retrieve function.
		//Unfortunately required Object array
		Object[] o = null;
		try {
			//Get the Map, get the collection from it, then get an array of that collection.
			o = bookInfo.retrieve(productId).values().toArray();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//Cast the BookBean out of the front of the object.
		BookBean theBook = (BookBean) o[0];
		
		//Get XML out of the BookBean
		
		
		return theBook.toString();
	}
}
