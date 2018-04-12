package com.jira.services;

import java.util.List;

import com.jira.bean.Participant;
import com.jira.bean.Staff;
import com.jira.dao.ParticipantsDao;

public class ParticipantsService {
	public List<Participant> queryParticipants(int pId) {
		return new ParticipantsDao().queryParticipantsByPId((pId));
	}

	public int addParticipants(List<Participant> participantList, int userId) {
		return new ParticipantsDao().addParticipants(participantList, userId);
	}

	public List<Staff> addParticipant(Participant participant) {
		new ParticipantsDao().addParticipant(participant);
		return new StaffService().queryStaffInfsByProjectID(participant
				.getProjectId());
	}

	public int deleteParticipant(int pId, int sId) {
		return new ParticipantsDao().deleteParticipant(pId, sId);
	}
}
