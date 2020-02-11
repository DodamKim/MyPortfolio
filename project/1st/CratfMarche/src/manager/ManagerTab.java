package manager;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class ManagerTab extends JPanel {
	
	public static int mno;
	
	public ManagerTab(){
	
		JTabbedPane tab = new JTabbedPane();
		
		tab.add("매출 통계", new SalesStatsPanel(mno));
		tab.addTab("회원 관리", new MemberStatsPanel(mno));
		
		setLayout(new BorderLayout());
		add(tab);
		setSize(1600, 900);
		setVisible(true);
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ManagerTab();
	}
	
}