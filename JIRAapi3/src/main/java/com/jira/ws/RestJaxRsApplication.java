package com.jira.ws;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

public class RestJaxRsApplication extends ResourceConfig {
	public RestJaxRsApplication() {

		this.register(UserResources.class);

		register(RequestContextFilter.class);

	}
}
