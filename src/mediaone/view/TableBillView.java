package mediaone.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import mediaone.model.Bill;

public class TableBillView extends JPanel{
	public static final int TABLE_BILL_WIDTH  = 950;
	public static final int TABLE_BILL_HEIGHT = 350;
	private JTable table;
	private String[] titleCols = {"Mã hóa đơn", "Mã nhân viên", "Ngày xuất"};
	
	public JTable getTable() {
		return table;
	}

	public TableBillView() {
		setLayout(new BorderLayout(5, 5));
		add(createTableStaffPanel(), BorderLayout.CENTER);
	}
	
	private JPanel createTableStaffPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(new EmptyBorder(5, 40, 5, 40));
		table = createTable();
		
		loadData(table);
		JScrollPane scroll = new JScrollPane(table);
		scroll.setPreferredSize(new Dimension(TABLE_BILL_WIDTH, TABLE_BILL_HEIGHT));
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
	public void updateTable(List<Bill> list) {
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
	private String[][] convertData(List<Bill> list) {
		int size = list.size();
		String data[][] = new String[size][titleCols.length];
		for (int i = 0; i < size; i++) {
			Bill aBill = list.get(i);
			data[i][0] = aBill.getIdBill() + "";
			data[i][1] = aBill.getIdStaff() + "";
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			data[i][2] = aBill.getExportDate().format(formatter);
		}
		return data;
	}
}
