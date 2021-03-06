package com.jira.dao;

import java.util.List;

import com.jira.bean.Log;

public interface ILogs {
	public int addLog(Log log);

	public int addLogWithAssigned(Log log);

	public List<Log> getLastLog(List<Integer> subIds);

	public List<Log> getLogsBySubId(int subId);

	public int deleteLog(int id);
}
