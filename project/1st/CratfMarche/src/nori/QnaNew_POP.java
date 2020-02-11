package nori;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import marcheDao.ItemDao;
import marcheDao.QnaDao;
import marcheVo.QnaVo;

public class QnaNew_POP extends JFrame {

	JTextField tfNtitle; 
	JTextField tfMno;
	JTextArea taNtext;
	JButton btNew; 
	JCheckBox check;
	int qcheck=0;
	public static int mno;
	String nickname;
	
	QnaNew_POP(){
		tfNtitle = new JTextField(30);
		tfMno = new JTextField(30);
		taNtext = new JTextArea();
		check = new JCheckBox("비밀글 여부", false);
		check.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(check.isSelected() == true) {
					qcheck = 1;
				}
				else {
					qcheck = 0;
				}
				
			}
		});

		ItemDao iDao = new ItemDao();
		nickname = iDao.getNickname(mno);
		tfMno.setEditable(false);
		tfMno.setText(nickname);
		
		btNew = new JButton("등록");
		btNew.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				insertQna();									//글쓰기 버튼 눌르면 db에 인서트 되야하는 메소드
				dispose();
			}
		});
		
		JPanel panTf = new JPanel();				//텍스트필드를 담은 판넬
		panTf.setLayout(new GridLayout(3,2,10,10));
		panTf.add(new JLabel("제목"));
		panTf.add(tfNtitle);
		panTf.add(new JLabel("작성자"));
		panTf.add(tfMno);
		panTf.add(new JLabel(""));
		panTf.add(check);
		
		
		JPanel panLb = new JPanel();				//[Qna 글 내용]라벨을 담은 판넬
		panLb.setLayout(new FlowLayout(FlowLayout.LEFT));
		panLb.add(new JLabel("[Qna 글 내용]"));
		
		JPanel panUp = new JPanel();				//위의 2개의 판넬을 담은 판넬
		panUp.setLayout(new BorderLayout());
		panUp.add(panTf, BorderLayout.NORTH);
		panUp.add(panLb, BorderLayout.SOUTH);
		
		
		JPanel panBt = new JPanel();				//버튼을 담은 판넬
		panBt.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panBt.add(btNew);
		
		JScrollPane jsp = new JScrollPane(taNtext);
		
		setLayout(new BorderLayout());
		add(panUp, BorderLayout.NORTH);
		add(jsp, BorderLayout.CENTER);
		add(panBt, BorderLayout.SOUTH);	
		
		setTitle("QnA 글 쓰기");
		setSize(700, 900);
		setResizable(false);
		setVisible(true);
	}

	protected void insertQna() {
		QnaDao dao = new QnaDao();
		QnaVo vo = new QnaVo();
		// 1. 문의번호, 2. 제목, 3. 내용, 4. 작성일, 5. 답변 확인, 6. 비공개 여부, 7. 회원 번호, 8. 상품 번호
		
		vo.setQtitle(tfNtitle.getText());
		vo.setQtext(taNtext.getText());
		vo.setSecret((char) qcheck);
		vo.setMno(mno);
		vo.setIno(0);
		
		int re = dao.insertQna(vo);
		 if(re >=1 )
         {
             JOptionPane.showMessageDialog(null, "일반 문의 등록 완료 ");
             dispose();
             
         }
         else
         {
             JOptionPane.showMessageDialog(null, "일반 문의 등록 실패");
             dispose();
         }		
		
	}
	
}