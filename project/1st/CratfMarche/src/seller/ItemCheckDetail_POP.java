package seller;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
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

import javax.swing.JTextArea;
import javax.swing.JTextField;

import marcheDao.ItemDao;
import marcheVo.ItemVo;

public class ItemCheckDetail_POP extends JFrame {

	JTextField tfIno;
	JTextField tfIname;
	JTextField tfNick;
	JTextField tfPrice;
	JTextField tfNum;
	JComboBox<Integer> cbNum;
	Vector<Integer> vNum;
	JTextField tfImg;

	HashMap<Integer, String> mapCate;
	JComboBox<String> cbType;
	JComboBox<String> cbCategory;
	JComboBox<String> cbCondition;
	String[] forCbType = { "유형", "무형" };
	String[] forCbcondi = { "판매중", "판매중지" };

	JTextArea taContnt;
	JFileChooser chooser;
	JLabel label;
	File file, file2;
	ItemDao dao;
	ItemVo vo;

	FileInputStream fis;
	FileOutputStream fos;
	String a;
	String nick, ilv, category, condition, img, oldimg, type, tname = "";
	int tno, cno, mno, num, condi = -1;

	ImageIcon icon;

	ItemCheckPanel p;

//	public static int mno;

	public ItemCheckDetail_POP(ItemVo vo, ItemCheckPanel p) {
		
		this.vo = vo;
		
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
				
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				p.ListAll();
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		dao = new ItemDao();
		chooser = new JFileChooser();

		tfIno = new JTextField(vo.getIno() + "");
		tfIname = new JTextField(vo.getIname());
		tfPrice = new JTextField(vo.getIprice() + "");
		tfNick = new JTextField(vo.getMno() + "");
		tfNick.setEditable(false);
		mno = vo.getMno();
		dao = new ItemDao();
		nick = dao.getNickname(mno);
		tfNick.setText(nick);

		cbNum = new JComboBox<Integer>();
		for (int i = 1; i <= 100; i++) {
			cbNum.addItem(i);
		}
		cbNum.setSelectedItem(vo.getInum());

		oldimg = vo.getImg();
		icon = new ImageIcon("c:/marche/item/" + vo.getImg());
		label = new JLabel(icon);
		tfImg = new JTextField(oldimg);

		cbType = new JComboBox<String>(forCbType);
		cbType.setSelectedItem(vo.getIlv());
		// 유, 무형의 선택에 따라 그 다음 콤보박스인 상세타입의 내용이 바뀌게 되는 기능
		cbType.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				callCategory();
			}
		});

		cbCategory = new JComboBox<String>();

		cbCategory.addActionListener(new ActionListener() {
			// 콤보박스에 선택된 문자값을 해당되는 숫자로 바꿔 줌.
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				category = (String) cbCategory.getSelectedItem();
				tno = dao.getCategory(category);
			}
		});

		cbCondition = new JComboBox<String>(forCbcondi);
		condi = vo.getCondition();
		if (condi == 1) {
			cbCondition.setSelectedItem("판매중");
		} else if (condi == 0) {
			cbCondition.setSelectedItem("판매중지");
		}

		cbCondition.addActionListener(new ActionListener() {
			@Override //// 콤보박스에 선택된 문자값을 해당되는 숫자로 바꿔 줌.
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				condition = (String) cbCondition.getSelectedItem();
				
				if (condition.equals("판매중지")) {
					cno = 0;
				} else {
					cno = 1;
				}
				
			}
		});

		taContnt = new JTextArea(vo.getIdtext());

		JPanel up = new JPanel();
		up.setLayout(new GridLayout(9, 2));
		up.add(new JLabel("셀러명:"));
		up.add(tfNick);
		up.add(new JLabel("상품번호:"));
		up.add(tfIno);
		up.add(new JLabel("상품명:"));
		up.add(tfIname);
		up.add(new JLabel("가격:"));
		up.add(tfPrice);
		up.add(new JLabel("수량:"));
		up.add(cbNum);
		up.add(new JLabel("타입명:"));
		up.add(cbType);
		up.add(new JLabel("카테고리명:"));
		up.add(cbCategory);
		up.add(new JLabel("상품상태:"));
		up.add(cbCondition);
		up.add(new JLabel("그림파일명:"));
		up.add(tfImg);

		JPanel upload = new JPanel();
		JButton btImg = new JButton("사진올리기");
		upload.setLayout(new FlowLayout(FlowLayout.RIGHT));
		upload.add(btImg);
		btImg.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				chooser.showOpenDialog(null);
				file = chooser.getSelectedFile();
				tfImg.setText(chooser.getSelectedFile().getName());
			}
		});
		JPanel labelimg = new JPanel();
		labelimg.add(label);
		labelimg.setPreferredSize(new Dimension(300, 300));

		JPanel upInput = new JPanel();
		upInput.setLayout(new BorderLayout());
		upInput.add(up, BorderLayout.CENTER);
		upInput.add(labelimg, BorderLayout.WEST);
		upInput.add(upload, BorderLayout.SOUTH);

		JPanel midLeft = new JPanel();
		midLeft.setLayout(new FlowLayout(FlowLayout.LEFT));
		midLeft.add(new JLabel("<<제품 상세>>"));

		JPanel midRight = new JPanel();
		midRight.setLayout(new FlowLayout(FlowLayout.RIGHT));
		JButton btUpdate = new JButton("수정");
		JButton btDelete = new JButton("삭제");
		midRight.add(btUpdate);
		btUpdate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				updateBtn();

			}
		});

		midRight.add(btDelete);
		btDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				deleteBtn();
			}
		});

		JPanel middle = new JPanel();
		middle.setLayout(new BorderLayout());
		middle.add(midLeft, BorderLayout.WEST);
		middle.add(midRight, BorderLayout.CENTER);

		JScrollPane jsp = new JScrollPane(taContnt);
		JPanel down = new JPanel();
		down.add(jsp);

		JPanel textArea = new JPanel();
		textArea.setLayout(new BorderLayout());
		textArea.add(middle, BorderLayout.NORTH);
		textArea.add(down, BorderLayout.CENTER);

		callCategory();
		tno = vo.getTno();
		mapCate = new HashMap<Integer, String>();
		mapCate = dao.categoryMach();
		tname = mapCate.get(tno);
		cbCategory.setSelectedItem(tname);

		setLayout(new BorderLayout());
		add(upInput, BorderLayout.CENTER);
		add(textArea, BorderLayout.SOUTH);

		setSize(600, 700);
		setVisible(true);

	}

	protected void callCategory() {
		// TODO Auto-generated method stub
		ilv = (String) cbType.getSelectedItem();
		Vector<String> vCategory = new Vector<String>();
		vCategory = dao.listIType(ilv);
		cbCategory.removeAllItems();
		for (String item : vCategory) {
			cbCategory.addItem(item);
		}
		cbCategory.updateUI();
	}

	protected void deleteBtn() {
		// TODO Auto-generated method stub
		int ino = Integer.parseInt(tfIno.getText());
		dao = new ItemDao();
		int re = dao.deleteItem(ino);
		if (re == 1) {
			File file = new File("c:/marche/item/" + oldimg);
			file.delete();
			JOptionPane.showMessageDialog(null, "해당 상품을 삭제하였습니다.");

		} else {
			JOptionPane.showMessageDialog(null, "해당 상품 삭제에 실패하였습니다.");
		}
	}

	protected void updateBtn() {
		// TODO Auto-generated method stub
		dao = new ItemDao();
		//ItemVo vo = new ItemVo();
		vo.setIno(Integer.parseInt(tfIno.getText()));
		vo.setIname(tfIname.getText());
		vo.setIprice(Integer.parseInt(tfPrice.getText()));
		vo.setInum((Integer) cbNum.getSelectedItem());
		if (ilv == null) {
			ilv = vo.getIlv();
			vo.setIlv(ilv);
		} else {
			vo.setIlv(ilv);
		}
		//vo.setTno(tno);
		if (tno == 0) {
			tno = vo.getTno();
			vo.setTno(tno);
			System.out.println("tno의if"+ tno);
		} else {
			vo.setTno(tno);
			System.out.println("tno의else"+ tno);
		}
		
		//vo.setCondition(cno);
		if (cbCondition.getSelectedItem()== null) {
			cno = vo.getCondition();
			vo.setCondition(cno);
			System.out.println("cno의 if"+ cno);
		} else {
			vo.setCondition(cno);
			System.out.println("cnoelse"+ cno);
		}
		
		
		vo.setImg(tfImg.getText());
		vo.setIdtext(taContnt.getText());

		if (tfImg.getText() == null) {
			if (oldimg == null) {
				vo.setImg("사진없음");
			} else {
				vo.setImg(oldimg);
			}
		}

		int re = dao.updateItem(vo);

		if (re == 2) {
			if (tfImg.getText() != null) {
				try {
					file2 = new File("c:/marche/item/" + oldimg);
					file2.delete();

					fis = new FileInputStream(file);
					fos = new FileOutputStream("c:/marche/item/" + file.getName());
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
				JOptionPane.showMessageDialog(null, "상품수정을 완료하였습니다.");
			}

		} else {
			JOptionPane.showMessageDialog(null, "상품 수정에 실패하였습니다.");
		}
	}

}