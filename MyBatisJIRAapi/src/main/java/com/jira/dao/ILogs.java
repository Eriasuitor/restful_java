package com.jira.dao;

import java.util.List;

import com.jira.bean.Log;

public interface ILogs {
	public int addLog(Log log);

	public List<Log> getLogsBySubId(int subId);
}
