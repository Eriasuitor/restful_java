package com.jira.dao;

import java.util.List;
import java.util.Map;

import com.jira.bean.Bug;

public interface IBugs {
	public int addBug(Bug bug);

	public Bug getBugsById(int bugId);

	public List<Bug> getBugsByUId(int uId);

	public List<Bug> getBugsBySubId(int subId);

	public int modifyStatus(Map<String, Object> map);

	public int resolveBug(Map<String, Object> map);

	// public List<Log> getLogsBySubId(int subId);
	//
	// public int deleteLog(int id);
}
