package marcheDao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import marcheDb.ConnectionProvider;
import marcheVo.ItemVo;

public class ItemDao {

	public HashMap<Integer, String> categoryMach() {
		HashMap<Integer, String> list = new HashMap<Integer, String>();
		String sql = "select tno, tname from type";
		try {
			Connection conn = ConnectionProvider.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {

				list.put(rs.getInt(1), rs.getString(2));
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("예외 : categoryMach:" + e.getMessage());
		}
		return list;
	}

	// 판매중인 모든 상품의 리스트 출력을 위한 메소드
	// ilv, tname, ino, iname, img, nickname, iprice, ihit
	// 정렬을 위하여 변수를 받음 _ 기본 정렬은 ino 를 기준으로 정렬됨
	// int n은 정렬 순서(오름/내림)을 정하기 위한 변수 _ n 이 짝수일때 내림차순 정렬
	public ArrayList<ArrayList<String>> listAllSearchItem(String arr, int n, String ilv, String tname, String select,
			String keyword) {
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		String ilvP = " and ilv = '";
		String tnameP = " and t.tno = (select tno from type where tname = '";
		String selectP = " and ";

		if (select.equals("셀러닉네임")) {
			selectP += "nickname like '%" + keyword + "%' ";
		} else if (select.equals("상품명")) {
			selectP += "iname like '%" + keyword + "%' ";
		} else {
			selectP = "";
		}

		if (ilv != null && !ilv.equals("전체")) {
			ilvP += ilv + "'";
		} else {
			ilvP = "";
		}
		if (tname != null && !tname.equals("전체")) {
			tnameP += tname + "')";
		} else {
			tnameP = "";
		}
		if (n % 2 == 1) {
			arr += " desc";
		}

		String sql = "select ilv, tname, ino, iname, img, nickname, iprice, ihit from item i, type t, member m "
				+ "where i.tno = t.tno and i.mno = m.mno and condition = 1 " + selectP + ilvP + tnameP + " order by "
				+ arr;

		try {
			System.out.println(sql);
			Connection conn = ConnectionProvider.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ArrayList<String> deList = new ArrayList<String>();
				deList.add(rs.getString(1));
				deList.add(rs.getString(2));
				deList.add(rs.getInt(3) + "");
				deList.add(rs.getString(4));
				deList.add(rs.getString(5));
				deList.add(rs.getString(6));
				deList.add(rs.getInt(7) + "");
				deList.add(rs.getInt(8) + "");

				list.add(deList);
			}
			ConnectionProvider.close(conn, stmt, rs);
		} catch (Exception e) {
			// TODO: handle exception
			 System.out.println("listAllSearchItem() 예외 발생 : " + e.getMessage());
		}
		return list;
	}

