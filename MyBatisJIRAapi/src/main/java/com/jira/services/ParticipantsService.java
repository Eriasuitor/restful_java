package com.jira.services;

import com.jira.bean.Participant;
import com.jira.dao.ParticipantsDao;
import com.jira.entity.ParticipantList;

public class ParticipantsService {
	public int addParticipants(ParticipantList participants) {
		return new ParticipantsDao().addParticipants(participants
				.getParticipants());
	}

	public int addParticipant(Participant participant) {
		return new ParticipantsDao().addParticipant(participant);
	}
}
