package marcheDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import marcheDb.ConnectionProvider;
import marcheVo.AnswerVo;

public class AnswerDao {

	public String getAnswer(int qno) {

		String text = "";
		String sql = "select atext from answer where qno=?";
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qno);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				text = rs.getString(1);

			}
			ConnectionProvider.close(conn, pstmt, rs);

		} catch (Exception e) {
			System.out.println("예외:getAnswer() :" + e.getMessage());
		}

		return text;

	}


	// 답변등록 메소드 - 매개변수(해당 질문번호, 답변내용)
	public int updateAnswer(int qno, String atext) {

		int r = 0;
		String sql = "update Answer set atext=? where qno=?";
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, atext);
			pstmt.setInt(2, qno);
			r = pstmt.executeUpdate();

			ConnectionProvider.close(conn, pstmt, null);

		} catch (Exception e) {
			System.out.println("예외:updateAnswer() :" + e.getMessage());
		}

		return r;
	}

}
