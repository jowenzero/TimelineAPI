package com.doublebox.timelineapi.models;


public class APIResponse {
	private RequestStatus request_status;
	private Object response;
	
	public APIResponse(RequestStatus request_status, 
			Object response) {
		this.request_status = request_status;
		this.response = response;
	}
	
	public APIResponse(RequestStatus request_status) {
		this.request_status = request_status;
		this.response = "";
	}

	public RequestStatus getRequest_status() {
		return request_status;
	}

	public void setRequest_status(RequestStatus request_status) {
		this.request_status = request_status;
	}

	public Object getResponse() {
		return response;
	}

	public void setResponse(Object response) {
		this.response = response;
	}
}
