package com.jira.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.jira.entity.GeneralResponse;
import com.jira.entity.ParticipantList;
import com.jira.services.ParticipantsService;
import com.sun.jersey.spi.resource.Singleton;

@Consumes({ "application/xml", "application/json" })
@Produces({ "application/xml", "application/json" })
@Path("projects/{projectid}/participants")
@Singleton
public class ParticipantsResources {

	@POST
	public GeneralResponse addParticipants(ParticipantList participants) {
		GeneralResponse resp = new GeneralResponse();
		try {
			resp.setEffectRows(new ParticipantsService()
					.addParticipants(participants));
		} catch (Exception e) {
			resp.setSuccessful(false);
			resp.setExceptionInf(e.getMessage());
		}
		return resp;
	}
}