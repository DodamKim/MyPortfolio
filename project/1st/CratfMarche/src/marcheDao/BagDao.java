package marcheDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import marcheDb.ConnectionProvider;
import marcheVo.BagVo;

public class BagDao {
	
	
	//특정회원의 장바구니 목록 출력(해당 회원의 회원번호 매개변수로 받음)
	public ArrayList<ArrayList<String>> getBag(int mno){
		
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		String sql = "select s.nickname, s.iname, bagnum, iprice*bagnum, s.ino, bagno "
				+ "from bag b, sellerview s where b.ino=s.ino and b.mno=?";
		
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mno);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				
				ArrayList<String> mybag = new ArrayList<String>();
				mybag.add(rs.getString(1));
				mybag.add(rs.getString(2));
				mybag.add(rs.getInt(3)+"");
				mybag.add(rs.getInt(4)+""); //장바구니 합계 계산하여 select 함
				mybag.add(rs.getInt(5)+"");
				mybag.add(rs.getInt(6)+"");
				
				list.add(mybag);
				
			}
			ConnectionProvider.close(conn, pstmt, rs);
			
		}catch (Exception e) {
			System.out.println("예외:getBag() :"+e.getMessage());
		}
		
		return list;
		
	}
	
	
	//장바구니 추가 sql
	public int insertBag(BagVo vo) {
		
		int r = 0;
		String sql = "insert into bag values(seq_bag.nextval, ?, ?, ?)";
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getBagnum());
			pstmt.setInt(2, vo.getMno());
			pstmt.setInt(3, vo.getIno());
			r = pstmt.executeUpdate();
			
			ConnectionProvider.close(conn, pstmt, null);
			
		}catch (Exception e) {
			System.out.println("예외:insertBag() :"+e.getMessage());
		}
		
		return r;
	}
	
	
	// 장바구니 안에서 1개의 상품삭제
	public int getDelete(int bagNo) {
		int re = 0;
		String sql = "delete bag where bagno=?";

		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bagNo);
			re = pstmt.executeUpdate();

			ConnectionProvider.close(conn, pstmt, null);

		} catch (Exception e) {
			System.out.println("예외:getDelete :" + e.getMessage());
		}

		return re;
	}
		
		
		// 장바구니 비우기
		public int allDelete(int mno) {
			int re = 0;
			String sql = "delete bag where mno=?";

			try {
				Connection conn = ConnectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, mno);
				re = pstmt.executeUpdate();
	            
				ConnectionProvider.close(conn, pstmt, null);
				
			}catch (Exception e) {
				System.out.println("예외:allDelete :"+e.getMessage());
			}
			
			return re;
		}
	
}
