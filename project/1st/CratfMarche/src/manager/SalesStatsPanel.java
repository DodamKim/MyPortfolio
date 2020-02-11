package manager;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import marcheDao.MemberDao;

public class SalesStatsPanel extends JPanel {

	MemberDao dao;

	JTable sellerTable;
	JTable itemTable;

	Vector<String> sellerColNames;
	Vector<String> itemColNames;

	Vector<Vector<String>> sellerRowData;
	Vector<Vector<String>> itemRowData;

	JLabel lbMemberNo; // 전체회원수
	JLabel lbSellerNo; // 전체 판매자수
	JLabel lbItemNo; // 전체 등록상품수
	JLabel lbMoneyNo; // 전체 매출액

	int memberNo, sellerNo, itemNo, moneyNo;

	ArrayList<ArrayList<String>> sellerList;
	ArrayList<ArrayList<String>> itemList;

	SalesStatsPanel(int mno) {

		sellerColNames = new Vector<String>();
		sellerColNames.add("회원번호");
		sellerColNames.add("닉네임");
		sellerColNames.add("매출");

		itemColNames = new Vector<String>();
		itemColNames.add("상품번호");
		itemColNames.add("상품명");
		itemColNames.add("매출");

		sellerRowData = new Vector<Vector<String>>();
		itemRowData = new Vector<Vector<String>>();

		lbMemberNo = new JLabel("");
		lbSellerNo = new JLabel("");
		lbItemNo = new JLabel("");
		lbMoneyNo = new JLabel("");

		sellerTable = new JTable(sellerRowData, sellerColNames);
		itemTable = new JTable(itemRowData, itemColNames);

		JScrollPane sellerJsp = new JScrollPane(sellerTable);
		JScrollPane itemJsp = new JScrollPane(itemTable);

		if (mno == 1) {
			getSellerRank();
			getItemRank();
			getAllMemberNum();
			getSellerNum();
			getAllItemNum();
			getAllSales();
		}
		
		JPanel panSeller = new JPanel();
		panSeller.setLayout(new BorderLayout());
		panSeller.add(new JLabel("판매자 매출 TOP5"), BorderLayout.NORTH);
		panSeller.add(sellerJsp, BorderLayout.CENTER);

		JPanel panItem = new JPanel();
		panItem.setLayout(new BorderLayout());
		panItem.add(new JLabel("상품 매출 TOP5"), BorderLayout.NORTH);
		panItem.add(itemJsp, BorderLayout.CENTER);

		JPanel panLeft = new JPanel();
		panLeft.setLayout(new GridLayout(1, 2, 50, 50));
		panLeft.add(panSeller);
		panLeft.add(panItem);

		JPanel panTf = new JPanel();
		panTf.setLayout(new GridLayout(4, 2, 50, 50));
		panTf.setPreferredSize(new Dimension(300, 100));

		panTf.add(new JLabel(" 전체 회원 수 : "));
		panTf.add(lbMemberNo);
		panTf.add(new JLabel(" 전체 판매자 수 : "));
		panTf.add(lbSellerNo);
		panTf.add(new JLabel(" 전체 등록상품 수 : "));
		panTf.add(lbItemNo);
		panTf.add(new JLabel(" 전체 매출  : "));
		panTf.add(lbMoneyNo);

		JPanel panRight = new JPanel();
		panRight.setLayout(new GridLayout(3, 3));
		panRight.add(new JLabel("  "));
		panRight.add(panTf);
		panRight.add(new JLabel("  "));

		setLayout(new BorderLayout());
		add(panLeft, BorderLayout.CENTER);
		add(panRight, BorderLayout.EAST);

	}

	private void getAllSales() {
		dao = new MemberDao();
		moneyNo = dao.getAllSales();
		lbMoneyNo.setText(moneyNo + "");
	}

	private void getAllItemNum() {
		dao = new MemberDao();
		itemNo = dao.getAllItemNum();
		lbItemNo.setText(itemNo + "");

	}

	private void getSellerNum() {
		dao = new MemberDao();
		sellerNo = dao.getSellerNum();
		lbSellerNo.setText(sellerNo + "");
	}

	private void getAllMemberNum() {
		// TODO Auto-generated method stub
		dao = new MemberDao();
		int memberNo = dao.getAllMemberNum();
		lbMemberNo.setText(memberNo + "");

	}

	private void getItemRank() {

		dao = new MemberDao();
		itemList = dao.getItemRank();
		for (ArrayList<String> aL : itemList) {
			Vector<String> v = new Vector<String>();
			v.add(aL.get(0));
			v.add(aL.get(1));
			v.add(aL.get(2));

			itemRowData.add(v);
		}
		itemTable.updateUI();

	}

	private void getSellerRank() {

		dao = new MemberDao();
		sellerList = dao.getSellerRank();
		for (ArrayList<String> aL : sellerList) {
			Vector<String> v = new Vector<String>();
			v.add(aL.get(0));
			v.add(aL.get(1));
			v.add(aL.get(2));

			sellerRowData.add(v);
		}
		sellerTable.updateUI();

	}

}