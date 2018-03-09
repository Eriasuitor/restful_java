package com.jira.bean;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Project {
	private int ID;
	private String Name;
	private String Status;
	private Date StartDate;
	private Date EndDate;
	private int ManagerID;
	private int TimeCost;
	private float EconomicCost;
	private int RequiredTime;

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public Date getStartDate() {
		return StartDate;
	}

	public void setStartDate(Date startDate) {
		StartDate = startDate;
	}

	public Date getEndDate() {
		return EndDate;
	}

	public void setEndDate(Date endDate) {
		EndDate = endDate;
	}

	public int getManagerID() {
		return ManagerID;
	}

	public void setManagerID(int managerID) {
		ManagerID = managerID;
	}

	public int getTimeCost() {
		return TimeCost;
	}

	public void setTimeCost(int timeCost) {
		TimeCost = timeCost;
	}

	public float getEconomicCost() {
		return EconomicCost;
	}

	public void setEconomicCost(float economicCost) {
		EconomicCost = economicCost;
	}

	public int getRequiredTime() {
		return RequiredTime;
	}

	public void setRequiredTime(int requiredTime) {
		RequiredTime = requiredTime;
	}

	public String getInsertUser() {
		return InsertUser;
	}

	public void setInsertUser(String insertUser) {
		InsertUser = insertUser;
	}

	public Date getInsertDate() {
		return InsertDate;
	}

	public void setInsertDate(Date insertDate) {
		InsertDate = insertDate;
	}

	public String getLastEditUser() {
		return LastEditUser;
	}

	public void setLastEditUser(String lastEditUser) {
		LastEditUser = lastEditUser;
	}

	public Date getLastEditDate() {
		return LastEditDate;
	}

	public void setLastEditDate(Date lastEditDate) {
		LastEditDate = lastEditDate;
	}

	private String InsertUser;
	private Date InsertDate;
	private String LastEditUser;
	private Date LastEditDate;

}
