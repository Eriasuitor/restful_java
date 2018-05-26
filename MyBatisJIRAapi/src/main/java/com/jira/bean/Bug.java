package com.jira.bean;

import java.util.Date;

public class Bug {
	public enum Status {
		Created, Processing, Resolved, Closed
	}

	private int id;
	private int projectID;
	private Project project;
	private int subtaskID;
	private Subtask subtask;
	private String name;
	private String description;
	private int importance;
	private Status status;
	private String origin;
	private String source;
	private String severity;
	private String priority;
	private int assignedID;
	private Staff assignedStaff;
	private String note;
	private int insertUser;
	private Date insertDate;
	private int lastEditUser;
	private Staff lasEditStaff;
	private Date lastEditDate;

	public Staff getAssignedStaff() {
		return assignedStaff;
	}

	public void setAssignedStaff(Staff assignedStaff) {
		this.assignedStaff = assignedStaff;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProjectID() {
		return projectID;
	}

	public void setProjectID(int projectID) {
		this.projectID = projectID;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public int getSubtaskID() {
		return subtaskID;
	}

	public void setSubtaskID(int subtaskID) {
		this.subtaskID = subtaskID;
	}

	public Subtask getSubtask() {
		return subtask;
	}

	public void setSubtask(Subtask subtask) {
		this.subtask = subtask;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getImportance() {
		return importance;
	}

	public void setImportance(int importance) {
		this.importance = importance;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public int getAssignedID() {
		return assignedID;
	}

	public void setAssignedID(int assignedID) {
		this.assignedID = assignedID;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
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

	public Staff getLasEditStaff() {
		return lasEditStaff;
	}

	public void setLasEditStaff(Staff lasEditStaff) {
		this.lasEditStaff = lasEditStaff;
	}

	public Date getLastEditDate() {
		return lastEditDate;
	}

	public void setLastEditDate(Date lastEditDate) {
		this.lastEditDate = lastEditDate;
	}

}
