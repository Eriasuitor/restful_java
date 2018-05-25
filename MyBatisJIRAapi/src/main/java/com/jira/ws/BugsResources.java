package com.jira.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.jira.bean.Bug;
import com.jira.entity.GeneralResponse;
import com.jira.services.BugsService;
import com.sun.jersey.spi.resource.Singleton;

@Consumes({ "application/json" })
@Produces({ "application/json" })
@Path("projects/{projectid}/bugs")
@Singleton
public class BugsResources {

	// @GET
	// public Response getLogsBySubId(@QueryParam("subId") int subId) {
	// return
	// Response.status(Response.Status.OK).header("Access-Control-Allow-Origin",
	// "*")
	// .entity(new GeneralResponse(new
	// LogsService().getLogsBySubId(subId))).build();
	// }

	@POST
	public Response addBug(@QueryParam("token") String token, Bug bug) {
		GeneralResponse resp = new GeneralResponse();
		// try {
		resp.setEffectRows(new BugsService().addBug(bug));
		resp.setInformation("提交成功");
		// } catch (Exception e) {
		// resp.setSuccessful(false);
		// resp.setInformation(e.getMessage());
		// }
		return Response.status(Response.Status.OK).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Headers",
						"Content-Type,Content-Length, Authorization, Accept,X-Requested-With")
				.header("Access-Control-Allow-Methods", "*").entity(resp).build();
	}

	@GET
	public Response getBugsBySubId(@QueryParam("token") String token, @QueryParam("subId") int subId) {
		GeneralResponse resp = new GeneralResponse();
		try {
			resp.setResults(new BugsService().getBugsBySubId(subId));
		} catch (Exception e) {
			resp.setSuccessful(false);
			resp.setInformation(e.getMessage());
		}
		return Response.status(Response.Status.OK).header("Access-Control-Allow-Origin", "*").entity(resp).build();
	}

	// @DELETE
	// @Path("{id}")
	// public Response deleteLog(@PathParam("id") int id) {
	// GeneralResponse resp = new GeneralResponse();
	// try {
	// resp.setEffectRows(new LogsService().deleteLog(id));
	// resp.setInformation("删除成功");
	// } catch (Exception e) {
	// resp.setSuccessful(false);
	// resp.setInformation(e.getMessage());
	// }
	// return
	// Response.status(Response.Status.OK).header("Access-Control-Allow-Origin",
	// "*")
	// .header("Access-Control-Allow-Headers",
	// "Content-Type,Content-Length, Authorization, Accept,X-Requested-With")
	// .header("Access-Control-Allow-Methods", "*").entity(resp).build();
	// }

	@OPTIONS
	@Path("{id}")
	public Response deleteLogOptions() {
		return Response.status(Response.Status.OK).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Headers",
						"Content-Type,Content-Length, Authorization, Accept,X-Requested-With")
				.header("Access-Control-Allow-Methods", "GET,PUT,DELETE,OPTIONS").build();
	}

	@OPTIONS
	public Response defaultOptions() {
		return Response.status(Response.Status.OK).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Headers",
						"Content-Type,Content-Length, Authorization, Accept,X-Requested-With")
				.header("Access-Control-Allow-Methods", "*").build();
	}
}
