package com.jira.ws;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.jira.bean.Staff;
import com.jira.services.UserService;
import com.sun.jersey.spi.resource.Singleton;

@Produces({ "application/xml", "application/json" })
@Path("users")
@Singleton
public class StaffResources {

	@GET
	@Path("{id}")
	public Staff QueryUserInfo(@PathParam("id") int cId) {
		return new UserService().QueryStaffInf(cId);
	}
}