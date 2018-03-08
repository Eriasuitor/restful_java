package com.jira.ws;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.jira.user.User;
import com.sun.jersey.spi.resource.Singleton;

@Produces({ "application/xml", "application/json" })
@Path("users")
@Singleton
public class UserResources {

	@GET
	public List<User> getUsers() {
		throw new UnsupportedOperationException("Not yet implemented.");
	}

	@GET
	@Path("{id}")
	public User getUser(@PathParam("id") int cId) {
		User ret = new User();
		ret.setId(100);
		ret.setName("Lory100");
		ret.setPassword("123456");
		return ret;

		// User user = userService.getUserById(1);
		// return user;

		// return new getInf().test1();

	}

	@POST
	@Path("add")
	@Produces("text/html")
	@Consumes("application/xml")
	public String addUser(User user) {
		throw new UnsupportedOperationException("Not yet implemented.");
	}
}