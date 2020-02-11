package myPage;

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
import javax.swing.JTextField;

import marcheDao.MemberDao;
import marcheVo.MemberVo;

public class MyInfo_POP extends JFrame {

	JTextField tfId;
	JPasswordField tfPw;
	JTextField tfName;
	JTextField tfNickname;
	JTextField tfBirth;
	JTextField tfTel;
	JTextField tfAddr;
	JTextField tfEmail;
	JTextField tfAccount;

	JButton btUpdateMyInfo;
	JButton btUpdateSeller;
	JButton btUpdateManager;

	MemberDao dao;
	public static int manager;
	int mno;
	int lv;

	// r = dao.updateMember(vo, 1);

	JLabel errorMsg1, errorMsg2, errorMsg3, errorMsg4, errorMsg5, errorMsg6;

	public MyInfo_POP(int mno) {

		this.mno = mno;
		dao = new MemberDao();

		lv = dao.checkMemberLv(mno);

		tfId = new JTextField(30);
		tfId.setEditable(false);
		tfPw = new JPasswordField(30);
		tfName = new JTextField(30);
		tfName.setEditable(false);
		tfNickname = new JTextField(30);
		tfBirth = new JTextField(30);
		tfBirth.setEditable(false);
		tfTel = new JTextField(30);
		tfAddr = new JTextField(30);
		tfEmail = new JTextField(30);
		tfAccount = new JTextField(30);

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

		getMember(mno);

		btUpdateMyInfo = new JButton("수정");
		btUpdateMyInfo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				updateMember(0);
			}
		});

		btUpdateSeller = new JButton("셀러신청");

		btUpdateSeller.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				updateApplySeller();

			}
		});

		btUpdateManager = new JButton("수정");

		btUpdateManager.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				updateMember(1);

			}
		});

		if (lv == 1 || lv == 2) {
			btUpdateSeller.setVisible(false);
		}

		JPanel panTf = new JPanel();
		panTf.setLayout(new GridLayout(18, 2, 10, 10));

		panTf.add(new JLabel("아이디"));
		panTf.add(tfId);
		panTf.add(new JLabel(" "));
		panTf.add(new JLabel(" "));

		if (manager != 1) {
			panTf.add(new JLabel("패스워드"));
			panTf.add(tfPw);
			panTf.add(new JLabel(" "));
			panTf.add(errorMsg1);
		}

		panTf.add(new JLabel("이름"));
		panTf.add(tfName);
		panTf.add(new JLabel(" "));
		panTf.add(new JLabel(" "));

		panTf.add(new JLabel("닉네임"));
		panTf.add(tfNickname);
		panTf.add(new JLabel(" "));
		panTf.add(errorMsg2);

		panTf.add(new JLabel("생년월일"));
		panTf.add(tfBirth);
		panTf.add(new JLabel(" "));
		panTf.add(new JLabel(" "));

		panTf.add(new JLabel("연락처"));
		panTf.add(tfTel);
		panTf.add(new JLabel(" "));
		panTf.add(errorMsg3);

		panTf.add(new JLabel("주소"));
		panTf.add(tfAddr);
		panTf.add(new JLabel(" "));
		panTf.add(errorMsg4);

		panTf.add(new JLabel("이메일"));
		panTf.add(tfEmail);
		panTf.add(new JLabel(" "));
		panTf.add(errorMsg5);

		panTf.add(new JLabel("계좌정보"));
		panTf.add(tfAccount);
		panTf.add(new JLabel(" "));
		panTf.add(errorMsg6);

		if (manager == 1) {

			tfId.setEditable(true);
			tfPw.setVisible(false);
			tfNickname.setEditable(false);
			tfName.setEditable(true);
			tfBirth.setEditable(true);
			tfTel.setEditable(false);
			tfAddr.setEditable(false);
			tfEmail.setEditable(false);
			tfAccount.setEditable(false);

		}

		JPanel panBt = new JPanel();
		panBt.setLayout(new FlowLayout(FlowLayout.RIGHT));

		if (manager == 1) {
			panBt.add(btUpdateManager);
		} else {
			panBt.add(btUpdateMyInfo);

		}
		panBt.add(btUpdateSeller);

		setLayout(new BorderLayout());
		add(panTf, BorderLayout.CENTER);
		add(panBt, BorderLayout.SOUTH);

		setSize(400, 600);
		// setResizable(false);
		setVisible(true);
	}

	protected void updateApplySeller() {

		if (lv == 3) {
			int r = JOptionPane.showConfirmDialog(null, "셀러신청 하시겠습니까?");

			if (r == 0) {
				int re = dao.updateApplySeller(mno);
				if (re == 1) {
					JOptionPane.showMessageDialog(null, "관리자에게 셀러신청이 요청되었습니다!");
					dispose();

				} else {
					JOptionPane.showMessageDialog(null, "신청오류");

				}
			}

		} else if (lv == 4) {
			JOptionPane.showMessageDialog(null, "이미 신청 대기중입니다!");
		}

	}

	protected void updateMember(int abc) {

		String pw = tfPw.getText().replaceAll(" ", "");
		String nickname = tfNickname.getText().replaceAll(" ", "");
		String tel = tfTel.getText().replaceAll(" ", "");
		String addr = tfAddr.getText().replaceAll(" ", "");
		String email = tfEmail.getText().replaceAll(" ", "");
		String account = tfAccount.getText().replaceAll(" ", "");

		String name = tfName.getText().replaceAll(" ", "");
		String id = tfId.getText().replaceAll(" ", "");
		String birth = tfBirth.getText().replaceAll(" ", "");

		MemberVo vo = new MemberVo();
		vo.setPw(pw);
		vo.setNickname(nickname);
		vo.setTel(tel);
		vo.setAddr(addr);
		vo.setEmail(email);
		vo.setAccount(account);
		vo.setMno(mno);

		vo.setName(name);
		vo.setId(id);
		vo.setBirth(birth);
		
		errorMsg1.setText("");
		errorMsg2.setText("");
		errorMsg3.setText("");
		errorMsg4.setText("");
		errorMsg5.setText("");
		errorMsg6.setText("");
		
		int r = 0;

		if (manager != 1) {

			if (pw.equals("") || pw == null) {
				errorMsg1.setText("정보 변경을 원하시면 비번을 입력하세요.");
			} else if (nickname.equals("") || nickname == null) {
				errorMsg2.setText("닉네임을 입력하세요.");
			} else if (tel.equals("") || tel == null) {
				errorMsg3.setText("필수 정보입니다.");
			} else if (addr.equals("") || addr == null) {
				errorMsg4.setText("필수 정보입니다.");
			} else if (email.equals("") || email == null) {
				errorMsg5.setText("필수 정보입니다.");
			} else if (account.equals("") || account == null) {
				errorMsg6.setText("필수 정보입니다.");
			}
			
			else {

				r = dao.updateMember(vo, abc);

				if (r == 1) {
					JOptionPane.showMessageDialog(null, "회원정보가 수정되었습니다.");
					getMember(mno);

				} else {
					JOptionPane.showMessageDialog(null, "회원정보 수정에 실패했습니다.\r\n 입력값을 확인하세요! ");
				}
			}
			
		} else {
			r = dao.updateMember(vo, abc);

			if (r == 1) {
				JOptionPane.showMessageDialog(null, "회원정보가 수정되었습니다.");
				getMember(mno);

			} else {
				JOptionPane.showMessageDialog(null, "회원정보 수정에 실패했습니다.\r\n 입력값을 확인하세요! ");
			}
		}

	}

	// id, pw, 이름, 닉네임, 생일, 전화, 주소, 이멜, 계좌
	private void getMember(int mno) {

		MemberVo vo = dao.getMember(mno);
		tfId.setText(vo.getId());
		tfName.setText(vo.getName());
		tfNickname.setText(vo.getNickname());
		tfBirth.setText(vo.getBirth());
		tfTel.setText(vo.getTel());
		tfAddr.setText(vo.getAddr());
		tfEmail.setText(vo.getEmail());
		tfAccount.setText(vo.getAccount());

	}

}
