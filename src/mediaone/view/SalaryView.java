package mediaone.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SalaryView extends JDialog{
	private MainUI mainUI;
	
	private JLabel lbIdStaff;
	private JLabel lbNameStaff;
	private JLabel lbNumDayWork;
	private JLabel lbSalary;
	
	public JLabel getLbIdStaff() {
		return lbIdStaff;
	}
	public JLabel getLbNameStaff() {
		return lbNameStaff;
	}
	public JLabel getLbNumDayWork() {
		return lbNumDayWork;
	}
	public JLabel getLbSalary() {
		return lbSalary;
	}
	
	public SalaryView(MainUI mainUI) {
		this.mainUI = mainUI;
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(true);
		setTitle("Tính lương cho nhân viên");

		add(createMainPanel());
		pack();
		setLocationRelativeTo(null);
	}
	
	private JPanel createMainPanel() {
		JPanel mainPanel = new JPanel(new BorderLayout(5, 5));
		mainPanel.add(createLabelPanel(), BorderLayout.WEST);
		mainPanel.add(createResultPanel(), BorderLayout.CENTER);
		return mainPanel;
	}
	
	private JPanel createLabelPanel() {
		JPanel labelPanel = new JPanel(new GridLayout(4, 1, 5, 5));
		labelPanel.add(new JLabel("Mã nhân viên "));
		labelPanel.add(new JLabel("Tên nhân viên "));
		labelPanel.add(new JLabel("Số buổi đi làm "));
		labelPanel.add(new JLabel("Tổng lương "));
		return labelPanel;
	}
	
	private JPanel createResultPanel() {
		JPanel panel = new JPanel(new GridLayout(4, 1, 5, 5));
		panel.add(lbIdStaff);
		panel.add(lbNameStaff);
		panel.add(lbNumDayWork);
		panel.add(lbSalary);
		return panel;
	}
}
