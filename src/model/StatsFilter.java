package model;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class StatsFilter implements Filter {

	public void init(FilterConfig config) throws ServletException {
		String testParam = config.getInitParameter("test-param");
		System.out.println("init");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws java.io.IOException, ServletException {

		String ipAddress = request.getRemoteAddr();
		System.out.println("IP " + ipAddress);

		chain.doFilter(request, response);
	}

	public void destroy() {
		System.out.println("destroy");
	}
}