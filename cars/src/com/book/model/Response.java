package com.book.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Response {
	private String status;
	private String message;

	public static Response ok(String value){
		
		Response resp = new Response();
		resp.setStatus("Ok");
		resp.setMessage(value);
		
		return resp;
	}

	public static Response error(String value){
		
		Response resp = new Response();
		resp.setStatus("ERROR");
		resp.setMessage(value);
		
		return resp;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
