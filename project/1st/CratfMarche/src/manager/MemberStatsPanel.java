package manager;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import marcheDao.MemberDao;
import marcheVo.MemberVo;
import myPage.MyInfo_POP;

public class MemberStatsPanel extends JPanel {

	Vector<String> colNames;
	Vector<Vector<String>> rowData;

	JTable table;

	JComboBox<String> cbNlv;
	String[] Nlv = { "모두보기", "판매자", "판매자 신청자" };
	String cbValue = "";
	int lv, mno;
	String mLv;

	ArrayList<MemberVo> list;

	JButton btUpdateSeller;

	HashMap<Integer, String> map;

	MemberStatsPanel(int manager) {

		colNames = new Vector<String>();
		rowData = new Vector<Vector<String>>();

		colNames.add("번호");
		colNames.add("ID");
		colNames.add("이름");
		colNames.add("전화번호");
		colNames.add("생일");
		colNames.add("주소");
		colNames.add("이메일");
		colNames.add("회원등급");

		cbNlv = new JComboBox<String>(Nlv);
		cbNlv.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cbValue = (String) cbNlv.getSelectedItem();
				if (cbValue.equals("모두보기")) {
					lv = 0;
					listMember();
					btUpdateSeller.setEnabled(false);

				} else if (cbValue.equals("판매자")) {
					lv = 2;
					listMember();
					btUpdateSeller.setEnabled(false);
				} else if (cbValue.equals("판매자 신청자")) {
					lv = 4;
					listMember();
					btUpdateSeller.setEnabled(true);
				} else {
					lv = 0;
				}
			}
		});

		map = new HashMap<Integer, String>();
		map.put(1, "관리자");
		map.put(2, "판매자");
		map.put(3, "일반회원");
		map.put(4, "판매자대기");

		btUpdateSeller = new JButton("판매자 승인");
		btUpdateSeller.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				updateSeller();

			}
		});
		btUpdateSeller.setEnabled(false);

		table = new JTable(rowData, colNames);
		table.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				mno = Integer.parseInt(rowData.get(row).get(0));
				mLv = rowData.get(row).get(7);
				
				if (mLv.equals("판매자대기")) {
					btUpdateSeller.setEnabled(true);
				} else {
					btUpdateSeller.setEnabled(false);
				}
				
				int mno = Integer.parseInt(rowData.get(row).get(0));
				new MyInfo_POP(mno);
				System.out.println(mno);
				
			}
		});

		JScrollPane jsp = new JScrollPane(table);

		if (manager == 1) {
			listMember();
		}

		JPanel panBt = new JPanel();
		panBt.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panBt.add(cbNlv);
		panBt.add(btUpdateSeller);

		setLayout(new BorderLayout());
		add(panBt, BorderLayout.NORTH);
		add(jsp, BorderLayout.CENTER);

	}

	protected void updateSeller() {

		MemberDao dao = new MemberDao();

		if (mno == 0) {
			JOptionPane.showMessageDialog(null, "회원을 선택해 주세요.");
		} else {
			int re = dao.updateSeller(mno);
			if (re == 1) {
				JOptionPane.showMessageDialog(null, "판매자로 변경되었습니다.");
				listMember();

			} else {
				JOptionPane.showMessageDialog(null, "등급변경에 실패했습니다.");
			}
		}
	}

	private void listMember() {
		rowData.clear();
		MemberDao dao = new MemberDao();
		list = dao.listMember(lv);

		for (MemberVo m : list) {
			Vector<String> v = new Vector<String>();
			v.add(m.getMno() + "");
			v.add(m.getId());
			v.add(m.getName());
			v.add(m.getTel());
			v.add(m.getBirth());
			v.add(m.getAddr());
			v.add(m.getEmail());
			v.add(map.get(m.getLv()));

			rowData.add(v);
		}
		table.updateUI();
	}

}