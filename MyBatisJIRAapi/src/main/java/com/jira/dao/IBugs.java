package com.jira.dao;

import java.util.List;

import com.jira.bean.Bug;

public interface IBugs {
	public int addBug(Bug bug);

	public List<Bug> getBugsByUId(int uId);

	public List<Bug> getBugsBySubId(int subId);

	// public List<Log> getLogsBySubId(int subId);
	//
	// public int deleteLog(int id);
}
