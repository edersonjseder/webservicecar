package com.book.utils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

/**
 * The XML responses needs to be sent to browser as a HTTP request, so this class is to write XML or JSON
 * with the standard content type (mime-type) so the client knows how to process the data sent.
 * 
 * @author ederson
 *
 */
public class ServletUtil {
	
	public static void writeXML(HttpServletResponse response, String xml) throws IOException {
		if(xml != null){
			PrintWriter writer = response.getWriter();
			response.setContentType("application/xml;charset=UTF-8");
			writer.write(xml);
			writer.close();
		}
	}
	
	public static void writeJSON(HttpServletResponse response, String json) throws IOException {
		if(json != null){
			PrintWriter writer = response.getWriter();
			response.setContentType("application/json;charset=UTF-8");
			writer.write(json);
			writer.close();
		}
	}

}
