package myPage;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import login.LoginMainPanel;
import marcheDao.OrdersDao;
import marcheDao.MemberDao;
import marcheVo.OrdersVo;


//주문현황 판넬  
//내정보 버튼이 있었으나 모든 탭에서 보여야 할것같아 텝 프레임으로 옮김
public class MyOrderPanel extends JPanel {
	
	Vector<String> colNames;
	Vector<Vector<String>> rowData;
	JTable table;
	int mno;
	MyOrderPanel p;
	
	MyOrderPanel(int mno){
		
		this.p = this;
		this.mno = mno;
		colNames = new Vector<String>();
		rowData = new Vector<Vector<String>>();
		
		colNames.add("주문번호");
		colNames.add("결제금액");
		colNames.add("주문날짜");
		colNames.add("결제방식");
		colNames.add("수취인이름");
		colNames.add("수취인주소");
		colNames.add("수취인연락처");
		
		table = new JTable(rowData, colNames);
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
			
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				int ono = Integer.parseInt(rowData.get(row).get(0));
				new MyOrderDetail_POP(ono, p);
			}
		});
		
		JScrollPane jsp = new JScrollPane(table);
		
		getMyOrders();
			
		setLayout(new BorderLayout());
		add(jsp);
		
	}
	
	
	void getMyOrders() {
		
		rowData.clear();
		
		OrdersDao dao = new OrdersDao();
		System.out.println(mno);
		
//		MemberDao mdao = new MemberDao();
//		String id = "sdy123";
//		mno = mdao.getMyMno(id);
		
		ArrayList<OrdersVo> myOrders = dao.getMyOrders(mno);
		
		for(OrdersVo vo : myOrders) {
			
			Vector<String> v = new Vector<String>();
			
			v.add(vo.getOno()+"");
			v.add(vo.getTotal()+"");
			v.add(vo.getOdate());
			v.add(vo.getCoc());
			v.add(vo.getOname());
			v.add(vo.getOaddr());
			v.add(vo.getOtel());
			
			rowData.add(v);
			
		}
		table.updateUI();
		
	}
}





