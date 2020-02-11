package item;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import marcheDao.ItemDao;

public class ItemMainPanel extends JPanel implements ActionListener{

	JComboBox<String> cbIlv;
	JComboBox<String> cbItype;
	JComboBox<String> cbMtype;
	JTextField tfKeyword;

	JButton btSearch;
	JButton btRankHit;
	JButton btRankPrice;
	JButton btRankIname;
	JButton btRankSellName;

	JButton btImg;
	
	JPanel panUp;
	JPanel panUpSearch;
	JPanel panUpRank;
	JPanel panMainList;
	JPanel panItem;
	JPanel panImg;
	JPanel panItemInfo;
	JScrollPane jspMain;

	ArrayList<JPanel> panList;

	ImageIcon icon;
	JLabel lbImg;
	JLabel lbIname;
	JLabel lbIprice;
	JLabel lbIlv;
	JLabel lbItname;
	JLabel lbSeller;
	JLabel lbIhit;

	String sIname = "  ";
	String sIprice = "  ";
	String sIlv="  ";
	String sItname = "  >  ";
	String seller = "  [ 셀러 닉네임 ] :  ";
	String sIhit="  [ 조회수 ] :  ";
	String sIno="";
	

	ItemDao dao;
	ArrayList<ArrayList<String>> list;
	Vector<String> vILv;
	Vector<String> vIType;
	Vector<String> vMType;
	String ilv = "";
	String selIlv="전체";
	String selItype="전체";
	String selMtype="";
	String arr="";
	int n=0;
	int i;
	int row;
	
	Vector<String> tList;
	//Vector<Integer> inoList;

	public ItemMainPanel() {

		dao = new ItemDao();
		//inoList = new Vector<Integer>();

		vILv = new Vector<String>();
		vILv.add("전체");
		vILv.add("유형");
		vILv.add("무형");

		vIType = new Vector<String>();
		vIType.add("전체");
		for (String s : dao.listIType(selIlv)) {
			vIType.add(s);
		}

		vMType = new Vector<String>();
		vMType.add("(선택)");
		vMType.add("셀러닉네임");
		vMType.add("상품명");

		list = new ArrayList<ArrayList<String>>();

		cbIlv = new JComboBox<String>(vILv);
		cbItype = new JComboBox<String>(vIType);
		cbMtype = new JComboBox<String>(vMType);

		cbIlv.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cbItype.removeAllItems();
				selIlv = (String) cbIlv.getSelectedItem();
				
				listTname(selIlv);				
			}
		});
		
		cbItype.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				selItype = (String)cbItype.getSelectedItem();
			}
		});
		
		cbMtype.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				selMtype = (String)cbMtype.getSelectedItem();
				tfKeyword.setText("");
			}
		});

		tfKeyword = new JTextField(30);
		tfKeyword.setText("(검색어를 입력해주세요.)");
		tfKeyword.addMouseListener(new MouseListener() {

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
				// tfKeyword.setEditable(true);
				tfKeyword.setText("");
			}
		});

		btSearch = new JButton("검색");
		btSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//매개변수 : 유무형, 타입명, 판매자/상품명, 검색어
				System.out.println("유무형/"+selIlv);
				System.out.println("타입/"+selItype);
				System.out.println("판매,상품/"+selMtype);
				System.out.println("검색어/"+tfKeyword.getText());
				
				if (selMtype == "" && !tfKeyword.getText().equals("") && !tfKeyword.getText().equals("(검색어를 입력해주세요.)")) {
					JOptionPane.showMessageDialog(null, "'(선택)'에서 '셀러 닉네임' 혹은 '상품명'중 하나를 선택해주세요!");
					return;
				} 
//				else if(selMtype == "" && !tfKeyword.getText().equals("(검색어를 입력해주세요.)")) {
//					
//				}
				
				list.clear();
				list = dao.listAllSearchItem("ino", 1, selIlv, selItype, selMtype, tfKeyword.getText());
				panList.clear();
				panMainList.removeAll();
				listAllItem();
				panMainList.revalidate();
				panMainList.repaint();
				
