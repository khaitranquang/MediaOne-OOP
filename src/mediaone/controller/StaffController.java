package mediaone.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import mediaone.model.Staff;
import mediaone.service.StaffService;
import mediaone.view.AddStaffView;
import mediaone.view.EditStaffView;
import mediaone.view.MainUI;
import mediaone.view.StaffInformation;
import mediaone.view.TableStaffView;

public class StaffController {
	private MainUI mainUI;
	private StaffService staffService;
	private JButton btnSalary;
	private JButton btnAdd;
	private JButton btnEdit;
	private JButton btnDelete;
	
	private TableStaffView tableStaffView;
	
	public StaffController(MainUI mainUI) {
		this.mainUI = mainUI;
		staffService = new StaffService();
		
		/* Initialize view */
		btnSalary = mainUI.getManagerStaff().getButtonStaffView().getBtnSalary();
		btnAdd = mainUI.getManagerStaff().getButtonStaffView().getBtnAdd();
		btnEdit = mainUI.getManagerStaff().getButtonStaffView().getBtnEdit();
		btnDelete = mainUI.getManagerStaff().getButtonStaffView().getBtnDelete();
		tableStaffView = mainUI.getManagerStaff().getTableStaffView();
		tableStaffView.updateTable(staffService.findAll());
		
		/*
		 * Set Actions for button
		 */
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				actionAddStaff();
			}
		});
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				actionEditStaff();
			}
		});
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				actionDeleteStaff();
			}
		});
		btnSalary.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				actionSalaryStaff();
			}
		});
		
	}
	
	/*
	 * Add Staff
	 * Show addStaff Dialog, get buttons on dialog and handle events
	 */
	private void actionAddStaff() {
		/* Show addStaff Dialog */
		AddStaffView addStaffView = new AddStaffView(mainUI);
		StaffInformation staffInformation = addStaffView.getStaffInformation();
		addStaffView.setVisible(true);
		/* Get all buttons on dialog */
		JButton btnAddStaff = addStaffView.getBtnAdd();
		JButton btnReset = addStaffView.getBtnReset();
		JButton btnCancel = addStaffView.getBtnCancel();
		/* Handle events */
		btnAddStaff.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					String idStaff = staffInformation.getTfIdStaff().getText().toString();
					String nameStaff = staffInformation.getTfNameStaff().getText().toString();
					Double salary = Double.parseDouble(staffInformation.getTfSalary().getText().toString());
					
					Staff staff = new Staff(idStaff, nameStaff, idStaff, salary, 0);
					Staff staffIsAdd = staffService.add(staff);
					if (staffIsAdd == null) {
						JOptionPane.showMessageDialog(new JDialog(), "Các trường dữ liệu cần nhập đúng định dạng - Không nhấp số âm \n"
																   + "Các trường không được để trống");
						return;
					}
					tableStaffView.updateTable(staffService.findAll());
					addStaffView.setVisible(false);
				}
				catch(NumberFormatException ex) {
					JOptionPane.showMessageDialog(new JDialog(), "Các trường số cần nhập đúng định dạng");
				}
				
				
			}
		});
		btnReset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				clearInput(staffInformation);
			}
		});
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clearInput(staffInformation);
				addStaffView.setVisible(false);
			}
		});
	}
	
	/* Clear Input */
	private void clearInput(StaffInformation staffInformation) {
		staffInformation.getTfIdStaff().setText("");
		staffInformation.getTfNameStaff().setText("");
		staffInformation.getTfSalary().setText("");
	}
	
	/*
	 * Edit Staff
	 * Get index of row. If one row is selected, we will show editStaff Dialog
	 */
	private void actionEditStaff() {
		int index = findIndexOfData();
		if (index >= 0) {
			/* Show editStaff dialog and load information of staff for this dialog */
			String idStaff = getValueFromTable(index, 0);
			EditStaffView editStaffView = new EditStaffView(mainUI);
			loadInfor(idStaff, editStaffView);
			editStaffView.setVisible(true);
			/* Get all buttons on dialog */
			JButton btnEdit = editStaffView.getBtnEdit();
			JButton btnCancel = editStaffView.getBtnCancel();
			/* confirm update this staff */
			btnEdit.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						Staff oldStaff = staffService.findOne(idStaff);
						String pass = oldStaff.getPass();
						int days = oldStaff.getDays();
						String newNameStaff = editStaffView.getStaffInformation().getTfNameStaff().getText().toString();
						Double newSalary = Double.parseDouble(editStaffView.getStaffInformation().getTfSalary().getText().toString());
						Staff staff = new Staff(idStaff, newNameStaff, pass, newSalary, days);
						Staff staffIsEdit =  staffService.update(staff);
						if (staffIsEdit == null) {
							JOptionPane.showMessageDialog(new JDialog(), "Các trường dữ liệu cần nhập đúng định dạng - Không nhấp số âm \n"
									   								   + "Các trường không được để trống");
							return;
						}
						tableStaffView.updateTable(staffService.findAll());
						editStaffView.setVisible(false);
					}
					catch(NumberFormatException ex) {
						JOptionPane.showMessageDialog(new JDialog(), "Các trường số cần nhập đúng định dạng");
					}
				}
			});
			/* Close edit dialog */
			btnCancel.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					editStaffView.setVisible(false);
				}
			});	
		}
		else {
			JOptionPane.showMessageDialog(new JDialog(), "Chọn một nhân viên để sửa");
		}
	}
	
	/* Find index of row is selected */
	private int findIndexOfData() {
		int index = tableStaffView.getTable().getSelectedRow();
		return index;
	}
	
	/* Get value from table */
	private String getValueFromTable(int indexRow, int indexCol) {
		JTable table = tableStaffView.getTable();
		String id = table.getModel().getValueAt(indexRow, indexCol).toString();
		return id;
	}
	
	/* Load information of staff */
	private void loadInfor(String id, EditStaffView editStaffView) {
		StaffInformation staffInformation = editStaffView.getStaffInformation();
		Staff oldStaff = staffService.findOne(id);
		staffInformation.getTfIdStaff().setText(id);
		staffInformation.getTfIdStaff().setEditable(false);
		staffInformation.getTfNameStaff().setText(oldStaff.getNameStaff());
		staffInformation.getTfSalary().setText(oldStaff.getSalary() + "");
	}
	
	/*
	 * Delete Staff
	 */
	private void actionDeleteStaff () {
		int indexOfRow = findIndexOfData();
		if (indexOfRow >= 0) {
			int select = JOptionPane.showOptionDialog(null, "Bạn có muốn xoá nhân viên này không?",
					 "Xoá", JOptionPane.YES_NO_OPTION,
					 JOptionPane.QUESTION_MESSAGE, null, null, null);
			if (select == 0) {
				String idStaff = getValueFromTable(indexOfRow, 0);
				staffService.remove(idStaff);
				tableStaffView.updateTable(staffService.findAll());
			}
		}
		else {
			JOptionPane.showMessageDialog(new JDialog(), "Chọn 1 nhân viên để xóa");
		}	
	}
	
	/*
	 * Calculating salary for staff
	 */
	private void actionSalaryStaff() {
		int indexOfRow = findIndexOfData();
		if (indexOfRow >= 0) {
			String idStaff = getValueFromTable(indexOfRow, 0);
			Double salaryOfStaff = staffService.countSalary(idStaff);
			JOptionPane.showMessageDialog(new JDialog(), "Lương của nhân viên là: " + salaryOfStaff);
			tableStaffView.updateTable(staffService.findAll());
		}
		else {
			JOptionPane.showMessageDialog(new JDialog(), "Chọn 1 nhân viên để tính lương");
		}
	}
}
