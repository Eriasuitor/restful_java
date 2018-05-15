package com.jira.services;

import com.jira.bean.Bug;
import com.jira.dao.BugsDao;

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

	// public int deleteLog(int id) {
	// return new LogsDao().deleteLog(id);
	// }
}
