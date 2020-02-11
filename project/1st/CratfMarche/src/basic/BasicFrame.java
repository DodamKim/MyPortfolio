package basic;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import cart.BagPanel;
import item.ItemMainPanel;
import login.LoginMainPanel;
import manager.ManagerTab;
import marcheDao.MemberDao;
import marcheDao.OdetailDao;
import myPage.MyPageTab;
import nori.NoriTab;
import seller.SellerTab;

/*	메인화면의 기본 배경 화면.
	최상단 버튼 및 판넬의 경우, 각 페이지에서 이름의 중복이 많아서
	기본 배경에 쓰이는 버튼과 판넬 앞에만 추가적으로 'm'을 붙힘
*/

public class BasicFrame extends JFrame {

	// 카드 레이아웃 객체 생성
	CardLayout c;
	public static int mno;

	public BasicFrame() {

		OdetailDao dao = new OdetailDao();
		dao.completedShip(); // 주문일로부터 7일 후 자동 배송완료 변경
		dao.changeCompletedShip(); // 주문 7일 후, 신청완료인 무형상품을 수강완료로 변경하는 메소드

		c = new CardLayout();

		// 상단 7개의 버튼을 담을 판넬 - 그리드레이아웃으로 균등 7배열
		JPanel mPanBtn = new JPanel(new GridLayout(1, 7));
		// 메인 화면을 담을 판넬
		// 생성한 카드 레이아웃 객체를 선언해줌
		JPanel mPanMain = new JPanel(c);

		// 상단 7개 버튼 생성
		JButton mBtLogin = new JButton("로그인");
		JButton mBtItem = new JButton("상품");

		JButton mBtBag = new JButton("장바구니");
//		mBtBag.setEnabled(false);
//		if(mno != 0) {
//			mBtBag.setEnabled(true);
//		}

		JButton mBtMypage = new JButton("마이페이지");
//		mBtMypage.setEnabled(false);
//		if(mno != 0) {
//			mBtMypage.setEnabled(true);
//		}

		MemberDao mdao = new MemberDao();
		JButton mBtSeller = new JButton("판매자");
//		mBtSeller.setEnabled(false);
//		if(mdao.getMember(mno).getLv() == 2) {
//			mBtSeller.setEnabled(true);
//		}

		JButton mBtComm = new JButton("놀이터");

		JButton mBtAdmin = new JButton("관리자");
//		mBtAdmin.setEnabled(false);
//		if(mno == 1) {
//			mBtAdmin.setEnabled(true);
//		}

		mPanBtn.add(mBtLogin);
		mPanBtn.add(mBtItem);
		mPanBtn.add(mBtBag);
		mPanBtn.add(mBtMypage);
		mPanBtn.add(mBtSeller);
		mPanBtn.add(mBtComm);
		mPanBtn.add(mBtAdmin);

		add(mPanBtn, BorderLayout.NORTH);
		add(mPanMain, BorderLayout.CENTER);

		// 미리 보여질 판넬을 담음, 추후 불러올 이름을 같이 선언해줌
		mPanMain.add("main", new LoginMainPanel());

		mBtLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// 선언한 이름으로 c 에 보여달라고 호출
				c.show(mPanMain, "main");
			}
		});

		mBtItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				mPanMain.add("item", new ItemMainPanel());
				c.show(mPanMain, "item");
			}
		});

		mBtBag.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				mPanMain.add("bag", new BagPanel());
				c.show(mPanMain, "bag");
			}
		});

		mBtMypage.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				mPanMain.add("myPage", new MyPageTab());
				c.show(mPanMain, "myPage");

			}
		});

		mBtSeller.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				mPanMain.add("seller", new SellerTab());
				c.show(mPanMain, "seller");
			}
		});

		mBtComm.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				mPanMain.add("nori", new NoriTab());
				c.show(mPanMain, "nori");
			}
		});

		mBtAdmin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				mPanMain.add("admin", new ManagerTab());
				c.show(mPanMain, "admin");
			}
		});

		setTitle("[ Craft Marche ]");
		setSize(1600, 900);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new BasicFrame();
	}
}
