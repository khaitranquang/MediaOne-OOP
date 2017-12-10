package mediaone.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import mediaone.service.BillServiceImpl;
import mediaone.view.MainUI;
import mediaone.view.TableBillView;

public class DeleteBillController {
	private MainUI mainUI;
	private BillServiceImpl billServiceImpl;
	private TableBillView tableBillView;
	
	public DeleteBillController(MainUI mainUI) {
		this.mainUI = mainUI;
		billServiceImpl = new BillServiceImpl();
		tableBillView = mainUI.getManagerBill().getTableBillView();
		
		/*
		 * Set action delete bill for btnDelete
		 *  Find index of row that selected and remove it
		 */
		JButton btnDelete = mainUI.getManagerBill().getButtonBillView().getBtnDelete();
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int indexOfRow = findIndexOfData();
				if (indexOfRow >= 0) {
					int select = JOptionPane.showOptionDialog(null, "Bạn có muốn xoá hóa đơn này không?",
							 "Xoá", JOptionPane.YES_NO_OPTION,
							 JOptionPane.QUESTION_MESSAGE, null, null, null);
					if (select == 0) {
						String idBill = getValueFromTable(indexOfRow, 0);
						billServiceImpl.removeByID(Integer.parseInt(idBill));
						tableBillView.updateTable(billServiceImpl.findAll());
					}
				}
				else {
					JOptionPane.showMessageDialog(new JDialog(), "Chọn 1 hóa đơn để xóa");
				}	
			}
		});
	}
	
	/* Find index of row is selected */
	private int findIndexOfData() {
		int index = tableBillView.getTable().getSelectedRow();
		return index;
	}
	
	/* Get value from table */
	private String getValueFromTable(int indexRow, int indexCol) {
		JTable table = tableBillView.getTable();
		String id = table.getModel().getValueAt(indexRow, indexCol).toString();
		return id;
	}
}
