package com.jira.services;

import java.io.IOException;

import org.apache.ibatis.session.SqlSession;

import com.jira.bean.User;
import com.jira.dao.DBAccess;

public class UserService {
	public User QueryUserInf(int id) {
		DBAccess dbAccess = new DBAccess();
		SqlSession sqlSession = null;
		try {
			sqlSession = dbAccess.getSqlSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sqlSession.selectOne("User.user1");
	}
}
