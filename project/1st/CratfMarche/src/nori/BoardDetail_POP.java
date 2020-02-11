package nori;

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

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import marcheDao.BoardDao;
import marcheVo.BoardVo;

public class BoardDetail_POP extends JFrame {
	JTextField tfBtitle;
	JTextField tfBdate;
	JTextField tfBtime;
	
	JTextArea taBtext;
	
	JComboBox<String> cbloc;
	JRadioButton radioBlvMarket;
	JRadioButton radioBlvShop;
	ButtonGroup radioGroup; // 라디오 버튼 그룹화

	JButton btUpdate;
	JButton btDelete;
	JButton btJfc;
	File file, file2;

	JFileChooser jfc;
	JLabel bimg;

	JFileChooser tcBimg;
	JTextField tfFname;
	String oldBimg;
	public static int mno;

	String[] bloc = { "모든지역", "서울", "경기", "인천", "대전", "대구", "부산", "울산", "광주", "세종", "제주", "전남/전북", "강남/경북", "충남/충북",
			"강원도" };
	String cbValue, cbValueStart;
	int blv = 3;
	int bno;

	ImageIcon icon;
	BoardListPanel p;
	
	BoardDetail_POP(int pop, BoardVo vo, BoardListPanel p, String cbV) {

		this.p = p;
		
//		addWindowListener(new WindowListener() {
//			
//			@Override
//			public void windowOpened(WindowEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//			@Override
//			public void windowIconified(WindowEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//			@Override
//			public void windowDeiconified(WindowEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//			@Override
//			public void windowDeactivated(WindowEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//			@Override
//			public void windowClosing(WindowEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//			@Override
//			public void windowClosed(WindowEvent e) {
//				p.listShopBoard(cbV);
//				
//			}
//			
//			@Override
//			public void windowActivated(WindowEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//		});
		
		
		bno = vo.getBno();
		oldBimg = vo.getBimg();

		tfBtitle = new JTextField(30);
		tfBdate = new JTextField(30);
		tfBtime = new JTextField(30);
		taBtext = new JTextArea();
		tfFname = new JTextField(30);

		tfBtitle.setText(vo.getBtitle());
		tfBdate.setText(vo.getBdate());
		tfBtime.setText(vo.getBtime());
		taBtext.setText(vo.getBtext());

		icon = new ImageIcon("C:\\marche\\board\\" + vo.getBimg());

		bimg = new JLabel(icon);

		jfc = new JFileChooser("C:\\marche\\board");

		cbloc = new JComboBox<String>(bloc);
		cbloc.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cbValue = (String) cbloc.getSelectedItem();

			}
		});

		cbloc.setSelectedItem(vo.getBloc());
		
		
		
		if (vo.getBlv() == 1) {
			radioBlvMarket = new JRadioButton("프리마켓", true);
			blv = 1;
			radioBlvShop = new JRadioButton("공방");
		} else {
			radioBlvMarket = new JRadioButton("프리마켓");
			blv = 0;
			radioBlvShop = new JRadioButton("공방", true);
		}

		radioBlvMarket.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				blv = 1;
			}
		});

		radioBlvShop.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				blv = 0;
			}
		});

		ButtonGroup radioGroup = new ButtonGroup();
		radioGroup.add(radioBlvMarket);
		radioGroup.add(radioBlvShop);

		btUpdate = new JButton("수정");
		btUpdate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				updateBoard(); // 수정버튼 이벤트
				//dispose();
			}
		});

		btDelete = new JButton("삭제");
		btDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				deleteBoard(); // 삭제버튼 이벤트
				dispose();
			}
		});

		btJfc = new JButton("사진 올리기");
		btJfc.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int r = jfc.showOpenDialog(null);
				if (r == 0) {
					file2 = jfc.getSelectedFile();
					tfFname.setText(jfc.getSelectedFile().getName());
				}
				
			}
		});

		JPanel panRadio = new JPanel();
		panRadio.add(radioBlvMarket);
		panRadio.add(radioBlvShop);

		JPanel panTf = new JPanel(); // 텍스트필드를 담은 판넬
		panTf.setPreferredSize(new Dimension(400, 400));
		panTf.setLayout(new GridLayout(6, 2, 10, 10));
		panTf.add(new JLabel("게시판 구분"));
		panTf.add(panRadio);
		panTf.add(new JLabel("공방 마켓 이름"));
		panTf.add(tfBtitle);
		panTf.add(new JLabel("지역"));
		panTf.add(cbloc);
		panTf.add(new JLabel("행사일"));
		panTf.add(tfBdate);
		panTf.add(new JLabel("운영시간"));
		panTf.add(tfBtime);
		if (mno == 1) {
			panTf.add(tfFname);
			panTf.add(btJfc);
		}
		JPanel panLb = new JPanel(); // [상세 소개]라벨을 담은 판넬
		panLb.setLayout(new FlowLayout(FlowLayout.LEFT));
		panLb.add(new JLabel("[상세소개]"));

		JPanel panBt = new JPanel(); // 버튼을 담은 판넬
		panBt.setLayout(new FlowLayout(FlowLayout.RIGHT));
		if (mno == 1) {
			panBt.add(btUpdate);
			panBt.add(btDelete);
		}
		JPanel panImg = new JPanel();
		panImg.setPreferredSize(new Dimension(600, 400));
		panImg.add(bimg);

		JPanel panUp = new JPanel();
		panUp.setLayout(new BorderLayout());
		panUp.add(panImg, BorderLayout.CENTER);
		panUp.add(panTf, BorderLayout.EAST);

		JScrollPane jsp = new JScrollPane(taBtext);

		setLayout(new BorderLayout());
		add(panUp, BorderLayout.NORTH);
		add(jsp, BorderLayout.CENTER);
		add(panBt, BorderLayout.SOUTH);
		setTitle("마켓/공방 상세보기");

		setSize(1000, 900);
		setResizable(false);
		setVisible(true);
	}

	protected void updateBoard() {
		BoardDao dao = new BoardDao();
		BoardVo vo = new BoardVo();
		vo.setBtitle(tfBtitle.getText());
		if(cbValue==null) {
			vo.setBloc(vo.getBloc());
		}
		else {
		vo.setBloc(cbValue);
		}
		vo.setBdate(tfBdate.getText());
		vo.setBtime(tfBtime.getText());
		vo.setBlv(blv);
		vo.setBno(bno);
		vo.setBimg(tfFname.getText());
		vo.setBtext(taBtext.getText());
		if (tfFname.getText() == null) {
			if(oldBimg == null) {
				vo.setBimg("사진없음");
			}else {
				vo.setBimg(oldBimg);
			}
		} else {
			vo.setBimg(tfFname.getText());
		}
		vo.setBtext(taBtext.getText());

		int re = dao.updateBoard(vo);
		
		if (re >= 1) {

			if (tfFname.getText() != null) {
				try {
					file2 = new File("C:\\marche\\board\\" + oldBimg);
					file2.delete();

					FileInputStream fis = new FileInputStream(file);
					FileOutputStream fos = new FileOutputStream("C:\\marche\\board\\" + file.getName());
					int data;

					while ((data = fis.read()) != -1) {
						fos.write(data);
					}

					fis.close();
					fos.close();

				} catch (Exception e) {
						System.out.println(e.getMessage());
				}
				
				JOptionPane.showMessageDialog(null, "공방/프리마켓 글 수정 완료");
			}
		} else {
			JOptionPane.showMessageDialog(null, "공방/프리마켓 글 수정 실패");
			dispose();
		}

	}

	protected void deleteBoard() {

		BoardDao dao = new BoardDao();
		int re = dao.deleteBoard(bno);

		if (re >= 1) {
			JOptionPane.showMessageDialog(null, "글이 삭제되었습니다.");
			try {

				file2 = new File("C:\\marche\\board\\" + oldBimg);
				file2.delete();

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			dispose();
		} else {
			JOptionPane.showMessageDialog(null, "글 삭제에 실패하였습니다.");
		}

	}

}
