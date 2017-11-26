package mediaone.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelManagerStaff extends JPanel{
	private ButtonStaffView buttonStaffView;
	private TableStaffView tableStaffView;
	
	public ButtonStaffView getButtonStaffView() {
		return buttonStaffView;
	}
	public TableStaffView getTableStaffView() {
		return tableStaffView;
	}

	public PanelManagerStaff() {
		setLayout(new BorderLayout(5, 5));
		add(createTitlePanel(), BorderLayout.PAGE_START);
		add(tableStaffView = new TableStaffView(), BorderLayout.CENTER);
		add(buttonStaffView = new ButtonStaffView(), BorderLayout.PAGE_END);
	}
	
	private JPanel createTitlePanel() {
		JPanel panel = new JPanel();
		JLabel label = new JLabel("Quản lí nhân viên");
		label.setFont(new Font("Caribli", Font.BOLD, 18));
		label.setForeground(Color.BLUE);;
		panel.add(label);
		
		return panel;
	}
}
