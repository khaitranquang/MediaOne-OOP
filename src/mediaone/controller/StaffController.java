package mediaone.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import mediaone.dao.StaffRepositoryImpl;
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
	private StaffRepositoryImpl staffRepositoryImpl;
	private JButton btnSalary;
	private JButton btnAdd;
	private JButton btnEdit;
	private JButton btnDelete;
	
	private TableStaffView tableStaffView;
	
	public StaffController(MainUI mainUI) {
		this.mainUI = mainUI;
		staffService = new StaffService();
		staffRepositoryImpl = new StaffRepositoryImpl();
		btnSalary = mainUI.getManagerStaff().getButtonStaffView().getBtnSalary();
		btnAdd = mainUI.getManagerStaff().getButtonStaffView().getBtnAdd();
		btnEdit = mainUI.getManagerStaff().getButtonStaffView().getBtnEdit();
		btnDelete = mainUI.getManagerStaff().getButtonStaffView().getBtnDelete();
		tableStaffView = mainUI.getManagerStaff().getTableStaffView();
		tableStaffView.updateTable(staffRepositoryImpl.findAll());
		
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
	}
	
	/*
	 * Add Staff
	 */
	private void actionAddStaff() {
		AddStaffView addStaffView = new AddStaffView(mainUI);
		StaffInformation staffInformation = addStaffView.getStaffInformation();
		addStaffView.setVisible(true);
		
		JButton btnAddStaff = addStaffView.getBtnAdd();
		JButton btnReset = addStaffView.getBtnReset();
		JButton btnCancel = addStaffView.getBtnCancel();
		
		btnAddStaff.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String idStaff = staffInformation.getTfIdStaff().getText().toString();
				String nameStaff = staffInformation.getTfNameStaff().getText().toString();
				Double salary = Double.parseDouble(staffInformation.getTfSalary().getText().toString());
				
				Staff staff = new Staff(idStaff, nameStaff, idStaff, salary, 0);
				staffRepositoryImpl.add(staff);
				tableStaffView.updateTable(staffRepositoryImpl.findAll());
				addStaffView.setVisible(false);
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
	 */
	private void actionEditStaff() {
		int index = findIndexOfData();
		if (index >= 0) {
			String idStaff = getValueFromTable(index, 0);
			EditStaffView editStaffView = new EditStaffView(mainUI);
			loadInfor(idStaff, editStaffView);
			editStaffView.setVisible(true);
			
			JButton btnEdit = editStaffView.getBtnEdit();
			JButton btnCancel = editStaffView.getBtnCancel();
			/* confirm update this staff */
			btnEdit.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Staff oldStaff = staffRepositoryImpl.findOne(idStaff);
					String pass = oldStaff.getPass();
					int days = oldStaff.getDays();
					String newNameStaff = editStaffView.getStaffInformation().getTfNameStaff().getText().toString();
					Double newSalary = Double.parseDouble(editStaffView.getStaffInformation().getTfSalary().getText().toString());
					Staff staff = new Staff(idStaff, newNameStaff, pass, newSalary, days);
					staffRepositoryImpl.update(staff, idStaff);
					tableStaffView.updateTable(staffRepositoryImpl.findAll());
					editStaffView.setVisible(false);
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
		Staff oldStaff = staffRepositoryImpl.findOne(id);
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
				staffRepositoryImpl.removebyid(idStaff);
				tableStaffView.updateTable(staffRepositoryImpl.findAll());
			}
		
		}
		else {
			JOptionPane.showMessageDialog(new JDialog(), "Chọn 1 nhân viên để xóa");
		}	
	}
}
