package mediaone.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class StaffInformation extends JPanel{
	private JTextField tfIdStaff;
	private JTextField tfNameStaff;
	private JTextField tfSalary;
	
	public JTextField getTfIdStaff() {
		return tfIdStaff;
	}
	public JTextField getTfNameStaff() {
		return tfNameStaff;
	}
	public JTextField getTfSalary() {
		return tfSalary;
	}
	
	public StaffInformation() {
		setSize(450, 350);
		setLayout(new BorderLayout());
		setBorder(new TitledBorder("Thông tin nhân viên"));
		add(createPanelLabel(), BorderLayout.WEST);
		add(createPanelTextField(), BorderLayout.CENTER);
	}
	
	private JPanel createPanelLabel() {
		JPanel panel = new JPanel(new GridLayout(3, 1, 5, 5));
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel.add(new JLabel("Mã nhân viên "));
		panel.add(new JLabel("Tên nhân viên "));
		panel.add(new JLabel("Lương "));
		
		return panel;
	}
	
	private JPanel createPanelTextField() {
		JPanel panel = new JPanel (new GridLayout(3, 1, 5, 5));
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		tfIdStaff   = new JTextField(30);			panel.add(tfIdStaff);
		tfNameStaff = new JTextField(30);			panel.add(tfNameStaff);
		tfSalary    = new JTextField(30);			panel.add(tfSalary);
		
			
		return panel;
	}	
}
