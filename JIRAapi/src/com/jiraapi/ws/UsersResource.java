package com.jiraapi.ws;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.sun.jersey.spi.resource.Singleton;

@Produces("application/json")
@Path("users")
@Singleton
public class UsersResource {

	@GET
	public List<IUser> getUsers() {
		throw new UnsupportedOperationException("Not yet implemented.");
	}

	@GET
	@Path("{id}")
	public IUser getUser(@PathParam("id") int cId) {
		IUser ret = new IUser();
		ret.setId(100);
		ret.setName("Lory100");
		ret.setPassword("123456");
		return ret;
	}

	@POST
	@Path("add")
	@Produces("text/html")
	@Consumes("application/xml")
	public String addUser(IUser user) {
		throw new UnsupportedOperationException("Not yet implemented.");
	}
}