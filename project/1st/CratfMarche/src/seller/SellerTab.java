package seller;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class SellerTab extends JPanel{
	
	public SellerTab()
	{
		
		JTabbedPane tab = new JTabbedPane();
		
		tab.addTab("판매관리", new ItemCheckPanel());
		tab.addTab("주문관리", new OrderCheckPanel());
		
		setLayout(new BorderLayout());
		add(tab);
		setSize(1600,900);
		setVisible(true);
		
	}
}