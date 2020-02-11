package cart;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import marcheDao.BagDao;
import marcheDao.ItemDao;
import marcheDao.OdetailDao;
import marcheDao.OrdersDao;
import marcheVo.OdetailVo;
import marcheVo.OrdersVo;

public class PayFrame extends JFrame {
	
	JTable table;
	Vector<String> colnames;
	Vector<Vector<String>> rowData;
	JTextField tfAddr;
	JTextField tfTel;
	JTextField tfPerson;
	JTextField tfSum;;
	JRadioButton rbCard;
	JRadioButton rbCash;
	BagPanel p;
	
	ArrayList<String> listItem;
	ArrayList<ArrayList<String>> listBag;
	public static int mno; 
	int allPrice = 0;
	int checkPay = 0;
	String coc;
	
	int itemNo, itemNum;
	
	public PayFrame(int itemNo, int itemNum, BagPanel bag)
	{
		
		this.p = bag;
		this.itemNo = itemNo;
		this.itemNum = itemNum;
		
		
		tfAddr = new JTextField(10);
		tfTel = new JTextField(10);
		tfPerson = new JTextField(10);
		tfSum = new JTextField(10);
		tfSum.setEditable(false);
		
		colnames = new Vector<String>();
		rowData = new Vector<Vector<String>>();
		colnames.add("셀러명");
		colnames.add("상품번호");
		colnames.add("상품명");
		colnames.add("수량");
		colnames.add("가격");		
		
		table = new JTable(rowData, colnames);
	 	JScrollPane jsp = new JScrollPane(table);
	 	
	 	if(itemNo == 0) {
	 		getBag();
	 		
	 	}
	 	else {
	 		getItem();
	 	}
	 	
	 	
	 	JButton btPay = new JButton("결제하기");
	 	btPay.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				insertOrders();
				
			}
		});
	 	
	 	JButton btCancel = new JButton("결제취소");
	 	btCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});
	 	
	 	rbCard = new JRadioButton("카드");
	 	rbCard.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				coc = "카드결제";
				checkPay = 1;
			}
		});
	 	rbCash = new JRadioButton("현금");
	 	rbCash.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				coc = "현금결제";
				checkPay = 2;
			}
		});
	 	
		ButtonGroup grop = new ButtonGroup();
		
	 	grop.add(rbCard);
	 	grop.add(rbCash);
	 	
	 	JLabel title = new JLabel("주문 결제 목록");
	 	JPanel label = new JPanel();
	 	label.add(title);
	 	
	 	JPanel up = new JPanel();
	 	up.setLayout(new BorderLayout());
	 	up.add(jsp,BorderLayout.CENTER);
	 	up.add(label,BorderLayout.NORTH);
	 	
	 	JPanel downLeft = new JPanel();
	 	downLeft.setLayout(new GridLayout(4,2));
	 	downLeft.add(new JLabel("주소:"));
	 	downLeft.add(tfAddr);
	 	downLeft.add(new JLabel("연락처:"));
	 	downLeft.add(tfTel);
	 	downLeft.add(new JLabel("수령인:"));
	 	downLeft.add(tfPerson);
	 	downLeft.add(new JLabel("총결제금액:"));
	 	downLeft.add(tfSum);
	 	
	 	JPanel downRight = new JPanel();
	 
	 	downRight.add(rbCash);
	 	downRight.add(rbCard);
		downRight.add(btPay);
		downRight.add(btCancel);
		
		JPanel down = new JPanel();
		down.setLayout(new BorderLayout());
		down.add(downLeft,BorderLayout.CENTER);
		down.add(downRight, BorderLayout.EAST);
		
		setLayout(new BorderLayout());
	 	add(up,BorderLayout.CENTER);
	 	add(down, BorderLayout.SOUTH);
	 	
	 	setSize(800, 600);
	 	setVisible(true);
	 	
addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				p.getBag();
				
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
	 	
	}
	
	protected void insertOrders() {
		OrdersVo ovo = new OrdersVo();
		
		ArrayList<OdetailVo> odList = new ArrayList<OdetailVo>();
		
		ovo.setTotal(Integer.parseInt(tfSum.getText()));
		ovo.setCoc(coc);
		ovo.setMno(mno);
		ovo.setOname(tfPerson.getText());
		ovo.setOtel(tfTel.getText());
		ovo.setOaddr(tfAddr.getText());
		
		
		for(Vector<String> v: rowData) {
			OdetailVo odvo = new OdetailVo();
			odvo.setIno(Integer.parseInt(v.get(1)));
			odvo.setOdnum(Integer.parseInt(v.get(3)));
			odvo.setOdprice(Integer.parseInt(v.get(4)));
			
			odList.add(odvo);
		}
		
		String addr = tfAddr.getText();
		String person = tfPerson.getText();
		String tel= tfTel.getText();
		
		addr = addr.replaceAll(" " , "");
		person = person.replaceAll(" " , "");
		tel = tel.replaceAll(" " , "");
		
		if (addr.equals("") || addr  == null) {
			JOptionPane.showMessageDialog(null, "주소를 입력하세요.");
		}
		else if (person.equals("") || person  == null) {
			JOptionPane.showMessageDialog(null, "수취인을 입력하세요.");
		}
		else if (tel.equals("") || tel  == null) {
			JOptionPane.showMessageDialog(null, "전화번호를 입력하세요.");
		}
		else if (checkPay == 0) {
			JOptionPane.showMessageDialog(null, "결제 방식을 선택하세요.");	
		}
		else {
		
			OrdersDao dao = new OrdersDao();
			int re = dao.insertOrders(ovo, odList);
			
			if(re > 1 )
			{
				JOptionPane.showMessageDialog(null, "주문을 완료 하였습니다.");
				allDelete();
				OdetailDao odao = new OdetailDao();
				odao.changeShip();
				//dispose();
			}
			else
			{
				JOptionPane.showMessageDialog(null, "주문을 실패하였습니다.");
				//dispose();
			}
			
		}
		
	}

	private void allDelete() {
		BagDao dao = new BagDao();
		 int re = dao.allDelete(mno);
		 if(re >= 1) {
			getBag();
		 }
		 else {
			 System.out.println("주문은 됬으나 장바구니 삭제실패");
		 }
	}

	private void getBag() {
		
		rowData.clear();
		allPrice = 0;
		BagDao dao = new BagDao();
		listBag = dao.getBag(mno);
		

		for(ArrayList<String> aL : listBag) {
			Vector<String> v = new Vector<String>();
			v.add(aL.get(0));
			v.add(aL.get(4));
			v.add(aL.get(1));
			v.add(aL.get(2));
			v.add((Integer.parseInt(aL.get(3))*Integer.parseInt(aL.get(2)))+"");
			v.add(aL.get(5));
			
			allPrice += Integer.parseInt(aL.get(3));	
			
			rowData.add(v);
		}
		table.updateUI();	
		tfSum.setText(allPrice+"");
	}
	
	private void getItem() {
		rowData.clear();
		ItemDao dao = new ItemDao();
		listItem = dao.getItem(itemNo);
		System.out.println(listItem);

		Vector<String> v = new Vector<String>();
		int price = Integer.parseInt(listItem.get(2));
		price = itemNum*price;
		v.add(listItem.get(1));
		v.add(String.valueOf(itemNo));
		v.add(listItem.get(0));
		v.add(String.valueOf(itemNum));
		v.add(String.valueOf(price));
		
		rowData.add(v);
		tfSum.setText(price+"");
		
		table.updateUI();
		
	}

}