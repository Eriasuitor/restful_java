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

	public int addBug(Bug bug, int userId) throws Exception {
		if (bug.getName() == null || bug.getName().trim() == "" || bug.getProjectID() < 0 || bug.getSubtaskID() < 0
				|| bug.getOrigin() == null || bug.getOrigin().trim() == "" || bug.getSource() == null
				|| bug.getSource().trim() == "" || bug.getSeverity() == null || bug.getSeverity().trim() == ""
				|| bug.getPriority() == null || bug.getPriority().trim() == "") {
			throw new Exception("Invalid Request");
		}
		Subtask sub = new SubtasksDao().querySubtaskBySubId(bug.getSubtaskID());
		if (sub == null) {
			throw new Exception("此子任务不存在");
		}
		if (sub.getStatus() == Subtask.Status.Created) {
			throw new Exception("此子任务尚未开始开发，所以无法提交相关Bug，请联系项目负责人开始项目。");
		}
		if (sub.getStatus() == Subtask.Status.Closed) {
			throw new Exception("此子任务已关闭，无法提交Bug");
		}
		bug.setAssignedID(sub.getManagerID());
		bug.setLastEditUser(userId);
		bug.setInsertUser(userId);
		return new BugsDao().addBug(bug);
	}

	public List<Bug> getBugsByUId(int uId) {
		List<Bug> bugs = new BugsDao().getBugsByUId(uId);
		if (bugs.size() == 0) {
			return new ArrayList<Bug>();
		}
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

	public List<Bug> getBugsBySubId(int subId, int userId) throws Exception {
		if (subId < 0) {
			throw new Exception("Invalid Request");
		}
		List<Staff> staffList = new StaffDao().queryStaffInfsBySubId(subId);
		for (Staff _staff : staffList) {
			if (_staff.getId() == userId) {
				List<Bug> bugs = new BugsDao().getBugsBySubId(subId);
				if (bugs.size() == 0) {
					return new ArrayList<Bug>();
				}
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
		}
		throw new Exception("你不是此项目的参与者，无法查看Bug信息。");
	}

	public Bug modifyStatus(Bug bug, int userId) throws Exception {
		Bug _bug = new BugsDao().getBugsById(bug.getId());
		if (_bug == null)
			throw new Exception("此Bug不存在。");
		if (_bug.getStatus() != bug.getStatus()) {
			throw new Exception("此Bug状态已被改变，请刷新后重试。");
		}
		if (bug.getAssignedID() != 0) {
			if (_bug.getStatus() == Bug.Status.Created) {
				throw new Exception("当前Bug尚未打开，请打开后再进行Resolve操作。");
			}
			if (_bug.getStatus() == Bug.Status.Resolved) {
				throw new Exception("当前Bug已被解决。");
			}
			bug.setStatus(Bug.Status.Resolved);
			new BugsDao().resolveBug(bug.getId(), bug.getStatus(), bug.getNote(), userId, bug.getAssignedID());
			Bug bugret = new BugsDao().getBugsById(bug.getId());
			bugret.setLasEditStaff(new StaffDao().query(bugret.getLastEditUser()));
			return bugret;
		}
		Bug.Status statusNext = null;
		switch (bug.getStatus()) {
		case Created:
			statusNext = Bug.Status.Processing;
			bug.setNote("打开了此Bug");
			break;
		case Recreated:
			statusNext = Bug.Status.Processing;
			bug.setNote("打开了此Bug");
			break;
		case Processing:
			statusNext = Bug.Status.Resolved;
			break;
		case Resolved:
			if (_bug.getInsertUser() != userId)
				throw new Exception("只有Bug提交者才能重新开始创建此Bug。");
			statusNext = Bug.Status.Recreated;
			break;
		default:
			break;
		}
		new BugsDao().modifyStatus(bug.getId(), statusNext, bug.getNote(), userId);
		Bug bugret = new BugsDao().getBugsById(bug.getId());
		bugret.setLasEditStaff(new StaffDao().query(bugret.getLastEditUser()));
		return bugret;
	}
	// public int deleteLog(int id) {
	// return new LogsDao().deleteLog(id);
	// }
}
