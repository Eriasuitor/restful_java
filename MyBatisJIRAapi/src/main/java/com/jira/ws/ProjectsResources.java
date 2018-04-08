package com.jira.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.jira.bean.Project;
import com.jira.entity.GeneralResponse;
import com.jira.services.ProjectsService;
import com.sun.jersey.spi.resource.Singleton;

@Consumes({ "application/xml", "application/json" })
@Produces({ "application/xml", "application/json" })
@Path("projects")
@Singleton
public class ProjectsResources {

	@GET
	public Response queryProjectsName(@QueryParam("staffid") int staffid) {
		return Response.status(Response.Status.OK)
				.header("Access-Control-Allow-Origin", "*")
				.entity(new ProjectsService().queryProjectsBySatffId(staffid))
				.build();
	}

	@POST
	public Response newProject(Project project,
			@QueryParam("staffid") int staffid) {
		// return Response.status(Response.Status.OK)
		// .header("Access-Control-Allow-Origin", "*")
		// .entity(new ProjectsService().queryProjectsBySatffId(2))
		// .build();
		GeneralResponse resp = new GeneralResponse();
		try {
			new ProjectsService().newProject(project, staffid);
			resp.setInformation("项目创建成功");

		} catch (Exception e) {
			// TODO: handle exception
			resp.setInformation(e.getMessage());
		}
		return Response
				.status(Response.Status.OK)
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Headers",
						"Content-Type,Content-Length, Authorization, Accept,X-Requested-With")
				.header("Access-Control-Allow-Methods", "*").entity(resp)
				.build();
	}

	@OPTIONS
	public Response newProjectOptions() {
		return Response
				.status(Response.Status.OK)
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Headers",
						"Content-Type,Content-Length, Authorization, Accept,X-Requested-With")
				.header("Access-Control-Allow-Methods", "*").build();
	}
}