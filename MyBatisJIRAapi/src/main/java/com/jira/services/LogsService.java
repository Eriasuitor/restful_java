package com.jira.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.jira.bean.Log;
import com.jira.bean.Staff;
import com.jira.bean.Subtask;
import com.jira.dao.LogsDao;
import com.jira.dao.StaffDao;
import com.jira.dao.SubtasksDao;

public class LogsService {
	public List<Log> getLogsBySubId(int subId, int userId) throws Exception {
		if (subId < 1) {
			throw new Exception("Invalid Request");
		}
		List<Staff> _staffList = new StaffDao().queryStaffInfsBySubId(subId);
		for (Staff _staff : _staffList) {
			if (_staff.getId() == userId) {
				List<Log> logList = new LogsDao().getLogsBySubId(subId);
				Set<Integer> idSet = new HashSet<Integer>();
				for (Log log : logList) {
					idSet.add(log.getInsertUser());
				}
				List<Staff> staffList = new StaffService().queryStaffInf(idSet);
				for (Log log : logList) {
					for (Staff staff : staffList) {
						if (staff.getId() == log.getInsertUser()) {
							log.setStaff(staff);
							break;
						}
					}
				}
				return logList;
			}
		}
		throw new Exception("你不是此项目的参与者，无法查看Log信息");
	}

	public List<Log> getLastLog(List<Integer> subIds) {
		List<Log> logList = new LogsDao().getLastLog(subIds);
		Set<Integer> idSet = new HashSet<Integer>();
		for (Log log : logList) {
			idSet.add(log.getInsertUser());
		}
		List<Staff> staffList = new StaffService().queryStaffInf(idSet);
		for (Log log : logList) {
			for (Staff staff : staffList) {
				if (staff.getId() == log.getInsertUser()) {
					log.setStaff(staff);
					break;
				}
			}
		}
		return logList;
	}

	public int addLog(Log log, int userId) throws Exception {
		System.out.println(11111);
		if (log.getSubtaskID() < 1) {
			throw new Exception("Invalid Request");
		}
		List<Staff> _staffList = new StaffDao().queryStaffInfsBySubId(log.getSubtaskID());
		for (Staff _staff : _staffList) {
			if (_staff.getId() == userId) {
				System.out.println(log.getCompleted());
				System.out.println(log.getStartDate());
				System.out.println(log.getTimeCost());
				System.out.println(log.getEndDate());
				System.out.println(log.getStartDate().after(log.getEndDate()));
				if (log.getCompleted() < 0 || log.getStartDate() == null || log.getTimeCost() < 0
						|| log.getEndDate() == null || log.getStartDate().after(log.getEndDate())) {
					throw new Exception("Invalid Request");
				}
				if (new SubtasksDao().querySubtaskBySubId(log.getSubtaskID())
						.getStatus() != Subtask.Status.Processing) {
					throw new Exception("当前子任务尚未进入开发阶段，请联系项该目负责人开始此项目。");
				}
				Subtask subtask = new SubtasksDao().querySubtaskBySubId(log.getSubtaskID());
				if (subtask.getCompleted() + log.getCompleted() > 100) {
					throw new Exception("Invalid Completed Request");
				}
				log.setInsertUser(userId);
				log.setLastEditUser(userId);
				if (log.getAssignedID() < 1) {
					return new LogsDao().addLog(log);
				} else {
					return new LogsDao().addLogWithAssigned(log);
				}
			}
		}
		throw new Exception("你不是此项目的参与者，无法Log。");
	}

	public int deleteLog(int id) {
		return new LogsDao().deleteLog(id);
	}
}
