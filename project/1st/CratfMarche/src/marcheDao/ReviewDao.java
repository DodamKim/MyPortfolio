package marcheDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import marcheDb.ConnectionProvider;
import marcheVo.ReviewVo;

public class ReviewDao {
	
	public ArrayList<ArrayList<String>> getMyReview(int mno){
		
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		
		// rno, rtext, rdate, score
		String sqlReview = "select rno, rtext, to_char(rdate,'yyyy/mm/dd'), "
				+ "score from review where rno in " + 
				"(select rno from review where odno in " + 
				"(select odno from odetail where ono in " + 
				"(select ono from orders where mno =?" + 
				")))";
		
		// nickname, iname
		String sqlSeller = "select nickname, iname from member m, " + 
				"(select iname, mno from item where ino in " + 
				"(select ino from odetail where odno in " + 
				"(select odno from odetail where ono in " + 
				"(select ono from orders where mno =?" + 
				")))) v where m.mno = v.mno";		
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmtReview = conn.prepareStatement(sqlReview);
			PreparedStatement pstmtSeller = conn.prepareStatement(sqlSeller);
			pstmtReview.setInt(1, mno);
			pstmtSeller.setInt(1, mno);
			ResultSet rsReview = pstmtReview.executeQuery();
			ResultSet rsSeller = pstmtSeller.executeQuery();
			
			// rno, rtext, rdate, score
			// nickname, iname
			while(rsReview.next() && rsSeller.next()) {
				
				ArrayList<String> myReview = new ArrayList<String>();
				myReview.add(rsReview.getInt(1)+"");
				myReview.add(rsReview.getString(2));
				myReview.add(rsReview.getString(3));
				myReview.add(rsReview.getInt(4)+"");
				myReview.add(rsSeller.getString(1));
				myReview.add(rsSeller.getString(2));
				
				list.add(myReview);
			}
			ConnectionProvider.close(null, pstmtSeller, rsSeller);
			ConnectionProvider.close(conn, pstmtReview, rsReview);
			
		}catch (Exception e) {
			System.out.println("getMyReview() 예외 : "+e.getMessage());
			// TODO: handle exception
		}
		return list;
		
	}
	
	
		// 리뷰글의 총 갯수 출력을 위한 메소드
		// odno를 받음
		public int countReview(int ino) {
			int c = -1;
			
			String sql = "select count(*) from review where odno in "
					+ "( select odno from odetail where ino = ?)";
			try {
				Connection conn = ConnectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, ino);
				ResultSet rs = pstmt.executeQuery();
				if(rs.next()) {
					c = rs.getInt(1);
				}
				ConnectionProvider.close(conn, pstmt, rs);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("countReview() 예외 : "+e.getMessage());
			}
			return c;
		}
		
	
	//해당 상품구매건의 리뷰 작성여부 판단 메소드
	public boolean checkReview(int odno) {
		
		boolean flag = false;
		String sql = "select rno from review where odno=?";
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, odno);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				flag = true;
			}
			ConnectionProvider.close(conn, pstmt, rs);
			
		}catch (Exception e) {
			System.out.println("예외:checkReview() :"+e.getMessage());
		}
		
		return flag;
		
	}
	
	
	//상품 페이지 : 해당 상품의 리뷰 모두 출력(ino - 상품번호)
	//여러 테이블의 정보 함께 담기때문에, vo로 포장 불가하여 
	//ArrayList안의 ArrayList에 건 별로 리뷰를 담음
	public ArrayList<ArrayList<String>> getReview(int ino){
		
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		//글번호, 작성자, 별점, 작성일, 내용
//		String sql = "select rno, (select name from member where mno in " + 
//				"(select mno from orders where ono in " + 
//				"(select ono from odetail where odno in (select odno from review " + 
//				"where odno in ( select odno from odetail where ino = ?))))) "
//				+ "name, score, rdate, rtext from review " + 
//				"where odno in ( select odno from odetail where ino = ?)";
		
		String sql = "select rno, m.nickname, r.score, rdate, rtext from review r, member m, orders o, "
				+ "odetail od, item i where m.mno = o.mno and o.ono = od.ono and i.ino = od.ino "
				+ "and od.odno = r.odno and i.ino = ? order by rno desc";
		
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, ino);
//			pstmt.setInt(2, ino);
			
			//rno, b.name, score, rdate, rtext
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				ArrayList<String> reviewInfo = new ArrayList<String>();
				reviewInfo.add(rs.getInt(1)+"");
				reviewInfo.add(rs.getString(2));
				reviewInfo.add(rs.getInt(3)+"");
				reviewInfo.add(rs.getString(4));
				reviewInfo.add(rs.getString(5));
				
				list.add(reviewInfo);
			}
			ConnectionProvider.close(conn, pstmt, rs);
			
		}catch (Exception e) {
			System.out.println("예외:getReview() :"+e.getMessage());
		}
		
		return list;
		
	}
	
	
	//리뷰등록 메소드
	public int insertReview(ReviewVo vo) {
		
		int r = 0;
		String sql = "insert into review values(seq_review.nextval, ?, sysdate, ?, ?)";
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getScore());
			pstmt.setString(2, vo.getRtext());
			pstmt.setInt(3, vo.getOdno());
			r = pstmt.executeUpdate();
			
			ConnectionProvider.close(conn, pstmt, null);
			
		}catch (Exception e) {
			System.out.println("예외:insertReview() :"+e.getMessage());
		}
		
		return r;
		
	}
	
}
