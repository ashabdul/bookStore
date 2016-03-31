package webservices.soap;

import java.io.File;
import java.io.FileWriter;
import java.io.StringWriter;
import java.sql.SQLException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import model.BookBean;
import model.BookDAO;

public class Partners {
	public String getProductInfo(String productId) {
		BookDAO bookInfo = null;
		try {
			bookInfo = new BookDAO();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		//Get the book out of the collection returned by the retrieve function.
		//Unfortunately required Object array
		Object[] o = null;
		try {
			//Get the Map, get the collection from it, then get an array of that collection.
			o = bookInfo.retrieve(productId).values().toArray();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//Cast the BookBean out of the front of the object.
		BookBean theBook = (BookBean) o[0];
		
		//Get XML out of the BookBean
		StringWriter sw = new StringWriter();
		try {
			JAXBContext jc = JAXBContext.newInstance(theBook.getClass());
			Marshaller marshaller = jc.createMarshaller();

			SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
			sw.write("\n");
			sw.write("<?xml-stylesheet type=\"text/xsl\" href=\"SIS.xsl\"?>");
			marshaller.marshal(theBook, new StreamResult(sw));
		} catch (PropertyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sw.toString();
	}
}
