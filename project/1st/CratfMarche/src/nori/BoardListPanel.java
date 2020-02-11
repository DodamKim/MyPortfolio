package nori;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;

import marcheDao.BoardDao;
import marcheVo.BoardVo;

public class BoardListPanel extends JPanel {
	
	JComboBox<String> cbMarketBloc;
	JComboBox<String> cbShopBloc;
	String[] bloc = {"모든지역","서울","경기","인천","대전","대구","부산","울산","광주","세종","제주","전남/전북","강남/경북","충남/충북","강원도"};
	String cbValueMarket;
	String cbValueShop;
	
	Vector<String> marketColNames;
	Vector<String> shopColNames;
	
	Vector<Vector<String>> marketRowData;
	Vector<Vector<String>> shopRowData;
	
	JTable marketTable;
	JTable shopTable;
	
	JButton btNew;
	
	BoardInsert_POP pop; 
	BoardDetail_POP popDetail; 				// 팝업프레임
	ArrayList<BoardVo> marketList;
	ArrayList<BoardVo> shopList;
	public static int mno;
	
	BoardDao dao;
	BoardVo vo;
	BoardListPanel p;
	
	BoardListPanel(){	
		
		this.p = this;
		
		btNew = new JButton("새 글쓰기");
		btNew.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				pop = new BoardInsert_POP(mno, p);
				
			}
		});
		
		cbMarketBloc = new JComboBox<String>(bloc);	
		cbMarketBloc.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cbValueMarket = (String)cbMarketBloc.getSelectedItem();
				listMarketBoard(cbValueMarket);
			}
		});
		
		cbShopBloc = new JComboBox<String>(bloc);	
		cbShopBloc.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cbValueShop = (String)cbShopBloc.getSelectedItem();
				listShopBoard(cbValueShop);
			}
		});
		
		
		marketColNames = new Vector<String>();
		marketColNames.add("마켓 이름");
		marketColNames.add("지역");
		marketColNames.add("기간");
		
		
		shopColNames = new Vector<String>();
		shopColNames.add("공방 이름");
		shopColNames.add("지역");
		shopColNames.add("기간");
		
		marketRowData =new Vector<Vector<String>>();
		shopRowData = new Vector<Vector<String>>();
		
		marketTable = new JTable(marketRowData, marketColNames);
		marketTable.addMouseListener(new MouseListener() {
			
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
				// TODO Auto-generated method stub
				BoardDao bdao = new BoardDao();
				int bno = Integer.parseInt(marketRowData.get(marketTable.getSelectedRow()).get(3));
				vo = bdao.getBoard(bno);
				popDetail = new BoardDetail_POP(2, vo, p, cbValueMarket);
				
			}
		});
		
		shopTable = new JTable(shopRowData, shopColNames);
		shopTable.addMouseListener(new MouseListener() {
			
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
				
				BoardDao bdao = new BoardDao();
				int bno = Integer.parseInt(shopRowData.get(shopTable.getSelectedRow()).get(3));
				vo = bdao.getBoard(bno);
				popDetail = new BoardDetail_POP(3, vo, p, cbValueShop);
				
			}
		});
		
		JScrollPane marketJsp = new JScrollPane(marketTable);
		JScrollPane shopJsp = new JScrollPane(shopTable);
	
		listMarketBoard(null);
		listShopBoard(null);
		
		
		JPanel panBt = new JPanel();	//버튼을 담은 판넬 오른쪽으로 밀기위해 만듬
		panBt.setLayout(new FlowLayout(FlowLayout.RIGHT));
		if( mno == 1) {
			panBt.add(btNew);
		}
		
		JPanel panMarketBloc = new JPanel();	//지역콤보박스를 담은 판넬 오른쪽으로 밀기위해 만듬
		panMarketBloc.setLayout(new FlowLayout(FlowLayout.LEFT));
		panMarketBloc.add(cbMarketBloc);
		
		JPanel panShopBloc = new JPanel();	//지역콤보박스를 담은 판넬 오른쪽으로 밀기위해 만듬
		panShopBloc.setLayout(new FlowLayout(FlowLayout.LEFT));
		panShopBloc.add(cbShopBloc);
		
		JPanel panMarket = new JPanel();	//마켓 지역 콤보박스와 리스트를 담은 판넬
		panMarket.setLayout(new BorderLayout());
		panMarket.add(panMarketBloc, BorderLayout.NORTH);
		panMarket.add(marketJsp, BorderLayout.CENTER);
		
		JPanel panShop = new JPanel();	//공방 지역 콤보박스와 리스트를 담은 판넬
		panShop.setLayout(new BorderLayout());
		panShop.add(panShopBloc, BorderLayout.NORTH);
		panShop.add(shopJsp, BorderLayout.CENTER);
		
		JPanel panList = new JPanel();
		panList.setLayout(new GridLayout(1,1,10,10));
		panList.add(panMarket);
		panList.add(panShop);
		
		setLayout(new BorderLayout());
		add(panList, BorderLayout.CENTER);
		add(panBt, BorderLayout.SOUTH);
	
	}


	void listMarketBoard(String bloc) {
		marketRowData.clear();
		dao = new BoardDao();
		marketList = dao.listBoard(1, bloc);
		for(BoardVo b : marketList ) {
			Vector<String> v = new Vector<String>();
			v.add(b.getBtitle());
			v.add(b.getBloc());
			v.add(b.getBdate());
			v.add(b.getBno()+"");
			
			marketRowData.add(v);
		}
		marketTable.updateUI();
	}
	
	void listShopBoard(String bloc) {
		shopRowData.clear();
		dao = new BoardDao();
		shopList = dao.listBoard(0, bloc);
		for(BoardVo b : shopList ) {
			Vector<String> v = new Vector<String>();
			v.add(b.getBtitle());
			v.add(b.getBloc());
			v.add(b.getBdate());
			v.add(b.getBno()+"");
			
			shopRowData.add(v);
		}
		shopTable.updateUI();
	}
		
	


	
	

}