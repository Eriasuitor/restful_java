package com.jira.services;

import java.util.ArrayList;
import java.util.List;

import com.jira.bean.Phase;
import com.jira.bean.Subtask;
import com.jira.dao.PhasesDao;

public class PhasesService {

	public int addPhase(Phase phase, int userId, int projectId) {
		phase.setProjectID(projectId);
		phase.setStatus("Proccessing");
		phase.setPhaseNum(0);
		phase.setInsertUser("API");
		phase.setLastEditUser("API");
		return new PhasesDao().addPhase(phase);
	}

	public List<Phase> queryPhase(int projectId) {
		List<Phase> phaseList = new PhasesDao().queryPhases(projectId);
		List<Integer> phaseIdList = new ArrayList<Integer>();
		for (int i = 0; i < phaseList.size(); i++) {
			phaseIdList.add(phaseList.get(i).getId());
		}
		List<Subtask> subtaskList = new SubtasksService()
				.querySubtasks(phaseIdList);
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
