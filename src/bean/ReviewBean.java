/**
	 * @author Michel Abd Alsamad
	 * 	
	 */

package bean;

public class ReviewBean {
	
	String id;
	String bid;
	String review;
	int stars;
	
	public ReviewBean(String id, String bid, String review, int stars) {
		super();
		this.id = id;
		this.bid = bid;
		this.review = review;
		this.stars = stars;
	}

	/**
	 * @return the stars
	 */
	public int getStars() {
		return stars;
	}

	/**
	 * @param stars the stars to set
	 */
	public void setStars(int stars) {
		this.stars = stars;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
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
	 * @return the review
	 */
	public String getReview() {
		return review;
	}

	/**
	 * @param review the review to set
	 */
	public void setReview(String review) {
		this.review = review;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ReviewBean [id=" + id + ", bid=" + bid + ", review=" + review
				+ "]";
	}
	
	
	
}
