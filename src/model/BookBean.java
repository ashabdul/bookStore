/**
 * 
 */
package model;

/**
 * @author Basit
 *
 */
public class BookBean {
	
	String bid = "", name = "", category = "";
	double price  = 0;
	
	public BookBean ()
	{
		
	}
	
	public BookBean (String bid, String name, String category, double price)
	{
		this.bid = bid;
		this.name = name;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
