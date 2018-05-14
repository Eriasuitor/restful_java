package com.jira.services;

import java.util.List;
import java.util.Set;

import com.jira.bean.Staff;
import com.jira.dao.StaffDao;
import com.jira.entity.StaffResults;

public class StaffService {
	public Staff queryStaffInf(int id) {
		return new StaffDao().query(id);
	}

	public List<Staff> queryStaffInf(Set<Integer> idSet) {
		if (idSet.size() == 0)
			return null;
		return new StaffDao().query(idSet);
	}

	public List<Staff> queryStaffInfsByProjectID(int pId) {
		return new StaffDao().queryStaffInfsByProjectID(pId);
	}

	public StaffResults searchStaff(String name) {
		List<Staff> staffList = new StaffDao().searchStaff(name);
		if (staffList == null)
			return null;
		StaffResults staffResults = new StaffResults();
		for (Staff s : staffList) {
			staffResults.add(s);
		}
		return staffResults;
	}
}
