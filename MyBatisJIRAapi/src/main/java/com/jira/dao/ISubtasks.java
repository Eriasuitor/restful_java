package com.jira.dao;

import java.util.List;

import com.jira.bean.Subtask;

public interface ISubtasks {
	public int addSubtask(Subtask subtask);

	public List<Subtask> querySubtasks(List<Integer> phaseIDList);

	public Subtask querySubtaskBySubId(int id);
}
