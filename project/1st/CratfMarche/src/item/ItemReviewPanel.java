package item;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import marcheDao.ReviewDao;

public class ItemReviewPanel extends JPanel {

	ArrayList<JPanel> panList;
	JPanel panMain;
	JPanel pan;
	JPanel panUp;

	JTextArea taContent;

	JLabel lbNo;
	JLabel lbWriter;
	JLabel lbScore;
	JLabel lbDate;

	String rno = "[ 글번호 ] : ";
	String writer = "[ 작성자 ] : ";
	String score = "[ 별점 ] : ";
	String rdate = "[ 작성일 ] : ";

	int row = 0;
	//int count=0;
	int ino;
	ReviewDao daoR;

	ArrayList<ArrayList<String>> list;

	public ItemReviewPanel(int ino) {
		// TODO Auto-generated constructor stub

		daoR = new ReviewDao();
		//count = daoR.countReview(ino);
		list = new ArrayList<ArrayList<String>>();
		list = daoR.getReview(ino);
		//count = list.size();
		//System.out.println(count);
		if (list.size() < 5) {
			row = 4;
		} else {
			row = list.size();
		}
		// 메인판을 row 크기를 주어 생성
		// 리뷰글의 총 갯수 + 1 만큼 생성
		// 새로이 입력할 필드는 항상 노출되어야 하므로 +1 해줌.
		panMain = new JPanel(new GridLayout(row + 1, 1));
		JScrollPane panJsp = new JScrollPane(panMain);

		panList = new ArrayList<JPanel>();

		if (list.size() == 0) {
			pan = new JPanel(new BorderLayout());
			panUp = new JPanel(new GridLayout(1, 4));

			lbNo = new JLabel(rno);
			lbWriter = new JLabel(writer);
			lbScore = new JLabel(score);
			lbDate = new JLabel(rdate);

			taContent = new JTextArea(3, 100);
			taContent.setText("( 아직 등록된 리뷰가 없습니다. )");
			taContent.setEditable(false);

			panUp.add(lbNo);
			panUp.add(lbWriter);
			panUp.add(lbScore);
			panUp.add(lbDate);

			pan.add(panUp, BorderLayout.NORTH);
			pan.add(taContent, BorderLayout.CENTER);

			panList.add(pan);
		}

		// i는 리뷰글의 총 갯수까지만
		// for 문을 한번 돌면 하나의 리뷰글에 대한 판넬이 생성됨
		for (int i = 0; i < list.size(); i++) {
			// 글번호, 작성자, 별점, 작성일, 내용
			
			rno = "[ 글번호 ] : ";
			writer = "[ 작성자 ] : ";
			score = "[ 별점 ] : ";
			rdate = "[ 작성일 ] : ";
			
			rno += list.get(i).get(0);
			writer += list.get(i).get(1);
			//score += list.get(i).get(2);
			for (int j = 0; j < Integer.parseInt(list.get(i).get(2)); j++) {
				score += "★";
			}
			rdate += list.get(i).get(3);

			pan = new JPanel(new BorderLayout());
			panUp = new JPanel(new GridLayout(1, 4));

			lbNo = new JLabel(rno);
			lbWriter = new JLabel(writer);
			lbScore = new JLabel(score);
			lbDate = new JLabel(rdate);

			taContent = new JTextArea(3, 100);
			taContent.setText(list.get(i).get(4));

			// 등록된 리뷰글을 보여주는 목적이므로, 수정 불가하도록 설정
			taContent.setEditable(false);

			panUp.add(lbNo);
			panUp.add(lbWriter);
			panUp.add(lbScore);
			panUp.add(lbDate);

			pan.add(panUp, BorderLayout.NORTH);
			pan.add(taContent, BorderLayout.CENTER);

			// 생성된 판을 list에 담음
			panList.add(pan);
			//System.out.println(rno+"+"+writer+"+"+rdate+"+"+score);
		}
		setLayout(new GridLayout(1, 1));

		// list안에 있는 판을 루프를 돌면서 메인에 담음
		for (JPanel p : panList) {
			panMain.add(p);
		}
		add(panJsp);
	}
}
