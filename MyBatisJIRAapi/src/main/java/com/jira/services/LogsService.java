package com.jira.services;

import com.jira.bean.Log;
import com.jira.dao.LogsDao;

public class LogsService {
	public int addLog(Log log) {
		return new LogsDao().addLog(log);
	}
}
