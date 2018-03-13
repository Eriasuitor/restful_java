package com.jira.services;

import com.jira.bean.Subtask;
import com.jira.dao.SubtasksDao;

public class SubtasksService {
	public int addSubtask(Subtask subtask) {
		return new SubtasksDao().addSubtask(subtask);
	}
}
