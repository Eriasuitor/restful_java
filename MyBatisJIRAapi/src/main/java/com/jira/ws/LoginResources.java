package com.jira.ws;

import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.jira.entity.GeneralResponse;
import com.jira.entity.LoginInfo;
import com.jira.services.LoginService;
import com.sun.jersey.spi.resource.Singleton;

@Consumes({ "application/json" })
@Produces({ "application/json" })
@Path("login")
@Singleton
public class LoginResources {

	@POST
	public Response login(LoginInfo loginInfo) {
		GeneralResponse resp = new GeneralResponse();
		try {
			loginInfo.setLoginDate(new Date());
			resp.setInformation(new LoginService().login(loginInfo));
			if (resp.getInformation() == null) {
				resp.setSuccessful(false);
				resp.setInformation("密码错误");
			}
		} catch (Exception e) {
			resp.setSuccessful(false);
			resp.setInformation(e.getMessage());
		}
		return Response.status(Response.Status.OK).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Headers",
						"Content-Type,Content-Length, Authorization, Accept,X-Requested-With")
				.header("Access-Control-Allow-Methods", "*").entity(resp).build();
	}

	@DELETE
	public Response deleteLog(@QueryParam("token") String token) {
		GeneralResponse resp = new GeneralResponse();
		try {
			new LoginService().logout(token);
			resp.setInformation("注销成功");
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
	public Response defaultOptions() {
		return Response.status(Response.Status.OK).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Headers",
						"Content-Type,Content-Length, Authorization, Accept,X-Requested-With")
				.header("Access-Control-Allow-Methods", "GET,PUT,DELETE,OPTIONS").build();
	}
}
