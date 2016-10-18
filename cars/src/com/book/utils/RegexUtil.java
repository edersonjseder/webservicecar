package com.book.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;

/**
 * This class is to read URL and identify the request type on the browser
 * and check if it's sent an id as parameter or not, so the service can return the 
 * proper information based on the request made by the client.
 * 
 * @author ederson
 *
 */
public class RegexUtil {
	private static final Pattern regexAll = Pattern.compile("/cars");
	private static final Pattern regexById = Pattern.compile("/cars/cars/([0-9]*)");

	/**
	 * This method verifies if the URL is on the pattern 'url/id'
	 * 
	 * @param requestUri
	 * @return
	 * @throws ServletException
	 */
	public static Integer matchId(String requestUri) throws ServletException {
		// Verify the id
		Matcher matcher = regexById.matcher(requestUri);
		
		if(matcher.find() && matcher.groupCount() > 0){
			String result = matcher.group(1);
			
			if(result != null && result.trim().length() > 0){
				Integer id = Integer.parseInt(result);
				return id;
			}
		}
		
		return null;
	}
	
	/**
	 * This method returns the complete list of information without verify
	 * if it's sent an id as parameter.
	 * 
	 * @param requestUri
	 * @return
	 * @throws ServletException
	 */
	public static boolean matchAll(String requestUri) throws ServletException {
		Matcher matcher = regexAll.matcher(requestUri);
		
		if(matcher.find()){
			return true;
		}
		
		return false;
	}
}
