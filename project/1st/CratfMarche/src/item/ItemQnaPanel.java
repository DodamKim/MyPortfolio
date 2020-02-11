package item;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import marcheDao.AnswerDao;
import marcheDao.MemberDao;
import marcheDao.QnaDao;
import marcheVo.AnswerVo;
import marcheVo.MemberVo;
import marcheVo.QnaVo;

public class ItemQnaPanel extends JPanel {

	// LoginMainPanel class 에서 isMember() 메소드 안에
	// ItemQnaPanel.mno = mno; 추가 후 변수 선언해줘야 함.
	public static int mno;

	Vector<String> colNames;
	Vector<Vector<String>> rowData;

	JTextField tfTitle;
	JTextField tfWriter;
	JTextArea taQna;
	JTextArea taAsw;

	JButton btNewQ;
	JButton btNewA;
	JButton btQnaAdd;
	JButton btAswAdd;

	JCheckBox boxSCR;

	JTable table;
	JPanel panAdd;

	int ino;
	int qno = -1;
	int sellerMno = -1;
	int secret = 0;

	QnaVo q;
	QnaDao daoQ;
	String a;
	AnswerDao daoA;
	MemberVo m;
	MemberDao daoM;

	ArrayList<ArrayList<String>> allList;
	ArrayList<String> qnaDetail;

