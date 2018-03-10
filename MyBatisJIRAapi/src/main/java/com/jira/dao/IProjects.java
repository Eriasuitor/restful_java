package com.jira.dao;

import com.jira.bean.Project;
import com.jira.entity.ProjectList;

public interface IProjects {
	public ProjectList queryProjectsByStaffId(int id);

	public Project queryProjectById(int id);

	public int newProject(Project project);
}
