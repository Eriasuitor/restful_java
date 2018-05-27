package com.jira.dao;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.jira.bean.Bug;
import com.jira.db.DBJIRAAccess;

public class BugsDao {

	// public List<Log> getLogsBySubId(int subId) {
	// SqlSession sqlSession = null;
	// try {
	// sqlSession = DBJIRAAccess.getSqlSession();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// List<Log> logList = sqlSession.getMapper(ILogs.class).getLogsBySubId(subId);
	// return logList;
	// }

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

	public Bug getBugsById(int bugId) {
		SqlSession sqlSession = null;
		try {
			sqlSession = DBJIRAAccess.getSqlSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sqlSession.getMapper(IBugs.class).getBugsById(bugId);
	}

	public List<Bug> getBugsByUId(int uId) {
		SqlSession sqlSession = null;
		try {
			sqlSession = DBJIRAAccess.getSqlSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sqlSession.getMapper(IBugs.class).getBugsByUId(uId);
	}

	public List<Bug> getBugsBySubId(int subId) {
		SqlSession sqlSession = null;
		try {
			sqlSession = DBJIRAAccess.getSqlSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sqlSession.getMapper(IBugs.class).getBugsByUId(subId);
	}

	public int modifyStatus(int bugId, Bug.Status status, String note, int userId) {
		SqlSession sqlSession = null;
		try {
			sqlSession = DBJIRAAccess.getSqlSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", bugId);
		map.put("status", status);
		map.put("note", note);
		map.put("userId", userId);
		int effectRows = sqlSession.getMapper(IBugs.class).modifyStatus(map);
		sqlSession.commit();
		return effectRows;
	}

	public int resolveBug(int bugId, Bug.Status status, String note, int userId, int assignedId) {
		SqlSession sqlSession = null;
		try {
			sqlSession = DBJIRAAccess.getSqlSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", bugId);
		map.put("status", status);
		map.put("note", note);
		System.out.println(note);
		map.put("userId", userId);
		map.put("assignedID", assignedId);
		int effectRows = sqlSession.getMapper(IBugs.class).resolveBug(map);
		sqlSession.commit();
		return effectRows;
	}
}
