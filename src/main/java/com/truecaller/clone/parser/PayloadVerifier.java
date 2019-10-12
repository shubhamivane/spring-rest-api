package com.truecaller.clone.parser;

import java.util.Map;

public class PayloadVerifier {
	private Map<String, String> payload;
	private String[] params;
	
	private PayloadVerifier() {
		
	}
	
	public PayloadVerifier(Map<String, String> payload, String[] params) {
		super();
		this.payload = payload;
		this.params = params;
	}
	
	public Map<String, String> getPayload() {
		return payload;
	}
	
	public void setPayload(Map<String, String> payload) {
		this.payload = payload;
	}
	
	public String[] getParams() {
		return params;
	}
	
	public void setParams(String[] params) {
		this.params = params;
	}
	
	public boolean isValid() {
		for(int i = 0 ; i < params.length ; i++) {
			if(!payload.containsKey(params[i])) {
				return false;
			}
		}
		return true;
	}
}
