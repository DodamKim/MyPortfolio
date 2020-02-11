package cart;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import item.ItemDetail_POP;
import marcheDao.BagDao;
import marcheVo.BagVo;

public class BagPanel extends JPanel {
	
	JTable table;	
	JTextField tfSum;
	Vector<String> colnames;
	Vector<Vector<String>> rowData;
	public static int mno; 
	ArrayList<ArrayList<String>> list;
	int allPrice, bagNo;
	JButton btOrder;
	JButton btCancel;
	BagPanel p;
	
	public BagPanel()
	{
		this.p = this;
		
		list = new ArrayList<ArrayList<String>>();
		colnames = new Vector<String>();
		rowData = new Vector<Vector<String>>();
		colnames.add("셀러명");
		colnames.add("상품번호");
		colnames.add("상품명");
		colnames.add("수량");
		colnames.add("가격");		
		
		btCancel = new JButton("장바구니 목록에서 삭제");
		btCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				getDelete();
			}
		});
		
	 	JLabel title = new JLabel("[ 장바구니 목록 ]");
	 	JLabel price = new JLabel("주문 총금액:");
	 	tfSum = new JTextField(10);
	 	tfSum.setEditable(false);
	 	
	 	
	 	table = new JTable(rowData, colnames);
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
				
				int row = table.getSelectedRow();
				bagNo = Integer.parseInt(rowData.get(row).get(5));
				int ino = Integer.parseInt(rowData.get(row).get(1));
				new ItemDetail_POP(ino, null);
				
			}
		});
	 	
	 	
	 	JScrollPane jsp = new JScrollPane(table);
	 	
	 	getBag();
	 	
	 	JPanel panbtn = new JPanel();
	 	panbtn.setLayout(new FlowLayout(FlowLayout.RIGHT));
	 	panbtn.add(btCancel);
	 	
	 	JPanel panLb = new JPanel();
	 	panLb.add(title);
	 	
	 	JPanel panTitle = new JPanel();
	 	panTitle.setLayout(new GridLayout(1,3));
	 	panTitle.add(new JLabel(""));
	 	panTitle.add(panLb);
	 	panTitle.add(panbtn);
	 	
	 	
	 	JPanel panUp = new JPanel();
	 	panUp.setLayout(new BorderLayout());
	 	panUp.add(panTitle,BorderLayout.NORTH);
	 	panUp.add(jsp,BorderLayout.CENTER);
	 	
	 	JPanel panDown = new JPanel();
	 	panDown.setLayout(new FlowLayout(FlowLayout.RIGHT));
	 	panDown.add(price);
	 	panDown.add(tfSum);
	 	
	 	
	 	btOrder = new JButton("주문하기");
	 	panDown.add(btOrder);
	 	btOrder.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new PayFrame(0, 0, p);
			}
		});
	 	
	 	setLayout(new BorderLayout());
	 	add(panUp,BorderLayout.CENTER);
	 	add(panDown, BorderLayout.SOUTH);
	 	
	 	setSize(800, 600);
	 	setVisible(true);
	 	
	 	
	}
	
	protected void getDelete() {
		// TODO Auto-generated method stub
		if(bagNo == 0) {
			JOptionPane.showMessageDialog(null, "장바구니에서 지울 상품을 클릭해주세요.");
		}
		else {
			BagDao dao = new BagDao();
			int re = dao.getDelete(bagNo);
			if(re >= 1) {
				JOptionPane.showMessageDialog(null, "장바구니 상품을 지웠습니다.");
				getBag();
			}
			else {
				JOptionPane.showMessageDialog(null, "장바구니 상품삭제를 실패하였습니다.");
			}
		}
	}

	void getBag() {
		// TODO Auto-generated method stub
		rowData.clear();
		allPrice = 0;
		BagDao dao = new BagDao();
		list = dao.getBag(mno);

		for(ArrayList<String> aL : list) {
			Vector<String> v = new Vector<String>();
			v.add(aL.get(0));
			v.add(aL.get(4));
			v.add(aL.get(1));
			v.add(aL.get(2));
			v.add(aL.get(3));
			v.add(aL.get(5));
			allPrice += Integer.parseInt(aL.get(3));	
			
			rowData.add(v);
		}
		table.updateUI();	
		tfSum.setText(allPrice+"");
	}


}