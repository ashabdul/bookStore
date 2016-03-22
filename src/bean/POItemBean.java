package bean;

public class POItemBean {
	int id;
	String bid;
	int price;
	
	
	public POItemBean(int id, String bid, int price) {
		super();
		this.id = id;
		this.bid = bid;
		this.price = price;
	}


	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
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
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}


	/**
	 * @param price the price to set
	 */
	public void setPrice(int price) {
		this.price = price;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "POItemBean [id=" + id + ", bid=" + bid + ", price=" + price
				+ "]";
	}
	
	
}
