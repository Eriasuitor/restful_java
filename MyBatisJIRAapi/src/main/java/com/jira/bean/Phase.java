package com.jira.bean;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Phase {
	private int id;
	private String name;
	private int projectID;
	private int phaseNum;
	private String status;
	private String description;
	private Date startDate;
	private Date endDate;
	private int managerID;
	private List<Subtask> subtaskList;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getProjectID() {
		return projectID;
	}

	public void setProjectID(int projectID) {
		this.projectID = projectID;
	}

	public int getPhaseNum() {
		return phaseNum;
	}

	public void setPhaseNum(int phaseNum) {
		this.phaseNum = phaseNum;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public int getManagerID() {
		return managerID;
	}

	public void setManagerID(int managerID) {
		this.managerID = managerID;
	}

	public List<Subtask> getSubtaskList() {
		return subtaskList;
	}

	public void setSubtaskList(List<Subtask> subtaskList) {
		this.subtaskList = subtaskList;
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
