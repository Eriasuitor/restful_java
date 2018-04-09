package com.jira.services;

import java.util.List;

import com.jira.bean.Subtask;
import com.jira.dao.SubtasksDao;

public class SubtasksService {
	public int addSubtask(Subtask subtask) {
		return new SubtasksDao().addSubtask(subtask);
	}

	public List<Subtask> querySubtasks(List<Integer> phaseIDList) {
		return new SubtasksDao().querySubtasks(phaseIDList);
	}

	public Subtask querySubtasks(int subId) {
		return new SubtasksDao().querySubtaskBySubId(subId);
	}

}
