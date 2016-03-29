package testing;

import java.io.PrintWriter;

import bean.*;
/**
 * @author William
 *
 */
public class BeanTester {
	public static boolean runTests(PrintWriter out) {
		//AddressBean.java Testing
		out.println("<h3>AddressBean:</h3>");
		AddressTest(out); //This creates a local scope for the AddressBean tests
		
		out.println("<h3>EventBean:</h3>");
		EventTest(out);
		
		out.println("<h3>POBean:</h3>");
		POTest(out);
		return true;
	}
	
	private static void POTest(PrintWriter out) {
		int id = 2;
		String Lname = "Smith";
		String Fname = "John";
		String status = "Denied";
		int address = 2;
		
		POBean poBean = new POBean(id, Lname, Fname, status, address);
		out.printf("Creating new PO Bean with values id=%d, Fname=%s, Lname=%s, status=%s, address=%d<br />", id, Lname, Fname, status, address);
		out.printf("Reading values out of POBean... we got %s<br />",  poBean.toString());
		
		if(poBean.toString().equals("POBean [id=" + id + ", Lname=" + Lname + ", Fname=" + Fname
				+ ", status=" + status + ", address=" + address + "]")) {
			out.print("<br />Match!<br />");
		}else {
			out.print("<br />Do not match!<br />");
		}
	}

	private static void EventTest(PrintWriter out) {
		String day = "Tuesday";
		String bid = "12345";
		String eventType = "Something Happened";
		
		EventBean evtBean = new EventBean(day, bid, eventType);
		out.printf("Creating new Event Bean with values day=%s, bid=%s, eventType=%s<br />",
				day, bid, eventType);
		out.printf("Reading Values out of EventBean... we got %s<br />", evtBean.toString());
		if(evtBean.toString().equals("EventBean [day=" + day + ", bid=" + bid + ", eventType=" + eventType + "]")) {
			out.print("<br />Match!<br />");
		}else {
			out.print("<br />Do not match!<br />");
		}
	}

	private static void AddressTest(PrintWriter out) {
		String id = "987654321";
		String street = "Keele Street";
		String province = "Ontario";
		String country = "Canada";
		String zip = "M6N 3E0";
		String phone = "9055555555";
		AddressBean addBean = new AddressBean(id, street, province, country, zip, phone);
		out.printf("Creating new Address Bean with values id=%s, street=%s, province=%s, country=%s, zip=%s, phone=%s<br />",
				id, street, province, country, zip, phone);
		out.printf("Reading Values out of AddressBean... we got %s<br />", addBean.toString());
		if(addBean.toString().equals("addressBean [id=" + id + ", street=" + street + ", province="
				+ province + ", country=" + country + ", zip=" + zip
				+ ", phone=" + phone + "]")) {
			out.print("<br />Match!<br />");
		}else {
			out.print("<br />Do not match!<br />");
		}
	}
}