	// 판매중인 모든 상품의 리스트 출력을 위한 메소드
	// ilv, tname, ino, iname, img, nickname, iprice, ihit
	// 정렬을 위하여 변수를 받음 _ 기본 정렬은 ino 를 기준으로 정렬됨
	// int n은 정렬 순서(오름/내림)을 정하기 위한 변수 _ n 이 짝수일때 내림차순 정렬
	public ArrayList<ArrayList<String>> listAllItem(String arr, int n) {
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		if (n % 2 == 1) {
			arr += " desc";
		}

		String sql = "select ilv, tname, ino, iname, img, nickname, iprice, ihit from item i, type t, member m "
				+ "where i.tno = t.tno and i.mno = m.mno and condition = 1 order by " + arr;
		try {
			Connection conn = ConnectionProvider.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ArrayList<String> deList = new ArrayList<String>();
				deList.add(rs.getString(1));
				deList.add(rs.getString(2));
				deList.add(rs.getInt(3) + "");
				deList.add(rs.getString(4));
				deList.add(rs.getString(5));
				deList.add(rs.getString(6));
				deList.add(rs.getInt(7) + "");
				deList.add(rs.getInt(8) + "");

				list.add(deList);
			}
			ConnectionProvider.close(conn, stmt, rs);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("listAllItem() 예외 발생 : " + e.getMessage());
		}
		return list;
	}

	// 콤보박스에서 선택된 상세타입명을 받아 해당되는 상세타입 번호로 바꿔줌
	public int getCategory(String category) {
		int re = 0;
		String sql = "select tno from type where tname=?";
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, category);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				re = rs.getInt(1);
			}
			ConnectionProvider.close(conn, pstmt, rs);
		} catch (Exception e) {
			System.out.println("getCategory() 예외 발생 : " +e.getMessage());
		}
		return re;
	}

	// 판매자가 등록한 모든 상품을 보여줌
	public ArrayList<ItemVo> listItem(int mno) {
		ArrayList<ItemVo> list = new ArrayList<ItemVo>();
		String sql = "select ino, iname, iprice, inum, ihit from item where mno=? and condition !=2 order by ino";
		
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mno);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {

				ItemVo vo = new ItemVo();
				vo.setIno(rs.getInt(1));
				vo.setIname(rs.getString(2));
				vo.setIprice(rs.getInt(3));
				vo.setInum(rs.getInt(4));
				vo.setIhit(rs.getInt(5));
				list.add(vo);
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("listItem() 예외 발생 : " +e.getMessage());
		}
		return list;
	}

	// 상품번호를 매개변수로 받아 상품목록에서 해당 상품클릭시 상세내용을 확인시 사용
	// ino,iname,iprice,inum,img,tno,ilv,condition,idtext
	public ItemVo detailItem(int ino) {
		ItemVo vo = null;
		String sql = "select ino,iname,iprice,inum,img,mno,tno,ilv,condition, (select idtext from idetail where ino=?)"
				+ " from item where ino=? and condition !=2 ";
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ino);
			pstmt.setInt(2, ino);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				vo = new ItemVo();
				vo.setIno(rs.getInt(1));
				vo.setIname(rs.getString(2));
				vo.setIprice(rs.getInt(3));
				vo.setInum(rs.getInt(4));
				vo.setImg(rs.getString(5));
				vo.setMno(rs.getInt(6));
				vo.setTno(rs.getInt(7));
				vo.setIlv(rs.getString(8));
				vo.setCondition(rs.getInt(9));
				vo.setIdtext(rs.getString(10));

			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("detailItem() 예외 발생 : " +e.getMessage());
		}
		return vo;
	}

	// 판매자의 번호를 매개변수로 받아 닉네임을 띄어주는 메소드
	public String getNickname(int mno) {
		String nick = null;
		String sql = "select nickname from member where mno=?";
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mno);
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				nick = rs.getString(1);
			}
			ConnectionProvider.close(conn, pstmt, rs);
		} catch (Exception e) {
			System.out.println("getNickname 예외:" + e.getMessage());
		}

		return nick;

	}

	// 조회수 증가 메소드
	public int addHit(String field, String column, int no) {
		int re = -1;
		String sql = "update item set "+field+" = (ihit+1) where "+column+" = ?";

		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			re = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("addHit() 예외 : " + e.getMessage());
		}
		return re;
	}

	// itype 목록 콤보박스 노출위한 리스트 출력
	public Vector<String> listIType(String ilv) {
		Vector<String> v = new Vector<String>();
		String sql = "select tname from type ";

		try {
			if (ilv.equals("유형")) {
				sql += "where tno between 1 and 20";
			} else if (ilv.equals("무형")) {
				sql += "where tno between 21 and 40";
			}

			Connection conn = ConnectionProvider.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				v.add(rs.getString(1));
			}
			ConnectionProvider.close(conn, stmt, rs);
		} catch (Exception e) {
			System.out.println("listIType() 예외 : " + e.getMessage());
		}
		return v;
	}

	// 전체상품 출력 및 검색 메소드
	// 매개변수 모두 null값 - 모든 상품출력
	// 매개변수 : 유무형, 타입명, 판매자/상품명, 검색어
	public ArrayList<ArrayList<String>> listSearchItem(String ilv, String tname, String field, String keyword) {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();

		String sql = "select ilv, ino, iname, (select nickname from member m where m.mno = i.mno), "
				+ "iprice, ihit from item i ";

		String check = "";
		check = sql.substring(sql.indexOf("item"));
		System.out.println(check);

		if (ilv != null) {
			if (!ilv.equals("전체")) {
				sql += "where ilv='" + ilv + "' ";
				check += sql;
			}
		}

		if (tname != null) {

			if (check.indexOf("where") == -1) {
				sql += "where ";

			} else {
				sql += "and ";
			}

			sql += "tno=(select tno from type where tname = '" + tname + "' ) ";

			if (keyword != null) {

				sql += "and ";

				if (field.equals("셀러닉네임")) {
					sql += "mno=(select mno from member where nickname like '%" + keyword + "%')";

				} else if (field.equals("상품명")) {
					sql += "iname like '%" + keyword + "%'";
				}
			}

		} else {

			if (keyword != null) {

				if (check.indexOf("where") == -1) {
					sql += "where ";

				} else {
					sql += "and ";
				}

				if (field.equals("셀러닉네임")) {
					sql += "mno=(select mno from member where nickname like '%" + keyword + "%')";

				} else if (field.equals("상품명")) {
					sql += "iname like '%" + keyword + "%'";
				}
			}
		}
		System.out.println(check);
		System.out.println(sql);
		try {
			Connection conn = ConnectionProvider.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			// ilv, ino, iname, nickname, iprice, ihit
			while (rs.next()) {
				ArrayList<String> item = new ArrayList<String>();
				item.add(rs.getString(1));
				item.add(rs.getInt(2) + "");
				item.add(rs.getString(3));
				item.add(rs.getString(4));
				item.add(rs.getInt(5) + "");
				item.add(rs.getInt(6) + "");

				list.add(item);
			}
			ConnectionProvider.close(conn, stmt, rs);
		} catch (Exception e) {
			System.out.println("listSearchItem() 예외 : " + e.getMessage());
		}
		return list;
	}

	// 상품 정렬 정렬할 해당필드명, 정렬조건 매개변수로 받음
	// condition-> 0 오름차순/ 1 내림차순(높은 인기/높은 가격순)
	public ArrayList<ArrayList<String>> orderItem(String field, int condition) {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		String sql = "select ilv, ino, iname, " + "(select nickname from member m where m.mno = i.mno), iprice, ihit "
				+ "from item i order by '" + field + "' ";

		if (condition == 1) { // 내림차순
			sql += "desc";
		}

		try {
			Connection conn = ConnectionProvider.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			// ilv, ino, iname, nickname, iprice, ihit
			if (rs.next()) {
				ArrayList<String> item = new ArrayList<String>();
				item.add(rs.getString(1));
				item.add(rs.getInt(2) + "");
				item.add(rs.getString(3));
				item.add(rs.getString(4));
				item.add(rs.getInt(5) + "");
				item.add(rs.getInt(6) + "");

				list.add(item);

			}

			ConnectionProvider.close(conn, stmt, rs);

		} catch (Exception e) {
			System.out.println("orderItem() 예외 : " + e.getMessage());
		}

		return list;

	}

	// 판매자 페이지 : 전체 상품수 출력 메소드
	public int getItemNum() {

		int r = 0;
		String sql = "select count(ino) from item where condition = 1";
		try {
			Connection conn = ConnectionProvider.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				r = rs.getInt(1);
			}
			ConnectionProvider.close(conn, stmt, rs);

		} catch (Exception e) {
			System.out.println("getItemNum() 예외 : " + e.getMessage());
		}

		return r;

	}

	// 상품명, 셀러명, 가격, 사진, 상세내용 출력
	// 상품상세 출력 메소드
	public ArrayList<String> getItem(int ino) {

		ArrayList<String> list = new ArrayList<String>();
		String sql = "select iname, (select nickname from member m where m.mno = i.mno), iprice, img, "
				+ "(select idtext from idetail id where i.ino=id.ino) from item i where i.ino=?";
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ino);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				list.add(rs.getString(1));
				list.add(rs.getString(2));
				list.add(rs.getInt(3) + "");
				list.add(rs.getString(4));
				list.add(rs.getString(5));

			}
			ConnectionProvider.close(conn, pstmt, rs);

		} catch (Exception e) {
			System.out.println("getItem() 예외 : " + e.getMessage());
		}

		return list;

	}

	// 상품수정 메소드
	public int updateItem(ItemVo vo) {

		int r = 0;
		String sqlItem = "update item set iname=?, iprice=?, inum=?, img=?, tno=?, ilv=?, condition=? where ino=?";
		String sqlIdetail = "update idetail set idtext=? where ino=?";
		try {
			Connection conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmtItem = conn.prepareStatement(sqlItem);
			PreparedStatement pstmtIdetail = conn.prepareStatement(sqlIdetail);
			// iname,iprice,inum,img,tno,ilv,condition,idtext
			// 1 2 3 4 5 6 7
			pstmtItem.setString(1, vo.getIname());
			pstmtItem.setInt(2, vo.getIprice());
			pstmtItem.setInt(3, vo.getInum());
			pstmtItem.setString(4, vo.getImg());
			pstmtItem.setInt(5, vo.getTno());
			pstmtItem.setString(6, vo.getIlv());
			pstmtItem.setInt(7, vo.getCondition());
			pstmtItem.setInt(8, vo.getIno());
			r = pstmtItem.executeUpdate();

			pstmtIdetail.setString(1, vo.getIdtext());
			pstmtIdetail.setInt(2, vo.getIno());
			r += pstmtIdetail.executeUpdate();

			if (r == 2) {
				conn.commit();

			} else {
				conn.rollback();
			}

			ConnectionProvider.close(null, pstmtItem, null);
			ConnectionProvider.close(conn, pstmtIdetail, null);

		} catch (Exception e) {
			System.out.println("updateItem() 예외 : " + e.getMessage());
		}

		return r;

	}

	// 판매중지 상태로 변경 메소드
	public int deleteItem(int ino) {

		int r = 0;
		String sql = "update item set condition=2 where ino=?";
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ino);
			r = pstmt.executeUpdate();

			ConnectionProvider.close(conn, pstmt, null);

		} catch (Exception e) {
			System.out.println("deleteItem() 예외 : " + e.getMessage());
		}
		return r;

	}

	// 상품등록 메소드
	public int insertItem(ItemVo vo) {

		int ino = makeIno();
		int r = 0;
		String sqlItem = "insert into item values(?, ?, ?, 0, ?, ?, ?, ?, ?, 1)";
		String sqlIdetail = "insert into idetail values(?, ?)";
		try {
			Connection conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmtItem = conn.prepareStatement(sqlItem);
			PreparedStatement pstmtIdetail = conn.prepareStatement(sqlIdetail);

			pstmtItem.setInt(1, ino);
			pstmtItem.setString(2, vo.getIname());
			pstmtItem.setInt(3, vo.getIprice());
			pstmtItem.setInt(4, vo.getInum());
			pstmtItem.setString(5, vo.getImg());
			pstmtItem.setInt(6, vo.getMno());
			pstmtItem.setInt(7, vo.getTno());
			pstmtItem.setString(8, vo.getIlv());
			r = pstmtItem.executeUpdate();

			pstmtIdetail.setInt(1, ino);
			pstmtIdetail.setString(2, vo.getIdtext());
			r += pstmtIdetail.executeUpdate();

			if (r == 2) {
				conn.commit();

			} else {
				conn.rollback();
			}

			ConnectionProvider.close(null, pstmtItem, null);
			ConnectionProvider.close(conn, pstmtIdetail, null);

		} catch (Exception e) {
			System.out.println("insertItem() 예외 : " + e.getMessage());
		}

		return r;
	}

	// ino 만드는 메소드
	public int makeIno() {

		int ino = 0;
		String sql = "select nvl(max(ino),0)+1 from item";
		try {

			Connection conn = ConnectionProvider.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				ino = rs.getInt(1);
			}

			ConnectionProvider.close(conn, stmt, rs);

		} catch (Exception e) {
			System.out.println("makeIno() 예외 : " + e.getMessage());
		}

		return ino;

	}

}
