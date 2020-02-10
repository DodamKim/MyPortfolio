package com.bit.campfire.db;

import java.io.Reader;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.bit.campfire.vo.NoticeVo;

public class NoticeManager {
	
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
	
	public static int updateNotice(NoticeVo vo) {
		
		SqlSession session = factory.openSession();
		int r = session.update("notice.updateNotice", vo);
		r += session.update("notice.updateBdetail", vo);

		if (r == 2) {
			session.commit();
			
		} else {
			session.rollback();
		}
		session.close();
		
		return r;
	}
	
	public static NoticeVo getNotice(HashMap map) {

		SqlSession session = factory.openSession();
		NoticeVo vo = session.selectOne("notice.getNotice", map);
		session.close();

		return vo;
	}
	
	public static int insertNotice(NoticeVo vo) {

		SqlSession session = factory.openSession();
		int r = session.insert("notice.insertNotice", vo);
		System.out.println(r);
		r += session.insert("notice.insertBdetail", vo);
		System.out.println(r);
		if (r == 2) {
			session.commit();
			
		} else {
			session.rollback();
		}
		session.close();

		return r;
	}
	
	public static int deleteNotice(HashMap map) {

		SqlSession session = factory.openSession(true);
		int r = session.delete("notice.deleteNotice", map);
		session.close();
		
		return r;
	}
	
	public static List<NoticeVo> listNotice(HashMap map){
		SqlSession session = factory.openSession();
		List<NoticeVo> list = session.selectList("notice.listNotice", map);
		session.close();
		
		return list;
	}
	
	public static int getNoticeCnt() {

		SqlSession session = factory.openSession();
		int cnt = session.selectOne("notice.getNoticeCnt");
		session.close();

		return cnt;
	}
	
}