//				if(!tfKeyword.getText().equals("(검색어를 입력해주세요.)")) {
//					list.clear();
//					list = dao.listAllSearchItem("ino", 1, selIlv, selItype, selMtype, tfKeyword.getText());
//					panList.clear();
//					panMainList.removeAll();
//					listAllItem();
//					panMainList.revalidate();
//					panMainList.repaint();
//				} else {
//					JOptionPane.showMessageDialog(null, "검색어를 입력해주세요!");
//				}
			}
		});
		
		btRankHit = new JButton("인기순");
		btRankHit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				n += 1;
				list.clear();
				list = dao.listAllSearchItem("ihit", n, selIlv, selItype, selMtype, tfKeyword.getText());
				panList.clear();
				panMainList.removeAll();
				listAllItem();
				panMainList.revalidate();
				panMainList.repaint();
			}
		});
		
		btRankPrice = new JButton("가격순");
		btRankPrice.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				n += 1;
				list.clear();
				list = dao.listAllSearchItem("iprice", n, selIlv, selItype, selMtype, tfKeyword.getText());
				panList.clear();
				panMainList.removeAll();
				listAllItem();
				panMainList.revalidate();
				panMainList.repaint();
			}
		});
		
		btRankIname = new JButton("상품명");
		btRankIname.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				n += 1;
				list.clear();
				list = dao.listAllSearchItem("iname", n, selIlv, selItype, selMtype, tfKeyword.getText());
				panList.clear();
				panMainList.removeAll();
				listAllItem();
				panMainList.revalidate();
				panMainList.repaint();
			}
		});
		
		btRankSellName = new JButton("셀러명");
		btRankSellName.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				n += 1;
				list.clear();
				list = dao.listAllSearchItem("nickname", n, selIlv, selItype, selMtype, tfKeyword.getText());
				panList.clear();
				panMainList.removeAll();
				listAllItem();
				panMainList.revalidate();
				panMainList.repaint();
			}
		});

		panUpSearch = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panUpSearch.add(cbIlv);
		panUpSearch.add(cbItype);
		panUpSearch.add(cbMtype);
		panUpSearch.add(tfKeyword);
		panUpSearch.add(btSearch);

		panUpRank = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panUpRank.add(btRankHit);
		panUpRank.add(btRankPrice);
		panUpRank.add(btRankIname);
		panUpRank.add(btRankSellName);

		panUp = new JPanel(new BorderLayout());
		panUp.add(panUpSearch, BorderLayout.CENTER);
		panUp.add(panUpRank, BorderLayout.EAST);

		// 상품 리스트 판넬로 각기 출력
		list = dao.listAllSearchItem("ino", 1, selIlv, selItype, selMtype, "");
		//System.out.println("크기"+list.size());
		row = list.size();
		panList = new ArrayList<JPanel>();
		panMainList = new JPanel(new GridLayout((row+1)/2, 2));
		//JScrollPane jspMain = new JScrollPane(panMainList);
		jspMain = new JScrollPane(panMainList);
		//listAllItem("ino", 1, selIlv, selItype, selMtype, "");
		listAllItem();
		
		setLayout(new BorderLayout());
		add(panUp, BorderLayout.NORTH);
		add(jspMain, BorderLayout.CENTER);

	}
	
	public void listAllItem() {
		// 상품 리스트 판넬로 각기 출력
		for (i = 0; i < list.size(); i++) {
			//inoList.clear();
			sIname = "  ";
			sIprice = "  ";
			sIlv="  ";
			sItname = "  >  ";
			seller = "  [ 셀러 닉네임 ] :  ";
			sIhit="  조회수  :  ";
			sIno = "";
			
			// ilv, tname, ino, iname, img, nickname, iprice, ihit 
			sIname += list.get(i).get(3);
			sIprice += list.get(i).get(6);
			sIlv += list.get(i).get(0);
			sItname += list.get(i).get(1);
			seller  += list.get(i).get(5);
			sIhit += list.get(i).get(7);
			sIno = list.get(i).get(2);
						
			ImageIcon originIcon = new ImageIcon("C:\\marche\\item/"+list.get(i).get(4));
			Image originImg = originIcon.getImage();
			Image changeImg = originImg.getScaledInstance(250, 150, Image.SCALE_SMOOTH);
			icon = new ImageIcon(changeImg);
			
			//lbIno = new JLabel(sIno);
			btImg = new JButton(sIno);
			btImg.setIcon(icon);
			btImg.setBackground(Color.WHITE);
			btImg.addActionListener(this);
			
			lbIname = new JLabel(sIname);
			lbIname.setFont(new Font("", Font.BOLD, 20));
			lbIprice = new JLabel(sIprice+" 원");
			lbIprice.setForeground(Color.RED);
			lbItname = new JLabel(sIlv + sItname);
			lbSeller = new JLabel(seller);
			lbIhit = new JLabel(sIhit);
			
			panItem = new JPanel(new BorderLayout());
			panItem.setBorder(new LineBorder(Color.DARK_GRAY));
			panImg = new JPanel(new BorderLayout());
			panImg.setPreferredSize(new Dimension(250, 150));
			panItemInfo = new JPanel(new GridLayout(5, 1));
			
			panImg.add(btImg, BorderLayout.CENTER);
			panItemInfo.add(lbIname);
			panItemInfo.add(lbIprice);
			panItemInfo.add(lbItname);
			panItemInfo.add(lbSeller);
			panItemInfo.add(lbIhit);
			panItem.add(panImg, BorderLayout.WEST);
			panItem.add(panItemInfo, BorderLayout.CENTER);
			
			panList.add(panItem);
			
		}
		
		for(JPanel p : panList) {
			panMainList.add(p);
		}
	}

	public void listTname(String sel) {
		vIType.clear();
		vIType = dao.listIType(sel);
		cbItype.addItem("전체");
		for (String s : vIType) {
			cbItype.addItem(s);
		}
		cbItype.updateUI();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		JButton button = (JButton) e.getSource();
	    String command = button.getActionCommand();
	    //System.out.println("이벤트:"+command);
	    int ino = Integer.parseInt(command);
	    
	    new ItemDetail_POP(ino, this);
	    dao.addHit("ihit", "ino", ino);
	    
	}
}