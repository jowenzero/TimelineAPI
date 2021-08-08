package com.doublebox.timelineapi.models;

public class RequestData {
	private final String data;
	

	public RequestData(String data) {
		this.data = data;
	}
	
	public RequestData() {
		this.data = "";
	}

	public String getData() {
		return data;
	}

}
