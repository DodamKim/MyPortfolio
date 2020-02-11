package com.bit.campfire.db;

import java.io.Reader;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.bit.campfire.vo.DataVo;
import com.bit.campfire.vo.RecordVo;

public class RecordManager {

	private static SqlSessionFactory factory;
	
	static {
		
		try {
			Reader reader = Resources.getResourceAsReader("com/bit/campfire/db/sqlMapConfig.xml");
			factory = new SqlSessionFactoryBuilder().build(reader);
			reader.close();
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static List<RecordVo> listRecord() {

		SqlSession session = factory.openSession();
		List<RecordVo> list = session.selectList("record.listRecord");
		session.close();
		
		return list;
	}

	public static List<RecordVo> todayRecord() {
		
		SqlSession session = factory.openSession();
		List<RecordVo> list = session.selectList("record.todayRecord");
		session.close();
		
		return list;
	}
	
	
	/* 방문회원수 기록 처리 */
	public static int insertTempVisit() {
		
		SqlSession session = factory.openSession(true);
		int r = session.insert("record.insertTempVisit");
		session.close();
		
		return r;
	}
	
	public static int deleteTemp() {
		
		SqlSession session = factory.openSession(true);
		int r = session.delete("record.deleteAll");
		session.close();
		
		return r;
		
	}
	
	public static int getTodayVisit() {
		
		SqlSession session = factory.openSession();
		int r = session.selectOne("record.getTodayVisit");
		session.close();
		
		return r;
		
	}
	
	public static int insertData(HashMap map) {
		
		SqlSession session = factory.openSession(true);
		int r = session.insert("record.insertData", map);
		session.close();
		
		return r;
	}

	public static int insertTempPlay() {
		
		SqlSession session = factory.openSession(true);
		int r = session.insert("record.insertTempPlay");
		session.close();
		
		return r;
	}

	public static int getTodayPlay() {
		
		SqlSession session = factory.openSession();
		int r = session.selectOne("record.getTodayPlay");
		session.close();
		
		return r;
		
	}
	
}








