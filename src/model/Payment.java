package model;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Payment {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub		
		int count = 0;
		if (request.getParameter("submit")!=null){
			count++;
		}
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
		
	}
	
	
	
}
