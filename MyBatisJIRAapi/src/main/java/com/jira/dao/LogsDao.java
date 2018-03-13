package com.jira.dao;

import java.io.IOException;

import org.apache.ibatis.session.SqlSession;

import com.jira.bean.Log;
import com.jira.db.DBJIRAAccess;

public class LogsDao {
	public int addLog(Log log) {
		SqlSession sqlSession = null;
		try {
			sqlSession = DBJIRAAccess.getSqlSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
		int effectRows = sqlSession.getMapper(ILogs.class).addLog(log);
		sqlSession.commit();
		return effectRows;
	}
}
