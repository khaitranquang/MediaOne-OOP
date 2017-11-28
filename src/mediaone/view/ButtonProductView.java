package mediaone.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ButtonProductView extends JPanel{
	private JButton btnAdd = new JButton("Thêm");
	private JButton btnEdit = new JButton("Sửa");
	private JButton btnDelete = new JButton("Xóa");
	
	public JButton getBtnAdd() {
		return btnAdd;
	}
	public JButton getBtnEdit() {
		return btnEdit;
	}
	public JButton getBtnDelete() {
		return btnDelete;
	}
	
	public ButtonProductView() {
		setLayout(new BorderLayout(10, 10));
		setBorder(new EmptyBorder(45, 200, 45, 200));
		add (createButtonPanel(), BorderLayout.CENTER);
	}
	
	private JPanel createButtonPanel() {
		JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 10, 10));
		buttonPanel.add(btnAdd);
		buttonPanel.add(btnEdit);
		buttonPanel.add(btnDelete);
		return buttonPanel;
	}
}
