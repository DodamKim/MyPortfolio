package nori;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import marcheDao.AnswerDao;
import marcheDao.QnaDao;

public class QnaQcheck extends JFrame {

	JTextField tfQtitle; 
	JTextField tfMno;
	JTextArea taQtext;
	JTextArea taQanw;
	JButton btQcheck; 
	ArrayList<String> list;
	int mno, qno;
	
	QnaQcheck(int mno, int qno){
		this.mno = mno;
		this.qno = qno;
		
		tfQtitle = new JTextField(30);
		tfMno = new JTextField(30);
		taQtext = new JTextArea();
		taQanw = new JTextArea();
		
		tfQtitle.setEditable(false);
		tfMno.setEditable(false);
		taQtext.setEditable(false);
		if ( mno == 1) {
			taQanw.setEditable(true);
		}
		else {
			taQanw.setEditable(false);
		}
		btQcheck = new JButton("답변 등록");
		btQcheck.addActionListener(new ActionListener() {
				
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				updateAnswer();					//답변 등록버튼 눌렀을때 인서트 되야하는 메소드
				dispose();
			}
		});
	
		JPanel panTf = new JPanel();				//텍스트필드를 담은 판넬
		panTf.setLayout(new GridLayout(2,2,10,10));
		panTf.add(new JLabel("제목"));
		panTf.add(tfQtitle);
		panTf.add(new JLabel("작성자"));
		panTf.add(tfMno);
			
		JPanel panLb = new JPanel();				//[Qna 글 내용]라벨을 담은 판넬
		panLb.setLayout(new FlowLayout(FlowLayout.LEFT));
		panLb.add(new JLabel("[Qna 글 내용]"));
		
		JPanel panTfLb1 = new JPanel();				//위의 2개의 판넬을 담은 판넬
		panTfLb1.setLayout(new BorderLayout());
		panTfLb1.add(panTf, BorderLayout.NORTH);
		panTfLb1.add(panLb, BorderLayout.SOUTH);
		
		
		JPanel panBt = new JPanel();				//버튼을 담은 판넬
		panBt.setLayout(new FlowLayout(FlowLayout.RIGHT));
		if(mno == 1) {
			panBt.add(btQcheck);	
		}
		
		JScrollPane qtextJsp = new JScrollPane(taQtext);
		JScrollPane qanwJsp = new JScrollPane(taQanw);
		
		getQnaAns();
		
		
		JPanel panUp = new JPanel();
		panUp.setLayout(new BorderLayout());
		panUp.add(panTfLb1, BorderLayout.NORTH);
		panUp.add(qtextJsp, BorderLayout.CENTER);
		
		JPanel panLb2 = new JPanel();				//[답변 내용]라벨을 담은 판넬
		panLb2.setLayout(new FlowLayout(FlowLayout.LEFT));
		panLb2.add(new JLabel("[답변 내용]"));
		
		
		JPanel panDown = new JPanel();
		panDown.setLayout(new BorderLayout());
		panDown.add(panLb2, BorderLayout.NORTH);
		panDown.add(qanwJsp, BorderLayout.CENTER);
		panDown.add(panBt, BorderLayout.SOUTH);
		
		setLayout(new GridLayout(2,1));
		add(panUp);
		add(panDown);
		
		setTitle("QnA 글 보기");
		setSize(700, 900);
		setResizable(false);
		setVisible(true);
		
		
	}

	protected void updateAnswer() {
		AnswerDao dao = new AnswerDao();
		int re = dao.updateAnswer(qno, taQanw.getText());
		 
			if(re >= 1 )
		        {
		            JOptionPane.showMessageDialog(null, "답변 등록 완료. ");
		            
		        }
		        else
		        {
		            JOptionPane.showMessageDialog(null, "답변 등록 실패. ");
		            dispose();
		        }
		
	}

	private void getQnaAns() {
		QnaDao dao = new QnaDao();
		list = dao.getQnaAns(qno);
		// 출력내용 : 제목, 작성자, 내용 표기 + 답변까지 출력
		Vector<String> v = new Vector<String>();
		
		tfQtitle.setText(list.get(0));
		tfMno.setText(list.get(1));
		taQtext.setText(list.get(2));
		taQanw.setText(list.get(3));
		
	}
	
}