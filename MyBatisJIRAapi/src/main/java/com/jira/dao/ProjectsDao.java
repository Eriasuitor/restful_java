package com.jira.dao;

import java.io.IOException;

import org.apache.ibatis.session.SqlSession;

import com.jira.db.DBJIRAAccess;
import com.jira.entity.ProjectList;

public class ProjectsDao {
	public ProjectList query(int id) {
		SqlSession sqlSession = null;
		try {
			sqlSession = DBJIRAAccess.getSqlSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sqlSession.getMapper(IProjects.class).queryProjectNameList(id);
		// ProjectList ret = new ProjectList();
		// List<Project> r = new ArrayList<Project>();
		// r = sqlSession.selectList("Projects.queryProjectNameList", id);
		// ret.setProjectNames(r);
		// return ret;
	}
}
