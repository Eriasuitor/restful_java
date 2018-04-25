package com.jira.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.jira.bean.Project;
import com.jira.entity.GeneralResponse;
import com.jira.entity.ProjectResponse;
import com.jira.services.ProjectsService;
import com.sun.jersey.spi.resource.Singleton;

@Consumes({ "application/xml", "application/json" })
@Produces({ "application/xml", "application/json" })
@Path("projects")
@Singleton
public class ProjectsResources {

	@GET
	public Response queryProjectsByUserId(@QueryParam("staffid") int staffid,
			@QueryParam("q") String pName) {
		if (pName == null) {
			return Response
					.status(Response.Status.OK)
					.header("Access-Control-Allow-Origin", "*")
					.entity(new ProjectsService()
							.queryProjectsBySatffId(staffid)).build();
		}
		return Response.status(Response.Status.OK)
				.header("Access-Control-Allow-Origin", "*")
				.entity(new ProjectsService().searchProject(pName)).build();
	}

	@GET
	@Path("{id}")
	public Response queryProjectsById(@PathParam("id") int pId) {
		return Response.status(Response.Status.OK)
				.header("Access-Control-Allow-Origin", "*")
				.entity(new ProjectsService().queryProjectById(pId)).build();
	}

	@POST
	public Response newProject(Project project,
			@QueryParam("staffid") int staffid) {
		GeneralResponse resp = new GeneralResponse();
		try {
			new ProjectsService().newProject(project, staffid);
			resp.setInformation("项目创建成功");

		} catch (Exception e) {
			// TODO: handle exception
			resp.setInformation(e.getMessage());
			resp.setSuccessful(false);
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

	@PUT
	@Path("{id}")
	public Response modifyProject(Project project) {
		GeneralResponse resp = new GeneralResponse();
		try {
			new ProjectsService().modifyProject(project);
			resp.setInformation("修改成功");
		} catch (Exception e) {
			// TODO: handle exception
			resp.setInformation(e.getMessage());
			resp.setSuccessful(false);
		}
		return Response
				.status(Response.Status.OK)
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Headers",
						"Content-Type,Content-Length, Authorization, Accept,X-Requested-With")
				.header("Access-Control-Allow-Methods",
						"GET,PUT,DELETE,OPTIONS").entity(resp).build();
	}

	@PUT
	@Path("{id}/manager")
	public Response modifyManagerID(Project project) {
		ProjectResponse resp = new ProjectResponse();
		try {
			resp.setProject(new ProjectsService().modifyManagerID(project));
			resp.setInformation("修改成功");
		} catch (Exception e) {
			// TODO: handle exception
			resp.setInformation(e.getMessage());
			resp.setSuccessful(false);
		}
		return Response
				.status(Response.Status.OK)
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Headers",
						"Content-Type,Content-Length, Authorization, Accept,X-Requested-With")
				.header("Access-Control-Allow-Methods",
						"GET,PUT,DELETE,OPTIONS").entity(resp).build();
	}

	@OPTIONS
	@Path("{id}/manager")
	public Response modifyProjectOptionsOptions() {
		return Response
				.status(Response.Status.OK)
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Headers",
						"Content-Type,Content-Length, Authorization, Accept,X-Requested-With")
				.header("Access-Control-Allow-Methods",
						"GET,PUT,DELETE,OPTIONS").build();
	}

	@OPTIONS
	@Path("{id}")
	public Response modifyProjectOptions() {
		return Response
				.status(Response.Status.OK)
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Headers",
						"Content-Type,Content-Length, Authorization, Accept,X-Requested-With")
				.header("Access-Control-Allow-Methods",
						"GET,PUT,DELETE,OPTIONS").build();
	}

	@DELETE
	@Path("{id}")
	public Response modifyProject(@PathParam("id") int id) {
		GeneralResponse resp = new GeneralResponse();
		try {
			resp.setEffectRows(new ProjectsService().deleteProject(id));
			resp.setInformation("删除成功");
		} catch (Exception e) {
			// TODO: handle exception
			resp.setInformation(e.getMessage());
			resp.setSuccessful(false);
		}
		return Response
				.status(Response.Status.OK)
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Headers",
						"Content-Type,Content-Length, Authorization, Accept,X-Requested-With")
				.header("Access-Control-Allow-Methods",
						"GET,PUT,DELETE,OPTIONS").entity(resp).build();
	}
}