package com.jira.services;

import java.util.ArrayList;
import java.util.List;

import com.jira.bean.Log;
import com.jira.bean.Subtask;
import com.jira.dao.ProjectsDao;
import com.jira.dao.SubtasksDao;
import com.jira.entity.StatisticsSubtask;
import com.jira.entity.SubtaskWithStaff;

public class SubtasksService {
	public int addSubtask(Subtask subtask) {
		return new SubtasksDao().addSubtask(subtask);
	}

	public List<Subtask> querySubtasks(List<Integer> phaseIDList) {
		return new SubtasksDao().querySubtasks(phaseIDList);
	}

	public Subtask querySubtasks(int subId) {
		return new SubtasksDao().querySubtaskBySubId(subId);
	}

	public List<StatisticsSubtask> querySubtasksByUId(int uId) {
		List<StatisticsSubtask> ret = new ArrayList<StatisticsSubtask>();
		List<Subtask> subtaskList = new SubtasksDao().querySubtasksByUId(uId);
		List<Integer> subIds = new ArrayList<Integer>();
		for (Subtask subtask : subtaskList) {
			subIds.add(subtask.getId());
		}
		List<Log> logs = new LogsService().getLastLog(subIds);
		for (Subtask subtask : subtaskList) {
			Log _log = null;
			for (Log log : logs) {
				if (log.getSubtaskID() == subtask.getId()) {
					_log = log;
					break;
				}
			}
			ret.add(new StatisticsSubtask(subtask, _log,
					new ProjectsDao().queryProjectByPhaseId(subtask.getPhaseID())));
		}
		return ret;
	}

	public SubtaskWithStaff querySubtasksWithStaff(int subId) {
		SubtaskWithStaff subStaff = new SubtaskWithStaff(new SubtasksDao().querySubtaskBySubId(subId));
		subStaff.setManagerStaff(new StaffService().queryStaffInf(subStaff.getManagerID()));
		subStaff.setAssignedStaff(new StaffService().queryStaffInf(subStaff.getAssignedID()));
		return subStaff;
	}
}
