package com.jira.dao;

import java.util.List;

import com.jira.bean.Phase;

public interface IPhases {
	public int addPhase(Phase phase);

	public List<Phase> queryPhases(int projectId);

	public Phase queryPhasesById(int phaseId);

	public int deletePhase(int phaId);
}
