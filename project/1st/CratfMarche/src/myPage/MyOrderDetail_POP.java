package myPage;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import marcheDao.OdetailDao;
import marcheDao.OrdersDao;

public class MyOrderDetail_POP extends JFrame {

	Vector<String> colNames;
	Vector<Vector<String>> rowData;
	JTable table;
	JButton btOrderCancel; // 부분취소
	JButton btAllCancel;
	JButton btNewReview;
	JButton btCheckShip;

	JComboBox<String> cbDelivery;
	String[] delivery = { "모두보기", "주문완료", "배송중", "배송완료" };
	String ship = "";
	int odno = 0;
	int ono = 0;
	String reviewShip = "";
	boolean check;

	OdetailDao dao;
	OrdersDao odao;
	MyOrderPanel p;

	MyOrderDetail_POP(int ono, MyOrderPanel p) {

		this.ono = ono;
		this.p = p;

		dao = new OdetailDao();
		odao = new OrdersDao();

		colNames = new Vector<String>();
		rowData = new Vector<Vector<String>>();
		cbDelivery = new JComboBox<String>(delivery);

		// 버튼 초기값 비활성화 - 조건에 맞는 상황에서만 활성화
		btNewReview = new JButton("리뷰 작성");
		btNewReview.setEnabled(false);

		btOrderCancel = new JButton("개별 주문 취소");
		btOrderCancel.setEnabled(false);

		btAllCancel = new JButton("전체 주문 취소");

		btAllCancel.setVisible(false);

		btCheckShip = new JButton("수령완료");

		btCheckShip.addActionListener(new ActionListener() {
			// 수령완료 이벤트 처리(고객이 배송완료 확정)
			@Override
			public void actionPerformed(ActionEvent e) {

				int r = dao.checkShip(odno);
				if (r == 1) {
					int re = JOptionPane.showConfirmDialog(null, "수령완료 하셨습니까?");
					if (re == 0) {
						JOptionPane.showMessageDialog(null, "배송완료 처리되었습니다.");
					}
				} else {
					JOptionPane.showMessageDialog(null, "수령완료 등록에 실패했습니다.");

				}
				getMyOdetail();
			}
		});

		cbDelivery.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ship = (String) cbDelivery.getSelectedItem();
				getMyOdetail();
				// System.out.println(ship);

				if (ship.equals("배송완료")) {
					btNewReview.setEnabled(true);
				} else {
					btNewReview.setEnabled(false);
				}

				if (ship.equals("주문완료")) {
					btOrderCancel.setEnabled(true);

				} else {
					btOrderCancel.setEnabled(false);
				}

			}
		});

		btNewReview.addActionListener(new ActionListener() {
			// 리뷰 등록 이벤트 버튼
			@Override
			public void actionPerformed(ActionEvent e) {
				new NewReview_POP(odno);
			}
		});

		btOrderCancel.addActionListener(new ActionListener() {
			// 부분 주문 취소
			@Override
			public void actionPerformed(ActionEvent e) {

				deleteOdetail();
			}
		});

		btAllCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 전체 주문 취소
				deleteAllOrder();
			}
		});

		colNames.add("상품번호");
		colNames.add("셀러명");
		colNames.add("상품명");
		colNames.add("수량");
		colNames.add("가격");
		colNames.add("배송여부");

		table = new JTable(rowData, colNames);
		JScrollPane jsp = new JScrollPane(table);

		table.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				odno = Integer.parseInt(rowData.get(row).get(6));

				reviewShip = rowData.get(row).get(5);

				if (reviewShip.equals("배송완료")) {
					btNewReview.setEnabled(true);

				} else {
					btNewReview.setEnabled(false);
				}

				if (reviewShip.equals("주문완료")) {
					btOrderCancel.setEnabled(true);
				} else {
					btOrderCancel.setEnabled(false);
				}

				if (reviewShip.equals("배송중")) {
					btCheckShip.setEnabled(true);

				} else {
					btCheckShip.setEnabled(false);
				}

			}
		});

		getMyOdetail();

		check = isAbleAllDelete();

		if (check) {
			btAllCancel.setVisible(true);

		} else {
			btAllCancel.setVisible(false);
		}

		JPanel panBt = new JPanel();
		panBt.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panBt.add(cbDelivery);
		panBt.add(btCheckShip);
		panBt.add(btNewReview);
		panBt.add(btOrderCancel);
		panBt.add(btAllCancel);

		setLayout(new BorderLayout());
		add(jsp, BorderLayout.CENTER);
		add(panBt, BorderLayout.NORTH);

		setSize(600, 500);
		setResizable(false);
		setVisible(true);

		addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowClosing(WindowEvent e) {
				p.getMyOrders();

			}

			@Override
			public void windowClosed(WindowEvent e) {
//				MyOrderPanel.odPOP = null;
//				MyOrderPanel odpan = new MyOrderPanel();
//				odpan.getMyOrders();
			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub

			}
		});

	}

	// 모든 주문건이 배송전인지 확인하는 메소드
	protected boolean isAbleAllDelete() {

		boolean flag = false;
		int count = 0;
		for (Vector<String> v : rowData) {

			if (v.get(5).equals("주문완료")) {
				count++;
			}
		}

		if (count == rowData.size()) {
			flag = true;
		}

		return flag;
	}

	protected void deleteAllOrder() {

		int re = JOptionPane.showConfirmDialog(null, "모든 주문을 취소하시겠습니까?");

		if (re == 0) {

			int r = odao.deleteOrder(ono);
			if (r == 1) {
				JOptionPane.showMessageDialog(null, "주문이 취소되었습니다.");

			} else {
				JOptionPane.showMessageDialog(null, "주문취소에 실패했습니다.");

			}
		}

		getMyOdetail();

	}

	protected void deleteOdetail() {

		int re = JOptionPane.showConfirmDialog(null, "해당 주문을 취소하시겠습니까?");

		if (re == 0) {

			if (rowData.size() == 1) {

				int r = odao.deleteOrder(ono);

				if (r == 1) {

					JOptionPane.showMessageDialog(null, "해당 주문이 취소되었습니다.");

				} else {
					JOptionPane.showMessageDialog(null, "주문취소에 실패했습니다.");

				}

			} else {

				int price = dao.getOdprice(odno);
				int r = dao.deleteOdetail(odno);

				if (r == 1) {

					int r2 = dao.changeTotal(price, ono);

					if (r2 == 1) {
						System.out.println("수량변경완료");

					} else {
						System.out.println("수량변경실패");
					}

					JOptionPane.showMessageDialog(null, "해당 주문이 취소되었습니다.");

				} else {
					JOptionPane.showMessageDialog(null, "주문취소에 실패했습니다.");

				}
			}
			getMyOdetail();
		}

		if (dao.isNull(ono) == false) {
			// 해당 주문번호를 가진 주문상세건이 0일때(false)

			odao.deleteOrder(ono); // 부모테이블에서 해당 주문건 삭제
		}
		getMyOdetail();
	}

	private void getMyOdetail() {

		rowData.clear();

		ArrayList<ArrayList<String>> list = dao.getMyOdetail(ono, ship);
		// od.ino, nickname, iname, odnum, odprice, ship, odno
		for (ArrayList<String> odetail : list) {

			Vector<String> v = new Vector<String>();
			v.add(odetail.get(0));
			v.add(odetail.get(1));
			v.add(odetail.get(2));
			v.add(odetail.get(3));
			v.add(odetail.get(4));
			v.add(odetail.get(5));
			v.add(odetail.get(6));

			rowData.add(v);

		}
		table.updateUI();

	}

}