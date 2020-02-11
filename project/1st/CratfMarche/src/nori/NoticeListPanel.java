package nori;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import marcheDao.ItemDao;
import marcheDao.NoticeDao;
import marcheVo.NoticeVo;

//공지사항 패널
public class NoticeListPanel extends JPanel {
	
	Vector<String> colNames;
	Vector<Vector<String>> rowData;
	JTable table;
	JButton btNew;
	Notice_POP pop;		// 팝업창 프레임
	ArrayList<NoticeVo> list;
	int no = 0;
	int nno;
	public static int mno;
	
	
	
	NoticeListPanel(){
		
		colNames = new Vector<String>();
		rowData = new Vector<Vector<String>>();
		btNew = new JButton("새글쓰기");
		btNew.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				pop = new Notice_POP(1, 0);
			
			}
		});
		
		
		colNames.add("No.");
		colNames.add("글제목");
		colNames.add("작성일");
		colNames.add("작성자");
		
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
				nno = Integer.parseInt(rowData.get(row).get(4));		
				new Notice_POP(2, nno);
				
				//글 클릭시 Notice_Pop 클래스 팝업 
			}
		});
		
		
		
		JScrollPane jsp = new JScrollPane(table);
		
	
		
		listNotice();
		
		
		
		JPanel panBt = new JPanel();
		panBt.setLayout(new FlowLayout(FlowLayout.RIGHT));
		if(mno == 1) {
			panBt.add(btNew);
		}
		
		setLayout(new BorderLayout());
		add(jsp, BorderLayout.CENTER);
		add(panBt, BorderLayout.SOUTH);

	}

	public void listNotice() {
		rowData.clear();
		NoticeDao dao = new NoticeDao();
		ItemDao iDao = new ItemDao();
		list = dao.listNotice();
		
		
		for(NoticeVo n :list) {
			Vector<String> v = new Vector<String>();
			no = no +1;
			v.add(no+"");
			v.add(n.getNtitle());
			v.add(n.getNdate());
			v.add(iDao.getNickname(n.getMno()));
			v.add(n.getNno()+"");
			
			rowData.add(v);
		}
		table.updateUI();
		
	}
	
}
