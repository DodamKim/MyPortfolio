package com.bit.campfire.db;

import java.io.Reader;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.bit.campfire.vo.BoardVo;
import com.bit.campfire.vo.CommentsVo;

public class BoardManager {

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

	public static List<BoardVo> listBoard(HashMap map) {

		SqlSession session = factory.openSession();
		List<BoardVo> list = session.selectList("board.listBoard", map);
		session.close();

		return list;
	}

	public static BoardVo getBoard(HashMap map) {

		SqlSession session = factory.openSession();
		BoardVo vo = session.selectOne("board.getBoard", map);
		session.close();

		return vo;
	}

	public static int getBoardCnt() {

		SqlSession session = factory.openSession();
		int cnt = session.selectOne("board.getBoardCnt");
		session.close();

		return cnt;
	}

	public static int insertBoard(BoardVo vo) {

		SqlSession session = factory.openSession();
		int r = session.insert("board.insertBoard", vo);
		r += session.insert("board.insertBdetail", vo);

		System.out.println("r: "+r);
		if (r == 2) {
			session.commit();
			
		} else {
			session.rollback();
		}
		session.close();

		return r;
	}

	public static int updateBoard(BoardVo vo) {
		
		//System.out.println("update manager 동작함");
		SqlSession session = factory.openSession();
		int r = session.update("board.updateBoard", vo);
		r += session.update("board.updateBdetail", vo);

		if (r == 2) {
			session.commit();
			
		} else {
			session.rollback();
		}
		session.close();
		
		return r;
	}

	public static int deleteBoard(HashMap map) {

		SqlSession session = factory.openSession(true);
		int r = session.delete("board.deleteBoard", map);
		session.close();
		
		return r;
	}

	public static int getCno() {
		
		SqlSession session = factory.openSession();
		int cno = session.selectOne("board.getCno");
		session.close();
		
		return cno;
	}

	public static CommentsVo getComment(HashMap map) {

		SqlSession session = factory.openSession();
		CommentsVo vo = session.selectOne("board.getComment", map);
		session.close();
		
		return vo;
	}

	public static void updateStep(HashMap map) {
		
		SqlSession session = factory.openSession(true);
		session.update("board.updateStep", map);
		session.close();
		
	}

	public static int insertCommets(CommentsVo vo) {
		
		SqlSession session = factory.openSession(true);
		int r = session.insert("board.insertComment", vo);
		session.close();
		
		return r;
	}

	public static List<CommentsVo> listComments(HashMap map) {
		
		SqlSession session = factory.openSession();
		List<CommentsVo> list = session.selectList("board.listComments", map);
		session.close();
		
		return list;
	}

	public static int getCommentNum(HashMap map) {

		SqlSession session = factory.openSession();
		int r = session.selectOne("board.getCommentNum", map);
		session.close();
		
		return r;
	}

	public static int updateRC(HashMap map) {

		SqlSession session = factory.openSession(true);
		int r = session.update("board.updateRC", map);
		session.close();
		
		return r;
	}
	
	public static int updateDC(HashMap map) {
		
		SqlSession session = factory.openSession(true);
		int r = session.update("board.updateDC", map);
		session.close();
		
		return r;
	}
	
	public static int updateRP(HashMap map) {
		
		SqlSession session = factory.openSession(true);
		int r = session.update("board.updateRP", map);
		session.close();
		
		return r;
	}

	public static List<BoardVo> listAdminBoard() {

		SqlSession session = factory.openSession();
		List<BoardVo> list = session.selectList("board.listAdminBoard");
		session.close();
		
		return list;
	}

	public static int resetReport(HashMap map) {
		
		SqlSession session = factory.openSession(true);
		int r = session.update("board.resetReport", map);
		session.close();
		
		return r;
	}

}
