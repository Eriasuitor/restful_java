package com.jira.dao;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.jira.bean.Bug;
import com.jira.bean.Log;
import com.jira.db.DBJIRAAccess;

public class BugsDao {

//	public List<Log> getLogsBySubId(int subId) {
//		SqlSession sqlSession = null;
//		try {
//			sqlSession = DBJIRAAccess.getSqlSession();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		List<Log> logList = sqlSession.getMapper(ILogs.class).getLogsBySubId(subId);
//		return logList;
//	}

	public int addBug(Bug bug) {
		SqlSession sqlSession = null;
		try {
			sqlSession = DBJIRAAccess.getSqlSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
		int effectRows = sqlSession.getMapper(IBugs.class).addBug(bug);
		sqlSession.commit();
		return effectRows;
	}
	//
	// public int deleteLog(int id) {
	// SqlSession sqlSession = null;
	// try {
	// sqlSession = DBJIRAAccess.getSqlSession();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// int effectRows = sqlSession.getMapper(ILogs.class).deleteLog(id);
	// sqlSession.commit();
	// return effectRows;
	// }
}
