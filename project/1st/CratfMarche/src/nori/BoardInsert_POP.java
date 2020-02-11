package nori;

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

import javax.swing.ButtonGroup;
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

public class BoardInsert_POP extends JFrame {
	JTextField tfBtitle;
	JTextField tfBdate;
	JTextField tfBtime;
	
	JTextArea taBtext;
	
	JComboBox<String> cbloc;
	JRadioButton radioBlvMarket;
	JRadioButton radioBlvShop;
	ButtonGroup radioGroup;	//라디오 버튼 그룹화
	JButton btNew;

	JButton btJfc;
	File file;
	
	JFileChooser jfc;
	
	JFileChooser tcBimg;
	JTextField tfFname;
	
	String[] bloc = {"모든지역","서울","경기","인천","대전","대구","부산","울산","광주","세종","제주","전남/전북","강남/경북","충남/충북","강원도"};
	String cbValue;
	BoardListPanel p;
	int blv = 3;
	
	//int pop, BoardVo vo, BoardListPanel p, String cbV
	BoardInsert_POP(int mno, BoardListPanel p){
		
		this.p = p;
		
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
		
		tfBtitle = new JTextField(30);
		tfBdate = new JTextField(30);
		tfBtime = new JTextField(30);
		
		taBtext = new JTextArea();
		
		jfc = new JFileChooser("C:\\");
		tfFname = new JTextField(30);
		cbloc = new JComboBox<String>(bloc);
		cbloc.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cbValue = (String)cbloc.getSelectedItem();
		
			}
		});
		
		if(cbValue == null) {
			cbValue ="모든지역";
		}
		
		radioBlvMarket = new JRadioButton("프리마켓");
		radioBlvMarket.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {	
				blv = 1;
			}
		});
		radioBlvShop = new JRadioButton("공방");
		radioBlvShop.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {	
				blv = 0;
			}
		});
		
		ButtonGroup radioGroup = new ButtonGroup();
		radioGroup.add(radioBlvMarket);
		radioGroup.add(radioBlvShop);
		
		btNew = new JButton("등록");
		btNew.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				insetBoard();							//등록버튼 이벤트
				dispose();
			}
		});
		
		
		btJfc = new JButton("사진 올리기");
		btJfc.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int r = jfc.showOpenDialog(null);
				if (r == 0) {
					file = jfc.getSelectedFile(); 
					tfFname.setText(jfc.getSelectedFile().getName());
				}
			}
		});
		
		
		JPanel panRadio = new JPanel();
		panRadio.add(radioBlvMarket);
		panRadio.add(radioBlvShop);
		
		JPanel panTf = new JPanel();				//텍스트필드를 담은 판넬
		panTf.setLayout(new GridLayout(6,2,10,10));
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
		if(mno == 1) {
			panTf.add(tfFname);
			panTf.add(btJfc);
		}
		JPanel panLb = new JPanel();				//[상세 소개]라벨을 담은 판넬
		panLb.setLayout(new FlowLayout(FlowLayout.LEFT));
		panLb.add(new JLabel("[상세소개]"));
		
		JPanel panBtNew = new JPanel();				//새글 쓰기 버튼을 담은 판넬
		panBtNew.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panBtNew.add(btNew);
		
		JScrollPane jsp = new JScrollPane(taBtext);
		
		setLayout(new BorderLayout());
		add(panTf, BorderLayout.NORTH);
		add(jsp, BorderLayout.CENTER);
		add(panBtNew, BorderLayout.SOUTH);
		setTitle("마켓/공방 등록");
		
		
		setSize(700, 900);
		setResizable(false);
		setVisible(true);
	}

	// 1. 게시판 번호, 2. 제목, 3. 행사 지역, 4. 행사일, 5. 운영 시간, 6. 조회수, 7. 관리자 번호, 8. 게시판 구분, 9. 이미지
	protected void insetBoard() {
		BoardDao dao = new BoardDao();
		BoardVo vo = new BoardVo();
		vo.setBtitle(tfBtitle.getText());
		vo.setBloc(cbValue);
		vo.setBdate(tfBdate.getText());
		vo.setBtime(tfBtime.getText());
		vo.setBlv(blv);
		vo.setBimg(tfFname.getText());
		vo.setBtext(taBtext.getText());
		
		if(blv == 3 ) {
			JOptionPane.showMessageDialog(null, "공방/프리마켓을 선택해주세요.");
		}
		else {
			int re = dao.insetBoard(vo);
			if(re >=1 )
	          {
					try {
						FileInputStream fis = new FileInputStream(file);
						FileOutputStream fos = new FileOutputStream("C:\\marche\\borard\\"+file.getName());
						int data;
						while( (data = fis.read()) != -1 ) {
							fos.write(data);
						}
						
						fis.close();
						fos.close();
						
						
					}catch (Exception e) {
						System.out.println("사진 업로드 예외발생 :"+e.getMessage());
					} 
					JOptionPane.showMessageDialog(null, "글 등록 완료.");
					dispose();
	          }
	          else
	          {
	              JOptionPane.showMessageDialog(null, "글 등록 실패.");
	              dispose();
	          }
		}
		
		
	}
	
}

