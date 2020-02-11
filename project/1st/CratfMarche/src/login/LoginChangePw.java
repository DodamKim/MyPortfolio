package login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;


import marcheDao.MemberDao;

public class LoginChangePw extends JFrame{

	JPasswordField tfPw;
	JPasswordField tfCheckPw;
	
	JButton btUpdate;
	
	JLabel errorMsg1,errorMsg2;
	String id;
	
	LoginChangePw(String id){
		
		this.id = id;
		tfPw = new JPasswordField(30);
		tfCheckPw = new JPasswordField(30);
		
		errorMsg1 = new JLabel("");
		errorMsg1.setForeground(Color.RED);
		errorMsg2 = new JLabel("");
		errorMsg2.setForeground(Color.RED);
		
		btUpdate = new JButton("비밀번호 변경");
		btUpdate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				updateNewPw();
				
			}
		});
		
		JPanel panTf = new JPanel();
		panTf.setLayout(new GridLayout(4,2));
		panTf.add(new JLabel("비밀번호 입력"));
		panTf.add(tfPw);
		panTf.add(new JLabel(""));
		panTf.add(errorMsg1);
		panTf.add(new JLabel("비밀번호 확인"));
		panTf.add(tfCheckPw);
		panTf.add(new JLabel(""));
		panTf.add(errorMsg2);
		
		JPanel panBt = new JPanel();
		panBt.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panBt.add(btUpdate);
		
		
		setLayout(new BorderLayout());
		add(panTf, BorderLayout.CENTER);
		add(panBt, BorderLayout.SOUTH);
		
		setTitle("[ 비밀번호 변경 ]");
		setSize(700, 300);
		setResizable(false);
		setVisible(true);
	}

	protected void updateNewPw() {
		
		String pw = tfPw.getText();
		String checkPw = tfCheckPw.getText();
		
		pw = pw.replaceAll(" " , "");
		checkPw = checkPw.replaceAll(" " , "");
		
		if (pw.equals("")|| pw==null || pw.length()<8 || pw.length()>16) {
			errorMsg1.setText("8~16자의 영문 소문자, 숫자만 사용 가능합니다.");
		}
		else if  (checkinput(pw) == false) {
			errorMsg1.setText("8~16자의 영문 소문자, 숫자만 사용 가능합니다.");
		}
		else if (!pw.equals(checkPw)) {
			errorMsg2.setText("비밀번호가 일치하지 않습니다.");
		}
		else {
			MemberDao dao = new MemberDao();
			int re = dao.updateNewPw(id, pw);
			 if(re >=1 )
             {
                 JOptionPane.showMessageDialog(null, "비밀번호가 변경되었습니다.\r\n 변경된 비밀번호로 로그인해주세요.");
                 dispose();
             }
             else
             {
                 JOptionPane.showMessageDialog(null, "비밀번호 변경이 실패하였습니다.");
                 dispose();
			
             }
		}

		
	}
	
	private boolean checkinput(String input) {		
		// 숫자와 영문자를 입력받을때 입력받은 텍스트 내에 다른 문자가 입력되면 false 숫자와 영문자만 있으면 true반환 메소드
		boolean check = true;
		char chrInput;
		
		for (int i = 0; i < input.length(); i++) {
 
			chrInput = input.charAt(i); // 입력받은 텍스트에서 문자 하나하나 가져와서 체크
		   
			if (chrInput >= 0x61 && chrInput <= 0x7A) {
			    // 영문(소문자) OK!
			} 
			else if (chrInput >= 0x30 && chrInput <= 0x39) {
			    // 숫자 OK!
			} 
			else {
			    check = false;
				return check;   // 소문자도 아니고 숫자도 아님!
			    
			}

		}
		return check;
	}
}
