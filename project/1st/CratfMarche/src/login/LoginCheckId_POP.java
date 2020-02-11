package login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import marcheDao.MemberDao;

public class LoginCheckId_POP extends JFrame {

	JTextField tfBirth;
	JTextField tfEmail;
	
	JButton btCheck;
	JLabel errorMsg;
	
	LoginCheckId_POP(){
		
		tfBirth = new JTextField(25);
		tfEmail = new JTextField(25);
		
		errorMsg = new JLabel("");
		errorMsg.setForeground(Color.RED);
		
	
		btCheck = new JButton("확인");
		btCheck.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				isMyId();
			}
		});
		
		JPanel panTf = new JPanel();
		panTf.setLayout(new GridLayout(3, 2, 10, 10));
		panTf.add(new JLabel("생년월일 ex) 19910101 : "));
		panTf.add(tfBirth);
		panTf.add(new JLabel("이메일: "));
		panTf.add(tfEmail);
		panTf.add(new JLabel(""));
		panTf.add(errorMsg);
		
		
		setLayout(new BorderLayout());
		add(panTf, BorderLayout.CENTER);
		add(btCheck, BorderLayout.SOUTH);
		
		setTitle("[ 아이디 찾기 ]");
		setSize(400, 300);
		setResizable(false);
		setVisible(true);
		
	}

	protected void isMyId() {
		String birth = tfBirth.getText();
		String email = tfEmail.getText();
		
		birth = birth.replaceAll(" " , "");
		email = email.replaceAll(" " , "");
		
		if (birth.equals("") || birth == null ||birth.length()!= 8 ) {
			errorMsg.setText("8자리의 숫자를 입력하세요.");
		}
		else if (email.equals("") || email  == null) {
			errorMsg.setText("이메일을 입력하세요.");
		}
		else {
		
			MemberDao dao = new MemberDao();
			String id = dao.isMyId(birth, email);
			if (id == null) {
				JOptionPane.showMessageDialog(null, "해당 정보의 아이디가 존재하지 않습니다.");
			}
			else {
				JOptionPane.showMessageDialog(null, "아이디는 '" +id+"'입니다.");
				dispose();
			}
		}
		
	}
	
	
}
