package com.jira.entity;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.jira.bean.Staff;

@XmlRootElement
public class StaffResults {

	private List<StaffForQuery> results;

	public StaffResults() {
		this.results = new ArrayList<StaffForQuery>();
	}

	public List<StaffForQuery> getResults() {
		return results;
	}

	public void setResults(List<StaffForQuery> results) {
		this.results = results;
	}

	public void add(Staff s) {
		this.results.add(new StaffForQuery(s));
	}
}
