package com.jira.bussiness;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jira.service.IUserService;
import com.jira.user.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-mybatis.xml" })
public class GetInf {
	@Resource
	private IUserService userService = null;

	@Test
	public User test1() {
		// return userService.getUserById(1);
		User ret = new User();
		ret.setId(100);
		ret.setName("Lory100");
		ret.setPassword("132456");
		return ret;
	}
}