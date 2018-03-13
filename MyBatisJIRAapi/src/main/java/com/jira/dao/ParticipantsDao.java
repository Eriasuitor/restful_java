package com.jira.dao;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.jira.bean.Participant;
import com.jira.db.DBJIRAAccess;

public class ParticipantsDao {
	public int addParticipants(List<Participant> participants) {
		SqlSession sqlSession = null;
		try {
			sqlSession = DBJIRAAccess.getSqlSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
		int effectRows = sqlSession.getMapper(IParticipants.class)
				.addParticipants(participants);
		sqlSession.commit();
		return effectRows;
	}

	public int addParticipant(Participant participant) {
		SqlSession sqlSession = null;
		try {
			sqlSession = DBJIRAAccess.getSqlSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
		int effectRows = sqlSession.getMapper(IParticipants.class)
				.addParticipant(participant);
		sqlSession.commit();
		return effectRows;
	}
}
