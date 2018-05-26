package com.jira.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.jira.bean.Log;
import com.jira.db.DBJIRAAccess;

public class LogsDao {

	public List<Log> getLogsBySubId(int subId) {
		SqlSession sqlSession = null;
		try {
			sqlSession = DBJIRAAccess.getSqlSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
		List<Log> logList = sqlSession.getMapper(ILogs.class).getLogsBySubId(subId);
		return logList;
	}

	public List<Log> getLastLog(List<Integer> subIds) {
		if (subIds.size() == 0)
			return new ArrayList<Log>();
		SqlSession sqlSession = null;
		try {
			sqlSession = DBJIRAAccess.getSqlSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sqlSession.getMapper(ILogs.class).getLastLog(subIds);
	}

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

	public int addLogWithAssigned(Log log) {
		SqlSession sqlSession = null;
		try {
			sqlSession = DBJIRAAccess.getSqlSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
		int effectRows = sqlSession.getMapper(ILogs.class).addLogWithAssigned(log);
		sqlSession.commit();
		return effectRows;
	}

	public int deleteLog(int id) {
		SqlSession sqlSession = null;
		try {
			sqlSession = DBJIRAAccess.getSqlSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
		int effectRows = sqlSession.getMapper(ILogs.class).deleteLog(id);
		sqlSession.commit();
		return effectRows;
	}
}
