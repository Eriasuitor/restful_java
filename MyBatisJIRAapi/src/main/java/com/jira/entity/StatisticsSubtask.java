package com.jira.entity;

import javax.xml.bind.annotation.XmlRootElement;

import com.jira.bean.Log;
import com.jira.bean.Project;
import com.jira.bean.Subtask;

@XmlRootElement
public class StatisticsSubtask extends Subtask {
	private Log log;
	private Project project;

	public StatisticsSubtask() {
	}

	public StatisticsSubtask(Subtask subtask, Log log, Project project) {
		this.setAssignedID(subtask.getAssignedID());
		this.setCompleted(subtask.getCompleted());
		this.setDescription(subtask.getDescription());
		this.setId(subtask.getId());
		this.setInsertDate(subtask.getInsertDate());
		this.setInsertUser(subtask.getInsertUser());
		this.setLastEditDate(subtask.getLastEditDate());
		this.setLastEditUser(subtask.getLastEditUser());
		this.setManagerID(subtask.getManagerID());
		this.setName(subtask.getName());
		this.setPhaseID(subtask.getAssignedID());
		this.setRequiredTime(subtask.getRequiredTime());
		this.setStartDate(subtask.getStartDate());
		this.setEndDate(subtask.getEndDate());
		this.setStatus(subtask.getStatus());
		this.setTimeCost(subtask.getTimeCost());
		this.log = log;
		this.project = project;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Log getLog() {
		return log;
	}

	public void setLog(Log log) {
		this.log = log;
	}
}
