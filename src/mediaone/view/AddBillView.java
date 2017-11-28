package mediaone.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class AddBillView extends JDialog{
	private MainUI mainUI;
	private BillInformation billInformation;
	
	private JButton btnAdd    = new JButton("THANH TOÁN");
	private JButton btnReset  = new JButton("TẠO LẠI");
	private JButton btnCancel = new JButton("HỦY");
	
	// Constructor
	public AddBillView(MainUI mainUI) {
		this.mainUI = mainUI;
		setPreferredSize(new Dimension(1020, 338));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(true);
		setTitle("Thêm mượn trả mới");
		
		billInformation = new BillInformation();
		
		add(createMainPanel());
		pack();
		setLocationRelativeTo(null);
	}

	public BillInformation getBillInformation() {
		return billInformation;
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
	
	private JPanel createMainPanel() {
		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.add(billInformation, BorderLayout.CENTER);
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
