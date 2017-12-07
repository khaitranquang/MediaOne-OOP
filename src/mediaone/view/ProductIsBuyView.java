package mediaone.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class ProductIsBuyView extends JPanel{
	private JLabel lbIdProduct;
	private JLabel lbOutPrice;
	private JLabel lbQuantity;
	
	public JLabel getLbIdProduct() {
		return lbIdProduct;
	}
	public JLabel getLbOutPrice() {
		return lbOutPrice;
	}
	public JLabel getLbQuantity() {
		return lbQuantity;
	}
	
	public ProductIsBuyView(String idProduct, String outPrice, String quantity) {
		setBorder(new TitledBorder(""));
		GridBagLayout layout = new GridBagLayout();
		setLayout(layout);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.ipadx = 40;
		gbc.ipady = 20;
		add(createIdProductPanel(idProduct), gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.ipadx = 200;
		add(createOutPricePanel(outPrice), gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.ipadx = 25;
		add(createQuantityPanel(quantity), gbc);
	}
	
	private JPanel createIdProductPanel(String idProduct) {
		JPanel panel = new JPanel (new FlowLayout());
		panel.setPreferredSize(new Dimension(15, 20));
		panel.add(lbIdProduct = new JLabel(idProduct), FlowLayout.LEFT);
		return panel;
	}
	
	private JPanel createOutPricePanel(String outPrice) {
		JPanel panel = new JPanel (new FlowLayout());
		panel.setPreferredSize(new Dimension(140, 20));
		panel.add(lbOutPrice = new JLabel(outPrice), FlowLayout.LEFT);
		return panel;
	}
	
	private JPanel createQuantityPanel(String quantity) {
		JPanel panel = new JPanel (new FlowLayout());
		panel.setPreferredSize(new Dimension(15, 20));
		panel.add(lbQuantity = new JLabel(quantity), FlowLayout.LEFT);
		return panel;
	}
}
