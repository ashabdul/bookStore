/**
 * 
 */
package model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Basit
 *
 */
@XmlRootElement(name="bookReport")
public class BookBean {
	private String bid = "", title = "", category = "";
	private double price  = 0;
	
	public BookBean ()
	{
		
	}
	
	public BookBean (String bid, String title, String category, double price)
	{
		this.bid = bid;
		this.title = title;
		this.category = category;
		this.price = price;
	}

	/**
	 * @return the bid
	 */
	public String getBid() {
		return bid;
	}

	/**
	 * @param bid the bid to set
	 */
	public void setBid(String bid) {
		this.bid = bid;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}
}
