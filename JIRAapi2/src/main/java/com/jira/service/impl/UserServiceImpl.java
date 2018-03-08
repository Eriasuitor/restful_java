package com.jira.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jira.dao.IUserDao;
import com.jira.service.IUserService;
import com.jira.user.User;

@Service("userService")
public class UserServiceImpl implements IUserService {
	@Resource
	private IUserDao userDao;

	@Override
	public User getUserById(int userId) {
		// TODO Auto-generated method stub
		return this.userDao.selectByPrimaryKey(userId);
	}
}
