package com.jira.dao;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.jira.bean.Project;
import com.jira.db.DBJIRAAccess;
import com.jira.entity.ProjectList;

public class ProjectsDao {
	public ProjectList queryByStaffId(int staffId) {
		SqlSession sqlSession = null;
		try {
			sqlSession = DBJIRAAccess.getSqlSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// return sqlSession.selectList("Namespaces"."FunctionName",
		// Parameters);
		return sqlSession.getMapper(IProjects.class).queryProjectsByStaffId(staffId);
	}

	public Project queryProjectByPhaseId(int phaseId) {
		SqlSession sqlSession = null;
		try {
			sqlSession = DBJIRAAccess.getSqlSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sqlSession.getMapper(IProjects.class).queryProjectByPhaseId(phaseId);
	}

	public Project queryById(int id) {
		SqlSession sqlSession = null;
		try {
			sqlSession = DBJIRAAccess.getSqlSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sqlSession.getMapper(IProjects.class).queryProjectById(id);
	}

	public List<Project> queryProjects(List<Integer> ids) {
		SqlSession sqlSession = null;
		try {
			sqlSession = DBJIRAAccess.getSqlSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sqlSession.getMapper(IProjects.class).queryProjects(ids);
	}

	public List<Project> searchProjects(String pName, int userId) {
		System.out.println(pName);
		SqlSession sqlSession = null;
		try {
			sqlSession = DBJIRAAccess.getSqlSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pName", pName);
		map.put("userId", userId);
		return sqlSession.getMapper(IProjects.class).searchProjects(map);
	}

	public int create(Project project) {
		SqlSession sqlSession = null;
		try {
			sqlSession = DBJIRAAccess.getSqlSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
		int projectId = sqlSession.getMapper(IProjects.class).newProject(project);
		sqlSession.commit();
		return projectId;
	}

	public void modifyProject(Project project) {
		SqlSession sqlSession = null;
		try {
			sqlSession = DBJIRAAccess.getSqlSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
		sqlSession.getMapper(IProjects.class).modifyProject(project);
		sqlSession.commit();
	}

	public void modifyManagerID(Project project) {
		SqlSession sqlSession = null;
		try {
			sqlSession = DBJIRAAccess.getSqlSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
		sqlSession.getMapper(IProjects.class).modifyManagerID(project);
		sqlSession.commit();

	}

	public int deleteProject(int id) {
		SqlSession sqlSession = null;
		try {
			sqlSession = DBJIRAAccess.getSqlSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
		int effectRows = sqlSession.getMapper(IProjects.class).deleteProject(id);
		sqlSession.commit();
		return effectRows;
	}
}
