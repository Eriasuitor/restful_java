package com.jira.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.ibatis.session.SqlSession;

import com.jira.bean.Staff;
import com.jira.db.DBJIRAAccess;

public class StaffDao {
	public Staff query(int id) {
		SqlSession sqlSession = null;
		try {
			sqlSession = DBJIRAAccess.getSqlSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sqlSession.getMapper(IStaff.class).queryStaffInf(id);
	}

	public List<Staff> query(Set<Integer> idSet) {
		SqlSession sqlSession = null;
		try {
			sqlSession = DBJIRAAccess.getSqlSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
		List<Integer> idList = new ArrayList<Integer>();
		idList.addAll(idSet);
		return sqlSession.getMapper(IStaff.class).queryStaffInfs(idList);
	}

	public List<Staff> queryStaffInfsByPhaseId(int phaId) {
		SqlSession sqlSession = null;
		try {
			sqlSession = DBJIRAAccess.getSqlSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sqlSession.getMapper(IStaff.class).queryStaffInfsByPhaseId(phaId);
	}

	public List<Staff> queryStaffInfsBySubId(int subId) {
		SqlSession sqlSession = null;
		try {
			sqlSession = DBJIRAAccess.getSqlSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sqlSession.getMapper(IStaff.class).queryStaffInfsBySubId(subId);
	}

	public List<Staff> queryStaffInfsByProjectID(int pId) {
		SqlSession sqlSession = null;
		try {
			sqlSession = DBJIRAAccess.getSqlSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sqlSession.getMapper(IStaff.class).queryStaffInfsByProjectID(pId);
	}

	public List<Staff> searchStaff(String name) {
		SqlSession sqlSession = null;
		try {
			sqlSession = DBJIRAAccess.getSqlSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sqlSession.getMapper(IStaff.class).searchStaff(name);
	}
}
