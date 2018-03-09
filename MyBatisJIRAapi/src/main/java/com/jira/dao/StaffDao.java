package com.jira.dao;

import java.io.IOException;

import org.apache.ibatis.session.SqlSession;

import com.jira.bean.Staff;
import com.jira.db.DBJIRAAccess;

public class StaffDao {
	public Staff query(int id) {
		SqlSession sqlSession = null;
		try {
			sqlSession = DBJIRAAccess.getSqlSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sqlSession.getMapper(IStaff.class).queryStaffInf(id);
	}
}
