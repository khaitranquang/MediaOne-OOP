package mediaone.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class EditProductView extends JDialog{
	private MainUI mainUI;
	private ProductInformation productInformation;
	
	private JButton btnEdit   = new JButton("SỬA");
	private JButton btnCancel = new JButton("HỦY");
	
	public ProductInformation getProductInformation() {
		return productInformation;
	}
	public JButton getBtnEdit() {
		return btnEdit;
	}
	public JButton getBtnCancel() {
		return btnCancel;
	}
	
	public EditProductView(MainUI mainUI) {
		this.mainUI = mainUI;
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setTitle("Sửa Sản phẩm");
		productInformation = new ProductInformation();
		add(createMainPanel());
		pack();      					//Auto update UI
		setLocationRelativeTo(null);
	}
	
	private JPanel createMainPanel() {
		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.add(productInformation, BorderLayout.CENTER);
		mainPanel.add(createButtonPanel(), BorderLayout.PAGE_END);
		return mainPanel;
	}
	
	private JPanel createButtonPanel() {
		JPanel panel = new JPanel(new GridLayout(1, 2, 10, 10));
		panel.setBorder(new EmptyBorder(10,10,10,10));
		
		panel.add(btnEdit);
		panel.add(btnCancel);
		return panel;
	}
}
