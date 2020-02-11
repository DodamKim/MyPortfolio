package item;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import cart.PayFrame;
import marcheDao.BagDao;
import marcheDao.ItemDao;
import marcheVo.BagVo;

public class ItemDetail_POP extends JFrame {

	// LoginMainPanel class 에서 isMember() 메소드 안에
	// ItemDetailFrame.mno = mno; 추가 후 변수 선언해줘야 함.
	public static int mno;
	
	JPanel panItem;
	JTabbedPane tab;
	JPanel panItemDtImg;
	JPanel panItemDt1;
	JPanel panItemDt2;
	JPanel panBt;

	JTextField tfToTalprice;
	
	JLabel lbimg;
	JLabel lbIname;
	JLabel lbSellNick;
	JLabel lbIprice;
	JComboBox<Integer> cbInum;

	JTextArea taItem;

	JButton btBag;
	JButton btBuy;
	
	String iname;
	String sellNick;
	String iprice;
	ImageIcon icon;
	
	Image img;
	int num;
	int ino;
	ItemDao dao;
	//BagVo b;
	ItemMainPanel p;
	
	// iname, nickname, iprice, img, idtext,
	public ItemDetail_POP(int ino, ItemMainPanel p) {
		// TODO Auto-generated constructor stub
		this.p= p;
		
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
		
		//System.out.println(ino);
		dao = new ItemDao();
		ArrayList<String> listIt = new ArrayList<String>();
		listIt = dao.getItem(ino);

		ArrayList<ArrayList<String>> listRv;
		
		Vector<Integer> vNum = new Vector<Integer>();
		for(int i=0; i<100; i++) {
			vNum.add(i);
		}

		iname = listIt.get(0);
		sellNick = listIt.get(1);
		iprice = listIt.get(2);		
		icon = new ImageIcon("C:\\marche\\item/"+listIt.get(3));
		
		lbimg = new JLabel(icon);
		lbIname = new JLabel(iname);
		lbSellNick = new JLabel(sellNick);
		lbIprice = new JLabel(iprice+" 원");
		
		tfToTalprice = new JTextField();
		tfToTalprice.setEditable(false);
		cbInum = new JComboBox<Integer>(vNum);
		
		taItem = new JTextArea();
		taItem.setText(listIt.get(4));
		taItem.setEditable(false);
		taItem.setLineWrap(true); // 자동 줄바꿈 
		JScrollPane jspItem = new JScrollPane(taItem);

		btBag = new JButton("장바구니 담기");
		btBuy = new JButton("바로구매");

		panItem = new JPanel(new BorderLayout());
		panItemDtImg = new JPanel(new BorderLayout());
		panItemDtImg.setPreferredSize(new Dimension(600, 450));
		panItemDt1 = new JPanel(new BorderLayout());
		//panItemDt1.setBounds(20, 20, 20, 20);
		panItemDt2 = new JPanel(new GridLayout(17, 1));
		panItemDt2.setPreferredSize(new Dimension(250, 450));
		//panItemDt2.setBounds(20, 20, 20, 20);
		panBt = new JPanel(new GridLayout(1,2));
		panBt.add(btBag);
		panBt.add(btBuy);

		panItem.add(panItemDtImg, BorderLayout.WEST);
		panItem.add(panItemDt1, BorderLayout.CENTER);
		panItem.add(panItemDt2, BorderLayout.EAST);

		panItemDtImg.add(new JLabel("[ 상품 이미지 ]"), BorderLayout.NORTH);
		panItemDtImg.add(lbimg, BorderLayout.CENTER);
		panItemDt1.add(new JLabel("[ 상품 상세 보기 ]"), BorderLayout.NORTH);
		panItemDt1.add(jspItem, BorderLayout.CENTER);
		
		cbInum.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				num = (Integer)cbInum.getSelectedItem();
				tfToTalprice.setText(Integer.parseInt(iprice)*num+" 원");
			}
		});
		
		btBag.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				if(tfToTalprice.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "구매 수량을 선택해주세요 :)");
					return;
				}
				BagVo b = new BagVo();
				b.setBagnum(num);
				b.setIno(ino);
				b.setMno(mno);

					
				BagDao bagD = new BagDao();
				int re = bagD.insertBag(b);
				
				if(re >=1 ) {
					JOptionPane.showMessageDialog(null, "상품이 장바구니에 담겼습니다.");
				} else {
					JOptionPane.showMessageDialog(null, "장바구니 담기에 실패했습니다!");
				}
			}
		});
		
		btBuy.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				if(tfToTalprice.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "구매 수량을 선택해주세요 :)");
					return;
				}
				
				new PayFrame(ino, num, null);
			}
		});
		
		panItemDt2.add(new JLabel("[ 상품명 ]"));
		panItemDt2.add(lbIname);
		panItemDt2.add(new JLabel(""));
		panItemDt2.add(new JLabel("[ 셀러 닉네임 ]"));
		panItemDt2.add(lbSellNick);
		panItemDt2.add(new JLabel(""));
		panItemDt2.add(new JLabel("[ 상품 가격 ]"));
		panItemDt2.add(lbIprice);
		panItemDt2.add(new JLabel(""));
		panItemDt2.add(new JLabel("[ 구매 수량 ]"));
		panItemDt2.add(cbInum);
		panItemDt2.add(new JLabel(""));
		panItemDt2.add(new JLabel("[ 총 구매 가격 ]"));
		panItemDt2.add(tfToTalprice);
		for(int i=1; i<3; i++) {
			panItemDt2.add(new JLabel(""));
		}
		panItemDt2.add(panBt);

		tab = new JTabbedPane();
		tab.addTab("QnA", new ItemQnaPanel(ino));
		tab.addTab("Review", new ItemReviewPanel(ino));

		setLayout(new GridLayout(2, 1));
		add(panItem);
		add(tab);

		setTitle("[ Craft Marche ]");
		setSize(1200, 900);
		setVisible(true);
	}
}