	public ItemQnaPanel(int ino) {
		// TODO Auto-generated constructor stub

		// q = new QnaVo();
		daoQ = new QnaDao();
		daoM = new MemberDao();
		daoA = new AnswerDao();
		m = daoM.getMember(mno);
		a = daoA.getAnswer(qno);

		sellerMno = daoQ.checkSellerMno(ino);
//		System.out.println("ino:"+ino);
//		System.out.println("mno:"+mno);
//		System.out.println("loginMno:"+loginMno);

		colNames = new Vector<String>();
		rowData = new Vector<Vector<String>>();

		colNames.add("글번호");
		colNames.add("제목");
		colNames.add("작성자");
		colNames.add("작성일");
		colNames.add("비밀글");
		colNames.add("답변여부");

		table = new JTable(rowData, colNames);
		table.getColumnModel().getColumn(0).setPreferredWidth(20);
		table.getColumnModel().getColumn(1).setPreferredWidth(220);
		table.getColumnModel().getColumn(2).setPreferredWidth(80);
		table.getColumnModel().getColumn(4).setPreferredWidth(40);
		table.getColumnModel().getColumn(5).setPreferredWidth(40);
		JScrollPane jsp = new JScrollPane(table);

		listAllQna(ino);

		boxSCR = new JCheckBox("비밀글");
		boxSCR.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (boxSCR.isSelected()) {
					secret = 1;
				}
			}
		});

		tfTitle = new JTextField(25);
		tfWriter = new JTextField(9);
		if (sellerMno != 0) {
			tfWriter.setText(m.getNickname());
		}
		tfWriter.setEditable(false);
		tfWriter.setBackground(Color.WHITE);
		taQna = new JTextArea();
		JScrollPane jspQna = new JScrollPane(taQna);
		taAsw = new JTextArea();
		taAsw.setBackground(getBackground());
		// 판매자에게만 활성화
		taAsw.setEditable(false);

		JScrollPane jspAsw = new JScrollPane(taAsw);

		table.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
				qno = Integer.parseInt(rowData.get(table.getSelectedRow()).get(0));
				
				if (rowData.get(table.getSelectedRow()).get(5).equals("Y")) {
					if (mno == Integer.parseInt(rowData.get(table.getSelectedRow()).get(6))
							|| mno == sellerMno) {
						detailQnaAns();
						boxSCR.setSelected(true);
					} else {
						JOptionPane.showMessageDialog(null, "죄송합니다! 비밀글은 작성자만 확인 가능합니다.");
					}
				} else {
					detailQnaAns();
				}
				
			}
		});

		btNewQ = new JButton("새글쓰기");
		btQnaAdd = new JButton("질문등록");
		btNewA = new JButton("답변하기");
		btAswAdd = new JButton("답변등록");
		btNewA.setVisible(false);
		btAswAdd.setVisible(false);

		if (mno == sellerMno) {
			btNewA.setVisible(true);
			btAswAdd.setVisible(true);

			tfTitle.setEditable(false);
			tfWriter.setEditable(false);
			taQna.setEditable(false);
		}

		btNewQ.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				tfTitle.setText("");
				tfTitle.setEditable(true);
				tfWriter.setBackground(Color.WHITE);
				if (mno != 0) {
					tfWriter.setText(m.getNickname());
				} else {
					tfWriter.setText("");
				}
				boxSCR.setEnabled(true);
				taQna.setText("");
				taQna.setEditable(true);
				taQna.setBackground(Color.WHITE);
				taAsw.setText("");

			}

		}); 

		btQnaAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				if(tfWriter.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "로그인 후 작성이 가능합니다.");
					return;
				}
				if (tfTitle.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "제목을 입력해주세요!");
					return;
				}
				if (taQna.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "내용을 입력해주세요!");
					return;
				}
				// System.out.println(secret);
				q = new QnaVo();
				// 2. 제목, 3. 내용, 6. 비공개 여부, 7. 회원 번호, 8. 상품 번호
				q.setQtitle(tfTitle.getText());
				q.setQtext(taQna.getText());
				q.setSecret(secret);
				q.setIno(ino);
				q.setMno(mno);

				int r = daoQ.insertQna(q);
				if (r >= 1) {
					JOptionPane.showMessageDialog(null, "감사합니다! 질문이 등록되었습니다. 곧 답변 드리겠습니다 :)");
					System.out.println(ino);
					//listAllQna(ino);
				} else {
					JOptionPane.showMessageDialog(null, "죄송합니다. 질문 등록에 실패했습니다. 다시 시도해주세요.");
				}
			}
		});

		btNewA.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				taAsw.setText("");
				taAsw.setEditable(true);
				taAsw.setBackground(Color.WHITE);
			}
		});

		btAswAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(qno);
				int r = daoA.updateAnswer(qno, taAsw.getText() );
				if(taAsw.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "답변 내용을 입력해주세요!");
					return;
				}
				
				if(r >= 1) {
					JOptionPane.showMessageDialog(null, "답변이 등록되었습니다.");
					listAllQna(ino);
				} else {
					JOptionPane.showMessageDialog(null, "죄송합니다. 답변 등록에 실패했습니다.");
				}
			}                        
		});

		panAdd = new JPanel(new GridLayout(2, 1));
		JPanel panBts = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JPanel panQs = new JPanel(new BorderLayout());
		JPanel panQs1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel panUp = new JPanel(new BorderLayout());
		JPanel panAns = new JPanel(new BorderLayout());
		JPanel panAns1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel panAns2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));

		panBts.add(boxSCR);
		panBts.add(btNewQ);
		panBts.add(btQnaAdd);
		panQs1.add(new JLabel("[ 제목 ] : "));
		panQs1.add(tfTitle);
		panQs1.add(new JLabel("[ 작성자 ] : "));
		panQs1.add(tfWriter);
		panQs1.add(boxSCR);

		panQs.add(panQs1, BorderLayout.NORTH);
		panQs.add(jspQna, BorderLayout.CENTER);

		panUp.add(panBts, BorderLayout.NORTH);
		panUp.add(panQs, BorderLayout.CENTER);

		panAns1.add(new JLabel("[ 답변 ]"));
		panAns2.add(btNewA);
		panAns2.add(btAswAdd);
		panAns.add(panAns1, BorderLayout.NORTH);
		panAns.add(jspAsw, BorderLayout.CENTER);
		panAns.add(panAns2, BorderLayout.SOUTH);

		panAdd.add(panUp);
		panAdd.add(panAns);

		setLayout(new GridLayout(1, 2));
		add(jsp);
		add(panAdd);
	}
	
	public void detailQnaAns() {
		qnaDetail = daoQ.getQnaAns(qno);
		//qnaDetail = daoQ.getQnaAns(Integer.parseInt(rowData.get(table.getSelectedRow()).get(0)));
		tfTitle.setText(qnaDetail.get(0));
		tfTitle.setEditable(false);
		tfWriter.setText(qnaDetail.get(1));
		tfWriter.setEditable(false);
		tfWriter.setBackground(getBackground());
		boxSCR.setEnabled(false);
		taQna.setText(qnaDetail.get(2));
		taQna.setEditable(false);
		taQna.setBackground(getBackground());
		taAsw.setText(a);
	}

	// 리스트 출력 : 문의글번호, 제목, 작성자(닉네임), 작성일, 답변여부, 비밀글, (회원번호)
	// qno, qtitle, nickname, qdate, qcheck, secret, (m.mno)
	public void listAllQna(int ino) {

		rowData.clear();
		daoQ = new QnaDao();
		// daoA= new QnaDao();
		System.out.println(ino);
		allList = daoQ.listAllQna(ino);

		for (ArrayList<String> a : allList) {
			Vector<String> v = new Vector<String>();
			v.add(a.get(0));
			v.add(a.get(1));
			v.add(a.get(2));
			v.add(a.get(3));
			if (a.get(5).equals("0")) {
				v.add("N");
			} else if (a.get(5).equals("1")) {
				v.add("Y");
			}
			if (a.get(4).equals("0")) {
				v.add("N");
			} else if (a.get(4).equals("1")) {
				v.add("Y");
			}
			v.add(a.get(6));

			rowData.add(v);
		}
		table.updateUI();
	}
}
