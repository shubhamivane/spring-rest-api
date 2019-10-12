package com.truecaller.clone.parser;

public class ResponseParser {
	private String message;
	private String status;
	
	public ResponseParser(String message, String status) {
		this.setMessage(message);
		this.setStatus(status);
	}

	public ResponseParser() {
		// TODO Auto-generated constructor stub
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
