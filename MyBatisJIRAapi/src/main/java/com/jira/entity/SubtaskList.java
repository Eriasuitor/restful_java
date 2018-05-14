package com.jira.entity;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.jira.bean.Subtask;

@XmlRootElement
public class SubtaskList {
	private List<Subtask> subtaskList;

	public List<Subtask> getSubtaskList() {
		return subtaskList;
	}

	public void setSubtaskList(List<Subtask> subtaskList) {
		this.subtaskList = subtaskList;
	}

}
