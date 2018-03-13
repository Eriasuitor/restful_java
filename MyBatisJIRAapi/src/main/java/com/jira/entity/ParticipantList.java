package com.jira.entity;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.jira.bean.Participant;

@XmlRootElement
public class ParticipantList extends GeneralResponse {
	private List<Participant> participants;

	public List<Participant> getParticipants() {
		return participants;
	}

	public void setParticipants(List<Participant> participants) {
		this.participants = participants;
	}
}
