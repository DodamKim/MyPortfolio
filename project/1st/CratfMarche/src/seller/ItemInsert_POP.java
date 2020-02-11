package seller;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import marcheDao.ItemDao;
import marcheVo.ItemVo;

public class ItemInsert_POP extends JFrame {
	JTextField tfIname;
	JTextField tfNick;
	JTextField tfPrice;
	JComboBox<Integer> cbNum;
	Vector<Integer> vNum;
	JTextField tfImg;
	JComboBox<String> cbType;
	JComboBox<String> cbCategory;
	HashMap<String, Integer> mapCname;
	HashMap<String, Integer> mapMname;

	JTextArea taContnt;
	JFileChooser chooser;
	File file;

	String[] forCbType = { "유형", "무형" };

	ItemDao dao;

	String ilv, category;
	int tno = 0;
	String nick;
	public static int mno;
	ItemCheckPanel p;
	
	public ItemInsert_POP(ItemCheckPanel p) {
		this.p=p;
		
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
				// TODO Auto-generated method stub
				p.ListAll();
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		dao = new ItemDao();
		chooser = new JFileChooser();
		vNum = new Vector<Integer>();
		for (int i = 1; i <= 100; i++) {
			vNum.add(i);
		}

		tfNick = new JTextField(10);
		tfNick.setEditable(false);
		nick = dao.getNickname(mno);
		System.out.println(nick);
		tfNick.setText(nick);

		tfIname = new JTextField(10);
		tfPrice = new JTextField(10);
		tfImg = new JTextField(10);

		cbType = new JComboBox<String>(forCbType);

		cbType.addActionListener(new ActionListener() {

			@Override // 카테고리 타입을 콤보박스에 넣어줌.
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				ilv = (String) cbType.getSelectedItem();
//			vCategory =	dao.listIType(ilv);
//			System.out.println("다오"+vCategory);
				Vector<String> vCategory = new Vector<String>();
				vCategory = dao.listIType(ilv);
				cbCategory.removeAllItems();
				for (String item : vCategory) {

					cbCategory.addItem(item);
				}
				cbCategory.updateUI();
			}
		});

		cbCategory = new JComboBox<String>();
		cbCategory.addActionListener(new ActionListener() {

			@Override // 카테고리 콤보박스르 통해 선택한 값을 상세번호로 다시 바꿔줌.
						// 상품insert할때 이 번호가 들어감.
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				category = (String) cbCategory.getSelectedItem();
				tno = dao.getCategory(category);
				System.out.println(tno);
			}
		});
		cbNum = new JComboBox<Integer>(vNum);
		taContnt = new JTextArea(50, 70);
		JScrollPane jsp = new JScrollPane(taContnt);

		JPanel input = new JPanel();
		input.setLayout(new GridLayout(7, 2));
		input.add(new JLabel("판매자명:"));
		input.add(tfNick);
		input.add(new JLabel("상품명:"));
		input.add(tfIname);
		input.add(new JLabel("가격:"));
		input.add(tfPrice);
		input.add(new JLabel("재고:"));
		input.add(cbNum);
		input.add(new JLabel("상품타입:"));
		input.add(cbType);
		input.add(new JLabel("상세카테고리:"));
		input.add(cbCategory);
		input.add(new JLabel("사진파일명:"));
		input.add(tfImg);

		JPanel input_img = new JPanel();

		input_img.setLayout(new FlowLayout(FlowLayout.RIGHT));
		JButton upload = new JButton("사진올리기");

		input_img.add(upload);
		upload.addActionListener(new ActionListener() {
			// 파일 선택하는 창을 띄우고 선택한 이미지파일의 파일명을 가져옴.
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				chooser.showOpenDialog(null);
				file = chooser.getSelectedFile();
				tfImg.setText(chooser.getSelectedFile().getName());
			}
		});

		JPanel up = new JPanel();
		up.setLayout(new BorderLayout());
		up.add(input, BorderLayout.CENTER);
		up.add(input_img, BorderLayout.SOUTH);

		JPanel down_btn = new JPanel();
		down_btn.setLayout(new GridLayout());
		down_btn.add(new JLabel("<<상세설명>>"), BorderLayout.CENTER);
		JButton insert_btn = new JButton("새상품 등록");

		insert_btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				insertItem();

			}
		});
		down_btn.add(insert_btn, BorderLayout.EAST);

		JPanel down_Area = new JPanel();
		down_Area.add(jsp);

		JPanel down = new JPanel();
		down.setLayout(new BorderLayout());
		down.add(down_btn, BorderLayout.NORTH);
		down.add(down_Area, BorderLayout.CENTER);

		setLayout(new BorderLayout());
		add(up, BorderLayout.NORTH);
		add(down, BorderLayout.CENTER);

		setSize(400, 500);
		setVisible(true);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	// 상품등록 버튼을 눌렀을때 작동되는 메소드
	protected void insertItem() {

		ItemVo v = new ItemVo();
		v.setIname(tfIname.getText());
		v.setIprice(Integer.parseInt(tfPrice.getText()));
		v.setInum((Integer) cbNum.getSelectedItem());
		v.setImg(tfImg.getText());
		v.setMno(mno);
		v.setTno(tno);
		v.setIlv((String) cbType.getSelectedItem());
		v.setIdtext(taContnt.getText());

		dao = new ItemDao();
		int re = dao.insertItem(v);

		if (re == 2) {

			try {
				FileInputStream fis = new FileInputStream(file);
				FileOutputStream fos = new FileOutputStream("c:/marche/item/" + file.getName());
				int data;
				while ((data = fis.read()) != -1) {
					fos.write(data);
				}
				fos.close();
				fis.close();

			} catch (Exception ee) {
				// TODO: handle exception
				System.out.println(ee.getMessage());
			}
			JOptionPane.showMessageDialog(null, "상품을 등록하였습니다.");

		}

	}

}