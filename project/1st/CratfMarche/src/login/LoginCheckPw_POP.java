package login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import marcheDao.MemberDao;

public class LoginCheckPw_POP extends JFrame {

	JTextField tfId;
	JTextField tfAnw;
	JTextField tfPw;
	JLabel errorMsg;
	
	JButton btCheck;
	
	String cbValue = "";
	JComboBox<String> cbQns;
	String[] qns = {"질문을 선택하세요.","자신의 보물 1호는?", "내가 좋아하는 캐릭터는?", "가장 친한친구 이름은?", "어릴적 고향은?", "다시 태어나면 되고 싶은것은?"};
	
	LoginCheckPw_POP(){
		
		cbQns = new JComboBox<String>(qns);
		cbQns.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cbValue = (String)cbQns.getSelectedItem();	//콤보박스 선택 값 불러오기
				
			}
		});
		
		errorMsg = new JLabel("");
		errorMsg.setForeground(Color.RED);
		
		tfId = new JTextField(30);
		tfAnw = new JTextField(30);
		tfPw = new JTextField(30);
		
		btCheck = new JButton("확인");
		btCheck.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				isMyPw();
			}
		});
		
		JPanel panTf = new JPanel();
		panTf.setLayout(new GridLayout(4,2,10,10));
		panTf.add(new JLabel(" 아이디 : "));
		panTf.add(tfId);
		panTf.add(new JLabel(" 질문 : "));
		panTf.add(cbQns);
		panTf.add(new JLabel(" 답 : "));
		panTf.add(tfAnw);
		panTf.add(new JLabel(""));
		panTf.add(errorMsg);
		
		
		JPanel panBt = new JPanel();				//버튼을 담은 판넬
		panBt.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panBt.add(btCheck);
		
		setLayout(new BorderLayout());
		add(panTf, BorderLayout.CENTER);
		add(panBt, BorderLayout.SOUTH);
		
		
		setTitle("[ 비밀번호 찾기 ]");
		setSize(700, 300);
		setResizable(false);
		setVisible(true);
		
		
	}

	protected void isMyPw() {
		String id = tfId.getText();
		String anw = tfAnw.getText();
		
		id = id.replaceAll(" " , "");
		anw = anw.replaceAll(" " , "");		//공백 삭제
		
		boolean boo;
		MemberDao dao = new MemberDao();
		boo = dao.isMyPw(id, cbValue, anw);
		
		if(id.equals("") || id==null || id.length()<5 || id.length()>20) {			
			errorMsg.setText("아이디는 5~20자의 영문 소문자, 숫자만 사용 가능합니다.");
		}
		else if  (checkinput(id) == false) {
			errorMsg.setText("아이디는 5~20자의 영문 소문자, 숫자만 사용 가능합니다.");
		}
		else if (cbValue.equals("질문을 선택하세요.")) {
			errorMsg.setText("질문을 선택하세요.");
		}
		else if (anw.equals("") || anw  == null) {
			errorMsg.setText("질문의 답을 입력하세요.");
		}
		
		else {
			if( boo == true) {
				new LoginChangePw(id);
				dispose();
			}
			else {
				JOptionPane.showMessageDialog(null, "일치하는 아이디가 없습니다.");
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
