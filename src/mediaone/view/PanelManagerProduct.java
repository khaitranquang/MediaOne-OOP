package mediaone.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelManagerProduct extends JPanel{
	private ButtonProductView buttonProductView;
	private TableProductView tableProductView;
	
	public ButtonProductView getButtonProductView() {
		return buttonProductView;
	}
	public TableProductView getTableProductView() {
		return tableProductView;
	}

	public PanelManagerProduct() {
		setLayout(new BorderLayout(5, 5));
		add(createTitlePanel(), BorderLayout.PAGE_START);
		add(tableProductView = new TableProductView(), BorderLayout.CENTER);
		add(buttonProductView = new ButtonProductView(), BorderLayout.PAGE_END);
	}
	
	private JPanel createTitlePanel() {
		JPanel panel = new JPanel();
		JLabel label = new JLabel("Quản lí sản phẩm");
		label.setFont(new Font("Caribli", Font.BOLD, 18));
		label.setForeground(Color.BLUE);;
		panel.add(label);
		
		return panel;
	}
}
