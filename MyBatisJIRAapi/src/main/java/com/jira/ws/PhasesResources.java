package com.jira.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.jira.bean.Phase;
import com.jira.entity.GeneralResponse;
import com.jira.services.PhasesService;
import com.sun.jersey.spi.resource.Singleton;

@Consumes({ "application/xml", "application/json" })
@Produces({ "application/xml", "application/json" })
@Path("projects/{projectid}/phases")
@Singleton
public class PhasesResources {

	@POST
	public GeneralResponse addPhase(Phase phase) {
		GeneralResponse resp = new GeneralResponse();
		try {
			resp.setEffectRows(new PhasesService().addPhase(phase));
		} catch (Exception e) {
			resp.setSuccessful(false);
			resp.setInformation(e.getMessage());
		}
		return resp;
	}
}