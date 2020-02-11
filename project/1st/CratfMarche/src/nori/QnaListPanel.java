package nori;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import marcheDao.QnaDao;

public class QnaListPanel extends JPanel {
	
	Vector<String> colNames;
	Vector<Vector<String>> rowData;
	JTable table;
	JButton btNew;
	QnaNew_POP pop;	// 팝업창 프레임
	ArrayList<ArrayList<String>> list;
	int no=0;
	HashMap<Integer, String> mapCheck;
	HashMap<Integer, String> mapSecret;
	public static int mno;
	int checkMno, qno;
	String checkSecret;
	
	
	QnaListPanel(){
		
		colNames = new Vector<String>();
		rowData = new Vector<Vector<String>>();
		
		mapCheck = new HashMap<Integer, String>();	
		mapCheck.put(0, "N");
		mapCheck.put(1, "Y");
		
		mapSecret = new HashMap<Integer, String>();	
		mapSecret.put(0, "N");
		mapSecret.put(1, "Y");
		
		btNew = new JButton("새글쓰기");
		btNew.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new QnaNew_POP();		//새글 쓰기 버튼 눌렀을때
				
			}
		});
		
		colNames.add("No.");
		colNames.add("글제목");
		colNames.add("작성자");
		colNames.add("작성일");
		colNames.add("답변여부");
		colNames.add("비밀글 여부");
		
		table = new JTable(rowData, colNames);
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
				int row = table.getSelectedRow();
				checkMno = Integer.parseInt(rowData.get(row).get(6));
				checkSecret = rowData.get(row).get(5);
				qno = Integer.parseInt(rowData.get(row).get(7));
				
				
				if(checkSecret.equals("N")) {
					new QnaQcheck(mno, qno);
				}
				else {
					if(checkMno == mno || mno == 1) {
						new QnaQcheck(mno, qno);
					}
					else {
						JOptionPane.showMessageDialog(null, "비밀글은 당사자만 확인할 수 있습니다.");
					}
				}
				
			}
		});
		
		JScrollPane jsp = new JScrollPane(table);
		
		listAllQna();
		
		JPanel panBt = new JPanel();
		panBt.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panBt.add(btNew);
		
		setLayout(new BorderLayout());
		add(jsp, BorderLayout.CENTER);
		add(panBt, BorderLayout.SOUTH);
	}


	private void listAllQna() {
		rowData.clear();
		QnaDao dao = new QnaDao();
		list = dao.listAllQna(0);
		
		// 리스트 출력 : 문의글번호, 제목, 작성자, 작성일, 답변여부, 비밀글 여부
		for(ArrayList<String> s :list) {
			Vector<String> v = new Vector<String>();
			no = no +1;
			
			v.add(no+"");
			v.add(s.get(1));
			v.add(s.get(2));
			v.add(s.get(3));
			v.add(mapCheck.get(Integer.parseInt(s.get(4))));
			v.add(mapSecret.get(Integer.parseInt(s.get(5))));
			v.add(s.get(6));
			v.add(s.get(0));
			
			rowData.add(v);
		}
		table.updateUI();
		
	}
}