package com.jira.entity;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.jira.bean.Project;

@XmlRootElement
public class ProjectList extends GeneralResponse {
	private List<Project> projectNames;

	public List<Project> getProjectNames() {
		return projectNames;
	}

	public void setProjectNames(List<Project> projectNames) {
		this.projectNames = projectNames;
	}
}
