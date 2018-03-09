package com.jira.ws;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.jira.entity.ProjectList;
import com.jira.services.ProjectsService;
import com.sun.jersey.spi.resource.Singleton;

@Produces({ "application/xml", "application/json" })
@Path("projects")
@Singleton
public class ProjectsResources {

	@GET
	@Path("{id}")
	public ProjectList QueryProjectsName(@PathParam("id") int sId) {
		return new ProjectsService().queryProjectsName(sId);
	}
}