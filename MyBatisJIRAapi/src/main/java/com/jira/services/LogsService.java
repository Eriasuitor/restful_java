package com.jira.services;

import java.util.List;

import com.jira.bean.Log;
import com.jira.dao.LogsDao;

public class LogsService {
	public List<Log> getLogsBySubId(int subId) {
		return new LogsDao().getLogsBySubId(subId);
	}

	public int addLog(Log log) {
		return new LogsDao().addLog(log);
	}
}
