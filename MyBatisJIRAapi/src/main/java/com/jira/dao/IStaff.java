package com.jira.dao;

import java.util.List;

import com.jira.bean.Staff;

public interface IStaff {
	public Staff queryStaffInf(int id);

	public List<Staff> queryStaffInfs(List<Integer> idSet);

	public List<Staff> queryStaffInfsByProjectID(int pId);

	public List<Staff> queryStaffInfsByPhaseId(int phaId);

	public List<Staff> queryStaffInfsBySubId(int subId);

	public List<Staff> searchStaff(String name);
}
