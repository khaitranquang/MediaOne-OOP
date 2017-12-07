package mediaone.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class AddStaffView extends JDialog{
	private MainUI mainUI;
	private StaffInformation staffInformation;
	
	private JButton btnAdd    = new JButton("Thêm");
	private JButton btnReset  = new JButton("Tạo lại");
	private JButton btnCancel = new JButton("Hủy");
	
	public StaffInformation getStaffInformation() {
		return staffInformation;
	}
	public JButton getBtnAdd() {
		return btnAdd;
	}
	public JButton getBtnReset() {
		return btnReset;
	}
	public JButton getBtnCancel() {
		return btnCancel;
	}
	
	public AddStaffView(MainUI mainUI) {
		this.mainUI = mainUI;
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setTitle("Thêm nhân viên mới");
		staffInformation =  new StaffInformation();
		add(createMainPanel());
		pack();
		setLocationRelativeTo(null);
	}
	
	private JPanel createMainPanel() {
		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.add(staffInformation, BorderLayout.CENTER);
		mainPanel.add(createButtonPanel(), BorderLayout.PAGE_END);
		
		return mainPanel;
	}
	
	private JPanel createButtonPanel() {
		JPanel panel = new JPanel (new GridLayout(1, 3, 10, 10));
		panel.setBorder(new EmptyBorder(10,10,10,10));
		
		panel.add(btnAdd);
		panel.add(btnReset);
		panel.add(btnCancel);
		return panel;
	}
}
