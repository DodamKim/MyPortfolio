package myPage;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

import marcheDao.ReviewDao;

public class MyReviewPanel extends JPanel {
	
	int mno;
	
	ArrayList<JPanel> panList;
	JPanel panMain;
	JPanel pan;
	JPanel panUp;
	
	JTextArea taContent;

	JLabel lbNo;
	JLabel lbWriter;
	JLabel lbScore;
	JLabel lbDate;
	JLabel lbIname;

	String rno = " 글번호 : ";
	String writer = " 판매자 : ";
	String score = " 별점 : ";
	String rdate = " 작성일 : ";
	String iname = " 상품명 : ";

	int row = 0;
	int count;
	ReviewDao dao;

	ArrayList<ArrayList<String>> list;

	public MyReviewPanel(int mno) {
		
		this.mno = mno;
		list = new ArrayList<ArrayList<String>>();
		dao = new ReviewDao();
		
		list = dao.getMyReview(mno);
		count = list.size();
		
		// rno, rtext, rdate, score, nickname, iname

		if (count < 5) {
			row = 4;
		} else {
			row = count;
		}

		// 메인판을 row 크기를 주어 생성
		// 리뷰글의 총 갯수 + 1 만큼 생성
		// 새로이 입력할 필드는 항상 노출되어야 하므로 +1 해줌.
		panMain = new JPanel(new GridLayout(row + 1, 1));
		JScrollPane panJsp = new JScrollPane(panMain);

		panList = new ArrayList<JPanel>();
		
		if(count == 0) {
			pan = new JPanel(new BorderLayout());
			panUp = new JPanel(new GridLayout(1, 4));
			
			lbNo = new JLabel(rno);
			lbWriter = new JLabel(writer);
			lbScore = new JLabel(score);
			lbDate = new JLabel(rdate);
			lbIname = new JLabel(iname);

			taContent = new JTextArea(3, 100);
			taContent.setText("( 아직 등록된 리뷰가 없습니다. )");
			taContent.setEditable(false);
			
			panUp.add(lbNo);
			panUp.add(lbWriter);
			panUp.add(lbIname);
			panUp.add(lbScore);
			panUp.add(lbDate);

			pan.add(panUp, BorderLayout.NORTH);
			pan.add(taContent, BorderLayout.CENTER);

			panList.add(pan);
		}

		// i는 리뷰글의 총 갯수까지만
		// for 문을 한번 돌면 하나의 리뷰글에 대한 판넬이 생성됨
		for (int i = 0; i < count; i++) {
			// rno, rtext, rdate, score, nickname, iname
			// 글번호, 판매자, 별점, 작성일, 내용
			
			rno = " 글번호 : ";
			writer = " 판매자 : ";
			score = " 별점 : ";
			rdate = " 작성일 : ";
			iname = " 상품명 : ";
			
			rno += list.get(i).get(0);
			writer += list.get(i).get(4);
			rdate += list.get(i).get(2);
			
			String star="";
			for(int j=0; j<Integer.parseInt(list.get(i).get(3)); j++) {
				star += "★";
			}
			
			score += star;
			iname += list.get(i).get(5);

			pan = new JPanel(new BorderLayout());
			panUp = new JPanel(new GridLayout(1, 5));

			lbNo = new JLabel(rno);
			lbWriter = new JLabel(writer);
			lbIname = new JLabel(iname);
			lbScore = new JLabel(score);
			lbDate = new JLabel(rdate);

			taContent = new JTextArea(3, 100);
			taContent.setText(list.get(i).get(1));

			// 등록된 리뷰글을 보여주는 목적이므로, 수정 불가하도록 설정
			taContent.setEditable(false);
			
			panUp.add(lbNo);
			panUp.add(lbWriter);
			panUp.add(lbIname);
			panUp.add(lbScore);
			panUp.add(lbDate);
			
			pan.add(panUp, BorderLayout.NORTH);
			pan.add(taContent, BorderLayout.CENTER);
			
			// 생성된 판을 list에 담음
			panList.add(pan);
		}
		
		setLayout(new GridLayout(1, 1));

		// 생성된 list의 크기를 row 에 전달해줌
		// row = panList.size();
		// System.out.println(row);

		// list안에 있는 판을 루프를 돌면서 메인에 담음
		for (JPanel p : panList) {
			panMain.add(p);
		}

		add(panJsp);
	}

}