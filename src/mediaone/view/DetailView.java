package mediaone.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class DetailView extends JDialog{
	private MainUI mainUI;
	private DetailInformation detailInformation;
	
	private JButton btnCancel = new JButton("HỦY");
	
	public DetailInformation getDetailInformation() {
		return detailInformation;
	}
	public JButton getBtnCancel() {
		return btnCancel;
	}
	
	public DetailView(MainUI mainUI) {
		this.mainUI = mainUI;
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setTitle("Chi tiết hoá đơn");
	
		detailInformation =  new DetailInformation();
		
		add(createMainPanel());
		pack();
		setLocationRelativeTo(null);
	}
	
	private JPanel createMainPanel() {
		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		mainPanel.add(detailInformation, BorderLayout.CENTER);
		mainPanel.add(createButtonPanel(), BorderLayout.PAGE_END);
		
		return mainPanel;
	}
	
	private JPanel createButtonPanel() {
		JPanel panel = new JPanel (new GridLayout(1, 1, 10, 10));
		panel.setBorder(new EmptyBorder(10,10,10,10));
		panel.add(btnCancel);
		return panel;
	}
}
