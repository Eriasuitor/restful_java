package com.jira.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.jira.bean.Log;
import com.jira.bean.Staff;
import com.jira.dao.LogsDao;

public class LogsService {
	public List<Log> getLogsBySubId(int subId) {
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

	public int addLog(Log log) {
		return new LogsDao().addLog(log);
	}

	public int deleteLog(int id) {
		return new LogsDao().deleteLog(id);
	}
}
