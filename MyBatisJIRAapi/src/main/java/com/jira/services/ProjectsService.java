package com.jira.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.jira.bean.Participant;
import com.jira.bean.Project;
import com.jira.bean.Staff;
import com.jira.dao.ParticipantsDao;
import com.jira.dao.ProjectsDao;
import com.jira.entity.ProjectList;

public class ProjectsService {
	public ProjectList queryProjectsBySatffId(int staffId) {
		return new ProjectsDao().queryByStaffId(staffId);
	}

	public Project queryProjectById(int id) {

		List<Participant> participantList = new ParticipantsService()
				.queryParticipants(id);
		Set<Integer> idSet = new HashSet<Integer>();
		for (Participant participant : participantList) {
			idSet.add(participant.getStaffId());
		}
		List<Staff> staffList = new StaffService().queryStaffInf(idSet);

		Project project = new ProjectsDao().queryById(id);
		project.setStaff(new StaffService().queryStaffInf(project
				.getManagerID()));
		project.setStaffList(staffList);
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

	public void modifyProject(Project project) {
		new ProjectsDao().modifyProject(project);
	}

	public Project modifyManagerID(Project project) {
		new ProjectsDao().modifyManagerID(project);
		project.setStaff(new StaffService().queryStaffInf(project
				.getManagerID()));
		return project;
	}

	public int deleteProject(int id) {
		return new ProjectsDao().deleteProject(id);
	}
}
