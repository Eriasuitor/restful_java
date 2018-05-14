package com.jira.entity;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.jira.bean.Phase;

@XmlRootElement
public class PhaseList extends GeneralResponse {
	private List<Phase> phaseList;

	public PhaseList() {

	}

	public PhaseList(List<Phase> phaseList) {
		this.phaseList = phaseList;
	}

	public List<Phase> getPhaseList() {
		return phaseList;
	}

	public void setPhaseList(List<Phase> phaseList) {
		this.phaseList = phaseList;
	}

}
