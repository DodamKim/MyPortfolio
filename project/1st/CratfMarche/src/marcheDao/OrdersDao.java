package marcheDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import marcheDb.ConnectionProvider;
import marcheVo.OdetailVo;
import marcheVo.OrdersVo;

public class OrdersDao {
	
	
	//상품번호로 상품구분(유형,무형)을 알아내는 메소드
		public String ilvSelect(int ino) {
			String lv = "";
			String sql = "select ilv from item where ino = ?";
			
			try {
				Connection conn = ConnectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, ino);
				ResultSet rs = pstmt.executeQuery();
				
				if(rs.next()) {
					lv = rs.getString(1);
				}
				
				ConnectionProvider.close(conn, pstmt, rs);
				
			}catch (Exception e) {
				System.out.println("예외:ilvSelect() :"+e.getMessage());
			}

			return lv;
		}
	
	
	//주문삭제 메소드(해당 주문번호 매개변수로 받음)
	//같은 번호의 주문상세 트리거 발동(자식테이블)
	public int deleteOrder(int ono) {
		
		int r = 0;
		String sql = "delete orders where ono=?";
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ono);
			r = pstmt.executeUpdate();
			
			ConnectionProvider.close(conn, pstmt, null);
			
		}catch (Exception e) {
			System.out.println("예외:deleteOrder() :"+e.getMessage());
		}
		
		return r;
		
	}
	
	
	//마이페이지 내 주문(orders 묶음주문)목록 - 고객번호 
	public ArrayList<OrdersVo> getMyOrders(int mno){
		
		ArrayList<OrdersVo> list = new ArrayList<OrdersVo>();
		String sql = "select * from orders where mno=?";
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mno);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				OrdersVo vo = new OrdersVo();
				vo.setOno(rs.getInt(1));
				vo.setTotal(rs.getInt(2));
				vo.setOdate(rs.getString(3));
				vo.setCoc(rs.getString(4));
				vo.setMno(mno);
				vo.setOname(rs.getString(6));
				vo.setOtel(rs.getString(7));
				vo.setOaddr(rs.getString(8));
				
				list.add(vo);
				
			}
			
			ConnectionProvider.close(conn, pstmt, rs);
			
		}catch (Exception e) {
			System.out.println("예외:getMyOrders() :"+e.getMessage());
		}
		
		return list;
	}
	
	
	//주문번호 생성 메소드
	public int makeOno() {
			
		int ono = 0;
		String sql = "select nvl(max(ono),0)+1 from orders";
		try {
			Connection conn = ConnectionProvider.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
				
			if(rs.next()) {
				ono = rs.getInt(1);
			}
			
			ConnectionProvider.close(conn, stmt, rs);
			
			}catch (Exception e) {
				System.out.println("예외:makeOno() :"+e.getMessage());
			}
			
			return ono;
			
		}
	
	
	//주문시 주문, 주문상세 테이블 insert 메소드
	public int insertOrders(OrdersVo ovo, ArrayList<OdetailVo> list) {
		
		int ono = makeOno();
		int r = 0;
		String sqlOrders = "insert into orders values(?, ?, sysdate, ?, ?, ?, ?, ?)";
		String sqlOdetail = "insert into odetail values(seq_odetail.nextval, ?, ?, ?, ?, '주문완료', '0')";
		try {
			Connection conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmtOrders = conn.prepareStatement(sqlOrders);
			pstmtOrders.setInt(1, ono);
			pstmtOrders.setInt(2, ovo.getTotal());
			pstmtOrders.setString(3, ovo.getCoc());
			pstmtOrders.setInt(4, ovo.getMno());
			pstmtOrders.setString(5, ovo.getOname());
			pstmtOrders.setString(6, ovo.getOtel());
			pstmtOrders.setString(7, ovo.getOaddr());
			r = pstmtOrders.executeUpdate();
			
			PreparedStatement pstmtOdetail = conn.prepareStatement(sqlOdetail);
			
			for(OdetailVo vo : list) {
				
				pstmtOdetail.setInt(1, vo.getOdnum());
				pstmtOdetail.setInt(2, ono);
				pstmtOdetail.setInt(3, vo.getIno());
				pstmtOdetail.setInt(4, vo.getOdprice());
				
				r += pstmtOdetail.executeUpdate();
				
			}
			
			if(r == list.size()+1) {
				conn.commit();
				
			}else {
				conn.rollback();
			}
			
			ConnectionProvider.close(null, pstmtOrders, null);
			ConnectionProvider.close(conn, pstmtOdetail, null);
			
		}catch (Exception e) {
			System.out.println("예외:insertOrders() :"+e.getMessage());
		}
		return r;
		
	}

}
