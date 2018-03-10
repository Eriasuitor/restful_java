package com.jira.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.jira.bean.Project;
import com.jira.entity.ProjectList;
import com.jira.services.ProjectsService;
import com.sun.jersey.spi.resource.Singleton;

@Consumes({ "application/xml", "application/json" })
@Produces({ "application/xml", "application/json" })
@Path("projects")
@Singleton
public class ProjectsResources {

	@GET
	public ProjectList queryProjectsName(@QueryParam("staffid") int staffid) {
		return new ProjectsService().queryProjectsBySatffId(staffid);
	}

	@POST
	public Project newProject(Project project) {
		new ProjectsService().newProject(project);
		return project;
	}
}