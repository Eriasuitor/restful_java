package com.jira.services;

import java.util.List;

import com.jira.bean.Subtask;
import com.jira.dao.SubtasksDao;
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
	
	public List<Subtask> querySubtasksByUId(int uId) {
		return new SubtasksDao().querySubtasksByUId(uId);
	}

	public SubtaskWithStaff querySubtasksWithStaff(int subId) {
		SubtaskWithStaff subStaff = new SubtaskWithStaff(
				new SubtasksDao().querySubtaskBySubId(subId));
		subStaff.setManagerStaff(new StaffService().queryStaffInf(subStaff
				.getManagerID()));
		subStaff.setAssignedStaff(new StaffService().queryStaffInf(subStaff
				.getAssignedID()));
		return subStaff;
	}
}
