package com.bit.campfire.db;

import java.io.Reader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.bit.campfire.vo.DataVo;
import com.bit.campfire.vo.MemberVo;

public class DataManager {

	private static SqlSessionFactory factory;
	
	static {
		try {
			Reader reader = Resources.getResourceAsReader("com/bit/campfire/db/sqlMapConfig.xml");
			factory = new SqlSessionFactoryBuilder().build(reader);
			reader.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static int getTotalPlay() {
		SqlSession session = factory.openSession();
		int re = session.selectOne("data.getTotalPlay");
		session.close();
		return re;
	}
	
	public static int getTotalVisit() {
		SqlSession session = factory.openSession();
		int re = session.selectOne("data.getTotalVisit");
		session.close();
		return re;
	}

	public static List<DataVo> dataList(HashMap map) {
		List<DataVo> list = null;
		System.out.println("manager.dataList.day : "+map);	
		SqlSession session = factory.openSession();		
		list = session.selectList("data.dataList", map);
		session.close();
		return list;
	}
}








