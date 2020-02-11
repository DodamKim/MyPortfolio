package marcheDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import marcheDb.ConnectionProvider;
import marcheVo.OdetailVo;

public class OdetailDao {

	
	// 주문상세 번호를 매개변수로 받아 정보를 받아오는 메소드(판매자 기준의 주문관리에서 사용)
	public ArrayList<String> getOdetail(int odno) {
		ArrayList<String> list = new ArrayList<String>();
		String sql = "select odno, name, iname, odnum, odprice, odate, oname, oaddr, otel, ship "
				+ "from buyview v, item i, odetail od where od.ino=i.ino "
				+ "and v.ono=od.ono and odno=?";
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, odno);
			ResultSet rs = pstmt.executeQuery();
			// odno,name,iname,odnum, odprice, odate, oname, oaddr, otel, ship
			// 1 2 3 4 5 6 7 8 9 10
			while (rs.next()) {
				list.add(rs.getInt(1) + "");
				list.add(rs.getString(2));
				list.add(rs.getString(3));
				list.add(rs.getInt(4) + "");
				list.add(rs.getInt(5) + "");
				list.add(rs.getString(6));
				list.add(rs.getString(7));
				list.add(rs.getString(8));
				list.add(rs.getString(9));
				list.add(rs.getString(10) + "");

			}
			ConnectionProvider.close(conn, pstmt, rs);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("예외:getOdetail()" + e.getMessage());
		}
		return list;
	}

	
	// 주문상세건 삭제시 주문테이블의 총가격 변환 메소드
	public int changeTotal(int price, int ono) {
		
		int r = 0;
		String sql = "update orders set total=total-? where ono=?";
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmtTotal = conn.prepareStatement(sql);
			pstmtTotal.setInt(1, price);
			pstmtTotal.setInt(2, ono);
			
			r = pstmtTotal.executeUpdate();
			
			ConnectionProvider.close(conn, pstmtTotal);
			
		}catch (Exception e) {
			System.out.println("예외:changeTotal() :" +e.getMessage());
		}
		return r;
		
	}
	
	// 주문상세건 삭제시 주문테이블의 총가격 변환 메소드에게 가격정보 전달을 위한 메소드
	public int getOdprice(int odno) {
		
		System.out.println("매개변수:"+odno);
		int odprice = 0;
		String sql = "select odprice from odetail where odno=?";
		
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, odno);
			ResultSet rs = pstmt.executeQuery();
			System.out.println(sql);
			while(rs.next()) {
				odprice = rs.getInt(1);
			}
			ConnectionProvider.close(conn, pstmt, rs);
			
			System.out.println("전달값:"+odprice);
		} catch (Exception e) {
			System.out.println("예외:getOdprice() :" + e.getMessage());
		}
		
		return odprice;
	}
	

	// 해당 주문번호를 가진 주문상세 건이 있는지 확인해주는 메소드
	// 해당 주문번호를 가진 주문상세 건이 0이면, false반환
	public boolean isNull(int ono) {

		boolean flag = false;
		String sql = "select odno from odetail where ono=?";
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ono);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				flag = true;
			}

		} catch (Exception e) {
			System.out.println("예외:isNull() :" + e.getMessage());
		}

		return flag;

	}

	// 주문상세 삭제
	public int deleteOdetail(int odno) {

		int r = 0;
		String sql = "delete odetail where odno=?";
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, odno);
			r = pstmt.executeUpdate();

			ConnectionProvider.close(conn, pstmt);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return r;

	}

	// 주문일로부터 7일 후 자동 배송완료 변경 메소드
	public void completedShip() {

		String sql = "update odetail set ship='배송완료' "
				+ "where ono in (select ono from orders where sysdate >=any odate+7)";

		try {
			Connection conn = ConnectionProvider.getConnection();
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);

			ConnectionProvider.close(conn, stmt, null);

		} catch (Exception e) {
			System.out.println("예외:completedShip() :" + e.getMessage());
		}
	}

	// 주문 7일 후, 신청완료인 무형상품을 수강완료로 변경하는 메소드
	public int changeCompletedShip() {

		ArrayList<Integer> list = getShipOdno();

		int r = 0;
		String sql = "update odetail set ship='수강완료' where odno=?";
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);

			for (Integer i : list) {

				pstmt.setInt(1, i);
				r += pstmt.executeUpdate();

			}
			ConnectionProvider.close(conn, pstmt, null);

		} catch (Exception e) {
			System.out.println("예외:changeCompletedShip() :" + e.getMessage());
		}

		return r;

	}

	// '무형' 상품의 경우, 주문완료가 아닌 신청완료로 변경
	public int changeShip() {

		ArrayList<Integer> list = getShipOdno();

		int r = 0;
		String sql = "update odetail set ship='신청완료' where odno=?";
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);

			for (Integer i : list) {

				pstmt.setInt(1, i);
				r += pstmt.executeUpdate();

			}
			ConnectionProvider.close(conn, pstmt, null);

		} catch (Exception e) {
			System.out.println("예외:changeShip() :" + e.getMessage());
		}

		return r;

	}

	public ArrayList<Integer> getShipOdno() {

		// 무형상품의 주문상세 번호 출력
		ArrayList<Integer> list = new ArrayList<Integer>();
		String sql = "select odno from odetail where ino in (select ino from item where ilv='무형')";
//		String sql = "select odno from odetail od, orders o where od.ono=o.ono and ilv='무형'";
		try {
			Connection conn = ConnectionProvider.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				
				list.add(rs.getInt(1));
			}
			ConnectionProvider.close(conn, stmt, rs);

		} catch (Exception e) {
			System.out.println("예외:getShipOdno() :" + e.getMessage());
		}

		return list;

	}

	// 해당 판매자의 주문건들 출력(판매자번호 매개변수로)
	// 배송여부(주문완료/배송중/배송완료)를 매개변수로 받아서 해당건만 출력
	public ArrayList<ArrayList<String>> listMyOrders(int mno, String ship) {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();

		String sql = "select odno, name, iname, odnum, odprice, odate, oname, oaddr, otel, ship "
				+ "from buyview v, item i, odetail od  " 
				+ "where od.ino=i.ino and v.ono=od.ono and i.mno = ?";

		if (ship != null) {
			sql += "and ship=?";
		}

		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mno);

			if (ship != null) {
				pstmt.setString(2, ship);
			}

			ResultSet rs = pstmt.executeQuery();
			// odno, name, iname, odnum, odprice, odate, oname, oaddr, otel, ship
			while (rs.next()) {

				ArrayList<String> myorders = new ArrayList<String>();
				myorders.add(rs.getInt(1) + "");
				myorders.add(rs.getString(2));
				myorders.add(rs.getString(3));
				myorders.add(rs.getInt(4) + "");
				myorders.add(rs.getInt(5) + "");
				myorders.add(rs.getString(6));
				myorders.add(rs.getString(7));
				myorders.add(rs.getString(8));
				myorders.add(rs.getString(9));
				myorders.add(rs.getString(10));

				list.add(myorders);
			}
			ConnectionProvider.close(conn, pstmt, rs);

		} catch (Exception e) {
			System.out.println("예외:listMyOrders() :" + e.getMessage());
		}
		return list;

	}

	// 마이페이지 주문상세 출력메소드
	// 마이페이지 클릭한 해당 주문의 주문번호 매개변수로 받음
	public ArrayList<ArrayList<String>> getMyOdetail(int ono, String ship) {
		
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		String sql = "select od.ino, nickname, iname, odnum, odprice, ship, odno " 
		+ "from sellerview sv, odetail od "
		+ "where sv.ino = od.ino and ono=? ";

		if (!ship.equals("모두보기") && !ship.equals("")) {
			sql += "and ship='" + ship + "'";
		}

		// System.out.println(sql);
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ono);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> odetail = new ArrayList<String>();
				// 하나의 vo가 아닌 다양한 테이블의 정보가 필요
				// ArrayList안에 필요한 정보들을 건당 ArrayList로 묶어서 담도록 함

				odetail.add(rs.getInt(1) + "");
				odetail.add(rs.getString(2));
				odetail.add(rs.getString(3));
				odetail.add(rs.getInt(4) + "");
				odetail.add(rs.getInt(5) + "");
				odetail.add(rs.getString(6));
				odetail.add(rs.getString(7));

				list.add(odetail);

			}
			ConnectionProvider.close(conn, pstmt, rs);

		} catch (Exception e) {
			System.out.println("예외:getMyOdetail() :" + e.getMessage());
		}
		return list;

	}

	// 판매자 페이지 : 주문완료 -> 배송중 변경
	// 매개변수는 선택한 테이블의 주문상세번호
	public int updateShip(int odno) {

		int r = 0;
		String sql = "update odetail set ship='배송중' where odno=?";
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, odno);
			r = pstmt.executeUpdate();

			ConnectionProvider.close(conn, pstmt, null);

		} catch (Exception e) {
			System.out.println("예외:updateShip() :" + e.getMessage());
		}

		return r;

	}

	// 마이페이지 고객 : 배송확정 버튼
	public int checkShip(int odno) {

		int r = 0;
		String sql = "update odetail set ship='배송완료' where odno=?";
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, odno);
			r = pstmt.executeUpdate();

			ConnectionProvider.close(conn, pstmt, null);

		} catch (Exception e) {
			System.out.println("예외:checkShip() :" + e.getMessage());
		}

		return r;

	}

}
