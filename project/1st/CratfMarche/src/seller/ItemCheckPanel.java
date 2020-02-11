package seller;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.print.attribute.standard.MediaSize.Other;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import marcheDao.ItemDao;
import marcheDao.MemberDao;
import marcheVo.ItemVo;

public class ItemCheckPanel extends JPanel {

	JTable table;
	Vector<String> colnames;
	Vector<Vector<String>> rowData;
	ArrayList<ItemVo> list;
	ItemDao dao;
	int ino;
	int lv;
	MemberDao mdao;
	public static int mno;
	ItemCheckDetail_POP detailPop;
	ItemCheckPanel p;
	ItemVo vo;

	public ItemCheckPanel() {

		this.p=this;
		vo = new ItemVo();
		mdao = new MemberDao();
		lv = mdao.checkMemberLv(mno);

		colnames = new Vector<String>();
		rowData = new Vector<Vector<String>>();
		colnames.add("상품번호");
		colnames.add("상품명");
		colnames.add("가격");
		colnames.add("재고");
		colnames.add("조회수");

		table = new JTable(rowData, colnames);
		JScrollPane jsp = new JScrollPane(table);
		ListAll();

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

				int index = table.getSelectedRow();
				ino = Integer.parseInt(rowData.get(index).get(0));
				vo = dao.detailItem(ino);
				detailPop = new ItemCheckDetail_POP(vo, p);

			}
		});

		JPanel upLeft = new JPanel();
		upLeft.setLayout(new FlowLayout(FlowLayout.LEFT));
		upLeft.add(new JLabel("등록상품 LIST"));

		JPanel upRight = new JPanel();
		upRight.setLayout(new FlowLayout(FlowLayout.RIGHT));
		JButton bt_list = new JButton("목록 보기");

		if (lv != 2) {
			bt_list.setEnabled(false);
		}

		JButton bt_insert = new JButton("새상품 등록");
		bt_insert.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (lv != 2) {
					JOptionPane.showMessageDialog(null, "셀러만 상품등록이 가능합니다!");
					
				} else {
					new ItemInsert_POP(p);
					//System.out.println("레벨"+lv);
				}
			}
		});
		
		upRight.add(bt_list);
		bt_list.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				ListAll();
			}
		});

		upRight.add(bt_insert);

		JPanel up = new JPanel();
		up.setLayout(new BorderLayout());
		up.add(upLeft, BorderLayout.CENTER);
		up.add(upRight, BorderLayout.EAST);

		setLayout(new BorderLayout());
		add(up, BorderLayout.NORTH);
		add(jsp, BorderLayout.CENTER);

		setVisible(true);

	}

	void ListAll() {
		// TODO Auto-generated method stub
		rowData.clear();
		dao = new ItemDao();
		list = dao.listItem(mno);
		for (ItemVo vo : list) {
			Vector<String> v = new Vector<String>();
			v.add(vo.getIno() + "");
			v.add(vo.getIname());
			v.add(vo.getIprice() + "");
			v.add(vo.getInum() + "");
			v.add(vo.getIhit() + "");
			rowData.add(v);
		}
		table.updateUI();
	}

}