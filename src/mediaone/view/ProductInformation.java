package mediaone.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class ProductInformation extends JPanel{
	private JTextField tfIdProduct;
	private JTextField tfNameProduct;
	private JTextField tfQuantity;
	private JTextField tfOutPrice;
	private JTextField tfInPrice;
	private JTextField tfProp1;
	private JTextField tfProp2;
	
	private JLabel lbProp1 = new JLabel();
	private JLabel lbProp2 = new JLabel();
	
	public JTextField getTfIdProduct() {
		return tfIdProduct;
	}
	public JTextField getTfNameProduct() {
		return tfNameProduct;
	}
	public JTextField getTfQuantity() {
		return tfQuantity;
	}
	public JTextField getTfOutPrice() {
		return tfOutPrice;
	}
	public JTextField getTfInPrice() {
		return tfInPrice;
	}
	public JTextField getTfProp1() {
		return tfProp1;
	}
	public JTextField getTfProp2() {
		return tfProp2;
	}
	public JLabel getLbProp1() {
		return lbProp1;
	}
	public JLabel getLbProp2() {
		return lbProp2;
	}
	
	public ProductInformation() {
		setSize(450, 350);
		setLayout(new BorderLayout());
		setBorder(new TitledBorder("Thông tin"));
		add(createPanelLabel(), BorderLayout.WEST);
		add(createPanelTextField(), BorderLayout.CENTER);
	}
	
	private JPanel createPanelLabel() {
		JPanel panel = new JPanel(new GridLayout(7, 1, 5, 5));
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel.add(new JLabel("Mã sản phẩm"));
		panel.add(new JLabel("Tên sản phẩm"));
		panel.add(lbProp1);
		panel.add(lbProp2);
		panel.add(new JLabel("Số lượng"));
		panel.add(new JLabel("Giá bán"));
		panel.add(new JLabel("Giá nhập"));
		
		return panel;
	}
	
	private JPanel createPanelTextField() {
		JPanel panel = new JPanel (new GridLayout(7, 1, 5, 5));
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		tfIdProduct   = new JTextField(30);			panel.add(tfIdProduct);
		tfNameProduct = new JTextField(30);			panel.add(tfNameProduct);
		tfProp1       = new JTextField(30);			panel.add(tfProp1);
		tfProp2       = new JTextField(30);			panel.add(tfProp2);
		tfQuantity    = new JTextField(30);			panel.add(tfQuantity);
		tfOutPrice    = new JTextField(30);			panel.add(tfOutPrice);
		tfInPrice     = new JTextField(30);			panel.add(tfInPrice);
			
		return panel;
	}	
}
