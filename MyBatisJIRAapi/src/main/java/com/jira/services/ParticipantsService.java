package com.jira.services;

import java.util.List;

import com.jira.bean.Participant;
import com.jira.dao.ParticipantsDao;

public class ParticipantsService {
	public int addParticipants(List<Participant> participantList, int userId) {
		return new ParticipantsDao().addParticipants(participantList, userId);
	}

	public int addParticipant(Participant participant) {
		return new ParticipantsDao().addParticipant(participant);
	}
}
