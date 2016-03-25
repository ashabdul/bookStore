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
		
		String BAddress = request.getParameter("Baddress");
		String SAddress = request.getParameter("SAddress");
		
		if (BAddress != SAddress){
			System.out.println("Addresses do not match");
		}
		
	}
	
	
	
}
