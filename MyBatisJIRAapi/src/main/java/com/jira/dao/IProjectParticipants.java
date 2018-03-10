package com.jira.dao;

import java.util.List;

import com.jira.bean.Participant;

public interface IProjectParticipants {
	public int addParticipants(List<Participant> participants);

	public int addParticipant(Participant participant);
}
