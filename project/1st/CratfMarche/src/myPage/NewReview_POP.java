package myPage;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import marcheDao.ReviewDao;
import marcheVo.ReviewVo;

public class NewReview_POP extends JFrame implements ActionListener{

	JTextArea taRtext;
	JRadioButton radioScore1;
	JRadioButton radioScore2;
	JRadioButton radioScore3;
	JRadioButton radioScore4;
	JRadioButton radioScore5;
	ButtonGroup radioGroup;
	
	JButton btInsert;
	
	int score = 0;
	int odno = 0;
	
	ReviewDao dao;
	
	NewReview_POP(int odno) {
		
		this.odno = odno;
		dao = new ReviewDao();
		
		btInsert = new JButton("등록");
		btInsert.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				boolean flag = dao.checkReview(odno);
				
				if(flag) {
					JOptionPane.showMessageDialog(null, "이미 리뷰를 등록하셨습니다!");
				
				}else {
					insertReview();
				}
				dispose();
			}
		});
		
		taRtext = new JTextArea(50, 50);
		radioScore1 = new JRadioButton("★");
		radioScore2 = new JRadioButton("★★");
		radioScore3 = new JRadioButton("★★★");
		radioScore4 = new JRadioButton("★★★★");
		radioScore5 = new JRadioButton("★★★★★");
		
		radioScore1.addActionListener(this);
		radioScore2.addActionListener(this);
		radioScore3.addActionListener(this);
		radioScore4.addActionListener(this);
		radioScore5.addActionListener(this);
		
		ButtonGroup radioGroup = new ButtonGroup();
		
		radioGroup.add(radioScore1);
		radioGroup.add(radioScore2);
		radioGroup.add(radioScore3);
		radioGroup.add(radioScore4);
		radioGroup.add(radioScore5);
	
		JPanel panScore = new JPanel();
		panScore.setLayout(new FlowLayout());
		panScore.add(new JLabel(" 별점 : "));
		panScore.add(radioScore1);
		panScore.add(radioScore2);
		panScore.add(radioScore3);
		panScore.add(radioScore4);
		panScore.add(radioScore5);
		
		JPanel panTf = new JPanel();
		panTf.setLayout(new FlowLayout());
		panTf.add(new JLabel("[ 상품평  ]"));
		panTf.add(taRtext);
		
		JPanel panBt = new JPanel();
		panBt.setLayout(new FlowLayout());
		panBt.add(btInsert);
		
		JPanel panUp = new JPanel();
		panUp.setLayout(new BorderLayout());
		panUp.add(panScore, BorderLayout.NORTH);
		panUp.add(panTf, BorderLayout.CENTER);
		
		
		setLayout(new BorderLayout());
		add(panUp, BorderLayout.CENTER);
		add(panBt, BorderLayout.SOUTH);
		
		setTitle("리뷰 작성");
		setSize(600, 300);
		setResizable(false);
		setVisible(true);
		
	}

	protected void insertReview() {
		
		ReviewVo vo = new ReviewVo();
		vo.setScore(score);
		vo.setRtext(taRtext.getText());
		vo.setOdno(odno);
		
		int r = dao.insertReview(vo);
		
		if(r == 1) {
			JOptionPane.showMessageDialog(null, "리뷰가 등록되었습니다.");
		
		}else {
			JOptionPane.showMessageDialog(null, "리뷰 등록에 실패했습니다.");
		}
		
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String star = e.getActionCommand();
		
		switch(star) {
			case"★" : score = 1; break;
			case"★★" : score = 2; break;
			case"★★★" : score = 3; break;
			case"★★★★" : score = 4; break;
			case"★★★★★" : score = 5; break;
		}
		//System.out.println(score);
		
	}

}
