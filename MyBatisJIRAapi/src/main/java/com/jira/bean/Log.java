package com.jira.bean;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Log {
	private int id;
	private int subtaskID;
	private Date startDate;
	private Date endDate;
	private int completed;
	private int assignedID;
	private int timeCost;
	private float economicCost;
	private String note;
	private Staff staff;
	private int insertUser;
	private Date insertDate;
	private int lastEditUser;
	private Date lastEditDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSubtaskID() {
		return subtaskID;
	}

	public void setSubtaskID(int subtaskID) {
		this.subtaskID = subtaskID;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getCompleted() {
		return completed;
	}

	public void setCompleted(int completed) {
		this.completed = completed;
	}

	public int getAssignedID() {
		return assignedID;
	}

	public void setAssignedID(int assignedID) {
		this.assignedID = assignedID;
	}

	public int getTimeCost() {
		return timeCost;
	}

	public void setTimeCost(int timeCost) {
		this.timeCost = timeCost;
	}

	public float getEconomicCost() {
		return economicCost;
	}

	public void setEconomicCost(float economicCost) {
		this.economicCost = economicCost;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public int getInsertUser() {
		return insertUser;
	}

	public void setInsertUser(int insertUser) {
		this.insertUser = insertUser;
	}

	public Date getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}

	public int getLastEditUser() {
		return lastEditUser;
	}

	public void setLastEditUser(int lastEditUser) {
		this.lastEditUser = lastEditUser;
	}

	public Date getLastEditDate() {
		return lastEditDate;
	}

	public void setLastEditDate(Date lastEditDate) {
		this.lastEditDate = lastEditDate;
	}

}
