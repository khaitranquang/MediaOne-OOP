package mediaone.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ButtonStaffView extends JPanel{
	private JButton btnSalary = new JButton("Tính lương");
	private JButton btnAdd = new JButton("Thêm");
	private JButton btnEdit = new JButton("Sửa");
	private JButton btnDelete = new JButton("Xóa");
	
	public JButton getBtnSalary() {
		return btnSalary;
	}
	public JButton getBtnAdd() {
		return btnAdd;
	}
	public JButton getBtnEdit() {
		return btnEdit;
	}
	public JButton getBtnDelete() {
		return btnDelete;
	}
	
	public ButtonStaffView() {
		setLayout(new BorderLayout(10, 10));
		setBorder(new EmptyBorder(50, 200, 45, 200));
		add (createButtonPanel(), BorderLayout.CENTER);
	}
	
	private JPanel createButtonPanel() {
		JPanel buttonPanel = new JPanel(new GridLayout(1, 4, 10, 10));
		
		btnAdd.setIcon(new ImageIcon(this.getClass().getResource("/mediaone/resource/add-employee-icon.png")));
		btnEdit.setIcon(new ImageIcon(this.getClass().getResource("/mediaone/resource/edit-employee-icon.png")));
		btnDelete.setIcon(new ImageIcon(this.getClass().getResource("/mediaone/resource/delete-employee-icon.png")));
		
		buttonPanel.add(btnSalary);
		buttonPanel.add(btnAdd);
		buttonPanel.add(btnEdit);
		buttonPanel.add(btnDelete);
		return buttonPanel;
	}
}
