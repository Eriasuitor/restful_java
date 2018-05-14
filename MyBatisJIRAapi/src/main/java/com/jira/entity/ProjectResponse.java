package com.jira.entity;

import javax.xml.bind.annotation.XmlRootElement;

import com.jira.bean.Project;

@XmlRootElement
public class ProjectResponse extends GeneralResponse {
	private Project project;

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

}
