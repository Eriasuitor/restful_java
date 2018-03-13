package com.jira.services;

import com.jira.bean.Phase;
import com.jira.dao.PhasesDao;

public class PhasesService {

	public int addPhase(Phase phase) {
		return new PhasesDao().addPhase(phase);
	}
}
