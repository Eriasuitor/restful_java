package com.jira.dao;

import java.util.List;

import com.jira.bean.Staff;

public interface IStaff {
	public Staff queryStaffInf(int id);

	public List<Staff> searchStaff(String name);
}
