package com.jira.entity;

import javax.xml.bind.annotation.XmlRootElement;

import com.jira.bean.Log;
import com.jira.bean.Subtask;

@XmlRootElement
public class StatisticsSubtask extends Subtask {
	private Log log;

	public StatisticsSubtask() {
	}

	public StatisticsSubtask(Subtask subtask, Log log) {
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
		this.setStatus(subtask.getStatus());
		this.setTimeCost(subtask.getTimeCost());
		this.log = log;
	}

	public Log getLog() {
		return log;
	}

	public void setLog(Log log) {
		this.log = log;
	}
}
