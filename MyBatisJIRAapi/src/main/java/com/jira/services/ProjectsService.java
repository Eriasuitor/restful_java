package com.jira.services;

import com.jira.bean.Project;
import com.jira.dao.ProjectsDao;
import com.jira.entity.ProjectList;

public class ProjectsService {
	public ProjectList queryProjectsBySatffId(int staffId) {
		return new ProjectsDao().queryByStaffId(staffId);
	}

	public Project queryProjectById(int id) {
		return new ProjectsDao().queryById(id);
	}

	public int newProject(Project project) {
		return new ProjectsDao().create(project);
	}
}
