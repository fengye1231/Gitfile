package com.project.web.util;

import java.io.IOException;
import java.io.InputStream;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtil {
	//得到sqlSessionFactory
	public static SqlSessionFactory getSqlSessionFactory() {
		String str = "conf.xml";
		InputStream in = null;
		SqlSessionFactory sqlSessionFactory = null;
		try {
			in = Resources.getResourceAsStream(str);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sqlSessionFactory;
	}
	
	public static SqlSession getSqlSession() {
		SqlSessionFactory sessionFactory = getSqlSessionFactory();
		SqlSession session = sessionFactory.openSession();
		return session;
	}
	
	public static SqlSession getSqlSession(boolean flag) {
		SqlSessionFactory sessionFactory = getSqlSessionFactory();
		SqlSession session = sessionFactory.openSession(flag);
		return session;
	}
}
