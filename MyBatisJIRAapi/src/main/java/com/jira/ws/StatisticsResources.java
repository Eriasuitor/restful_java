package com.jira.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.jira.entity.GeneralResponse;
import com.jira.services.SubtasksService;
import com.jira.bean.Subtask;
import com.sun.jersey.spi.resource.Singleton;


@Consumes({ "application/xml", "application/json" })
@Produces({ "application/xml", "application/json" })
@Path("statistics/subtasks")
@Singleton
public class StatisticsResources {
	@GET
	public Response querySubtasksByStaffID(@QueryParam("uId") int uId) {
		GeneralResponse resp = new GeneralResponse();
		try {
			resp.setResults(new SubtasksService().querySubtasksByUId(uId));
		} catch (Exception e) {
			resp.setSuccessful(false);
			resp.setInformation(e.getMessage());
		}
		System.out.println(resp);
		return Response.status(Response.Status.OK).header("Access-Control-Allow-Origin", "*").entity(resp).build();
	}
}
