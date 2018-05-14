package com.jira.entity;

import javax.xml.bind.annotation.XmlRootElement;

import com.jira.bean.Staff;

@XmlRootElement
public class StaffForQuery {
	private int id;
	private String name;
	private int value;

	public StaffForQuery() {

	}

	public StaffForQuery(Staff s) {
		this.id = s.getId();
		this.name = s.getName();
		this.value = s.getId();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}
