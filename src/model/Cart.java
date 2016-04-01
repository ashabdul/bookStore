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

	private String isbn = "";
	private Map<String, BookBean> list = new HashMap<String, BookBean>();
	
	public void add(BookBean book)
	{
		list.put(book.getBid(), book);
	}
	
	public void remove(String bid)
	{
		list.remove(bid);
	}
	
	public int size()
	{
		return list.size();
	}
	
	public Map<String, BookBean> getCartItems()
	{
		return list;
	}
	
	public void clear()
	{
		list.clear();
	}
}
