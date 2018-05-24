package com.jira.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.jira.bean.Bug;
import com.jira.bean.Project;
import com.jira.bean.Staff;
import com.jira.bean.Subtask;
import com.jira.dao.BugsDao;
import com.jira.dao.ProjectsDao;
import com.jira.dao.StaffDao;
import com.jira.dao.SubtasksDao;

public class BugsService {
	// public List<Log> getLogsBySubId(int subId) {
	// List<Log> logList = new LogsDao().getLogsBySubId(subId);
	// Set<Integer> idSet = new HashSet<Integer>();
	// for (Log log : logList) {
	// idSet.add(log.getInsertUser());
	// }
	// List<Staff> staffList = new StaffService().queryStaffInf(idSet);
	// for (Log log : logList) {
	// for (Staff staff : staffList) {
	// if (staff.getId() == log.getInsertUser()) {
	// log.setStaff(staff);
	// break;
	// }
	// }
	// }
	// return logList;
	// }

	public int addBug(Bug bug) {
		return new BugsDao().addBug(bug);
	}

	public List<Bug> getBugsByUId(int uId) {
		List<Bug> bugs = new BugsDao().getBugsByUId(uId);
		List<Integer> projectIds = new ArrayList<Integer>();
		List<Integer> subtaskIds = new ArrayList<Integer>();
		Set<Integer> assignedIds = new HashSet<Integer>();
		Set<Integer> staffIds = new HashSet<Integer>();
		for (Bug bug : bugs) {
			projectIds.add(bug.getProjectID());
			subtaskIds.add(bug.getSubtaskID());
			staffIds.add(bug.getLastEditUser());
			assignedIds.add(bug.getAssignedID());
		}
		List<Project> projects = new ProjectsDao().queryProjects(projectIds);
		List<Subtask> subtasks = new SubtasksDao().querySubtasksByIds(subtaskIds);
		List<Staff> assignedStaff = new StaffDao().query(assignedIds);
		List<Staff> staff = new StaffDao().query(staffIds);
		for (Bug bug : bugs) {
			for (Project _ : projects) {
				if (_.getId() == bug.getProjectID()) {
					bug.setProject(_);
					break;
				}
			}
			for (Subtask _ : subtasks) {
				if (_.getId() == bug.getSubtaskID()) {
					bug.setSubtask(_);
					break;
				}
			}
			for (Staff _ : assignedStaff) {
				if (_.getId() == bug.getAssignedID()) {
					bug.setAssignedStaff(_);
					break;
				}
			}
			for (Staff _ : staff) {
				if (_.getId() == bug.getLastEditUser()) {
					bug.setLasEditStaff(_);
					break;
				}
			}
		}
		return bugs;
	}

	public List<Bug> getBugsBySubId(int subId) {
		return new BugsDao().getBugsBySubId(subId);
	}
	// public int deleteLog(int id) {
	// return new LogsDao().deleteLog(id);
	// }
}
