package listener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionIdListener;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class PopularBook
 *
 */
@WebListener
public class PopularBook implements HttpSessionAttributeListener {

	private List<Integer> list = new ArrayList<Integer>();
	
    /**
     * Default constructor. 
     */
    public PopularBook() {
        // TODO Auto-generated constructor stub
    	
    	
    }

    public void attributeAdded(HttpSessionBindingEvent arg0)  { 
         // TODO Auto-generated method stub
    	
    	handleEvent(arg0 );
    	
    	
    	System.out.println("Added attribute");
    	System.out.println(arg0.getName() + "value is "+ arg0.getValue());
    	String value = arg0.getValue().toString();
    	list.add(Integer.parseInt(value));
    	
    }

	/**
     * @see HttpSessionAttributeListener#attributeRemoved(HttpSessionBindingEvent)
     */
    public void attributeRemoved(HttpSessionBindingEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see HttpSessionAttributeListener#attributeReplaced(HttpSessionBindingEvent)
     */
    public void attributeReplaced(HttpSessionBindingEvent arg0)  { 
         // TODO Auto-generated method stub
    	
    	handleEvent(arg0);
    	
    	System.out.println("attribute replaced");
    	System.out.println(arg0.getName() + " value is "+ arg0.getValue());
    	String value = arg0.getValue().toString();
    	list.add(Integer.parseInt(value));
    	
    	System.out.println("the new addToCart is : " + getvalue());
    	arg0.getSession().getServletContext().setAttribute("PopularBook", getvalue());
    }
    
    private void handleEvent(HttpSessionBindingEvent event){//teacher method
    	//check for attribute name to see which one was replaced
    	if(event.getName().equals("BookToCart")){//if the attribute changed was called principle  
    	
    	System.out.println("New book added");
    	}
    }
    
    private int getvalue(){//this method will return the result
    	Collections.sort(list);
    	if(list.size()==0)return 0;
    	else{
    		return list.get(list.size()-1);	
    	}
    	
    }
	
}
