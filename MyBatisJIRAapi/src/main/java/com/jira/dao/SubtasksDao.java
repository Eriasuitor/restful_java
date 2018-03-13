package com.jira.dao;

import java.io.IOException;

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
		int effectRows = sqlSession.getMapper(ISubtasks.class).addSubtask(
				subtask);
		sqlSession.commit();
		return effectRows;
	}
}
