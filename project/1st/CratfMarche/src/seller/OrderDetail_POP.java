package seller;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import marcheDao.ItemDao;
import marcheDao.OdetailDao;

public class OrderDetail_POP extends JFrame {
	
	JTextField tfOno;
	JTextField tfMname;
	JTextField tfIname;
	JTextField tfNum;
	JTextField tfPrice;
	JTextField tfDate;
	JTextField tfPerson;
	JTextField tfAddr;
	JTextField tfTel;
	JTextField tfShip;
	OdetailDao dao;
	ArrayList<String> list;
	
	public static int odno;
	
	public OrderDetail_POP()
	{
		
		 dao = new OdetailDao();
	     list = dao.getOdetail(odno);
				
		//odno,name,iname,odnum, odprice, odate, oname, oaddr, otel, ship
	    //  1   2      3    4       5       6      7      8     9     10	 
		
		tfOno = new JTextField(list.get(0).toString());
		tfMname = new JTextField(list.get(1).toString());
		tfIname = new JTextField(list.get(2).toString());
		tfNum = new JTextField(list.get(3).toString());
		tfPrice = new JTextField(list.get(4).toString());
		tfDate = new JTextField(list.get(5).toString());
		tfPerson = new JTextField(list.get(6).toString());
		tfAddr = new JTextField(list.get(7).toString());
		tfTel = new JTextField(list.get(8).toString());
		tfShip = new JTextField(list.get(9).toString());
		
		JPanel up = new JPanel();
		up.setLayout(new GridLayout(10,2));
		up.add(new JLabel("주문번호:"));
		up.add(tfOno);
		up.add(new JLabel("주문자명:"));
		up.add(tfMname);
		up.add(new JLabel("상품명:"));
		up.add(tfIname);
		up.add(new JLabel("수량:"));
		up.add(tfNum);
		up.add(new JLabel("주문가격:"));
		up.add(tfPrice);
		up.add(new JLabel("주문일:"));
		up.add(tfDate);
		up.add(new JLabel("수취인이름:"));
		up.add(tfPerson);
		up.add(new JLabel("수취인주소:"));
		up.add(tfAddr);
		up.add(new JLabel("수취인연락처:"));
		up.add(tfTel);
		up.add(new JLabel("배송상태:"));
		up.add(tfShip);
		
		add(up);
		
		setSize(400, 300);
		setVisible(true);
		
	
    }
}