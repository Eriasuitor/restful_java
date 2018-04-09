package com.jira.entity;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class GeneralResponse {
	private int responseCode = 200;
	private boolean isSuccessful = true;
	private String Information;
	private int effectRows;
	private List<?> results;

	public GeneralResponse() {

	}

	public GeneralResponse(List<?> list) {
		this.results = list;
	}

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

	public String getInformation() {
		return Information;
	}

	public void setInformation(String Information) {
		this.Information = Information;
	}

	public int getEffectRows() {
		return effectRows;
	}

	public void setEffectRows(int effectRows) {
		this.effectRows = effectRows;
	}

	public List<?> getResults() {
		return results;
	}

	public void setResults(List<?> results) {
		this.results = results;
	}

}
