package com.jira.bean;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Participant {
	public enum Role {
		Developer, Manager
	}

	private int id;
	private int projectId;
	private int staffId;
	private Role role;
	private int insertUser;
	private Date insertDate;
	private int lastEditUser;
	private Date lastEditDate;

	public Participant() {

	}

	public Participant(int projectId, int staffId, Role role) {
		this.projectId = projectId;
		this.staffId = staffId;
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public int getStaffId() {
		return staffId;
	}

	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public int getInsertUser() {
		return insertUser;
	}

	public void setInsertUser(int insertUser) {
		this.insertUser = insertUser;
	}

	public void setLastEditUser(int lastEditUser) {
		this.lastEditUser = lastEditUser;
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

	public Date getLastEditDate() {
		return lastEditDate;
	}

	public void setLastEditDate(Date lastEditDate) {
		this.lastEditDate = lastEditDate;
	}

}
