package com.jira.entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class GeneralResponse {
	private int responseCode = 200;
	private boolean isSuccessful = true;
	private String exceptionInf;
	private int effectRows;

	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	public boolean isSuccessful() {
		return isSuccessful;
	}

	public void setSuccessful(boolean isSuccessful) {
		this.isSuccessful = isSuccessful;
	}

	public String getExceptionInf() {
		return exceptionInf;
	}

	public void setExceptionInf(String exceptionInf) {
		this.exceptionInf = exceptionInf;
	}

	public int getEffectRows() {
		return effectRows;
	}

	public void setEffectRows(int effectRows) {
		this.effectRows = effectRows;
	}

}
