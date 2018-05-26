package com.jira.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.jira.entity.GeneralResponse;
import com.jira.services.BugsService;
import com.jira.services.LoginService;
import com.jira.services.SubtasksService;
import com.sun.jersey.spi.resource.Singleton;

@Consumes({ "application/json" })
@Produces({ "application/json" })
@Path("statistics")
@Singleton
public class StatisticsResources {

	@GET
	@Path("subtasks")
	public Response querySubtasksByStaffID(@QueryParam("token") String token) {
		GeneralResponse resp = new GeneralResponse();
		try {
			int staffid = LoginService.getUserId(token);
			if (staffid == 0) {
				resp.setResponseCode(401);
				throw new Exception("无效的Token");
			}
			resp.setResults(new SubtasksService().querySubtasksByUId(staffid));
		} catch (Exception e) {
			resp.setSuccessful(false);
			resp.setInformation(e.getMessage());
		}
		return Response.status(Response.Status.OK).header("Access-Control-Allow-Origin", "*").entity(resp).build();
	}

	@GET
	@Path("bugs")
	public Response getBugsByUId(@QueryParam("token") String token) {
		GeneralResponse resp = new GeneralResponse();
		try {
			int staffid = LoginService.getUserId(token);
			if (staffid == 0) {
				resp.setResponseCode(401);
				throw new Exception("无效的Token");
			}
			resp.setResults(new BugsService().getBugsByUId(staffid));
		} catch (Exception e) {
			resp.setSuccessful(false);
			resp.setInformation(e.getMessage());
		}
		return Response.status(Response.Status.OK).header("Access-Control-Allow-Origin", "*").entity(resp).build();
	}

	@OPTIONS
	@Path("bugs")
	public Response postOPtion() {
		return Response.status(Response.Status.OK).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Headers",
						"Content-Type,Content-Length, Authorization, Accept,X-Requested-With")
				.header("Access-Control-Allow-Methods", "*").build();
	}
}
