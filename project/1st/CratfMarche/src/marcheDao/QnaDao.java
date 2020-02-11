package marcheDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import marcheDb.ConnectionProvider;
import marcheVo.QnaVo;

public class QnaDao {

	// 선택한 상품의 판매자 mno 출력
	public int checkSellerMno(int ino) {
		int no = -1;
		String sql = "select mno from item where ino =?";
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ino);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				no = rs.getInt(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("checkSellerMno() 예외 : " + e.getMessage());
		}
		return no;
	}

	
	// 기능 설명 : Q&A DB에 신규 레코드 삽입
		// 매개 변수 : qnaVo
		public int insertQna(QnaVo vo) {
			String sql = "insert into qna values( seq_qna.nextval, ?, ?, sysdate, 0, ?, ?, ?)";
			// 1. 문의번호, 2. 제목, 3. 내용, 4. 작성일, 5. 답변 확인, 6. 비공개 여부, 7. 회원 번호, 8. 상품 번호
			int r = 0;
			
			try {
				Connection conn = ConnectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, vo.getQtitle()); // 제목 설정
				pstmt.setString(2, vo.getQtext()); // 내용 설정
				pstmt.setInt(3, vo.getSecret()); // 비공개 여부
				pstmt.setInt(4, vo.getMno()); // 회원 번호
				pstmt.setInt(5, vo.getIno()); // 상품 번호
				r = pstmt.executeUpdate(); // 값 추가 성공 여부 확인
				
				ConnectionProvider.close(conn, pstmt, null);
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			return r; // 추가 결과를 받고 호출한 곳으로 반환
		}

		
		// qna 전체 리스트 출력
		// 리스트 출력 : 문의글번호, 제목, 작성자(닉네임), 작성일, 답변여부, 비밀글
		// 하나의 vo 안에 있는 값이 아니기 때문에 다시 한번 ArrayList에 담아줌
		public ArrayList<ArrayList<String>> listAllQna(int ino) {

			ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
//			String sql = "select qno, qtitle, (select nickname from member where mno in "
//					+ "(select mno from qna where ino = ?)) nickname, qdate, qcheck, secret, mno from qna where ino=?";
			String sql = "select qno, qtitle, nickname, qdate, qcheck, secret, m.mno from member m, qna q, item i "
					+ "where m.mno = q.mno and q.ino = i.ino and i.ino=? order by qno desc";
			
			try {
				Connection conn = ConnectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, ino);
				//pstmt.setInt(2, ino);
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()) {
					ArrayList<String> subList = new ArrayList<String>();
					subList.add(rs.getInt(1)+"");
					subList.add(rs.getString(2));
					subList.add(rs.getString(3));
					subList.add(rs.getDate(4)+"");
					subList.add(rs.getInt(5)+"");
					subList.add(rs.getInt(6)+"");
					subList.add(rs.getInt(7)+"");
					
					list.add(subList);
					//list.add(new ArrayList<String>(rs.getInt(1)+"", rs.getString(2), rs.getString(3), rs.getDate(4)+"");
				}
				ConnectionProvider.close(conn, pstmt, rs);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("listAllQna() 예외 : "+e.getMessage());
			}
			return list;
		}
		
		// 리스트 내 클릭시 해당  qna 상세 내용 출력
		// 출력내용 : 제목, 작성자(닉네임), 내용 표기 + 답변까지 출력
		// 답변은 answerDao에서 호출
		public ArrayList<String> getQnaAns(int qno){
			ArrayList<String> list = new ArrayList<String>();
			 String sql = "select qtitle, (select nickname from member where mno = "
			 		+ "(select mno from qna where qno=?)) name, qtext from qna where qno=?";
			 
			 try {
				Connection conn = ConnectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, qno);
				pstmt.setInt(2, qno);

				ResultSet rs = pstmt.executeQuery();
				while(rs.next()) {
					list.add(rs.getString(1));
					list.add(rs.getString(2));
					list.add(rs.getString(3));
				}
				ConnectionProvider.close(conn, pstmt, rs);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("getQnaAns() 예외 : "+e.getMessage());
			}
			 return list;
		}
		
		
		
	// 기능 설명 : MyPage에서 사용자 계정에서 작성된 질문을 게시글만 출력
	public ArrayList<QnaVo> getMyQna(int mno) {
		ArrayList<QnaVo> temp = new ArrayList<QnaVo>();
		String sql = "select * from qna where mno=?";

		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mno); // 매개 변수 mno를 SQL에 대입
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				temp.add(new QnaVo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5),
						rs.getInt(6), rs.getInt(7), rs.getInt(8)));
			}
			ConnectionProvider.close(conn, pstmt, rs);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return temp;
	}
}
