package mediaone.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import mediaone.model.Staff;

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
	public void updateTable(List<Staff> list) {
		SwingUtilities.invokeLater(new Runnable(){public void run(){
		    //Update the model here
			String data[][] = convertData(list);
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
	
	// Convert list of Book => Array 2D
	private String[][] convertData(List<Staff> list) {
		int size = list.size();
		String data[][] = new String[size][titleCols.length];
		for (int i = 0; i < size; i++) {
			Staff staff = list.get(i);
			data[i][0] = staff.getIdStaff();
			data[i][1] = staff.getNameStaff();
			data[i][2] = staff.getSalary() + "";
			data[i][3] = staff.getDays() + "";
		}
		return data;
	}
}
