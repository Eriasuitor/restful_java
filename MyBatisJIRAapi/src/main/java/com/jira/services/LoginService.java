package com.jira.services;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.jira.bean.Staff;
import com.jira.entity.LoginInfo;

public class LoginService {
	private static Map<String, LoginInfo> tokens = new HashMap<String, LoginInfo>();

	public static int validateToken(String token) {
		return tokens.get(token).getId();
	}

	public static int getUserId(String token) {
		int id = tokens.get(token) == null ? 0 : tokens.get(token).getId();
		return id;
	}

	public static String login(LoginInfo loginInfo) {
		Staff staff = new StaffService().queryStaffInf(loginInfo.getId());
		if (staff != null && staff.getPassword().equals(loginInfo.getPassword())) {
			UUID uuid = UUID.randomUUID();
			tokens.put(uuid.toString() + loginInfo.getId(), loginInfo);
			return uuid.toString() + loginInfo.getId();
		}
		return null;
	}

	public static void logout(String token) {
		tokens.remove(token);
	}
}
