package com.jira.services;

import java.util.List;

import com.jira.bean.Participant;
import com.jira.bean.Project;
import com.jira.bean.Staff;
import com.jira.dao.ParticipantsDao;

public class ParticipantsService {
	public List<Participant> queryParticipants(int pId) {
		return new ParticipantsDao().queryParticipantsByPId((pId));
	}

	public int addParticipants(List<Participant> participantList, int userId) {
		return new ParticipantsDao().addParticipants(participantList, userId);
	}

	public Project addParticipant(Participant participant, int userId) throws Exception {
		if (participant.getProjectId() < 1 || participant.getStaffId() < 1) {
			throw new Exception("Invalidate Post");
		}
		Project project = new ProjectsService().queryProjectById(participant.getProjectId(), userId);
		if (project.getManagerID() != userId) {
			throw new Exception("你不是此项目的负责人，无法删除参与者。");
		}
		for (Staff staff : project.getStaffList()) {
			if (staff.getId() == participant.getStaffId()) {
				throw new Exception("该用户已是此项目的参与者，请勿重复添加。");
			}
		}
		participant.setRole(Participant.Role.Developer);
		participant.setLastEditUser(userId);
		participant.setInsertUser(userId);
		new ParticipantsDao().addParticipant(participant);
		return new ProjectsService().queryProjectById(participant.getProjectId(), userId);
	}

	public Project deleteParticipant(int pId, int sId, int userId) throws Exception {
		if (pId < 1 || sId < 1) {
			throw new Exception("Invalidate Post");
		}
		Project project = new ProjectsService().queryProjectById(pId, userId);
		if (project.getManagerID() != userId) {
			throw new Exception("你不是此项目的负责人，无法删除参与者");
		}
		if (new ParticipantsDao().deleteParticipant(pId, sId) != 1) {
			throw new Exception("该用户已不是此项目的参与者");
		}
		return new ProjectsService().queryProjectById(pId, userId);
	}
}
