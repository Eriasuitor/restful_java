package com.jira.dao;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.jira.bean.Participant;
import com.jira.db.DBJIRAAccess;

public class ParticipantsDao {
	public List<Participant> queryParticipantsByPId(int pId) {
		SqlSession sqlSession = null;
		try {
			sqlSession = DBJIRAAccess.getSqlSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sqlSession.getMapper(IParticipants.class)
				.queryParticipantsByPId(pId);
	}

	public int addParticipants(List<Participant> participantList, int userId) {
		SqlSession sqlSession = null;
		try {
			sqlSession = DBJIRAAccess.getSqlSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", participantList);
		map.put("userId", userId);
		int effectRows = sqlSession.getMapper(IParticipants.class)
				.addParticipants(map);
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

	public int deleteParticipant(int pId, int sId) {
		SqlSession sqlSession = null;
		try {
			sqlSession = DBJIRAAccess.getSqlSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("pId", pId);
		map.put("sId", sId);
		int effectRows = sqlSession.getMapper(IParticipants.class)
				.deleteParticipant(map);
		sqlSession.commit();
		return effectRows;
	}

}
