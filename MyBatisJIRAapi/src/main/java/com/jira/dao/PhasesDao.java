package com.jira.dao;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.jira.bean.Phase;
import com.jira.db.DBJIRAAccess;

public class PhasesDao {
	public int addPhase(Phase phase) {
		SqlSession sqlSession = null;
		try {
			sqlSession = DBJIRAAccess.getSqlSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
		int effectRows = sqlSession.getMapper(IPhases.class).addPhase(phase);
		sqlSession.commit();
		return effectRows;
	}

	public List<Phase> queryPhases(int projectId) {
		SqlSession sqlSession = null;
		try {
			sqlSession = DBJIRAAccess.getSqlSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sqlSession.getMapper(IPhases.class).queryPhases(projectId);
	}
}
