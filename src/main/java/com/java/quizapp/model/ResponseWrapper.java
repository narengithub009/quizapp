package com.java.quizapp.model;

public class ResponseWrapper {
	private int id;
	private String response;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public ResponseWrapper(int id, String response) {
		super();
		this.id = id;
		this.response = response;
	}
	public ResponseWrapper() {
		super();
	}
	
	
	

}
