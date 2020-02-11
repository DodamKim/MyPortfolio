package myPage;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class MyPageTab extends JPanel {

	JButton btMyInfo;
	public static int mno;
	
	public MyPageTab(){
		
		btMyInfo = new JButton("내 정보 보기");
		
		btMyInfo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new MyInfo_POP(mno);
				
			}
		});
		
		JTabbedPane tab =new JTabbedPane();
		
		tab.add("주문현황", new MyOrderPanel(mno));
		tab.addTab("나의 Qna", new MyQnaPanel(mno));
		tab.addTab("나의 리뷰", new MyReviewPanel(mno));
		
		JPanel panBt = new JPanel();
		panBt.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panBt.add(btMyInfo);
		
		setLayout(new BorderLayout());	
		add(tab, BorderLayout.CENTER);
		add(panBt, BorderLayout.NORTH);
		setSize(1600, 900);
		setVisible(true);
		
	}
	
	public static void main(String[] args) {
		new MyPageTab();
	}

}