package marcheDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import marcheDb.ConnectionProvider;
import marcheVo.BoardVo;

public class BoardDao {
	
	public int deleteBoard(int bno) {
		
		int r = 0;
		String sql = "delete board where bno=?";
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			r = pstmt.executeUpdate();
			
			ConnectionProvider.close(conn, pstmt, null);
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return r;
		
	}
	
	
	public int updateBoard(BoardVo vo) {
		
		int r = 0;
		String sqlBoard = "update board set btitle=?, bloc=?, bdate=?, btime=?, blv=?, bimg=? where bno=?";
		String sqlDetail = "update bdetail set btext=? where bno=?";
		// 2. 제목, 3. 행사 지역, 4. 행사일, 5. 운영 시간,  8. 게시판 구분, 9. 이미지 // 1. 게시판 번호
		// 수정사항 이름/지역/행사일/운영시간/구분/이미지/상세내용
		try {
			Connection conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			PreparedStatement pstmtBoard = conn.prepareStatement(sqlBoard);
			pstmtBoard.setString(1, vo.getBtitle());
			pstmtBoard.setString(2, vo.getBloc());
			pstmtBoard.setString(3, vo.getBdate());
			pstmtBoard.setString(4, vo.getBtime());
			pstmtBoard.setInt(5, vo.getBlv());
			pstmtBoard.setString(6, vo.getBimg());
			pstmtBoard.setInt(7, vo.getBno());
			r = pstmtBoard.executeUpdate();
			
			PreparedStatement pstmtDetail = conn.prepareStatement(sqlDetail);
			pstmtDetail.setString(1, vo.getBtext());
			pstmtDetail.setInt(2, vo.getBno());
			r += pstmtDetail.executeUpdate();
			
			if(r==2) {
				conn.commit();
				
			}else {
				conn.rollback();
			}
			
			ConnectionProvider.close(null, pstmtDetail);
			ConnectionProvider.close(conn, pstmtBoard);
			
		}catch (Exception e) {
			System.out.println("예외:updateBoard() :"+e.getMessage());
		}
		
		return r;
	}
	
	
	public ArrayList<BoardVo> listBoard(int blv, String bloc) {
		ArrayList<BoardVo> list = new ArrayList<BoardVo>();
		String sql ="select bno, btitle, bloc, bdate from board ";
		
		try {
		
			if(blv == 0) {
				sql += "where blv=0 ";
			} else if(blv == 1) {
				sql += "where blv=1 ";
			} 
			
			if(bloc != null) {
				
				if(bloc.equals("모든지역")) {
					sql = sql;
				}
				else if(sql.indexOf("where") == -1) {
					sql += "where ";
					sql += "bloc like '%"+bloc+"%'";
					
				}else {
					sql += "and ";
					sql += "bloc like '%"+bloc+"%'";
				}
				
			}
			
			Connection conn = ConnectionProvider.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				BoardVo vo = new BoardVo();
				
				vo.setBno(rs.getInt(1));
				vo.setBtitle(rs.getString(2));
				vo.setBloc(rs.getString(3));
				vo.setBdate(rs.getString(4));
				
				list.add(vo);
			}
			
			ConnectionProvider.close(conn, stmt, rs);
		
		}catch (Exception e) {
			System.out.println("예외:listBoard() :"+e.getMessage());
		}
		
		return list;
	}
	
	
	// 주문번호 생성 메소드
	public int makeBno() {
		
		int bno = 0;
		String sql = "select nvl(max(bno),0)+1 from board";
		try {
			Connection conn = ConnectionProvider.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				bno = rs.getInt(1);
			}
			
			ConnectionProvider.close(conn, stmt, rs);

		} catch (Exception e) {
			System.out.println("예외:makeBno() :" + e.getMessage());
		}

		return bno;
		
	}
	
	
	public int insetBoard(BoardVo vo) {
		
		int bno = makeBno();
		String sqlBoard = "insert into board values(?, ?, ?, ?, ?, 0, 1, ?, ?)";
		String sqlDetail = "insert into bdetail values(?, ?)";
		// 1. 게시판 번호, 2. 제목, 3. 행사 지역, 4. 행사일, 5. 운영 시간, 6. 조회수, 7. 관리자 번호, 8. 게시판 구분, 9. 이미지
		int r = 0;

		try {
			Connection conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmtBoard = conn.prepareStatement(sqlBoard);
			PreparedStatement pstmtDetail = conn.prepareStatement(sqlDetail);
			
			pstmtBoard.setInt(1, bno);
			pstmtBoard.setString(2, vo.getBtitle()); // 제목
			pstmtBoard.setString(3, vo.getBloc()); // 행사 지역
			pstmtBoard.setString(4, vo.getBdate()); // 행사일
			pstmtBoard.setString(5, vo.getBtime()); // 운영 시간
			pstmtBoard.setInt(6, vo.getBlv()); // 게시판 구분
			pstmtBoard.setString(7, vo.getBimg()); // 이미지
			
			r = pstmtBoard.executeUpdate();

			pstmtDetail.setInt(1, bno);
			pstmtDetail.setString(2, vo.getBtext());
			
			r += pstmtDetail.executeUpdate();
			
			if( r == 2) {
				conn.commit();
			}else {
				conn.rollback();
			}
			
			ConnectionProvider.close(null, pstmtDetail);
			ConnectionProvider.close(conn, pstmtBoard);

			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return r;
	}

	
	public BoardVo getBoard(int bno) {

		BoardVo vo = new BoardVo();

		String sql = "select * from board where bno = ?";
		String sqlDetail = "select btext from bdetail where bno=?";

		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			PreparedStatement pstmtDetail = conn.prepareStatement(sqlDetail);
			pstmt.setInt(1, bno);
			pstmtDetail.setInt(1, bno);

			ResultSet rs = pstmt.executeQuery();
			ResultSet rsDetail = pstmtDetail.executeQuery();

			if (rs.next()) {
				
				vo.setBno(rs.getInt(1));
				vo.setBtitle(rs.getString(2));
				vo.setBloc(rs.getString(3));
				vo.setBdate(rs.getString(4));
				vo.setBtime(rs.getString(5));
				vo.setBhit(rs.getInt(6));
				vo.setMno(rs.getInt(7));
				vo.setBlv(rs.getInt(8));
				vo.setBimg(rs.getString(9));

			}

			if (rsDetail.next()) {
				vo.setBtext(rsDetail.getString(1));
			}

			ConnectionProvider.close(null, pstmtDetail, null);
			ConnectionProvider.close(conn, pstmt, rs);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return vo;
	}
}