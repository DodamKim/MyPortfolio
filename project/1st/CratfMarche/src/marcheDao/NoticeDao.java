package marcheDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import marcheDb.ConnectionProvider;
import marcheVo.NoticeVo;

public class NoticeDao {
	
	//모든 공지사항 상세내역 출력
	public NoticeVo getNotice(int nno) {
		
		NoticeVo vo = new NoticeVo();
		String sql = "select n.nno, ntitle, to_char(ndate,'yyyy/mm/dd'), ntext, mno "
				+ "from notice n, ndetail d where n.nno=d.nno and n.nno=?";
		
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, nno);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				vo.setNno(rs.getInt(1));
				vo.setNtitle(rs.getString(2));
				vo.setNdate(rs.getString(3));
				vo.setNtext(rs.getString(4));
				vo.setMno(rs.getInt(5));
			}
			
			ConnectionProvider.close(conn, pstmt, rs);
			
		}catch (Exception e) {
			System.out.println("예외:getNotice() :"+e.getMessage());
		}
		
		return vo;
		
	}
	
	
	//모든 공지사항 내역 출력
	public ArrayList<NoticeVo> listNotice() {
		
		ArrayList<NoticeVo> list = new ArrayList<NoticeVo>();
		String sql = "select n.nno, ntitle, to_char(ndate,'yyyy/mm/dd'), ntext, mno "
				+ "from notice n, ndetail d where n.nno=d.nno order by ndate desc";
		try {
			Connection conn = ConnectionProvider.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				NoticeVo vo = new NoticeVo();
				vo.setNno(rs.getInt(1));
				vo.setNtitle(rs.getString(2));
				vo.setNdate(rs.getString(3));
				vo.setNtext(rs.getString(4));
				vo.setMno(rs.getInt(5));
				
				list.add(vo);
			}
			
			ConnectionProvider.close(conn, stmt, rs);
			
		}catch (Exception e) {
			System.out.println("예외:listNotice() :"+e.getMessage());
		}
		
		return list;
	}
	
	
	//공지사항 삭제 메소드
	//공지사항 삭제시 해당 공지사항상세 테이블 삭제 트리거 발동
	public int deleteNotice(int nno) {
		
		int r = 0;
		String sql = "delete notice where nno=?";
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, nno);
			r = pstmt.executeUpdate();
			
			ConnectionProvider.close(conn, pstmt, null);
			
		}catch (Exception e) {
			System.out.println("예외:deleteNotice() :"+e.getMessage());
		}
		
		return r;
	}
	
	
	//공지사항 수정 메소드
	public int updateNotice(NoticeVo vo) {
		
		int r = 0;
		String sqlNotice = "update notice set ntitle=? where nno=?";
		String sqlNdetail = "update ndetail set ntext=? where nno=?";
		try {
			Connection conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmtNotice = conn.prepareStatement(sqlNotice);
			pstmtNotice.setString(1, vo.getNtitle());
			pstmtNotice.setInt(2, vo.getNno());
			r = pstmtNotice.executeUpdate();
			
			PreparedStatement pstmtNdetail = conn.prepareStatement(sqlNdetail);
			pstmtNdetail.setString(1, vo.getNtext());
			pstmtNdetail.setInt(2, vo.getNno());
			r += pstmtNdetail.executeUpdate();
			
			if(r==2) {
				conn.commit();
				
			}else {
				conn.rollback();
			}
			ConnectionProvider.close(null, pstmtNotice, null);
			ConnectionProvider.close(conn, pstmtNdetail, null);
			
		}catch (Exception e) {
			System.out.println("예외:updateNotice() :"+e.getMessage());
		}
		
		return r;
		
	}
	
	
	//새글 등록시 필요한 nno생성 메소드
	public int makeNno() {
		
		int nno = 0;
		String sql = "select nvl(max(nno),0)+1 from notice";
		try {
			Connection conn = ConnectionProvider.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				nno = rs.getInt(1);
			}
			
			ConnectionProvider.close(conn, stmt, rs);
			
		}catch (Exception e) {
			System.out.println("예외:makeNno() :"+e.getMessage());
		}
		
		return nno;
		
	}
	
	
	//공지사항 새글 등록 메소드
	//실패 0, 성공 2 반환
	public int insertNotice(NoticeVo vo) {
		
		int nno = makeNno();
		int r = 0;
		String sqlNotice = "insert into notice values(?, ?, sysdate, ?)";
		String sqlNdetail = "insert into ndetail values(?, ?)";
		try {
			Connection conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmtNotice = conn.prepareStatement(sqlNotice);
			pstmtNotice.setInt(1, nno);
			pstmtNotice.setString(2, vo.getNtitle());
			pstmtNotice.setInt(3, vo.getMno());
			r = pstmtNotice.executeUpdate();
			
			PreparedStatement pstmtNdetail = conn.prepareStatement(sqlNdetail);
			pstmtNdetail.setInt(1, nno);
			pstmtNdetail.setString(2, vo.getNtext());
			r += pstmtNdetail.executeUpdate();
			
			if(r==2) {
				conn.commit();
				
			}else {
				conn.rollback();
			}
			ConnectionProvider.close(null, pstmtNotice, null);
			ConnectionProvider.close(conn, pstmtNdetail, null);
			
		}catch (Exception e) {
			System.out.println("예외:insertNotice() :"+e.getMessage());
		}
		
		return r;
		
	}

}
