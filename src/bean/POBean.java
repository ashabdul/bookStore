/**
	 * @author Michel Abd Alsamad
	 * 	
	 */

package bean;

public class POBean {
	int id;
	String Lname;
	String Fname;
	String status;
	int address;
	
	
	public POBean(int id, String lname, String fname, String status, int address) {
		this.id = id;
		Lname = lname;
		Fname = fname;
		this.status = status;
		this.address = address;
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
	 * @return the lname
	 */
	public String getLname() {
		return Lname;
	}


	/**
	 * @param lname the lname to set
	 */
	public void setLname(String lname) {
		Lname = lname;
	}


	/**
	 * @return the fname
	 */
	public String getFname() {
		return Fname;
	}


	/**
	 * @param fname the fname to set
	 */
	public void setFname(String fname) {
		Fname = fname;
	}


	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}


	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}


	/**
	 * @return the address
	 */
	public int getAddress() {
		return address;
	}


	/**
	 * @param address the address to set
	 */
	public void setAddress(int address) {
		this.address = address;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "POBean [id=" + id + ", Lname=" + Lname + ", Fname=" + Fname
				+ ", status=" + status + ", address=" + address + "]";
	}
	
	
}
