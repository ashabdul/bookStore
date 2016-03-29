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
		
		return true;
	}
	
	private static void AddressTest(PrintWriter out) {
		String id = "987654321";
		String street = "Keele Street";
		String province = "Ontario";
		String country = "Canada";
		String zip = "M6N 3E0";
		String phone = "9055555555";
		AddressBean addBean = new AddressBean(id, street, province, country, zip, phone);
		out.printf("Creating new Address Bean with values id=%s, street=%s, privince=%s, country=%s, zip=%s, phone=%s<br />",
				id, street, province, country, zip, phone);
		out.println();
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