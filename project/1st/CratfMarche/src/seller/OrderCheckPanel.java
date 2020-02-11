package seller;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import marcheDao.MemberDao;
import marcheDao.OdetailDao;
import marcheVo.OdetailVo;


public class OrderCheckPanel extends JPanel{

	JTable table;
	Vector<String> colNames;
	Vector<Vector<String>> rowData;
	JComboBox<String> cbShip;
	String forCb[]= {"주문완료","배송중","배송완료"};
	OdetailDao dao;
	ArrayList<ArrayList<String>> list;
	String ship;
	JCheckBox box;
	int odno;
	
	public static int mno;
	
	public OrderCheckPanel()
	{
		super();
		colNames = new Vector<String>();
		rowData = new Vector<Vector<String>>();
		box = new JCheckBox();
		colNames.add("주문번호");
		colNames.add("주문자명");
		colNames.add("상품명");
		colNames.add("주문일자");		
		colNames.add("배송상태");
		colNames.add("배송선택");
		
		cbShip = new JComboBox<String>(forCb);	
		cbShip.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				listAll();
			}
		});
					
		table = new JTable(new DefaultTableModel(rowData,colNames));
		JScrollPane jsp = new JScrollPane(table);	
		box = new JCheckBox();
		
        table.getColumn("배송선택").setCellRenderer(dcr);
        table.getColumn("배송선택").setCellEditor(new DefaultCellEditor(box));
        box.setHorizontalAlignment(JLabel.CENTER);
        
        listAll();
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
				// TODO Auto-generated method stub
				int index = table.getSelectedRow();			
				odno = Integer.parseInt(rowData.get(index).get(0));
				OrderDetail_POP.odno = odno;
				new OrderDetail_POP();
				
			}    
		});
        
        JPanel upLeft = new JPanel();
        upLeft.setLayout(new FlowLayout(FlowLayout.LEFT));
        upLeft.add(new JLabel("주문 LIST"));
        
        JPanel upRight = new JPanel();
        upRight.setLayout(new FlowLayout(FlowLayout.RIGHT));
        upRight.add(cbShip);
        JButton btShip = new JButton("배송수정");
        JButton btList = new JButton("목록보기");
        
        MemberDao mdao = new MemberDao();
        System.out.println(mdao.checkMemberLv(mno));
        if(mdao.checkMemberLv(mno) != 2) {
        	btShip.setEnabled(false);
        	btList.setEnabled(false);
        }
        
        upRight.add(btShip);
        upRight.add(btList);
        btShip.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub				
				ship= (String)cbShip.getSelectedItem();
				if(ship.equals("주문완료")) {
				  int re= dao.updateShip(odno);
				   if(re==1) {
					   JOptionPane.showMessageDialog(null, "배송수정이 완료되었습니다.");
					   table.updateUI();
				   }
				   
				}else {
					JOptionPane.showMessageDialog(null, "주문완료건만 배송수정이 가능합니다.");
				}
			}
		});
        
        btList.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				listAll();
			}
		});
        
        JPanel up = new JPanel();
        up.setLayout(new BorderLayout());
        up.add(upLeft,BorderLayout.CENTER);
        up.add(upRight, BorderLayout.EAST);
        
        setLayout(new BorderLayout());
		add(up,BorderLayout.NORTH);
		add(jsp,BorderLayout.CENTER);
        
       
        setVisible(true);
        
       
	}

	protected void listAll() {
		// TODO Auto-generated method stub
		ship= (String)cbShip.getSelectedItem();
		rowData.clear();
		dao = new OdetailDao();
		list = dao.listMyOrders(mno, ship);
		for(ArrayList<String> a : list) {
			Vector v = new Vector<String>();
		    v.add(a.get(0));
		    v.add(a.get(1));
		    v.add(a.get(2));
		    v.add(a.get(5));
		    v.add(a.get(9));
		    v.add(false);
		    rowData.add(v);
		}
		table.updateUI();
	}
	
	 DefaultTableCellRenderer dcr = new DefaultTableCellRenderer()
     {
      public Component getTableCellRendererComponent  // 셀렌더러
       (JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
      {
       JCheckBox box= new JCheckBox();
       box.setSelected(((Boolean)value).booleanValue());  
       box.setHorizontalAlignment(JLabel.CENTER);
       return box;
      }
     };

	
}


