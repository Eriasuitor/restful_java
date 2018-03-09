package com.jira.db;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DBJIRAAccess {

	private static SqlSessionFactory sqlSessionFactory = null;

	public static SqlSession getSqlSession() throws IOException {
		if (sqlSessionFactory == null) {
			Reader reader = Resources
					.getResourceAsReader("com/jira/config/Configuration.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		}
		return sqlSessionFactory.openSession();
	}
}
