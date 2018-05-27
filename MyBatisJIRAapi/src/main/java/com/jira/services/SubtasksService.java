package com.jira.services;

import java.util.ArrayList;
import java.util.List;

import com.jira.bean.Log;
import com.jira.bean.Phase;
import com.jira.bean.Project;
import com.jira.bean.Staff;
import com.jira.bean.Subtask;
import com.jira.dao.PhasesDao;
import com.jira.dao.ProjectsDao;
import com.jira.dao.StaffDao;
import com.jira.dao.SubtasksDao;
import com.jira.entity.StatisticsSubtask;
import com.jira.entity.SubtaskWithStaff;

public class SubtasksService {
	public int addSubtask(Subtask subtask, int userId) throws Exception {
		if (subtask.getPhaseID() < 1)
			throw new Exception("Invalid Request");
		List<Staff> staffList = new StaffDao().queryStaffInfsByPhaseId(subtask.getPhaseID());
		for (Staff staff : staffList) {
			if (staff.getId() == userId) {
				if (subtask.getName() == null || subtask.getName().trim() == "" || subtask.getManagerID() < 1
						|| subtask.getAssignedID() < 1 || subtask.getStartDate() == null || subtask.getEndDate() == null
						|| subtask.getStartDate().after(subtask.getEndDate()))
					throw new Exception("Invalid Request");
				Phase _phase = new PhasesDao().queryPhasesById(subtask.getPhaseID());
				if (_phase.getStartDate().after(subtask.getStartDate())) {
					throw new Exception("子任务的开始时间不得早于所属阶段任务的开始时间");
				}
				if (_phase.getEndDate().before(subtask.getEndDate())) {
					throw new Exception("子任务的结束时间不得晚于所属阶段任务的结束时间");
				}
				subtask.setLastEditUser(userId);
				subtask.setInsertUser(userId);
				Project pro = new ProjectsDao().queryProjectByPhaseId(subtask.getPhaseID());
				if (pro.getStatus() == Project.Status.Processing) {
					subtask.setStatus(Subtask.Status.Processing);
				} else {
					subtask.setStatus(Subtask.Status.Created);
				}
				return new SubtasksDao().addSubtask(subtask);
			}
		}
		throw new Exception("你并非当前项目的参与者之一，无法添加新的子任务。");
	}

	// public static void main(String[] args) {
	// Date d = new Date();
	// System.out.println(d.getTime());
	// }

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

	public SubtaskWithStaff querySubtasksWithStaff(int subId, int userId) throws Exception {
		if (subId < 1) {
			throw new Exception("Invalid Request");
		}
		List<Staff> _staffList = new StaffDao().queryStaffInfsBySubId(subId);
		for (Staff _staff : _staffList) {
			if (_staff.getId() == userId) {
				SubtaskWithStaff subStaff = new SubtaskWithStaff(new SubtasksDao().querySubtaskBySubId(subId));
				subStaff.setManagerStaff(new StaffService().queryStaffInf(subStaff.getManagerID()));
				subStaff.setAssignedStaff(new StaffService().queryStaffInf(subStaff.getAssignedID()));
				return subStaff;
			}
		}
		throw new Exception("你不是此项目的参与者，无法查看该子任务");

	}
}
