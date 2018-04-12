package com.jira.dao;

import java.util.List;
import java.util.Map;

import com.jira.bean.Participant;

public interface IParticipants {
	public List<Participant> queryParticipantsByPId(int pId);

	public int addParticipants(Map<String, Object> map);

	public int addParticipant(Participant participant);

	public int deleteParticipant(Map<String, Integer> map);
}
