package com.jira.services;

import com.jira.bean.User;
import com.jira.dao.StaffDao;

public class UserService {
	public User QueryStaffInf(int id) {
		return new StaffDao().query(id);
	}
}
