package login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import basic.BasicFrame;
import cart.BagPanel;
import cart.PayFrame;
import item.ItemDetail_POP;
import item.ItemQnaPanel;
import manager.ManagerTab;
import marcheDao.MemberDao;
import myPage.MyInfo_POP;
import myPage.MyPageTab;
import seller.ItemCheckPanel;
import seller.ItemInsert_POP;

public class LoginMainPanel extends JPanel {
	
	JTextField tfId;
	JPasswordField  tfPw;	
	JButton btLogin;
	JButton btNoMbLogin;
	JButton btCheckId;
	JButton btCheckPw;
	JButton btJoin;
	JOptionPane msg; 
	int mno; 
	//번호 받으려는 클래스 안에 public static int mno 생성후
	//로그인 성공시 담아줌 - 199번째줄  
	
	public LoginMainPanel(){
		
		msg = new JOptionPane();
		
		tfId = new JTextField(30);
		tfPw = new JPasswordField(30);
		
		
		btLogin = new JButton("로그인");
		btLogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				isMember();
				
			}
		});
		
		btNoMbLogin = new JButton("로그 아웃");		
		
		btNoMbLogin.setEnabled(false); 					
		// 로그아웃버튼 비활성화
		
		btNoMbLogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				mno = 0;
				msg.showMessageDialog(null, "로그아웃 되었습니다.");
				tfId.setText("");
				tfPw.setText("");
				btNoMbLogin.setEnabled(false);
				btLogin.setEnabled(true);
				btCheckId.setEnabled(true);
				btCheckPw.setEnabled(true);
				btJoin.setEnabled(true);				//로그아웃 눌렀을때 비활성화 했던 버튼들 활성화
				tfId.enable(true);
				tfPw.enable(true);						//텍스트필드 잠금 해제	
				tfId.setBackground(Color.WHITE);
				tfPw.setBackground(Color.WHITE);		//텍스트 필드 배경색 원래대로
				
			}
		});
		
		btCheckId = new JButton("아이디 찾기");
		btCheckId.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new LoginCheckId_POP();
			}
		});
		
		btCheckPw = new JButton("비밀번호 찾기");
		btCheckPw.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new LoginCheckPw_POP();
			}
		});
		
		btJoin = new JButton("회원가입");
		btJoin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new LoginJoin_POP();
				
			}
		});
		
		
		
		JLabel lbMain= new JLabel("CRAFT MARCHE");
		lbMain.setFont(new Font("Serif", Font.BOLD, 15));
		lbMain.setForeground(Color.RED);
		lbMain.setFont(lbMain.getFont().deriveFont(70.0f));
		
		//아이디 패스워드 텍스트필드가 들어간 판넬
		JPanel panTf = new JPanel();				
		panTf.setLayout(new GridLayout(2,2,10,10));
		panTf.add(new JLabel("아이디 : "));
		panTf.add(tfId);
		panTf.add(new JLabel("패스워드 : "));
		panTf.add(tfPw);
		
		//로그인버튼과 비회원 로그인 버튼이 들어간판넬
		JPanel panBtLogin = new JPanel();			
		panBtLogin.setLayout(new GridLayout(3,1,10,10));
		panBtLogin.add(btLogin);
		panBtLogin.add(btNoMbLogin);
		panBtLogin.add(btJoin);
		
		//아이디찾기 비밀번호 찾기 버튼이 들어간 판넬
		JPanel panBtCheck = new JPanel();			
		panBtCheck.setLayout(new GridLayout(2,2,10,10));
		panBtCheck.add(btCheckId);
		panBtCheck.add(btCheckPw);
		panBtCheck.add(new JLabel(""));
		
		
		//라벨, panTf, panBtLogin, panBtCheck, 회원가입 버튼이 들어간 판넬
		JPanel panCenter = new JPanel();			
		panCenter.setLayout(new GridLayout(4,1,10,10));
		panCenter.add(lbMain);
		panCenter.add(panTf);
		panCenter.add(panBtLogin);
		panCenter.add(panBtCheck);
		
		
		JPanel panLeft = new JPanel();
		panLeft.add(new JLabel("                                                                     "
			+ "                                                                                       "));
        
		JPanel panRight = new JPanel();
		panRight.add(new JLabel("                                                                     "
			+ "                                                                                       "));
		
		
		setLayout(new BorderLayout());
		add(panCenter, BorderLayout.CENTER);
		add(panLeft, BorderLayout.WEST);
		add(panRight, BorderLayout.EAST);
		
		
		setSize(1600, 900);
		setVisible(true);
		
	}
	
	protected void isMember() {
		
		boolean flag = false;
		String id = tfId.getText();
		String pw = tfPw.getText();
		id = id.replaceAll(" " , "");
		pw = pw.replaceAll(" " , "");
			
		MemberDao dao = new MemberDao();
		flag = dao.isMember(id, pw);
		
		if(flag == false) {
			msg.showMessageDialog(null, "로그인에 실패하였습니다.");
		}
		
		else{
			mno = dao.getMyMno(id);		
			
			if(mno == 1) {
				msg.showMessageDialog(null, "관리자님 로그인에 성공하였습니다.");
				
			}else {
				msg.showMessageDialog(null, id+"님 로그인에 성공하였습니다.");
			}
			btLogin.setEnabled(false);
			btNoMbLogin.setEnabled(true);
			btCheckId.setEnabled(false);
			btCheckPw.setEnabled(false);
			btJoin.setEnabled(false);//로그인시 로그아웃 버튼 빼고 모두 비활성화
			tfId.enable(false);
			tfPw.enable(false);	//텍스트필드 잠금 해제	
			tfId.setBackground(Color.lightGray);
			tfPw.setBackground(Color.lightGray);//텍스트필드 배경색 변경
			
			MyPageTab.mno = mno;
			PayFrame.mno = mno;
			BagPanel.mno = mno;
			ItemDetail_POP.mno =mno;
			ItemQnaPanel.mno = mno;
			BasicFrame.mno = mno;
			ManagerTab.mno = mno;
			ItemCheckPanel.mno = mno;
			MyInfo_POP.manager = mno;
			ItemInsert_POP.mno = mno;
			
			//원하는 곳으로 mno 전송
			
		}
		
	}

	
}
