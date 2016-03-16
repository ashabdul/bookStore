/**
 * 
 */
package model;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Basit
 *
 */
public class Cart {

	String isbn = "";
	Map<String, BookBean> list = new HashMap<String, BookBean>();
	
	public void add(String bid, String name, String category, double price)
	{
		BookBean book = new BookBean(bid, name, category, price);
		list.put(bid, book);
	}
	
	public void remove(String bid)
	{
		list.remove(bid);
	}
}
