package mediaone.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Label;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class BillInformation extends JPanel{
	private JTextField tfIdBill;
	private JLabel lbIdStaff;
	private JTextField tfIdProduct;
	private JTextField tfQuantity;
	private JTextField tfExportDate;
	
	private JLabel lbNameStaff;
	private JButton btnAddThisProduct = new JButton("THÊM");
	
	private JPanel rightPanel = createRightPanel();
	
	public BillInformation() {
		setLayout(new GridLayout(1, 2, 5, 5));
		add(createCenterPanel());
		JScrollPane scroll = new JScrollPane(rightPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
	            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setPreferredSize(new Dimension(430, 400));
		scroll.setBorder(new TitledBorder("Sản phẩm mua"));
		
		add(scroll);
		repaint();
	}
	
	// Getter
	public JTextField getTfIdBill() {
		return tfIdBill;
	}
	public JLabel getLbIdStaff() {
		return lbIdStaff;
	}
	public JTextField getTfIdProduct() {
		return tfIdProduct;
	}
	public JTextField getTfQuantity() {
		return tfQuantity;
	}
	public JTextField getTfExportDate() {
		return tfExportDate;
	}
	public JLabel getLbNameStaff() {
		return lbNameStaff;
	}
	public JButton getBtnAddThisProduct() {
		return btnAddThisProduct;
	}
	public JPanel getRightPanel() {
		return rightPanel;
	}
	
	// Create Right panel 
	private JPanel createRightPanel() {
		JPanel right = new JPanel();
		right.setLayout(new FlowLayout(FlowLayout.CENTER));
		right.add(createTitlePanel());
		right.setPreferredSize(new Dimension(430, 520));
		right.setVisible(true);
		return right;
	}
	
	private JPanel createTitlePanel () {
		JPanel titlePanel = new JPanel();
		GridBagLayout layout = new GridBagLayout();
		titlePanel.setLayout(layout);
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.ipadx = 40;
		gbc.ipady = 20;
		titlePanel.add(createLPanelTitle("Mã SP", 15, 20), gbc);
		
		gbc.gridx = 1;
		gbc.ipadx = 200;
		titlePanel.add(createLPanelTitle("Giá bán", 140, 20), gbc);
		
		gbc.gridx = 2;
		gbc.ipadx = 25;
		titlePanel.add(createLPanelTitle("SL", 15, 20), gbc);

		return titlePanel;
	}
	
	private JPanel createLPanelTitle(String titleLabel, int width, int height) {
		JPanel panelTitle = new JPanel(new FlowLayout());
		panelTitle.setPreferredSize(new Dimension(width, height));
		panelTitle.add(new Label(titleLabel), FlowLayout.LEFT);
		return panelTitle;
	}
	
	// Create Center Panel
	private JPanel createCenterPanel() {
		JPanel panel = new JPanel (new BorderLayout(5, 5));
		panel.setBorder(new TitledBorder("Hóa đơn"));
		panel.add(createLabelPanel(), BorderLayout.WEST);
		panel.add(createInputPanel(), BorderLayout.CENTER);
		panel.add(createSubPanel(), BorderLayout.EAST);
		return panel;
	}
	
	private JPanel createLabelPanel() {
		JPanel labelPanel = new JPanel(new GridLayout(4, 1, 5, 5));
		labelPanel.add(new JLabel("Mã hóa đơn "));
		labelPanel.add(new JLabel("Mã nhân viên "));
		labelPanel.add(new JLabel("Mã sản phẩm "));
		labelPanel.add(new JLabel("Ngày bán "));
		return labelPanel;
	}
	
	private JPanel createInputPanel() {
		JPanel inputPanel = new JPanel(new GridLayout(4, 1, 5, 5));
		inputPanel.add(tfIdBill = new JTextField(10));
		inputPanel.add(lbIdStaff = new JLabel());
		inputPanel.add(tfIdProduct = new JTextField(10));
		inputPanel.add(tfExportDate = new JTextField(10));
		return inputPanel;
	}
	
	private JPanel createSubPanel() {
		JPanel subPanel = new JPanel(new GridLayout(4, 1, 5, 5));
		subPanel.add(new JLabel(""));
		subPanel.add(lbNameStaff = new JLabel());
		subPanel.add(createSoLuongPanel());
		subPanel.add(new JLabel(""));
		return subPanel;
	}
	
	private JPanel createSoLuongPanel() {
		JPanel soLuongPanel = new JPanel (new BorderLayout(5, 5));
		soLuongPanel.add(new JLabel("Số lượng"), BorderLayout.WEST);
		soLuongPanel.add(tfQuantity = new JTextField(5), BorderLayout.CENTER);
		soLuongPanel.add(btnAddThisProduct, BorderLayout.EAST);
		return soLuongPanel;
	}
}
