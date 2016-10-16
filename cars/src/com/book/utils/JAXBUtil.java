package com.book.utils;

import java.io.StringWriter;

import javax.management.RuntimeErrorException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.stream.XMLStreamWriter;

import org.codehaus.jettison.mapped.MappedNamespaceConvention;
import org.codehaus.jettison.mapped.MappedXMLStreamWriter;

import com.book.model.Car;
import com.book.model.CarsList;
import com.book.model.Response;

public class JAXBUtil {
	private static JAXBUtil instance;
	private static JAXBContext context;
	
	@SuppressWarnings("unused")
	private static JAXBUtil getInstance(){
		return instance;
	}
	
	static {
		try {
			context = JAXBContext.newInstance(CarsList.class, Car.class, Response.class);
			
		} catch (Error e) {
			throw new RuntimeErrorException(e);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	public static String toXML(Object object){
		try {
			
			StringWriter writer = new StringWriter();
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(object, writer);
			String xml = writer.toString();
			
			return xml;
			
		} catch (JAXBException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String toJSON(Object object){
		try {
			
			StringWriter writer = new StringWriter();
			Marshaller marshaller = context.createMarshaller();
			MappedNamespaceConvention con = new MappedNamespaceConvention();
			XMLStreamWriter xmlStreamWriter = new MappedXMLStreamWriter(con, writer);
			marshaller.marshal(object, xmlStreamWriter);
			String json = writer.toString();
			
			return json;
			
		} catch (JAXBException e) {
			e.printStackTrace();
			return null;
		}
	}
}
