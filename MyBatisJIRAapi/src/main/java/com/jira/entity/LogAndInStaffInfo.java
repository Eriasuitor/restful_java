package com.jira.entity;

import javax.xml.bind.annotation.XmlRootElement;

import com.jira.bean.Log;
import com.jira.bean.Staff;

@XmlRootElement
public class LogAndInStaffInfo {
	private Staff insertUserInfo;
	private Log log;

	public LogAndInStaffInfo(Log log, Staff insertUserInfo) {
		this.insertUserInfo = insertUserInfo;
		this.log = log;
	}

	public Staff getInsertUserInfo() {
		return insertUserInfo;
	}

	public void setInsertUserInfo(Staff insertUserInfo) {
		this.insertUserInfo = insertUserInfo;
	}

	public Log getLog() {
		return log;
	}

	public void setLog(Log log) {
		this.log = log;
	}

}
