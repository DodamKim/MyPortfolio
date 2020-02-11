package seller;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class SellerFrame extends JFrame{
	
	ItemCheckPanel icheck;
	OrderCheckPanel ocheck;
	
	public SellerFrame()
	{
		icheck = new ItemCheckPanel();
		ocheck =  new OrderCheckPanel();
		
		JTabbedPane tab = new JTabbedPane();
		
		tab.addTab("판매관리", icheck);
		tab.addTab("주문관리", ocheck);
		
		add(tab);
		add(tab);
		
		setSize(1200,800);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
         new SellerFrame();
	}

}