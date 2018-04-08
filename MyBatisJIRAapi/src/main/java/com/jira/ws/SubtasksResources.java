package com.jira.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.jira.bean.Subtask;
import com.jira.entity.GeneralResponse;
import com.jira.services.SubtasksService;
import com.sun.jersey.spi.resource.Singleton;

@Consumes({ "application/xml", "application/json" })
@Produces({ "application/xml", "application/json" })
@Path("projects/{projectid}/subtasks")
@Singleton
public class SubtasksResources {
	@POST
	public Response addSubtask(Subtask subtask) {
		GeneralResponse resp = new GeneralResponse();
		try {
			resp.setEffectRows(new SubtasksService().addSubtask(subtask));
		} catch (Exception e) {
			resp.setSuccessful(false);
			resp.setInformation(e.getMessage());
		}
		return Response.status(Response.Status.OK).entity(resp).build();
	}
}
