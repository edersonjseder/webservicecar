package com.book.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;

public class RegexUtil {
	private static final Pattern regexAll = Pattern.compile("/cars");
	private static final Pattern regexById = Pattern.compile("/cars/cars/([0-9]*)");

	public static Integer matchId(String requestUri) throws ServletException {
		// Verifica o id
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
	
	public static boolean matchAll(String requestUri) throws ServletException {
		Matcher matcher = regexAll.matcher(requestUri);
		
		if(matcher.find()){
			return true;
		}
		
		return false;
	}
}
