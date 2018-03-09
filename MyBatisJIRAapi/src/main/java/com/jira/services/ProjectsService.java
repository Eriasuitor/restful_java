package com.jira.services;

import com.jira.dao.ProjectsDao;
import com.jira.entity.ProjectList;

public class ProjectsService {
	public ProjectList queryProjectsName(int id) {
		return new ProjectsDao().query(id);
	}
}
