package nori;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class NoriTab extends JPanel {

	public NoriTab(){
	
		JTabbedPane tab =new JTabbedPane();
		tab.addTab("공지사항", new NoticeListPanel());
		tab.addTab("커뮤니티", new QnaListPanel());
		tab.addTab("프리마켓&공방", new BoardListPanel());
		
		setLayout(new BorderLayout());
		add(tab);
		setSize(1600, 900);
		setVisible(true);
		
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new NoriTab();
	}
	
}
