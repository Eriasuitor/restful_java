package com.jira.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.jira.bean.Log;
import com.jira.entity.GeneralResponse;
import com.jira.services.LogsService;
import com.sun.jersey.spi.resource.Singleton;

@Consumes({ "application/xml", "application/json" })
@Produces({ "application/xml", "application/json" })
@Path("projects/{projectid}/logs")
@Singleton
public class LogsResources {
	@POST
	public GeneralResponse addLog(Log log) {
		GeneralResponse resp = new GeneralResponse();
		try {
			resp.setEffectRows(new LogsService().addLog(log));
		} catch (Exception e) {
			resp.setSuccessful(false);
			resp.setInformation(e.getMessage());
		}
		return resp;
	}
}
