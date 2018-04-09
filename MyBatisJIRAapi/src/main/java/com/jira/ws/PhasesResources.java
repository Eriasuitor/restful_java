package com.jira.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.jira.bean.Phase;
import com.jira.entity.GeneralResponse;
import com.jira.entity.PhaseList;
import com.jira.services.PhasesService;
import com.sun.jersey.spi.resource.Singleton;

@Consumes({ "application/xml", "application/json" })
@Produces({ "application/xml", "application/json" })
@Path("projects/{projectid}/phases")
@Singleton
public class PhasesResources {

	@GET
	public Response queryPhases(@PathParam("projectid") int projectId) {
		PhaseList resp = new PhaseList();
		try {
			resp.setPhaseList(new PhasesService().queryPhase(projectId));
		} catch (Exception e) {
			// TODO: handle exception
			resp.setSuccessful(false);
			resp.setInformation(e.getMessage());
		}
		return Response.status(Response.Status.OK)
				.header("Access-Control-Allow-Origin", "*").entity(resp)
				.build();
	}

	@POST
	public Response addPhase(Phase phase, @QueryParam("staffid") int userId,
			@PathParam("projectid") int projectId) {
		GeneralResponse resp = new GeneralResponse();
		try {
			new PhasesService().addPhase(phase, userId, projectId);
			resp.setInformation("创建成功");

		} catch (Exception e) {
			// TODO: handle exception
			resp.setSuccessful(false);
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
	public Response addPhaseOptions() {
		return Response
				.status(Response.Status.OK)
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Headers",
						"Content-Type,Content-Length, Authorization, Accept,X-Requested-With")
				.header("Access-Control-Allow-Methods", "*").build();
	}
}