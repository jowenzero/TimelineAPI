package com.doublebox.timelineapi.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


@JsonInclude(Include.NON_NULL)
public class RequestStatus {
	private final Boolean success;
	private final String message;
	private final Integer code;
	private final String raw_message;
	
	
	public RequestStatus(Boolean success, 
			String message,
			Integer code,
			String raw_message) {
		this.success = null;
		this.message = message;
		this.code = code;
		this.raw_message = raw_message;
	}
	
	public RequestStatus(Boolean success, 
			String message,
			Integer code) {
		this.success = success;
		this.message = message;
		this.code = code;
		this.raw_message = "";
	}
	
	public String getRaw_message() {
		return raw_message;
	}

	public Boolean getSuccess() {
		return success;
	}

	public String getMessage() {
		return message;
	}

	public Integer getCode() {
		return code;
	}
}
