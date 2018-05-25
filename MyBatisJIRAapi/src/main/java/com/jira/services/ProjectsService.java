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

		List<Participant> participantList = new ParticipantsService().queryParticipants(id);
		Set<Integer> idSet = new HashSet<Integer>();
		for (Participant participant : participantList) {
			idSet.add(participant.getStaffId());
		}
		List<Staff> staffList = new StaffService().queryStaffInf(idSet);

		Project project = new ProjectsDao().queryById(id);
		project.setStaff(new StaffService().queryStaffInf(project.getManagerID()));
		project.setStaffList(staffList);
		return project;
	}

	public ProjectList searchProject(String pName, int userId) {
		ProjectList projectList = new ProjectList();
		projectList.setProjectNames(new ProjectsDao().searchProjects(pName, userId));
		return projectList;
	}

	public int newProject(Project project, int userId) throws Exception {
		// private Staff staff;
		if (project.getName() == null || project.getName().trim() == "" || project.getManagerID() == 0
				|| project.getStartDate() == null || project.getEndDate() == null
				|| project.getEndDate().before(project.getStartDate()))
			throw new Exception("Invalidate Post");
		project.setInsertUser(userId);
		project.setLastEditUser(userId);
		new ProjectsDao().create(project);
		List<Participant> participantList = new ArrayList<Participant>();
		participantList.add(new Participant(project.getId(), project.getManagerID(), "Manager"));
		for (Participant participant : project.getParticipantsList()) {
			participantList.add(new Participant(project.getId(), participant.getStaffId(), "Developer"));
		}
		new ParticipantsDao().addParticipants(participantList, userId);
		return project.getId();
	}

	public Project modifyProject(Project project, int userId) throws Exception {
		if (project.getName() == null || project.getName().trim() == "" || project.getStartDate() == null
				|| project.getEndDate() == null || project.getEndDate().before(project.getStartDate())) {
			throw new Exception("Invalidate Post");
		}
		List<Staff> staffList = new StaffService().queryStaffInfsByProjectID(project.getId());
		for (Staff staff : staffList) {
			if (staff.getId() == userId) {
				project.setLastEditUser(userId);
				new ProjectsDao().modifyProject(project);
				return new ProjectsService().queryProjectById(project.getId());
			}
		}
		throw new Exception("你不是此项目的参与人之一，无权更改项目信息。");
	}

	public Project modifyManagerID(Project project) {
		new ProjectsDao().modifyManagerID(project);
		project.setStaff(new StaffService().queryStaffInf(project.getManagerID()));
		return project;
	}

	public int deleteProject(int id) {
		return new ProjectsDao().deleteProject(id);
	}
}
