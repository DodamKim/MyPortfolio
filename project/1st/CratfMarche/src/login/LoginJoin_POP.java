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
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import marcheDao.MemberDao;
import marcheVo.MemberVo;


public class LoginJoin_POP extends JFrame{

	JTextField tfId;
	JPasswordField tfPw;
	JPasswordField tfCheckPw;
	JTextField tfName;
	JTextField tfNickname;
	JTextField tfTel;
	JTextField tfBirth;
	JTextField tfAddr;
	JTextField tfAnw;
	JTextField tfEmail;
	JOptionPane msg; // 팝업 메세지
	JButton btNew;
	String cbValue = "";
	String[] qns = {"질문을 선택하세요.","자신의 보물 1호는?", "내가 좋아하는 캐릭터는?", "가장 친한친구 이름은?", "어릴적 고향은?", "다시 태어나면 되고 싶은것은?"};
	JComboBox<String> cbQns;

	JLabel errorMsg1, errorMsg2, errorMsg3, errorMsg4, errorMsg5, errorMsg6, errorMsg7, errorMsg8, errorMsg9, errorMsg10, errorMsg11;	
	//라벨 빨간 글씨 메세지
	
	LoginJoin_POP(){
		tfId = new JTextField(30);
		tfPw = new JPasswordField(30);
		tfCheckPw = new JPasswordField(30);
		tfName = new JTextField(30);
		tfNickname = new JTextField(30);
		tfTel = new JTextField(30);
		tfBirth = new JTextField(30);
		tfAddr = new JTextField(30);
		tfAnw = new JTextField(30);
		tfEmail = new JTextField(30);
		
		errorMsg1 = new JLabel("");
		errorMsg1.setForeground(Color.RED);	
		errorMsg2 = new JLabel("");
		errorMsg2.setForeground(Color.RED);	
		errorMsg3 = new JLabel("");
		errorMsg3.setForeground(Color.RED);	
		errorMsg4 = new JLabel("");
		errorMsg4.setForeground(Color.RED);	
		errorMsg5 = new JLabel("");
		errorMsg5.setForeground(Color.RED);	
		errorMsg6 = new JLabel("");
		errorMsg6.setForeground(Color.RED);	
		errorMsg7 = new JLabel("");
		errorMsg7.setForeground(Color.RED);	
		errorMsg8 = new JLabel("");
		errorMsg8.setForeground(Color.RED);	
		errorMsg9 = new JLabel("");
		errorMsg9.setForeground(Color.RED);	
		errorMsg10 = new JLabel("");
		errorMsg10.setForeground(Color.RED);	
		errorMsg11 = new JLabel("");
		errorMsg11.setForeground(Color.RED);	//에러메세지 빨간색으로변경
		
		
		btNew = new JButton("가입");
		btNew.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				insertMember();
				
			}
		});
		
		cbQns = new JComboBox<String>(qns);
		
		cbQns.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cbValue = (String)cbQns.getSelectedItem();
				
				
			}
		});
		
		JPanel panTf = new JPanel();		
		panTf.setLayout(new GridLayout(22,2,10,10));
		panTf.add(new JLabel("아이디 : "));
		panTf.add(tfId);
		panTf.add(new JLabel(" "));
		panTf.add(errorMsg1);

		panTf.add(new JLabel("패스워드 : "));
		panTf.add(tfPw);
		panTf.add(new JLabel(" "));
		panTf.add(errorMsg2);
		
		panTf.add(new JLabel("패스워드 확인 : "));
		panTf.add(tfCheckPw);
		panTf.add(new JLabel(" "));
		panTf.add(errorMsg3);
		
		panTf.add(new JLabel("이름 : "));
		panTf.add(tfName);
		panTf.add(new JLabel(" "));
		panTf.add(errorMsg4);
		
		panTf.add(new JLabel("닉네임 : "));
		panTf.add(tfNickname);
		panTf.add(new JLabel(" "));
		panTf.add(errorMsg5);
		
		panTf.add(new JLabel("연락처 : "));
		panTf.add(tfTel);
		panTf.add(new JLabel(" "));
		panTf.add(errorMsg6);
		
		panTf.add(new JLabel("생년월일(ex.19910101) : "));
		panTf.add(tfBirth);
		panTf.add(new JLabel(" "));
		panTf.add(errorMsg7);
		
		panTf.add(new JLabel("주소 : "));
		panTf.add(tfAddr);
		panTf.add(new JLabel(" "));
		panTf.add(errorMsg8);
		
		panTf.add(new JLabel("질문 : "));
		panTf.add(cbQns);
		panTf.add(new JLabel(" "));
		panTf.add(errorMsg9);
		
		panTf.add(new JLabel("답 : "));
		panTf.add(tfAnw);
		panTf.add(new JLabel(" "));
		panTf.add(errorMsg10);
		
		panTf.add(new JLabel("이메일 : "));
		panTf.add(tfEmail);
		panTf.add(new JLabel(" "));
		panTf.add(errorMsg11);								
		
		
		JPanel panBt = new JPanel();//버튼을 담은 판넬
		panBt.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panBt.add(btNew);
		
		
		setLayout(new BorderLayout());
		add(panTf, BorderLayout.CENTER);
		add(panBt, BorderLayout.SOUTH);
		
		
		setTitle("[ 회원가입 ]");
		setSize(600, 700);
		setResizable(false);
		setVisible(true);
		
	}
	protected void insertMember() {
		// TODO Auto-generated method stub
		String id = tfId.getText();
		String pw = tfPw.getText();
		String name = tfName.getText();
		String nickname = tfNickname.getText();
		String tel = tfTel.getText();
		String birth = tfBirth.getText();
		String addr = tfAddr.getText();
		String anw = tfAnw.getText();
		String email = tfEmail.getText();
		String checkPw = tfCheckPw.getText();
		
		id = id.replaceAll(" " , "");
		pw = pw.replaceAll(" " , "");
		name = name.replaceAll(" " , "");
		tel = tel.replaceAll(" " , "");
		birth = birth.replaceAll(" " , "");
		anw = anw.replaceAll(" " , "");
		email = email.replaceAll(" " , "");
		checkPw = checkPw.replaceAll(" " , "");			//공백허용x
		
		MemberDao dao = new MemberDao();
		MemberVo vo = new MemberVo();
		vo.setId(id);
		vo.setPw(pw);
		vo.setName(name);
		vo.setNickname(nickname);
		vo.setTel(tel);
		vo.setBirth(birth);
		vo.setAddr(addr);
		vo.setQns(cbValue);
		vo.setAnw(anw);
		vo.setEmail(email);
		
		
		errorMsg1.setText("");
		errorMsg2.setText("");
		errorMsg3.setText("");
		errorMsg4.setText("");
		errorMsg5.setText("");
		errorMsg6.setText("");
		errorMsg7.setText("");
		errorMsg8.setText("");
		errorMsg9.setText("");
		errorMsg10.setText("");
		errorMsg11.setText("");			// 에러 메세지 초기화

	
		
		if(id.equals("") || id==null || id.length()<5 || id.length()>20) {			
			errorMsg1.setText("5~20자의 영문 소문자, 숫자만 사용 가능합니다.");
		}
		else if  (checkinput(id) == false) {
			errorMsg1.setText("5~20자의 영문 소문자, 숫자만 사용 가능합니다.");
		}
		else if (pw.equals("")|| pw==null || pw.length()<8 || pw.length()>16) {
			errorMsg2.setText("8~16자의 영문 소문자, 숫자만 사용 가능합니다.");
		}
		else if  (checkinput(pw) == false) {
			errorMsg2.setText("8~16자의 영문 소문자, 숫자만 사용 가능합니다.");
		}
		else if (!pw.equals(checkPw)) {
			errorMsg3.setText("비밀번호가 일치하지 않습니다.");
		}
		else if (name.equals("") || name == null) {
			errorMsg4.setText("필수 정보입니다.");
		}
		else if (nickname.equals("") || nickname == null) {
			errorMsg5.setText("필수 정보입니다.");
		}
		else if (tel.equals("") || tel  == null) {
			errorMsg6.setText("전화번호를 입력하세요.");
		}
		else if (birth.equals("") || birth == null ||birth.length()!= 8 ) {
			errorMsg7.setText("8자리의 숫자를 입력하세요. ex)19910101");
		}
		else if (addr.equals("") || addr  == null) {
			errorMsg8.setText("주소를 입력하세요.");
		}
		else if (cbValue.equals("질문을 선택하세요.")) {
			errorMsg9.setText("질문을 선택하세요.");
		}
		else if (anw.equals("") || anw  == null) {
			errorMsg10.setText("질문의 답을 입력하세요.");
		}
		else if (email.equals("") || email  == null) {
			errorMsg11.setText("이메일을 입력하세요.");
		}
		else {
						
			int re = dao.insertMember(vo);
			  if(re >=1 )
              {
                  JOptionPane.showMessageDialog(null, "가입성공");
                  dispose();
              }
              else
              {
                  JOptionPane.showMessageDialog(null, "가입실패");
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