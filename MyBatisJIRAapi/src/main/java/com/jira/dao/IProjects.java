package com.jira.dao;

import java.util.List;
import java.util.Map;

import com.jira.bean.Project;
import com.jira.entity.ProjectList;

public interface IProjects {
	public ProjectList queryProjectsByStaffId(int id);

	public ProjectList queryAllProjectsByStaffId(int id);

	public Project queryProjectByPhaseId(int phaseId);

	public Project queryProjectById(int id);

	public List<Project> queryProjects(List<Integer> ids);

	public List<Project> searchProjects(Map<String, Object> map);

	public int newProject(Project project);

	public int modifyProject(Project project);

	public int modifyManagerID(Project project);

	public int modifyStatus(Map<String, Object> map);

	public int deleteProject(int id);
}
