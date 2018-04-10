package com.jira.dao;

import java.io.IOException;
import java.util.List;

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
		return sqlSession.getMapper(IProjects.class).queryProjectsByStaffId(
				staffId);
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

	public List<Project> searchProjects(String pName) {
		SqlSession sqlSession = null;
		try {
			sqlSession = DBJIRAAccess.getSqlSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sqlSession.getMapper(IProjects.class).searchProjects(pName);
	}

	public int create(Project project) {
		SqlSession sqlSession = null;
		try {
			sqlSession = DBJIRAAccess.getSqlSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
		int projectId = sqlSession.getMapper(IProjects.class).newProject(
				project);
		sqlSession.commit();
		return projectId;
	}
}
