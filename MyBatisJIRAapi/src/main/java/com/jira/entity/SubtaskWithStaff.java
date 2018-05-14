package com.jira.entity;

import javax.xml.bind.annotation.XmlRootElement;

import com.jira.bean.Staff;
import com.jira.bean.Subtask;

@XmlRootElement
public class SubtaskWithStaff extends Subtask {
	private Staff managerStaff;
	private Staff assignedStaff;

	public SubtaskWithStaff() {

	}

	public SubtaskWithStaff(Subtask subtask) {
		this.setAssignedID(subtask.getAssignedID());
		this.setCompleted(subtask.getCompleted());
		this.setDescription(subtask.getDescription());
		this.setEndDate(subtask.getEndDate());
		this.setId(subtask.getId());
		this.setInsertDate(subtask.getInsertDate());
		this.setInsertUser(subtask.getInsertUser());
		this.setLastEditDate(subtask.getLastEditDate());
		this.setLastEditUser(subtask.getLastEditUser());
		this.setManagerID(subtask.getManagerID());
		this.setName(subtask.getName());
		this.setPhaseID(subtask.getPhaseID());
		this.setRequiredTime(subtask.getRequiredTime());
		this.setStartDate(subtask.getStartDate());
		this.setStatus(subtask.getStatus());
		this.setTimeCost(subtask.getTimeCost());
	}

	public Staff getManagerStaff() {
		return managerStaff;
	}

	public void setManagerStaff(Staff managerStaff) {
		this.managerStaff = managerStaff;
	}

	public Staff getAssignedStaff() {
		return assignedStaff;
	}

	public void setAssignedStaff(Staff assignedStaff) {
		this.assignedStaff = assignedStaff;
	}

}
