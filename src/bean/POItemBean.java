/**
	 * @author Michel Abd Alsamad
	 * 	
	 */

package bean;

public class POItemBean {
	int id;
	String bid;
	int quantity;
	int unitPrice;
	int finalPrice;
	String userName;
	
	
	/**
	 * @constructor this is the main constructor 
	 */
	public POItemBean(int id, String bid, int quantity, int unitPrice,
			int finalPrice, String userName) {
		this.id = id;
		this.bid = bid;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		this.finalPrice = finalPrice;
		this.userName = userName;
	}

	/**
	 * @constructor this constructor does not take final price as input, only used for testing
	 */
	public POItemBean(int id, String bid, int quantity, int unitPrice) {
		this.id = id;
		this.bid = bid;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
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
	 * @return the quantiry
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantiry the quantiry to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the unitPrice
	 */
	public int getUnitPrice() {
		return unitPrice;
	}

	/**
	 * @param unitPrice the unitPrice to set
	 */
	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "POItemBean [id=" + id + ", bid=" + bid + ", quantity="
				+ quantity + ", unitPrice=" + unitPrice + "]";
	}
	
	
}