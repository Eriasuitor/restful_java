package com.jira.dao;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.jira.bean.Subtask;
import com.jira.db.DBJIRAAccess;

public class SubtasksDao {
	public int addSubtask(Subtask subtask) {
		SqlSession sqlSession = null;
		try {
			sqlSession = DBJIRAAccess.getSqlSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
		int effectRows = sqlSession.getMapper(ISubtasks.class).addSubtask(subtask);
		sqlSession.commit();
		return effectRows;
	}

	public List<Subtask> querySubtasksByIds(List<Integer> dis) {
		SqlSession sqlSession = null;
		try {
			sqlSession = DBJIRAAccess.getSqlSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sqlSession.getMapper(ISubtasks.class).querySubtasksByIds(dis);
	}

	public List<Subtask> querySubtasksByUId(int uId) {
		SqlSession sqlSession = null;
		try {
			sqlSession = DBJIRAAccess.getSqlSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sqlSession.getMapper(ISubtasks.class).querySubtasksByUId(uId);
	}

	public List<Subtask> querySubtasks(List<Integer> phaseIDList) {
		if (phaseIDList.size() == 0)
			return null;
		SqlSession sqlSession = null;
		try {
			sqlSession = DBJIRAAccess.getSqlSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sqlSession.getMapper(ISubtasks.class).querySubtasks(phaseIDList);
	}

	public Subtask querySubtaskBySubId(int subId) {
		SqlSession sqlSession = null;
		try {
			sqlSession = DBJIRAAccess.getSqlSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sqlSession.getMapper(ISubtasks.class).querySubtaskBySubId(subId);
	}
}
