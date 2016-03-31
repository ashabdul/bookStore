/**
	 * @author Michel Abd Alsamad
	 * 	
	 */


package bean;

public class VisitEventBean {

	String date;
	String bid;
	String eventType;
	
	/*
	 * constructor without date as it will be set automatically
	 */
	
	public VisitEventBean(String bid, String eventType) {
		this.bid = bid;
		this.eventType = eventType.toUpperCase();
	}

	/*
	 * constructor 
	 */
	public VisitEventBean(String date, String bid, String eventType) {
		this.date = date;
		this.bid = bid;
		this.eventType = eventType;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
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
	 * @return the eventType
	 */
	public String getEventType() {
		return eventType;
	}

	/**
	 * @param eventType the eventType to set
	 */
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "VisitevEventBean [date=" + date + ", bid=" + bid
				+ ", eventType=" + eventType + "]";
	}
	
	
}
