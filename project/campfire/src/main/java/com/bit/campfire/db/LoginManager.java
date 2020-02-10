package com.bit.campfire.db;

import java.io.Reader;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.bit.campfire.vo.MemberVo;

public class LoginManager {

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
		
	
	public static MemberVo getMember(HashMap map){
		MemberVo vo = null;
		SqlSession session = factory.openSession();
		vo = session.selectOne("login.getMember", map);
		session.close();
		
		return vo;
	}
	
	public static int insertMember(HashMap map) {
		int re = 0;
		SqlSession session = factory.openSession(true);
		re = session.insert("login.insertMember", map);
		session.close();
		return re;
	}

	public static List<MemberVo> listMember(HashMap map) {
		
		SqlSession session = factory.openSession();
		List<MemberVo> list = session.selectList("login.listMember", map);
		session.close();
		
		return list;
	}

	public static int stopMember(HashMap map) {
		
		SqlSession session = factory.openSession(true);
		int re = session.insert("login.stopMember", map);
		session.close();
		
		return re;
	}

	public static int resetMember(HashMap map) {
		
		SqlSession session = factory.openSession(true);
		int re = session.insert("login.resetMember", map);
		session.close();
		
		return re;
	}

	public static int deleteMember(HashMap map) {

		SqlSession session = factory.openSession(true);
		int re = session.insert("login.deleteMember", map);
		session.close();
		
		return re;
	}

	public static int getTotal() {
		
		SqlSession session = factory.openSession();
		int r = session.selectOne("login.getTotal");
		session.close();
		
		return r;
	}
	
	public static int getCountType(String type) {
		SqlSession session = factory.openSession();
		int r = session.selectOne("login.getCountType", type);
		return r;
	}
	
}
