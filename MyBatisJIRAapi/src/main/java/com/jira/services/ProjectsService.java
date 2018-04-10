package com.jira.services;

import java.util.ArrayList;
import java.util.List;

import com.jira.bean.Participant;
import com.jira.bean.Project;
import com.jira.dao.ParticipantsDao;
import com.jira.dao.ProjectsDao;
import com.jira.entity.ProjectList;

public class ProjectsService {
	public ProjectList queryProjectsBySatffId(int staffId) {
		return new ProjectsDao().queryByStaffId(staffId);
	}

	public Project queryProjectById(int id) {
		Project project = new ProjectsDao().queryById(id);
		project.setStaff(new StaffService().queryStaffInf(project
				.getManagerID()));
		return project;
	}

	public ProjectList searchProject(String pName) {
		ProjectList projectList = new ProjectList();
		projectList.setProjectNames(new ProjectsDao().searchProjects(pName));
		return projectList;
	}

	public int newProject(Project project, int userId) {
		project.setStatus("Proccessing");
		project.setInsertUser("MyEclipse");
		project.setLastEditUser("MyEclipse");
		project.setParticipants(project.getParticipantsList().size());
		new ProjectsDao().create(project);
		List<Participant> participantList = new ArrayList<Participant>();
		participantList.add(new Participant(project.getId(), project
				.getManagerID(), "Manager"));
		for (Participant participant : project.getParticipantsList()) {
			participantList.add(new Participant(project.getId(), participant
					.getStaffId(), "Developer"));
		}
		new ParticipantsDao().addParticipants(participantList, userId);
		return project.getId();
	}
}
