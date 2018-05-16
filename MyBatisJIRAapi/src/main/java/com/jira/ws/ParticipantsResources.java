package com.jira.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.jira.bean.Participant;
import com.jira.entity.GeneralResponse;
import com.jira.services.ParticipantsService;
import com.sun.jersey.spi.resource.Singleton;

@Consumes({ "application/xml", "application/json" })
@Produces({ "application/xml", "application/json" })
@Path("projects/{projectid}/participants")
@Singleton
public class ParticipantsResources {

	@POST
	public Response addParticipant(@QueryParam("token") String token, Participant participant) {
		GeneralResponse resp = new GeneralResponse();
		try {
			resp.setResults(new ParticipantsService().addParticipant(participant));
			resp.setInformation("添加成功");
		} catch (Exception e) {
			resp.setSuccessful(false);
			resp.setInformation(e.getMessage());
		}
		return Response.status(Response.Status.OK).header("Access-Control-Allow-Origin", "*").entity(resp).build();
	}

	@DELETE
	public Response deleteParticipant(@QueryParam("token") String token, @PathParam("projectid") int pId,
			@QueryParam("sId") int sId) {
		GeneralResponse resp = new GeneralResponse();
		try {
			new ParticipantsService().deleteParticipant(pId, sId);
			resp.setInformation("删除成功");
		} catch (Exception e) {
			// TODO: handle exception
			resp.setInformation(e.getMessage());
			resp.setSuccessful(false);
		}
		return Response.status(Response.Status.OK).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Headers",
						"Content-Type,Content-Length, Authorization, Accept,X-Requested-With")
				.header("Access-Control-Allow-Methods", "GET,PUT,DELETE,OPTIONS").entity(resp).build();
	}

	@OPTIONS
	public Response deleteParticipant() {
		return Response.status(Response.Status.OK).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Headers",
						"Content-Type,Content-Length, Authorization, Accept,X-Requested-With")
				.header("Access-Control-Allow-Methods", "GET,PUT,DELETE,OPTIONS").build();
	}
}