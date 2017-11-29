package mediaone.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class TableStaffView extends JPanel{
	public static final int TABLE_STAFF_WIDTH  = 950;
	public static final int TABLE_STAFF_HEIGHT = 350;
	private JTable table;
	private String[] titleCols = {"Mã nhân viên", "Tên nhân viên", "Lương", "Số buổi đi làm"};
	
	public JTable getTable() {
		return table;
	}

	public TableStaffView() {
		setLayout(new BorderLayout(5, 5));
		add(createTableStaffPanel(), BorderLayout.CENTER);
	}
	
	private JPanel createTableStaffPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(new EmptyBorder(5, 40, 5, 40));
		table = createTable();
		
		loadData(table);
		JScrollPane scroll = new JScrollPane(table);
		scroll.setPreferredSize(new Dimension(TABLE_STAFF_WIDTH, TABLE_STAFF_HEIGHT));
		panel.add(scroll);
		
		return panel;
	}
	
	private JTable createTable() {
		JTable table = new JTable();
		table.setCellSelectionEnabled(false);
		table.setRowSelectionAllowed(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		return table;
	}
	
	//Load Data
	private void loadData(JTable table) {
		SwingUtilities.invokeLater(new Runnable(){public void run(){
			String data[][] = null;
		    //Update the model here
			DefaultTableModel tableModel = new DefaultTableModel(data, titleCols) {
				@Override
				public boolean isCellEditable(int row, int column) {
					// TODO Auto-generated method stub
					return false;
				}
			};
			table.setModel(tableModel);		
		}});
	}
	
	// Update Model of table
//	public void updateTable(ArrayList<Xe> list) {
//		SwingUtilities.invokeLater(new Runnable(){public void run(){
//		    //Update the model here
//			String data[][] = convertData(list);
//			DefaultTableModel tableModel = new DefaultTableModel(data, titleCols) {
//				@Override
//				public boolean isCellEditable(int row, int column) {
//					// TODO Auto-generated method stub
//					return false;
//				}
//			};
//			table.setModel(tableModel);	
//		}});
//	}
//	
//	// Convert list of Book => Array 2D
//	private String[][] convertData(ArrayList<Xe> list) {
//		int size = list.size();
//		String data[][] = new String[size][titleCols.length];
//		for (int i = 0; i < size; i++) {
//			Xe xe = list.get(i);
//			data[i][0] = xe.getIdXe();
//			data[i][1] = xe.getBienXe();
//			data[i][2] = xe.getTenXe();
//			data[i][3] = xe.getLoaiXe();
//			data[i][4] = xe.getHangSanXuat();
//			data[i][5] = xe.getNamSanXuat();
//			data[i][6] = xe.getNgayBaoTri();
//			data[i][7] = xe.getNhienLieu();
//			data[i][8] = xe.getTrangThai() + "";
//			data[i][9] = xe.getGiaThue() + "";
//		}
//		return data;
//	}
}
