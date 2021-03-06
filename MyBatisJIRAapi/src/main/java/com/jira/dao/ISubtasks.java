package com.jira.dao;

import java.util.List;

import com.jira.bean.Subtask;

public interface ISubtasks {
	public int addSubtask(Subtask subtask);

	public List<Subtask> querySubtasksByIds(List<Integer> dis);

	public List<Subtask> querySubtasksByUId(int uId);

	public List<Subtask> querySubtasks(List<Integer> phaseIDList);

	public Subtask querySubtaskBySubId(int id);
}
