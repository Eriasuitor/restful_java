package com.jira.services;

import com.jira.bean.Staff;
import com.jira.dao.StaffDao;

public class UserService {
	public Staff QueryStaffInf(int id) {
		return new StaffDao().query(id);
	}
}
