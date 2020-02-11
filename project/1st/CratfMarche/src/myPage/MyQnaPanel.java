package myPage;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import marcheDao.AnswerDao;
import marcheDao.MemberDao;
import marcheDao.QnaDao;
import marcheVo.QnaVo;

public class MyQnaPanel extends JPanel {
	
	Vector<String> colNames;
	Vector<Vector<String>> rowData;
	JTable table;
	
	JTextField tfTitle;
	JTextField tfWriter;
	
	JTextArea taQna;
	JTextArea taAsw;
	
//	JButton btNew;
//	JButton btQnaAdd;
//	JButton btAswAdd;
	
	JPanel panAdd;
	
	int mno;
	String id;
	QnaDao qdao;
	
	
	MyQnaPanel(int mno){
		
		qdao = new QnaDao();
		this.mno = mno;
		MemberDao dao = new MemberDao();
		
		if(mno != 1) {
			id = dao.getMember(mno).getNickname();
			
		}else {
			id = "관리자";
		}
		
		
		colNames = new Vector<String>();
		rowData = new Vector<Vector<String>>();
		
		colNames.add("글번호");
		colNames.add("제목");
		colNames.add("작성자");
		colNames.add("작성일");
		
		table = new JTable(rowData, colNames);
		JScrollPane qnaJsp = new JScrollPane(table);
		
		tfTitle = new JTextField(30);
		
		tfWriter = new JTextField(10);
		tfWriter.setText(id);
		tfWriter.setEditable(false);
		
		taQna = new JTextArea(10, 50);
		taAsw = new JTextArea(10, 50);
		
		table.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			
			@Override //글번호, 제목, 작성자, 작성일, 내용
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				tfTitle.setText(rowData.get(row).get(1));
				taQna.setText(rowData.get(row).get(4));
				
				AnswerDao dao = new AnswerDao();
				String text = dao.getAnswer(Integer.parseInt(rowData.get(row).get(0)));
				taAsw.setText(text);
			}
		});
		
		
//		btAswAdd = new JButton("답변등록");
//		btAswAdd.setVisible(false);
//		
//		if(mno == 1) {
//			btAswAdd.setVisible(true);
//		}

		
		panAdd = new JPanel(new GridLayout(2,1));
		JPanel panBts = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JPanel panQs = new JPanel(new BorderLayout());
		JPanel panQs1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel panUp = new JPanel(new BorderLayout());
		JPanel panAns = new JPanel(new BorderLayout());
		JPanel panAns1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel panAns2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		

		panQs1.add(new JLabel("제목 : "));
		panQs1.add(tfTitle);
		panQs1.add(new JLabel("작성자 : "));
		panQs1.add(tfWriter);
		
		panQs.add(panQs1, BorderLayout.NORTH);
		panQs.add(taQna, BorderLayout.CENTER);
		
		panUp.add(panBts, BorderLayout.NORTH);
		panUp.add(panQs, BorderLayout.CENTER);
		
		panAns1.add(new JLabel("[ 답변 ]"));
//		panAns2.add(btAswAdd);
		panAns.add(panAns1, BorderLayout.NORTH);
		panAns.add(taAsw, BorderLayout.CENTER);
		panAns.add(panAns2, BorderLayout.SOUTH);
		
		panAdd.add(panUp);
		panAdd.add(panAns);
		
		JPanel panRight = new JPanel();		
		panRight.add(panAdd);
		
		setLayout(new GridLayout(1, 2));	
		add(qnaJsp);
		add(panRight);
		
		
		getMyQna();
		
	}



	private void getMyQna() {
		
		rowData.clear();
		
		ArrayList<QnaVo> list = qdao.getMyQna(mno);
		
		//글번호, 제목, 작성자, 작성일, 내용
		for(QnaVo vo : list) {
			Vector<String> v = new Vector<String>();
			v.add(vo.getQno()+"");
			v.add(vo.getQtitle());
			v.add(id);
			v.add(vo.getQdate());
			v.add(vo.getQtext());
			
			rowData.add(v);
			
		}
		table.updateUI();
		
	}
}


