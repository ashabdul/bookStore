package model;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.AddressBean;
import bean.POBean;
import bean.POItemBean;

public class Payment extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private PODAO purchaseOrder;
	private POItemDAO poItem;
	private AddressDAO address;
	private PODAO po;
	
	public Payment(){
		super();
		// TODO Auto-generated constructor stub
		try
		{
			purchaseOrder = new PODAO();
			poItem = new POItemDAO();
			address = new AddressDAO();
			po = new PODAO();
		}

		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		int count = 0;
		if (request.getParameter("submit")!=null){
			
			count++;
			
			if(count == 3){
				System.out.println("Request denied");
				request.getRequestDispatcher("Home.jspx").forward(request, response);
			}
			
			String BStreet = request.getParameter("Bstreet");
			String SStreet = request.getParameter("Sstreet");
			String BProvince = request.getParameter("Bprovince");
			String SProvince = request.getParameter("Sprovince");
			String BCountry = request.getParameter("Bcountry");
			String SCountry = request.getParameter("Scountry");
			String BZip = request.getParameter("Bzip");
			String SZip = request.getParameter("Szip");
			String BPhone = request.getParameter("Bphone");
			String SPhone = request.getParameter("Sphone");
			
			
			if (BStreet != SStreet || BProvince != SProvince || BCountry != SCountry || BZip != SZip || BPhone != SPhone){
				System.out.println("Addresses do not match");
			}
			
			String LName = request.getParameter("lname");
			String FName = request.getParameter("fname");
			
			
			/*try {
				
				address.addAddress(address);
				po.addPO(po);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			
		}	
	}
}
