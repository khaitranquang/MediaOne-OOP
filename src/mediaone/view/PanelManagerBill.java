package mediaone.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelManagerBill extends JPanel{
	private ButtonBillView buttonBillView;
	private TableBillView tableBillView;

	public ButtonBillView getButtonBillView() {
		return buttonBillView;
	}
	public TableBillView getTableBillView() {
		return tableBillView;
	}

	public PanelManagerBill() {
		setLayout(new BorderLayout(5, 5));
		add(createTitlePanel(), BorderLayout.PAGE_START);
		add(tableBillView = new TableBillView(), BorderLayout.CENTER);
		add(buttonBillView = new ButtonBillView(), BorderLayout.PAGE_END);
	}
	
	private JPanel createTitlePanel() {
		JPanel panel = new JPanel();
	JLabel label = new JLabel("Quản lí Bán hàng");
		label.setFont(new Font("Caribli", Font.BOLD, 18));
		label.setForeground(Color.BLUE);;
		panel.add(label);
		
		return panel;
	}
}
