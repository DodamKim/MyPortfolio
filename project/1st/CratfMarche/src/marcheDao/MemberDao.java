package marcheDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import marcheDb.ConnectionProvider;
import marcheVo.MemberVo;

public class MemberDao {
	
	
	//고객의 등급 가져오는 메소드
	public int checkMemberLv(int mno) {
		
		int lv = 0;
		String sql = "select lv from member where mno=?";
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mno);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				lv = rs.getInt(1);
			}
			ConnectionProvider.close(conn, pstmt, rs);
			
		}catch (Exception e) {
			System.out.println("예외:getMyLv() :"+e.getMessage());
		}
		
		return lv;
		
	}
	
	
//	//로그인한 고객의 id 가져오는 메소드
//		public String getMyid(int mno) {
//			
//			String id="";
//			String sql = "select id from member where mno=?";
//			try {
//				Connection conn = ConnectionProvider.getConnection();
//				PreparedStatement pstmt = conn.prepareStatement(sql);
//				pstmt.setInt(1, mno);
//				ResultSet rs = pstmt.executeQuery();
//				
//				if(rs.next()) {
//					id = rs.getString(1);
//				}
//				
//				ConnectionProvider.close(conn, pstmt, rs);
//				
//			}catch(Exception e) {
//				System.out.println("예외:getMyMno() :"+e.getMessage());
//			}
//			
//			return id;
//		}
		
	
	//로그인한 고객의 고객번호 가져오는 메소드
	public int getMyMno(String id) {
		
		int r = 0;
		String sql = "select mno from member where id=?";
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				r = rs.getInt(1);
			}
			
			ConnectionProvider.close(conn, pstmt, rs);
			
		}catch(Exception e) {
			System.out.println("예외:getMyMno() :"+e.getMessage());
		}
		
		return r;
	}
	
	//상품 매출순위
	public ArrayList<ArrayList<String>> getItemRank(){
		
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		String sql = "select i.ino, iname, sum from (select sum(odprice) sum, o.ino " + 
				"from odetail o, item i where o.ino=i.ino " + 
				"group by o.ino order by sum desc) A, "
				+ "item i where i.ino = A.ino and rownum <=5";
		try {
			Connection conn = ConnectionProvider.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()) {
				ArrayList<String> ranker = new ArrayList<String>();
				ranker.add(rs.getInt(1)+"");
				ranker.add(rs.getString(2));
				ranker.add(rs.getInt(3)+"");
				
				list.add(ranker);
			}
			ConnectionProvider.close(conn, stmt, rs);
			
		}catch (Exception e) {
			System.out.println("예외:getItemRank() :"+e.getMessage());
		}
		
		return list;
	}
	
	
	//판매자 매출순위
	public ArrayList<ArrayList<String>> getSellerRank(){
		
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		String sql = "select m.mno, nickname, sum from " + 
				"(select sum(odprice) sum, mno from odetail o, item i " + 
				"where o.ino=i.ino group by mno order by sum desc)A, " + 
				"member m where m.mno=A.mno and rownum <=5";
		try {
			Connection conn = ConnectionProvider.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				ArrayList<String> ranker = new ArrayList<String>();
				ranker.add(rs.getInt(1)+"");
				ranker.add(rs.getString(2));
				ranker.add(rs.getInt(3)+"");
				
				list.add(ranker);
			}
			ConnectionProvider.close(conn, stmt, rs);
			
		}catch (Exception e) {
			System.out.println("예외:getSellerRank() :"+e.getMessage());
		}
		
		return list;
	}
	
	
	//회원정보 수정 메소드
	//1.마이페이지 - 로그인 고객의 mno 사용 
	//2.판매자페이지 - 선택한 테이블의 줄에 있는 고객의 정보 사용
	public int updateMember(MemberVo vo, int abc) {
		
		int r = 0;
		
		String sql = "update member set nickname=?, tel=?, addr=?, email=?, account=? where mno=? and pw=?";
		
		if(abc == 1) {
			sql = "update member set id=?, name=?, birth=? where mno=?";
		}
		
		
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			if( abc != 1 ) {
				pstmt.setString(1, vo.getNickname());
				pstmt.setString(2, vo.getTel());
				pstmt.setString(3, vo.getAddr());
				pstmt.setString(4, vo.getEmail());
				pstmt.setString(5, vo.getAccount());
				pstmt.setInt(6, vo.getMno());
				pstmt.setString(7, vo.getPw());
				
			}else {
				pstmt.setString(1, vo.getId());
				pstmt.setString(2, vo.getName());
				pstmt.setString(3, vo.getBirth());
				pstmt.setInt(4, vo.getMno());
			}
			
			r = pstmt.executeUpdate();
			System.out.println(sql);
			
			ConnectionProvider.close(conn, pstmt, null);
			
		}catch(Exception e) {
			System.out.println("예외:updateMember() :"+e.getMessage());
		}
		
		return r;
		
	}
	
	
	//판매자 페이지 : 현재 판매중인 상품의 수
	public int getAllItemNum() {
		
		int r = 0;
		String sql = "select count(ino) from item where condition = 1";
		try {
			Connection conn = ConnectionProvider.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				r = rs.getInt(1);
			}
			
			ConnectionProvider.close(conn, stmt, rs);
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return r;
		
	}
	
	
	//판매자 페이지 : 전체 매출 반환(orders 테이블 이용하지만, 판매자 페이지 정보로 함께 있는것이 편할것 같아서)
	public int getAllSales() {

		int r = 0;
		String sql = "select sum(total) from orders";
		try {
			Connection conn = ConnectionProvider.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				r = rs.getInt(1);
			}
			
			ConnectionProvider.close(conn, stmt, rs);
			
		}catch (Exception e) {
			System.out.println("예외:getAllSales() :"+e.getMessage());
		}
		
		return r;
		
	}
	
	
	//판매자 페이지 : 전체 판매자수 반환 메소드
	public int getSellerNum() {

		int r = 0;
		String sql = "select count(mno) from member where lv = 2";
		try {
			Connection conn = ConnectionProvider.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				r = rs.getInt(1);
			}
			
			ConnectionProvider.close(conn, stmt, rs);
			
		}catch (Exception e) {
			System.out.println("예외:getSellerNum() :"+e.getMessage());
		}
		
		return r;
		
	}
	
	
	//판매자 페이지 : 전체 회원수 반환 메소드
	public int getAllMemberNum() {
		
		int r = 0;
		String sql = "select count(mno) from member where lv != 1";
		try {
			Connection conn = ConnectionProvider.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				r = rs.getInt(1);
			}
			
			ConnectionProvider.close(conn, stmt, rs);
			
		}catch (Exception e) {
			System.out.println("예외:getAllMemberNum() :"+e.getMessage());
		}
		
		return r;
		
	}
	
	
	//예비판매자 -> 판매자 승인 업데이트
	public int updateSeller(int mno) {
		
		int r = 0;
		String sql = "update member set lv=2 where mno=?";
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mno);
			r = pstmt.executeUpdate();
			
			ConnectionProvider.getConnection();
			
		}catch (Exception e) {
			System.out.println("예외:updateSeller() :"+e.getMessage());
		}
		
		return r;
		
	}
	
	
	//셀러신청 버튼 누르면, 판매자 대기 상태로 업데이트
	public int updateApplySeller(int mno) {
		
		int r = 0;
		String sql = "update member set lv=4 where mno=?";
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mno);
			r = pstmt.executeUpdate();
			
			ConnectionProvider.close(conn, pstmt, null);
			
		}catch (Exception e) {
			System.out.println("예외:updateApplySeller() :"+e.getMessage());
		}
		
		return r;
		
	}
	
	
	// 판매자 페이지 : 모든 회원의 정보 출력(매개변수 :0)
	// 판매자 페이지 : 모든 예비판매자 회원 정보 출력(매개변수 :4)
	// 판매자 페이지 : 모든 판매자 회원 정보 출력(매개변수 :2)
	public ArrayList<MemberVo> listMember(int lv){
		
		ArrayList<MemberVo> list = new ArrayList<MemberVo>();
		String sql = "select mno, id, name, nickname, tel, to_char(birth, 'yyyy/mm/dd'), addr, email, lv "
				+ "from member ";
		
		if(lv == 4) {
			sql += "where lv = 4"; //예비판매자만 출력
		}
		
		else if(lv == 2) {
			sql += "where lv = 2"; //판매자만 출력
		}
		
		try {
			Connection conn = ConnectionProvider.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				MemberVo vo = new MemberVo();
				vo.setMno(rs.getInt(1));
				vo.setId(rs.getString(2));
				vo.setName(rs.getString(3));
				vo.setNickname(rs.getString(4));
				vo.setTel(rs.getString(5));
				vo.setBirth(rs.getString(6));
				vo.setAddr(rs.getString(7));
				vo.setEmail(rs.getString(8));
				vo.setLv(rs.getInt(9));
				
				list.add(vo);
			}
			
			ConnectionProvider.close(conn, stmt, rs);
			
		}catch (Exception e) {
			System.out.println("예외:listMember() :"+e.getMessage());
		}
		
		return list;
		
	}
	
	
	//마이페이지 내 정보 출력 메소드
	//내 정보 출력시 해당 고객의 mmo는 변수에 담아두기!(수정등에서 필요)
	public MemberVo getMember(int mno) {
		
		MemberVo vo = new MemberVo();
		String sql = "select mno, id, pw, name, nickname, tel, to_char(birth,'yyyy/mm/dd'), addr,"
				+ " email, account, lv from member where mno=?";
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mno);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				vo.setMno(mno);
				vo.setId(rs.getString(2));
				vo.setPw(rs.getString(3));
				vo.setName(rs.getString(4));
				vo.setNickname(rs.getString(5));
				vo.setTel(rs.getString(6));
				vo.setBirth(rs.getString(7));
				vo.setAddr(rs.getString(8));
				vo.setEmail(rs.getString(9));
				vo.setAccount(rs.getString(10));
				vo.setLv(rs.getInt(11));
				
			}
			
			//System.out.println("다오 레벨 :"+vo.getLv());
			ConnectionProvider.close(conn, pstmt, rs);
			
			
		}catch (Exception e) {
			System.out.println("예외:getMember() :"+e.getMessage());
		}
		
		return vo;
	}
	
	
	//비밀번호 변경 메소드
	//isMyPw 값이 true일 경우, 비밀번호 변경 메소드
	//밑에서 입력 받은 id값 변수에 담아 저장해두고 다시 전달받기, 새로 변경할 pw도 받음
	public int updateNewPw(String id, String pw) {
		
		int r = 0;
		String sql = "update member set pw=? where id=?";
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pw);
			pstmt.setString(2, id);
			r = pstmt.executeUpdate();
			
			ConnectionProvider.close(conn, pstmt, null);
			
		}catch(Exception e) {
			System.out.println("예외:updateNewPw() :"+e.getMessage());
		}
		
		return r;
		
	}
	
	
	//패스워드 찾기 메소드
	//입력한 정보가 맞으면 true, 틀리면 false 반환
	public boolean isMyPw(String id, String qns, String anw) {
		 
		 boolean flag = false;
		 String sql = "select mno from member where id=? and qns=? and anw=?";
		 
		 try {
			 Connection conn = ConnectionProvider.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql);
			 pstmt.setString(1, id);
			 pstmt.setString(2, qns);
			 pstmt.setString(3, anw);
			 ResultSet rs = pstmt.executeQuery();
			 
			 if(rs.next()) {
				 flag = true;
			 }
			 
			 ConnectionProvider.close(conn, pstmt, rs);
			 
		 }catch (Exception e) {
			 System.out.println("예외:isMyPw() :"+e.getMessage());
		 }
		 
		 return flag;
		 
	 }
	
	
	//id찾기 메소드
	//성공시 해당 회원의 id, 실패시 null 반환
	public String isMyId(String birth, String email) {
		
		String id = null;
		String sql = "select id from member where birth=? and email=?";
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, birth);
			pstmt.setString(2, email);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				id = rs.getString(1);
			}
			
			ConnectionProvider.close(conn, pstmt, rs);
			
		}catch (Exception e) {
			System.out.println("예외:isMyId() :"+e.getMessage());
		}
		
		return id;
		
	}
	
	
	//로그인, 아이디-비밀번호 입력값 확인 메소드
	//성공시 true, 실패시 false반환
	//mmo 의미없음, 하나만 출력해보는 편이 효율적일 것 같아서
	public boolean isMember(String id, String pw) {
		
		boolean flag = false;
		String sql = "select mno from member where id=? and pw=?";
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				flag = true;
			}
			
			ConnectionProvider.close(conn, pstmt, rs);
			
		}catch (Exception e) {
			System.out.println("예외:isMember() :"+e.getMessage());
		}
		
		return flag;
		
	}

	
	//회원가입을 위한 메소드
	//닉네임 포함, mmo 시퀀스, 회원등급 초기값 3(구매자), 계좌번호 초기값'미등록'
	public int insertMember(MemberVo vo) {
		
		int r = 0;
		String sql = "insert into member values(seq_member.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, '미등록', 3)";
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPw());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getNickname());
			pstmt.setString(5, vo.getTel());
			pstmt.setString(6, vo.getBirth());
			pstmt.setString(7, vo.getAddr());
			pstmt.setString(8, vo.getQns());
			pstmt.setString(9, vo.getAnw());
			pstmt.setString(10, vo.getEmail());
			
			r = pstmt.executeUpdate();
			
			ConnectionProvider.close(conn, pstmt, null);
			
			
		}catch(Exception e) {
			System.out.println("예외:insertMember() :"+e.getMessage());
		}
		
		return r;
		
	}
	
}
