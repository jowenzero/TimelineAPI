package com.doublebox.timelineapi.models;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class SqlResult {
	private final boolean IsSuccess;
	private final String Message;
	private final SqlRowSet Data;
	private final String ScalarData;

	public SqlResult(boolean IsSuccess, 
			String Message, 
			String ScalarData) {
		this.IsSuccess = IsSuccess;
		this.Message = Message;
		this.Data = null;
		this.ScalarData = ScalarData;
	}
	
	public SqlResult(boolean IsSuccess, 
			String Message, 
			SqlRowSet Data) {
		this.IsSuccess = IsSuccess;
		this.Message = Message;
		this.Data = Data;
		this.ScalarData = "";
	}
	
	public boolean isIsSuccess() {
		return IsSuccess;
	}

	public String getMessage() {
		return Message;
	}

	public SqlRowSet getData() {
		return Data;
	}

	public String getScalarData() {
		return ScalarData;
	}
}
