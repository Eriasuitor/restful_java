package com.jira.services;

import java.util.ArrayList;
import java.util.List;

import com.jira.bean.Phase;
import com.jira.bean.Project;
import com.jira.bean.Staff;
import com.jira.bean.Subtask;
import com.jira.dao.PhasesDao;
import com.jira.dao.ProjectsDao;
import com.jira.dao.StaffDao;

public class PhasesService {

	public int addPhase(Phase phase, int userId, int projectId) throws Exception {
		List<Staff> staffList = new StaffDao().queryStaffInfsByProjectID(projectId);
		System.out.println(phase.getId());
		for (Staff staff : staffList) {
			if (staff.getId() == userId) {
				if (phase.getName() == null || phase.getName().trim() == "" || phase.getManagerID() < 1
						|| phase.getStartDate() == null || phase.getEndDate() == null
						|| phase.getEndDate().before(phase.getStartDate())) {
					throw new Exception("Invalid Request");
				}
				Project project = new ProjectsDao().queryById(projectId);
				if (project.getStartDate().after(phase.getStartDate())) {
					throw new Exception("阶段任务开始时间不可早于项目开始时间");
				}
				if (project.getEndDate().before(phase.getEndDate())) {
					throw new Exception("阶段任务结束时间不可晚于项目结束时间");
				}
				phase.setProjectID(projectId);
				phase.setInsertUser(userId);
				phase.setLastEditUser(userId);
				return new PhasesDao().addPhase(phase);
			}
		}
		throw new Exception("你并非当前项目的参与者之一，无法添加新的阶段任务。");

	}

	public void deletePhase(int phaId, int userId) throws Exception {
		Phase pha = new PhasesDao().queryPhasesById(phaId);
		Project proj = new ProjectsDao().queryProjectByPhaseId(phaId);
		if (pha.getManagerID() != userId && proj.getManagerID() != userId) {
			throw new Exception("你不是该项目或者该阶段的负责人，无法删除该阶段任务。");
		}
		new PhasesDao().deletePhase(phaId);
	}

	public List<Phase> queryPhase(int projectId) {
		List<Phase> phaseList = new PhasesDao().queryPhases(projectId);
		List<Integer> phaseIdList = new ArrayList<Integer>();
		for (int i = 0; i < phaseList.size(); i++) {
			phaseIdList.add(phaseList.get(i).getId());
		}
		List<Subtask> subtaskList = new SubtasksService().querySubtasks(phaseIdList);
		for (int j = 0; j < phaseList.size(); j++) {
			phaseList.get(j).setSubtaskList(new ArrayList<Subtask>());
			for (int i = 0; i < subtaskList.size(); i++) {
				if (subtaskList.get(i).getPhaseID() == phaseList.get(j).getId()) {
					phaseList.get(j).getSubtaskList().add(subtaskList.get(i));
				}
			}
		}
		return phaseList;
	}
}
