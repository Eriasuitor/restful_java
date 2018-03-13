package com.jira.dao;

import java.io.IOException;

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
}
