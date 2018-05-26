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

import com.jira.bean.Subtask;
import com.jira.entity.GeneralResponse;
import com.jira.services.LoginService;
import com.jira.services.SubtasksService;
import com.sun.jersey.spi.resource.Singleton;

@Consumes({ "application/xml", "application/json" })
@Produces({ "application/xml", "application/json" })
@Path("projects/{projectid}/subtasks")
@Singleton
public class SubtasksResources {
	@GET
	@Path("{id}")
	public Response querySubtaskWithStaffById(@QueryParam("token") String token, @PathParam("id") int subId) {
		GeneralResponse resp = new GeneralResponse();
		try {
			int staffid = LoginService.getUserId(token);
			if (staffid == 0) {
				resp.setResponseCode(401);
				throw new Exception("无效的Token");
			}
			resp.setObject(new SubtasksService().querySubtasksWithStaff(subId, staffid));
		} catch (Exception e) {
			resp.setSuccessful(false);
			resp.setInformation(e.getMessage());
		}
		return Response.status(Response.Status.OK).header("Access-Control-Allow-Origin", "*").entity(resp).build();
	}

	// @GET
	// public Response querySubtaskByProjectId(@QueryParam("token") String token,
	// @PathParam("projectid") int pId) {
	// return
	// Response.status(Response.Status.OK).header("Access-Control-Allow-Origin",
	// "*")
	// .entity(new SubtasksService().querySubtasksWithStaff(pId)).build();
	// }

	@POST
	public Response addSubtask(@QueryParam("token") String token, Subtask subtask) {
		GeneralResponse resp = new GeneralResponse();
		try {
			int staffid = LoginService.getUserId(token);
			if (staffid == 0) {
				resp.setResponseCode(401);
				throw new Exception("无效的Token");
			}
			resp.setEffectRows(new SubtasksService().addSubtask(subtask, staffid));
			resp.setInformation("添加成功");
		} catch (Exception e) {
			resp.setSuccessful(false);
			resp.setInformation(e.getMessage());
		}
		return Response.status(Response.Status.OK).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Headers",
						"Content-Type,Content-Length, Authorization, Accept,X-Requested-With")
				.header("Access-Control-Allow-Methods", "*").entity(resp).build();
	}

	@OPTIONS
	public Response addSubtaskOptions() {
		return Response.status(Response.Status.OK).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Headers",
						"Content-Type,Content-Length, Authorization, Accept,X-Requested-With")
				.header("Access-Control-Allow-Methods", "*").build();
	}
}
