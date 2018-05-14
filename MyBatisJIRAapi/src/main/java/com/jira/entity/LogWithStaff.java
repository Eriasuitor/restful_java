package com.jira.entity;

import javax.xml.bind.annotation.XmlRootElement;

import com.jira.bean.Log;
import com.jira.bean.Staff;

@XmlRootElement
public class LogWithStaff extends Log {
	private Staff managerStaff;
	private Staff assignedStaff;

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
