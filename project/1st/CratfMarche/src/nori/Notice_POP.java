package nori;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.security.auth.Refreshable;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import marcheDao.ItemDao;
import marcheDao.NoticeDao;
import marcheVo.NoticeVo;

public class Notice_POP extends JFrame {
	
	JTextField tfNtitle; 
	JTextField tfMno;
	JTextArea taNtext;
	JButton btNew; 
	JButton btUpdate; 
	JButton btDelete; 
	NoticeDao dao;
	ItemDao iDao;
	public static int mno;
	int nno;
	
	Notice_POP(int pop, int nno){
		
		this.nno = nno;
		
		tfNtitle = new JTextField(30);
		tfMno = new JTextField(30);
		taNtext = new JTextArea();
		tfNtitle.setEditable(false);
		tfMno.setEditable(false);
		taNtext.setEditable(false);
		
		
		btNew= new JButton("등록");
		btNew.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				insertNotice();
				dispose();							//글쓰기 버튼 눌르면 db에 인서트 되야하는 메소드
			}
		});
		
		btUpdate = new JButton("수정");
		btUpdate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				updateNotice();
				dispose();								//수정 버튼 눌렀을때 작동해야하는 메소드
			}
		});
		btDelete = new JButton("삭제");
		btDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteNotice();
				dispose();						//삭제 버튼 눌렀을때 작동해야하는 메소드
			}
		});
		
		JPanel panTf = new JPanel();				//텍스트필드를 담은 판넬
		panTf.setLayout(new GridLayout(2,2,10,10));
		panTf.add(new JLabel("제목"));
		panTf.add(tfNtitle);
		panTf.add(new JLabel("작성자"));
		panTf.add(tfMno);
			
		JPanel panLb = new JPanel();				//[공지클 내용]라벨을 담은 판넬
		panLb.setLayout(new FlowLayout(FlowLayout.LEFT));
		panLb.add(new JLabel("[공지글 내용]"));
		
		JPanel panUp = new JPanel();				//위의 2개의 판넬을 담은 판넬
		panUp.setLayout(new BorderLayout());
		panUp.add(panTf, BorderLayout.NORTH);
		panUp.add(panLb, BorderLayout.SOUTH);
		
		JPanel panBt = new JPanel();				//버튼을 담은 판넬
		panBt.setLayout(new FlowLayout(FlowLayout.RIGHT));
			
		if( pop == 1 && mno == 1) {
			panBt.add(btNew);
			setTitle("새 글쓰기");
			iDao = new ItemDao();
			tfMno.setText(iDao.getNickname(mno));
			tfNtitle.setEditable(true);
			taNtext.setEditable(true);
			
		}
		else {
			if(mno == 1 ) {
				panBt.add(btUpdate);
				panBt.add(btDelete);
				tfNtitle.setEditable(true);
				taNtext.setEditable(true);
			}
			setTitle("공지글 보기");
			getNotice(nno);
			
		}
		
		
		JScrollPane jsp = new JScrollPane(taNtext);
		
		setLayout(new BorderLayout());
		add(panUp, BorderLayout.NORTH);
		add(jsp, BorderLayout.CENTER);
		add(panBt, BorderLayout.SOUTH);	
		
		

		setSize(700, 900);
		setResizable(false);
		setVisible(true);
	}


	protected void deleteNotice() {
		dao = new NoticeDao();
		int re = dao.deleteNotice(nno);
		 
		if(re >= 1 )
	        {
	            JOptionPane.showMessageDialog(null, "공지글 삭제 완료");
	            
	        }
	        else
	        {
	            JOptionPane.showMessageDialog(null, "공지글 삭제 실패");
	            dispose();
	        }
		
		
	}


	protected void updateNotice() {
		dao = new NoticeDao();
		NoticeVo vo = new NoticeVo();
		vo.setNtitle(tfNtitle.getText());
		vo.setNno(nno);
		vo.setNtext(taNtext.getText());
		
		int re = dao.updateNotice(vo);
		
		  if(re >= 1 )
        {
            JOptionPane.showMessageDialog(null, "공지글 수정 완료");
            
        }
        else
        {
            JOptionPane.showMessageDialog(null, "공지글 수정 실패");
            dispose();
        }
	}


	protected void insertNotice() {
		dao = new NoticeDao();
		NoticeVo vo = new NoticeVo();
		vo.setNtitle(tfNtitle.getText());
		vo.setMno(mno);
		vo.setNtext(taNtext.getText());
		
		int re = dao.insertNotice(vo);
		  if(re >=1 )
          {
              JOptionPane.showMessageDialog(null, "공지글 등록 완료");
              
              
              dispose();
              
          }
          else
          {
              JOptionPane.showMessageDialog(null, "공지글 등록 실패");
              dispose();
          }
	}


	private void getNotice(int nno) {
		dao = new NoticeDao();
		iDao = new ItemDao();
		NoticeVo v = dao.getNotice(nno);
		tfNtitle.setText(v.getNtitle());
		tfMno.setText(iDao.getNickname(v.getMno()));
		taNtext.setText(v.getNtext());
		
		
		
	}
	
	
}
