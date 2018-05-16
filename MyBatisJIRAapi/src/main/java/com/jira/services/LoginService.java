package com.jira.services;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.jira.entity.LoginInfo;

public class LoginService {
	private static Map<String, LoginInfo> tokens = new HashMap<String, LoginInfo>();

	public static int getUserId(String token) {
		int id = tokens.get(token) == null ? 0 : tokens.get(token).getId();
		return id;
	}

	public static String login(LoginInfo loginInfo) {
		String passwd = new StaffService().queryStaffInf(loginInfo.getId()).getPassword();
		if (passwd.equals(loginInfo.getPassword())) {
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
