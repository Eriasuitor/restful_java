package com.jira.dao;

import java.io.IOException;

import org.apache.ibatis.session.SqlSession;

import com.jira.bean.User;
import com.jira.db.DBJIRAAccess;

public class StaffDao {
	public User query(int id) {
		SqlSession sqlSession = null;
		try {
			sqlSession = DBJIRAAccess.getSqlSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sqlSession.selectOne("User.user1", id);
	}
}
