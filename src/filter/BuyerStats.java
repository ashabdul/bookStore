package filter;

import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class BuyerStats
 */
@WebFilter(dispatcherTypes = {DispatcherType.REQUEST }
					, urlPatterns = { "/BuyerStats" }, servletNames = { "Start" })
public class BuyerStats implements Filter {

    /**
     * Default constructor. 
     */
    public BuyerStats() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		
		ServletResponse newResponse = response;
		
		if (request instanceof HttpServletRequest) {
		      newResponse = new CharResponseWrapper((HttpServletResponse) response);
		    }
		
		// pass the request along the filter chain
		chain.doFilter(request, response);
		
		if (newResponse instanceof CharResponseWrapper) {
		      String text = newResponse.toString();
		      if (text != null) {
		        text = "*";
		        response.getWriter().write(text);
		      }
		}
	}
	
	class CharResponseWrapper extends HttpServletResponseWrapper {
		  protected CharArrayWriter charWriter;

		  protected PrintWriter writer;

		  protected boolean getOutputStreamCalled;

		  protected boolean getWriterCalled;

		  public CharResponseWrapper(HttpServletResponse response) {
		    super(response);

		    charWriter = new CharArrayWriter();
		  }

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
}
