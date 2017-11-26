package mediaone.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ButtonBillView extends JPanel{
	private JButton btnAdd = new JButton("Thêm");
	private JButton btnFree = new JButton("Thanh Toán");
	private JButton btnDetail = new JButton("Xem chi tiết");
	private JButton btnDelete = new JButton("Xóa");
	
	public JButton getBtnAdd() {
		return btnAdd;
	}
	public JButton getBtnFree() {
		return btnFree;
	}
	public JButton getBtnDetail() {
		return btnDetail;
	}
	public JButton getBtnDelete() {
		return btnDelete;
	}

	public ButtonBillView() {
		setLayout(new BorderLayout(10, 10));
		setBorder(new EmptyBorder(5, 0, 90, 0));
		add (createButtonPanel(), BorderLayout.CENTER);
	}
	
	private JPanel createButtonPanel() {
		JPanel buttonPanel = new JPanel(new GridLayout(1, 4, 10, 10));
		buttonPanel.add(btnAdd);
		buttonPanel.add(btnFree);
		buttonPanel.add(btnDetail);
		buttonPanel.add(btnDelete);
		return buttonPanel;
	}
}
