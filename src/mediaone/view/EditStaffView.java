package mediaone.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class EditStaffView extends JDialog{
	private MainUI mainUI;
	private StaffInformation staffInformation;
	
	private JButton btnEdit   = new JButton("Sửa");
	private JButton btnCancel = new JButton("Hủy");
	
	public StaffInformation getStaffInformation() {
		return staffInformation;
	}
	public JButton getBtnEdit() {
		return btnEdit;
	}
	public JButton getBtnCancel() {
		return btnCancel;
	}
	
	public EditStaffView(MainUI mainUI) {
		this.mainUI = mainUI;
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setTitle("Sửa nhân viên");
		staffInformation = new StaffInformation();
		add(createMainPanel());
		pack();      					//Auto update UI
		setLocationRelativeTo(null);
	}
	
	private JPanel createMainPanel() {
		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.add(staffInformation, BorderLayout.CENTER);
		mainPanel.add(createButtonPanel(), BorderLayout.PAGE_END);
		return mainPanel;
	}
	
	private JPanel createButtonPanel() {
		JPanel panel = new JPanel(new GridLayout(1, 2, 10, 10));
		panel.setBorder(new EmptyBorder(10,10,10,10));
		
		panel.add(btnEdit);
		panel.add(btnCancel);
		return panel;
	}
}
