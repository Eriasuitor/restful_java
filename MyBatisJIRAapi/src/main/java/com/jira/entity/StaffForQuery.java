package com.jira.entity;

import javax.xml.bind.annotation.XmlRootElement;

import com.jira.bean.Staff;

@XmlRootElement
public class StaffForQuery {
	private int id;
	private String name;
	private int value;
	private String image;

	public StaffForQuery() {

	}

	public StaffForQuery(Staff s) {
		this.id = s.getId();
		this.name = s.getName();
		this.value = s.getId();
		this.image = s.getImage();
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
