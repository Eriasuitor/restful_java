package com.jira.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.jira.bean.Participant;
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

	public ProjectList queryAllProjectsByStaffId(int id) {
		SqlSession sqlSession = null;
		try {
			sqlSession = DBJIRAAccess.getSqlSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// return sqlSession.selectList("Namespaces"."FunctionName",
		// Parameters);
		return sqlSession.getMapper(IProjects.class).queryAllProjectsByStaffId(id);
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
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("pId", project.getId());
		map.put("sId", project.getLastEditUser());
		sqlSession.getMapper(IParticipants.class).deleteParticipant(map);
		map = new HashMap<String, Integer>();
		map.put("pId", project.getId());
		map.put("sId", project.getManagerID());
		sqlSession.getMapper(IParticipants.class).deleteParticipant(map);
		List<Participant> participants = new ArrayList<Participant>();
		participants.add(new Participant(project.getId(), project.getManagerID(), Participant.Role.Manager));
		participants.add(new Participant(project.getId(), project.getLastEditUser(), Participant.Role.Developer));
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("list", participants);
		map2.put("userId", project.getLastEditUser());
		sqlSession.getMapper(IParticipants.class).addParticipants(map2);
		sqlSession.commit();

	}

	public void modifyStatus(int projectId, int userId, Project.Status status) {
		SqlSession sqlSession = null;
		try {
			sqlSession = DBJIRAAccess.getSqlSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", projectId);
		map.put("userId", userId);
		map.put("status", status);
		sqlSession.getMapper(IProjects.class).modifyStatus(map);
		sqlSession.commit();
		return;
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
