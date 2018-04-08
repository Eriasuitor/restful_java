package com.jira.dao;

import java.util.Map;

import com.jira.bean.Participant;

public interface IParticipants {
	public int addParticipants(Map<String, Object> map);

	public int addParticipant(Participant participant);
}
