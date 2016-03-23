package bean;

public class EventBean {
	
	String day;
	String bid;
	String eventType;

	
	public EventBean(String day, String bid, String eventType) {
		this.day = day;
		this.bid = bid;
		this.eventType = eventType;
	}
	/**
	 * @return the day
	 */
	public String getDay() {
		return day;
	}
	/**
	 * @param day the day to set
	 */
	public void setDay(String day) {
		this.day = day;
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
		return "EventBean [day=" + day + ", bid=" + bid + ", eventType="
				+ eventType + "]";
	}
	
	
}
