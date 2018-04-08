package com.jira.ws;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.jira.services.StaffService;
import com.sun.jersey.spi.resource.Singleton;

@Produces({ "application/xml", "application/json" })
@Path("staff")
@Singleton
public class StaffResources {

	@GET
	@Path("{id}")
	public Response QueryStaffInfo(@PathParam("id") int id) {
		return Response.status(Response.Status.OK)
				.header("Access-Control-Allow-Origin", "*")
				.entity(new StaffService().queryStaffInf(id)).build();
	}

	@GET
	public Response SearchStaff(@QueryParam("name") String name) {
		return Response.status(Response.Status.OK)
				.header("Access-Control-Allow-Origin", "*")
				.entity(new StaffService().searchStaff(name)).build();
	}
}