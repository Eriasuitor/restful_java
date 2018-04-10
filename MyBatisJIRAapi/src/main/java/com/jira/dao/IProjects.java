package com.jira.dao;

import java.util.List;

import com.jira.bean.Project;
import com.jira.entity.ProjectList;

public interface IProjects {
	public ProjectList queryProjectsByStaffId(int id);

	public Project queryProjectById(int id);

	public List<Project> searchProjects(String pName);

	public int newProject(Project project);
}
