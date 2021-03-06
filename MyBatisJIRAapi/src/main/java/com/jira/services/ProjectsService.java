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

	public ProjectList queryAllProjectsBySatffId(int staffId) {
		return new ProjectsDao().queryAllProjectsByStaffId(staffId);
	}

	public Project queryProjectById(int id, int staffId) throws Exception {
		Project project = new ProjectsDao().queryById(id);
		if (project == null) {
			throw new Exception("此项目不存在");
		}
		List<Participant> participantList = new ParticipantsService().queryParticipants(id);
		Set<Integer> idSet = new HashSet<Integer>();
		for (Participant participant : participantList) {
			idSet.add(participant.getStaffId());
		}
		List<Staff> staffList = new StaffService().queryStaffInf(idSet);
		for (Staff staff : staffList) {
			if (staff.getId() == project.getManagerID()) {
				project.setStaff(staff);
				staffList.remove(staff);
				break;
			}
		}
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
		if (project.getName() == null || project.getName().trim() == "" || project.getManagerID() < 1
				|| project.getStartDate() == null || project.getEndDate() == null
				|| project.getEndDate().before(project.getStartDate()))
			throw new Exception("Invalidate Post");
		project.setInsertUser(userId);
		project.setLastEditUser(userId);
		new ProjectsDao().create(project);
		List<Participant> participantList = new ArrayList<Participant>();
		participantList.add(new Participant(project.getId(), project.getManagerID(), Participant.Role.Manager));
		for (Participant participant : project.getParticipantsList()) {
			participantList.add(new Participant(project.getId(), participant.getStaffId(), Participant.Role.Developer));
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
				return new ProjectsService().queryProjectById(project.getId(), userId);
			}
		}
		throw new Exception("你不是此项目的参与人之一，无权更改项目信息。");
	}

	public Project modifyManagerID(Project project, int userId) throws Exception {
		if (project.getId() == 0 || project.getManagerID() == 0) {
			throw new Exception("Invalid Request");
		}
		Project _project = new ProjectsDao().queryById(project.getId());
		if (_project == null) {
			throw new Exception("此项目已不存在");
		}
		if (_project.getManagerID() != userId) {
			throw new Exception("你不是此项目负责人，无法更换负责人。");
		}
		if (_project.getManagerID() == project.getManagerID()) {
			throw new Exception("该用户已是此项目负责人。");
		}
		project.setLastEditUser(userId);
		new ProjectsDao().modifyManagerID(project);
		return new ProjectsService().queryProjectById(project.getId(), userId);
	}

	public Project modifyStatus(Project project, int userId) throws Exception {
		if (project.getId() < 0 || project.getStatus() == null) {
			throw new Exception("Invalid Request");
		}
		Project _project = new ProjectsDao().queryById(project.getId());
		if (_project == null) {
			throw new Exception("此项目已不存在");
		}
		if (_project.getManagerID() != userId) {
			throw new Exception("你不是此项目负责人，无法更改此项目状态。");
		}
		if (_project.getStatus() != project.getStatus()) {
			throw new Exception("该项目状态已发生改变，请刷新后重试。");
		}
		switch (_project.getStatus()) {
		case Created:
			project.setStatus(Project.Status.Processing);
			break;
		case Processing:
			project.setStatus(Project.Status.Closed);
			break;
		case Closed:
			project.setStatus(Project.Status.Processing);
			break;
		default:
			break;
		}
		project.setLastEditUser(userId);
		new ProjectsDao().modifyStatus(project.getId(), userId, project.getStatus());
		return new ProjectsService().queryProjectById(project.getId(), userId);
	}

	public int deleteProject(int id, int userId) throws Exception {
		if (new ProjectsDao().queryById(id).getManagerID() == userId) {
			return new ProjectsDao().deleteProject(id);
		}
		throw new Exception("你不是当前项目负责人，无法删除此项目。");
	}
}
